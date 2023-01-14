package com.lyg.goodtravel.domain.tourtest.service;

import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.course.db.repository.CourseRepository;
import com.lyg.goodtravel.domain.tourtest.repository.TourTestRepository;
import com.lyg.goodtravel.domain.tourtest.request.TourTestResultPostReq;
import com.lyg.goodtravel.domain.user.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourTestServiceImpl implements TourTestService {

    @Autowired
    TourTestRepository tourTestRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;


    @Override
    public int tourTestResultByUser(TourTestResultPostReq tourTestResultPostReq) {
        // 회원이 존재하면
        if(userRepository.findById(tourTestResultPostReq.getUserId()).isPresent()) {
            return userRepository.tourTestResultByUserId(
                    tourTestResultPostReq.getUserId(),
                    tourTestResultPostReq.getTourTestId());
        }else return FAIL;
    }

    @Override
    public List<Course> tourTestCourseByUser(int courseId1, int courseId2) {
        return courseRepository.tourTestCourseByUser(courseId1, courseId2);
    }
}