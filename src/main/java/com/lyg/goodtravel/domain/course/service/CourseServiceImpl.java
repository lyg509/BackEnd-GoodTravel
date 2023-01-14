package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.course.db.entity.CourseData;
import com.lyg.goodtravel.domain.course.db.repository.CourseDataRepository;
import com.lyg.goodtravel.domain.course.db.repository.CourseRepository;
import com.lyg.goodtravel.domain.course.db.repository.CourseRepositorySpp;
import com.lyg.goodtravel.domain.course.request.CourseHitsPostReq;
import com.lyg.goodtravel.domain.course.request.CourseRegisterPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseDataRepository courseDataRepository;

    @Autowired
    CourseRepositorySpp courseRepositorySpp;

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

    @Override
    public int courseRegisterByUser(CourseRegisterPostReq courseRegisterPostReq) {
        Course course = new Course();

        // 코스 등록
        course.setUserId(courseRegisterPostReq.getUserId());
        course.setCourseName(courseRegisterPostReq.getCourseName());
        course.setCourseContent(courseRegisterPostReq.getCourseContent());
        course.setCourseDistance(courseRegisterPostReq.getCourseDistance());
        course.setCourseDays(courseRegisterPostReq.getCourseDays());
        course.setRegister(true);

        courseRepository.save(course);

        // 코스 관광지 등록
        CourseData courseData = new CourseData();

        int courseId = course.getCourseId();
        int size = courseRegisterPostReq.getCourseDataName().size();

        // 관광지 명
        Collection<String> courseNameList = courseRegisterPostReq.getCourseDataName().values();
        String[] courseName = courseNameList.toArray(new String[0]);

        // 관광지 주소
        Collection<String> courseAddressList = courseRegisterPostReq.getCourseAddress().values();
        String[] courseAddress = courseAddressList.toArray(new String[0]);

        for (int i = 0; i < size; i++) {
            courseData.setCourseId(courseId);
            courseData.setCourseDataId(i + 1);

            courseData.setCourseDataName(courseName[i]);
            courseData.setCourseAddress(courseAddress[i]);
            courseData.setCourseLng(courseRegisterPostReq.getCourseLng().get(i + 1));
            courseData.setCourseLat(courseRegisterPostReq.getCourseLat().get(i + 1));

            courseDataRepository.save(courseData);
        }

        return SUCCESS;
    }

    @Override
    public Page<Course> popularCourse(Pageable pageable) {
        return courseRepositorySpp.findPopularCourse(pageable);
    }

    @Override
    public Page<Course> courseSearch(String courseName, Pageable pageable) {
        return courseRepositorySpp.findCourseSearch(courseName, pageable);
    }

    @Override
    public Page<Course> courseListByUser(int userId, Pageable pageable) {
        return courseRepositorySpp.findCourseListByUser(userId, pageable);
    }
}