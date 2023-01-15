package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.entity.CourseData;

import java.util.List;

public interface CourseDetailService {
    List<CourseData> courseDataDetail(int courseId);
    boolean courseIsRegister(int courseId);
}