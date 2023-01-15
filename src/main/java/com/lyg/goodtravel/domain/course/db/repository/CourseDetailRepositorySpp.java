package com.lyg.goodtravel.domain.course.db.repository;

import com.lyg.goodtravel.domain.course.db.bean.CourseDetail;
import com.lyg.goodtravel.domain.course.db.bean.CourseDetailUser;
import com.lyg.goodtravel.domain.course.db.bean.TourTestResult;
import com.lyg.goodtravel.domain.course.db.entity.CourseData;
import com.lyg.goodtravel.domain.course.db.entity.QCourse;
import com.lyg.goodtravel.domain.course.db.entity.QCourseData;
import com.lyg.goodtravel.domain.course.db.entity.QTourist;
import com.lyg.goodtravel.domain.record.db.entity.QRecord;
import com.lyg.goodtravel.domain.record.db.entity.QTour;
import com.lyg.goodtravel.domain.record.db.entity.Record;
import com.lyg.goodtravel.domain.tourtest.db.entity.QTourTest;
import com.lyg.goodtravel.domain.user.db.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDetailRepositorySpp {
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    QCourse qCourse = QCourse.course;
    QCourseData qCourseData = QCourseData.courseData;
    QTourist qTourist = QTourist.tourist;

    QTour qTour = QTour.tour;
    QRecord qRecord = QRecord.record;

    QUser qUser = QUser.user;

    QTourTest qTourTest = QTourTest.tourTest;


    // 코스 상세보기 Query(1) - 사용자가 생성한 코스
    public List<CourseDetailUser> courseDataDetailUserByCourseId(int courseId) {
        return jpaQueryFactory.select(Projections.constructor(
                CourseDetailUser.class, qCourse.courseName.as("courseName"),
                        qCourse.courseContent.as("courseContent"),
                        qCourseData.courseDataName.as("courseDataName"),
                        qTourist.touristAddress.as("touristAddress"),
                        qTourist.touristLat.as("touristLat"), qTourist.touristLng.as("touristLng")))
                .from(qCourseData)
                .leftJoin(qTourist).on(qTourist.touristId.eq(qCourseData.touristId))
                .leftJoin(qCourse).on(qCourse.courseId.eq(qCourseData.courseId))
                .where(qCourse.courseId.eq(courseId))
                .fetch();
    }

    // 코스 상세보기 Query(2) - 사용자가 생성한 코스 x
    public List<CourseDetail> courseDataDetailByCourseId(int courseId) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        CourseDetail.class,
                        qCourse.courseName.as("courseName"),
                        qCourse.courseContent.as("courseContent"),
                        qCourseData.courseDataName.as("courseDataName")))
                .from(qCourseData)
                .leftJoin(qCourse).on(qCourse.courseId.eq(qCourseData.courseId))
                .where(qCourse.courseId.eq(courseId))
                .fetch();
    }


    // 코스 여행 레코드(일기) 조회 Query
    public List<Record> courseRecordDetailByCourseId(int courseId) {
        return jpaQueryFactory.select(qRecord).from(qRecord)
                .leftJoin(qUser).on(qUser.userId.eq(qRecord.userId))
                .where(qRecord.courseId.eq(courseId))
                .fetch();
    }


    // 코스 성향 분석 Query
    public List<TourTestResult> courseTourTestResultDetailByCourseId(int courseId) {
        return jpaQueryFactory.select(Projections.constructor(TourTestResult.class,
                        qTourTest.tourTestId.as("tourTestId"), qTourTest.tourTestName.as("tourTestName"),
                        qTourTest.tourTestId.count().as("tourTestCount")))
                .from(qTourTest)
                .leftJoin(qUser).on(qUser.tourTestId.eq(qTourTest.tourTestId))
                .leftJoin(qTour).on(qUser.userId.eq(qTour.userId))
                .leftJoin(qCourse).on(qCourse.courseId.eq(qTour.courseId))
                .where(qTour.isStart.eq(true).and(qCourse.courseId.eq(courseId)))
                .groupBy(qTourTest.tourTestId)
                .fetch();
    }
}