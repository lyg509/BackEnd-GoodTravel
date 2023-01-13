package com.lyg.goodtravel.domain.record.db.repository;

import com.lyg.goodtravel.domain.course.db.entity.CourseData;
import com.lyg.goodtravel.domain.course.db.entity.QCourseData;
import com.lyg.goodtravel.domain.record.db.entity.QTag;
import com.lyg.goodtravel.domain.record.db.entity.QTagCode;
import com.lyg.goodtravel.domain.record.db.entity.QTourStamp;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TourRepositorySpp {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QTourStamp qTourStamp = QTourStamp.tourStamp;
    QCourseData qCourseData = QCourseData.courseData;

    QTagCode qTagCode = QTagCode.tagCode;
    QTag qTag = QTag.tag;

    public List<CourseData> findVisitTouristName (int userId, int courseId) {
        return jpaQueryFactory.select(qCourseData).from(qCourseData)
                .leftJoin(qTourStamp).on(qTourStamp.courseId.eq(qCourseData.courseId))
                .where(qTourStamp.isStamp.eq(true).and(qTourStamp.userId.eq(userId)
                        .and(qTourStamp.courseId.eq(courseId)).and(qTourStamp.courseDataId.eq(qCourseData.courseDataId))))
                .fetch();
    }
}