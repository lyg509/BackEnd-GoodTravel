package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.course.request.CourseHitsPostReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    int courseHits(CourseHitsPostReq courseHitsPostReq);
    Page<Course> popularCourse(Pageable pageable);
    Page<Course> courseSearch(String courseName, Pageable pageable);
}
