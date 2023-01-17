package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.bean.CourseTagDetail;
import com.lyg.goodtravel.domain.course.db.bean.CourseTourTestResultDetail;
import com.lyg.goodtravel.domain.course.db.entity.CourseData;
import com.lyg.goodtravel.domain.course.db.repository.CourseDetailRepositorySpp;
import com.lyg.goodtravel.domain.course.db.repository.CourseRepository;
import com.lyg.goodtravel.domain.record.db.entity.Record;
import com.lyg.goodtravel.domain.tourtest.repository.TourTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDetailServiceImpl implements CourseDetailService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TourTestRepository tourTestRepository;

    @Autowired
    CourseDetailRepositorySpp courseDetailRepositorySpp;

    @Override
    public List<CourseData> courseDataDetail(int courseId) { return courseDetailRepositorySpp.courseDataDetailByCourseId(courseId); }

    @Override
    public List<Record> courseRecordDetail(int courseId) { return courseDetailRepositorySpp.courseRecordDetailByCourseId(courseId); }

    @Override
    public List<CourseTourTestResultDetail> courseTourTestResultDetail(int courseId) { return courseDetailRepositorySpp.courseTourTestResultDetailByCourseId(courseId); }

    @Override
    public List<CourseTagDetail> courseTagDetail(int courseId) { return courseDetailRepositorySpp.courseTagDetailByCourseId(courseId); }
}