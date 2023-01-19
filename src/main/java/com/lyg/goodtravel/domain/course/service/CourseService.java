package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.bean.CourseSearch;
import com.lyg.goodtravel.domain.course.db.bean.PopularCourse;
import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.course.request.CourseHitsPostReq;
import com.lyg.goodtravel.domain.course.request.CourseRegisterPostReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    int courseHits(CourseHitsPostReq courseHitsPostReq); // 코스 조회 수
    int courseRegisterByUser(CourseRegisterPostReq courseRegisterPostReq); // 사용자 코스 등록
    Page<Course> courseListByUser(int userId, Pageable pageable); // 사용자 지정 코스 조회
    Page<PopularCourse> popularCourse(Pageable pageable); // 인기 코스
    Page<CourseSearch> courseSearch(String courseName, Pageable pageable); // 코스 검색

}
