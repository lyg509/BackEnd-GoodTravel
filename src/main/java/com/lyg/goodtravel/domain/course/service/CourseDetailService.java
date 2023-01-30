package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.bean.*;
import com.lyg.goodtravel.domain.record.db.entity.Record;

import java.util.List;

public interface CourseDetailService {

    List<CourseDetail> courseDetail(int courseId);
    List<CourseTouristDetail> courseDataDetail(int courseId);
    List<CourseRecordDetail> courseRecordDetail(int courseId);
    List<CourseTourTestResultDetail> courseTourTestResultDetail(int courseId);
    List<CourseTagDetail> courseTagDetail(int courseId);

    double courseConnection(int courseId);
}