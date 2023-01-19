package com.lyg.goodtravel.domain.tourtest.repository;

import com.lyg.goodtravel.domain.course.db.entity.QCourse;
import com.lyg.goodtravel.domain.course.db.entity.QCourseData;
import com.lyg.goodtravel.domain.course.db.entity.QTouristImgPath;
import com.lyg.goodtravel.domain.tourtest.db.bean.TourTestCourse;
import com.lyg.goodtravel.domain.tourtest.db.entity.QTourTest;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TourTestRepositorySpp {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QTourTest qTourTest = QTourTest.tourTest;

    QCourse qCourse = QCourse.course;
    QCourseData qCourseData = QCourseData.courseData;
    QTouristImgPath qTouristImgPath = QTouristImgPath.touristImgPath;


    public List<Integer> findTourTestResult () {
        return jpaQueryFactory.select(qTourTest.tourTestResult).from(qTourTest)
                .groupBy(qTourTest.tourTestId)
                .where(qTourTest.tourTestId.lt(7))
                .fetchAll().fetch();
    }

    public List<TourTestCourse> findTourTestCourseByUser(int courseId) {
        return jpaQueryFactory
                .select(Projections.constructor(TourTestCourse.class,
                        qCourse.courseId,
                        qCourse.courseName,
                        qCourseData.touristId,
                        qTouristImgPath.fileId.min().as("fileId")))
                .from(qCourse)
                .leftJoin(qCourseData).on(qCourseData.courseId.eq(qCourse.courseId))
                .leftJoin(qTouristImgPath).on(qTouristImgPath.touristId.eq(qCourseData.touristId))
                .where(qCourse.courseId.eq(courseId).and(qCourseData.courseDataId.eq(1)))
                .groupBy(qCourse.courseId)
                .fetch();
    }

}