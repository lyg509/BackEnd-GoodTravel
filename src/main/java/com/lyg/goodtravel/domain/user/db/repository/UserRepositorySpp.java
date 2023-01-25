package com.lyg.goodtravel.domain.user.db.repository;

import com.lyg.goodtravel.domain.course.db.entity.QCourse;
import com.lyg.goodtravel.domain.user.db.bean.AreaAnalysisDetail;
import com.lyg.goodtravel.domain.course.db.entity.QCourseData;
import com.lyg.goodtravel.domain.course.db.entity.QTourist;
import com.lyg.goodtravel.domain.record.db.entity.QTour;
import com.lyg.goodtravel.domain.record.db.entity.QTourStamp;
import com.lyg.goodtravel.domain.user.db.bean.CourseNameVisitDetail;
import com.lyg.goodtravel.domain.user.db.bean.DateAnalysisDetail;
import com.lyg.goodtravel.domain.user.db.entity.QUser;
import com.lyg.goodtravel.domain.user.db.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UserRepositorySpp {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUser quser = QUser.user;
    QTour qTour = QTour.tour;
    QCourseData qCourseData = QCourseData.courseData;
    QTourist qTourist = QTourist.tourist;
    QTourStamp qTourStamp = QTourStamp.tourStamp;
    QCourse qCourse = QCourse.course;

    public User findByEmail(String userEmail) {
        return jpaQueryFactory.select(quser).from(quser)
                .where(quser.userEmail.eq(userEmail)).fetchOne();
    }

    ;

    //방문한 사용자 코스 데이터 Query
    public List<CourseNameVisitDetail> courseVisitDetailByUserId(int userId){
        return jpaQueryFactory.select(Projections.constructor(CourseNameVisitDetail.class,
                        qTour.courseId.as("courseId"), qCourse.courseName.as("courseName"), qTourist.touristLat.as("touristLat")
                        ,qTourist.touristLng.as("touristLng")))
                .from(qTour).join(qCourseData).on(qCourseData.courseId.eq(qTour.courseId))
                .join(qTourist).on(qTourist.touristId.eq(qCourseData.touristId))
                .join(qCourse).on(qCourse.courseId.eq(qTour.courseId))
                .where(qTour.isEnd.eq(true).and(qTour.userId.eq(userId)).and(qCourseData.courseDataId.eq(1))).fetch();

    }


    //사용자 분석 지역
    public List<AreaAnalysisDetail> areaAnalysisDetailByUserId(int userId){
        return jpaQueryFactory.select(Projections.constructor(AreaAnalysisDetail.class, qTourist.touristAddress.substring(0,3).as("touristAddress")
                        , qTourist.touristAddress.substring(0,3).count().as("touristCount")))
                .from(qTour).leftJoin(qCourseData).on(qCourseData.courseId.eq(qTour.courseId),qCourseData.courseDataId.eq(1))
                .leftJoin(qTourist).on(qTourist.touristId.eq(qCourseData.touristId))
                .where(qTour.userId.eq(userId).and(qTour.isEnd.isTrue()))
                .groupBy(qTourist.touristAddress.substring(0,3))
                .fetch();
    }

    //사용자 분석 월별
    public List<DateAnalysisDetail> dateAnalysisDetailByUserId(int userId){
        return jpaQueryFactory
                .select(Projections.constructor(DateAnalysisDetail.class,
                        qTour.tourEnd.yearMonth().as("dateName"),
                        qTour.tourEnd.yearMonth().count().as("dateCount")))
                .from(qTour).where(qTour.userId.eq(userId).and(qTour.isEnd.isTrue())
                        .and(qTour.tourEnd.between(
                                LocalDateTime.now().minusMonths(5),
                                LocalDateTime.now().minusMonths(0)
                        )))
                .groupBy(qTour.tourEnd.yearMonth()).fetch();
    }
}