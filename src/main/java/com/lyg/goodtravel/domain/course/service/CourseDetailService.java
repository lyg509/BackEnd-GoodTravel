package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.bean.CourseDetail;
import com.lyg.goodtravel.domain.course.db.bean.CourseDetailUser;
import com.lyg.goodtravel.domain.course.db.bean.CourseTourTestResultDetail;
import com.lyg.goodtravel.domain.record.db.entity.Record;

import java.util.List;

public interface CourseDetailService {
    List<CourseDetailUser> courseDataUserDetail(int courseId);
    List<CourseDetail> courseDataDetail(int courseId);

    boolean courseIsRegister(int courseId);

    List<Record> courseRecordDetail(int courseId);

    List<CourseTourTestResultDetail> courseTourTestResultDetail(int courseId);
}