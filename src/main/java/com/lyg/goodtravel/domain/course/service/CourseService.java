package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.bean.*;
import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.course.request.CourseHitsPostReq;
import com.lyg.goodtravel.domain.course.request.CourseRegisterPostReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    int courseHits(CourseHitsPostReq courseHitsPostReq); // 코스 조회 수
    int courseRegisterByUser(CourseRegisterPostReq courseRegisterPostReq); // 사용자 코스 등록
    Page<CourseInfo> courseListByUser(int userId, Pageable pageable);
    Page<CourseInfo> popularCourse(Pageable pageable); // 인기 코스
    Page<CourseInfo> courseSearch(String courseName, Pageable pageable); // 코스 검색

    List<AreaPopularCourse> areaPopularCourseFind(String areaName); // 지역별 인기 코스
    List<KeywordCourse> keywordCourseList(String keywordName);//키워드별 코스 추천
}
