package com.lyg.goodtravel.domain.user.db.repository;

import com.lyg.goodtravel.domain.user.db.bean.AreaAnalysisDetail;
import com.lyg.goodtravel.domain.course.db.entity.QCourseData;
import com.lyg.goodtravel.domain.course.db.entity.QTourist;
import com.lyg.goodtravel.domain.record.db.entity.QTour;
import com.lyg.goodtravel.domain.record.db.entity.QTourStamp;
import com.lyg.goodtravel.domain.user.db.bean.CourseNameVisitDetail;
import com.lyg.goodtravel.domain.user.db.entity.QUser;
import com.lyg.goodtravel.domain.user.db.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public User findByEmail(String userEmail) {
        return jpaQueryFactory.select(quser).from(quser)
                .where(quser.userEmail.eq(userEmail)).fetchOne();
    }

    ;

    //방문한 사용자 코스 데이터 Query
    public List<CourseNameVisitDetail> courseVisitDetailByUserId(int userId) {
        return jpaQueryFactory.select(Projections.constructor(CourseNameVisitDetail.class,
                        qTour.courseId.as("courseId"), qTourist.touristLat.as("touristLat")
                        , qTourist.touristLng.as("touristLng")))
                .from(qTour).join(qCourseData).on(qCourseData.courseId.eq(qTour.courseId))
                .join(qTourist).on(qTourist.touristId.eq(qCourseData.touristId))
                .where(qTour.isEnd.eq(true).and(qTour.userId.eq(userId))).fetch();

    }


    //사용자 분석 지역
    public List<AreaAnalysisDetail> areaAnalysisDetailByUserId(int userId) {
        return jpaQueryFactory.select(Projections.constructor(AreaAnalysisDetail.class, qTourist.touristAddress.substring(0, 3).as("touristAddress")
                        , qTourist.touristAddress.substring(0, 3).count().as("touristCount")))
                .from(qTourist).leftJoin(qCourseData).on(qCourseData.touristId.eq(qTourist.touristId))
                .leftJoin(qTourStamp).on(qTourStamp.courseId.eq(qCourseData.courseId), qTourStamp.courseDataId.eq(qCourseData.courseDataId))
                .where(qTourStamp.isStamp.eq(true).and(qTourStamp.userId.eq(userId)))
                .groupBy(qTourist.touristAddress.substring(0, 3))
                .fetch();
    }
}