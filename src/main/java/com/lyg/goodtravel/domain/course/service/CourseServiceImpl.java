package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.repository.CourseRepository;
import com.lyg.goodtravel.domain.course.request.CourseHitsPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseRepository courseRepository;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @Override
    public int courseHits(CourseHitsPostReq courseHitsPostReq) {
        if(courseRepository.findById(courseHitsPostReq.getCourseId()).isPresent()) {
            int courseId = courseRepository.findById(courseHitsPostReq.getCourseId()).get().getCourseId();

            courseRepository.courseHitsByCourseId(courseId);

            return SUCCESS;
        }else return FAIL;
    }
}