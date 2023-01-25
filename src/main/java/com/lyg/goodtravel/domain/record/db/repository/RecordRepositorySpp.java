package com.lyg.goodtravel.domain.record.db.repository;

import com.lyg.goodtravel.domain.course.db.entity.QCourse;
import com.lyg.goodtravel.domain.course.db.entity.QCourseData;
import com.lyg.goodtravel.domain.course.db.entity.QTourist;
import com.lyg.goodtravel.domain.record.db.bean.RecordWriteList;
import com.lyg.goodtravel.domain.record.db.entity.QRecord;
import com.lyg.goodtravel.domain.record.db.entity.QRecordImgPath;
import com.lyg.goodtravel.domain.user.db.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecordRepositorySpp {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QRecord qRecord = QRecord.record;
    QRecordImgPath qRecordImgPath = QRecordImgPath.recordImgPath;

    QCourse qCourse = QCourse.course;
    QCourseData qCourseData = QCourseData.courseData;
    QTourist qTourist = QTourist.tourist;


    public List<RecordWriteList> findRecordWriteList (int userId) {
        return jpaQueryFactory
                .select(Projections.constructor(RecordWriteList.class,
                        qRecord.recordId,
                        qCourse.courseId,
                        qCourse.courseName,
                        qTourist.touristId,
                        qTourist.touristLat,
                        qTourist.touristLng,
                        qRecord.recordContent,
                        qRecord.recordRegDt,
                        qRecordImgPath.fileId.min().as("fileId")))
                .from(qRecord)
                .leftJoin(qCourse).on(qCourse.courseId.eq(qRecord.courseId))
                .leftJoin(qCourseData).on(qCourseData.courseId.eq(qCourse.courseId))
                .leftJoin(qTourist).on(qTourist.touristId.eq(qCourseData.touristId))
                .leftJoin(qRecordImgPath).on(qRecordImgPath.recordId.eq(qRecord.recordId))
                .where(qRecord.userId.eq(userId).and(qCourseData.courseDataId.eq(1)))
                .groupBy(qRecord.recordId)
                .fetch();
    }

}