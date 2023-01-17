package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.bean.CourseDetail;
import com.lyg.goodtravel.domain.course.db.bean.CourseTagDetail;
import com.lyg.goodtravel.domain.course.db.bean.CourseTourTestResultDetail;
import com.lyg.goodtravel.domain.course.db.bean.CourseTouristDetail;
import com.lyg.goodtravel.domain.course.db.entity.CourseData;
import com.lyg.goodtravel.domain.record.db.entity.Record;

import java.util.List;

public interface CourseDetailService {

    List<CourseDetail> courseDetail(int courseId);
    List<CourseTouristDetail> courseDataDetail(int courseId);
    List<Record> courseRecordDetail(int courseId);
    List<CourseTourTestResultDetail> courseTourTestResultDetail(int courseId);
    List<CourseTagDetail> courseTagDetail(int courseId);

    double courseConnection(int courseId);
}