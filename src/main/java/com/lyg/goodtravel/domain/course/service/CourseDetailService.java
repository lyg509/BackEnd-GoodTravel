package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.bean.CourseTourTestResultDetail;
import com.lyg.goodtravel.domain.course.db.entity.CourseData;
import com.lyg.goodtravel.domain.record.db.entity.Record;

import java.util.List;

public interface CourseDetailService {
    List<CourseData>  courseDataDetail(int courseId);

    boolean courseIsRegister(int courseId);

    List<Record> courseRecordDetail(int courseId);

    List<CourseTourTestResultDetail> courseTourTestResultDetail(int courseId);
}