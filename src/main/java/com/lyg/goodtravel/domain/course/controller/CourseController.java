package com.lyg.goodtravel.domain.course.controller;

import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.course.request.CourseHitsPostReq;
import com.lyg.goodtravel.domain.course.request.CourseRegisterPostReq;
import com.lyg.goodtravel.domain.course.response.CourseListGetRes;
import com.lyg.goodtravel.domain.course.response.CourseSearchGetRes;
import com.lyg.goodtravel.domain.course.response.PopularCourseGetRes;
import com.lyg.goodtravel.domain.course.service.CourseService;
import com.lyg.goodtravel.global.model.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api("관광지 코스 API")
@Slf4j
@Controller
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @ApiOperation(value = "코스 조회수")
    @PutMapping("/course-hits")
    public ResponseEntity<? extends BaseResponseBody> courseHits (
            @RequestBody CourseHitsPostReq courseHitsPostReq) {

        log.info("courseHits - Call");

        if(courseService.courseHits(courseHitsPostReq) == SUCCESS) {
            return ResponseEntity.status(201).body(
                    BaseResponseBody.of(201, "Success"));
        }else {
            log.error("courseHits - The course doesn't exist.");
            return ResponseEntity.status(404).body(
                    BaseResponseBody.of(404, "The course doesn't exist."));
        }
    }

    @ApiOperation(value = "사용자 코스 추가")
    @PostMapping("")
    public ResponseEntity<? extends BaseResponseBody> courseRegister (
            @RequestBody CourseRegisterPostReq courseRegisterPostReq) {
        log.info("courseRegister - Call");

        if(courseService.courseRegisterByUser(courseRegisterPostReq) == SUCCESS) {
            return ResponseEntity.status(201).body(
                    BaseResponseBody.of(201, "Success"));
        }else {
            log.error("Course doesn't exist");
            return ResponseEntity.status(404).body(
                    BaseResponseBody.of(403, "Course doesn't exist"));
        }
    }

    @ApiOperation(value = "인기 코스")
    @GetMapping("/course-hits")
    public ResponseEntity<PopularCourseGetRes> popularCourse (int page, int size) {

        log.info("popularCourse - Call");

        Page<Course> popularCourseList =
                courseService.popularCourse(PageRequest.of(page - 1 , size));

        return ResponseEntity.status(200).body(
                PopularCourseGetRes.of(200, "Success", popularCourseList));
    }

    @ApiOperation(value = "코스 검색하기")
    @GetMapping("/{courseName}")
    public ResponseEntity<CourseSearchGetRes> courseSearch (
            @ApiParam(value = "코스 명")
            @PathVariable("courseName") String courseName, int page, int size) {
        log.info("courseSearch - Call");

        Page<Course> courseSearchList =
                courseService.courseSearch(courseName, PageRequest.of(page - 1, size));

        return ResponseEntity.status(200).body(
                CourseSearchGetRes.of(200, "Success", courseSearchList));
    }

    @ApiOperation(value = "사용자 생성 코스 조회")
    @GetMapping("/custom-course/{userId}/{courseId}")
    public ResponseEntity<CourseListGetRes> courseListByUser (
            @ApiParam(value = "회원 구분 번호")
            @PathVariable("userId") int userId, int page, int size) {
        log.info("courseListByUser - Call");

        Page<Course> courseList =
                courseService.courseListByUser(userId, PageRequest.of(page - 1 , size));

        if(courseList != null && !courseList.isEmpty()) {
            return ResponseEntity.status(200).body(
                    CourseListGetRes.of(200, "Success", courseList));
        } else {
            log.error("Course List doesn't exist");
            return ResponseEntity.status(403).body(
                    CourseListGetRes.of(403, "Course List doesn't exist", null));
        }
    }
}