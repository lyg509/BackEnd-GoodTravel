package com.lyg.goodtravel.domain.tourtest.controller;

import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.tourtest.request.TourTestResultPostReq;
import com.lyg.goodtravel.domain.tourtest.response.TourTestCourseGetRes;
import com.lyg.goodtravel.domain.tourtest.service.TourTestService;
import com.lyg.goodtravel.global.model.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api("여행 취향 테스트 API")
@Slf4j
@Controller
@RequestMapping("/api/tour-test")
public class TourTestController {

    @Autowired
    TourTestService tourTestService;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @ApiOperation(value = "여행 취향 테스트 결과 저장", notes = "로그인 한 회원은 여행 취향 테스트 결과를 저장한다.")
    @PutMapping("")
    public ResponseEntity<? extends BaseResponseBody> tourTestResultByUser(
            @RequestBody TourTestResultPostReq tourTestResultPostReq) {

        log.info("tourTestResultByUser - Call");

        if(tourTestService.tourTestResultByUser(tourTestResultPostReq) == SUCCESS) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        }else return ResponseEntity.status(404).body(BaseResponseBody.of(201, "userId doesn't exist"));
    }

    @ApiOperation(value = "여행 취향 테스트 결과 추천 코스", notes = "여행 취향 테스트 결과를 바탕으로 코스를 추천한다.")
    @GetMapping("/{courseId1}/{courseId2}")
    public ResponseEntity<TourTestCourseGetRes> tourTestCourseResultByUser(
            @ApiParam(value = "코스 구분 번호 1") @PathVariable("courseId1") int courseId1,
            @ApiParam(value = "코스 구분 번호 2") @PathVariable("courseId2") int courseId2) {
        log.info("tourTestCourseResultByUser - Call");

        List<Course> tourTestCourseList =  tourTestService.tourTestCourseByUser(courseId1, courseId2);

        if(tourTestCourseList != null && !tourTestCourseList.isEmpty()) {
            return ResponseEntity.status(201).body(
                    TourTestCourseGetRes.of(200, "Success", tourTestCourseList));
        }else {
            log.error("Course List doesn't exist");
            return ResponseEntity.status(403).body(
                    TourTestCourseGetRes.of(403, "Course List doesn't exist", null));
        }
    }
}
