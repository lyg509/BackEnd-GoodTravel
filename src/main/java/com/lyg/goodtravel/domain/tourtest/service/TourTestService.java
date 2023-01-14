package com.lyg.goodtravel.domain.tourtest.service;

import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.tourtest.request.TourTestResultPostReq;

import java.util.List;

public interface TourTestService {

    int tourTestResultByUser(TourTestResultPostReq tourTestResultPostReq);
    List<Course> tourTestCourseByUser(int courseId1, int courseId2);
}
