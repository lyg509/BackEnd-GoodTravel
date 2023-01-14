package com.lyg.goodtravel.domain.tourtest.service;

import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.course.db.repository.CourseRepository;
import com.lyg.goodtravel.domain.tourtest.repository.TourTestRepository;
import com.lyg.goodtravel.domain.tourtest.repository.TourTestRepositorySpp;
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

    @Autowired
    TourTestRepositorySpp tourTestRepositorySpp;

    private static final int SUCCESS = 1;
    private static final int NONE = 2;
    private static final int FAIL = -1;



    @Override
    public int tourTestResultByUser(int userId, int tourTestId) {

        if(userRepository.findById(userId).isPresent()) {
            int tourTest = userRepository.findById(userId).get().getTourTestId();

            // ******** 기존 결과 합계 - 1 ******** //
            tourTestRepository.tourTestBeforeCountByUser(tourTest);

            // ******** 테스트 결과 등록 ******** //
            userRepository.tourTestResultByUserId(userId, tourTestId);
            tourTestRepository.tourTestResultCountByUser(tourTestId);

            return SUCCESS;
        }else {
            tourTestRepository.tourTestResultCountByUser(tourTestId);
            return NONE;
        }
    }

    @Override
    public List<Course> tourTestCourseByUser(int courseId1, int courseId2) {
        return courseRepository.tourTestCourseByUser(courseId1, courseId2);
    }

    @Override
    public List<Integer> tourTestResult() {
        return tourTestRepositorySpp.findTourTestResult();
    }
}