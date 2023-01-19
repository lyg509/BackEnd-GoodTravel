package com.lyg.goodtravel.domain.tourtest.service;

import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.tourtest.db.bean.TourTestCourse;
import com.lyg.goodtravel.domain.tourtest.request.TourTestResultPostReq;

import java.util.List;

public interface TourTestService {

    int tourTestResultByUser(int userId, int tourTestId);
    List<TourTestCourse> tourTestCourseByUser(int courseId);
    List<Integer> tourTestResult();
}
