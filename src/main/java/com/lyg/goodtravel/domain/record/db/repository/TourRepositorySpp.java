package com.lyg.goodtravel.domain.record.db.repository;

import com.lyg.goodtravel.domain.course.db.bean.VisitTouristName;
import com.lyg.goodtravel.domain.course.db.entity.QCourse;
import com.lyg.goodtravel.domain.course.db.entity.QCourseData;
import com.lyg.goodtravel.domain.course.db.entity.QTourist;
import com.lyg.goodtravel.domain.record.db.bean.TourIsStart;
import com.lyg.goodtravel.domain.record.db.entity.QTour;
import com.lyg.goodtravel.domain.record.db.entity.QTourStamp;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TourRepositorySpp {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QTourStamp qTourStamp = QTourStamp.tourStamp;
    QCourseData qCourseData = QCourseData.courseData;
    QTourist qTourist = QTourist.tourist;

    QTour qTour = QTour.tour;
    QCourse qCourse = QCourse.course;


    public List<VisitTouristName> findVisitTouristName (int userId, int courseId) {
        return jpaQueryFactory
                .select(Projections.constructor(VisitTouristName.class, qTourist.touristName))
                .from(qCourseData)
                .leftJoin(qTourStamp).on(qTourStamp.courseId.eq(qCourseData.courseId))
                .leftJoin(qTourist).on(qTourist.touristId.eq(qCourseData.touristId))
                .where(qTourStamp.isStamp.eq(true)
                        .and(qTourStamp.userId.eq(userId)
                        .and(qTourStamp.courseId.eq(courseId))
                        .and(qTourStamp.courseDataId.eq(qCourseData.courseDataId))))
                .fetch();
    }

    public int findVisitIsStartByUser (int userId) {
        return jpaQueryFactory
                .select(Projections.constructor(TourIsStart.class, qCourse.courseId))
                .from(qTour)
                .leftJoin(qCourse).on(qCourse.courseId.eq(qTour.courseId))
                .where(qTour.isStart.eq(true).and(qTour.isEnd.eq(false)).and(qTour.userId.eq(userId)))
                .fetchOne().getCourseId();
    }
}