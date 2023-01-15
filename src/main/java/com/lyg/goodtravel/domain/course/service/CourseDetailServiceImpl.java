package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.entity.CourseData;
import com.lyg.goodtravel.domain.course.db.repository.CourseDetailRepositorySpp;
import com.lyg.goodtravel.domain.course.db.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDetailServiceImpl implements CourseDetailService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseDetailRepositorySpp courseDetailRepositorySpp;


    @Override
    public List<CourseData> courseDataDetail(int courseId) { return courseDetailRepositorySpp.courseDataDetailByCourseId(courseId); }

    @Override
    public boolean courseIsRegister(int courseId) {return courseRepository.findCourseIsRegisterByCourseId(courseId);}
}
