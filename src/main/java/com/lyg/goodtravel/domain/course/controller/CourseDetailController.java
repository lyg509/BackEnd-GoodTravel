package com.lyg.goodtravel.domain.course.controller;

import com.lyg.goodtravel.domain.course.db.bean.CourseTourTestResultDetail;
import com.lyg.goodtravel.domain.course.db.entity.CourseData;
import com.lyg.goodtravel.domain.course.response.CourseDetailGetRes;
import com.lyg.goodtravel.domain.course.response.CourseTourTestResultDetailGetRes;
import com.lyg.goodtravel.domain.course.service.CourseDetailService;
import com.lyg.goodtravel.domain.record.db.entity.Record;
import com.lyg.goodtravel.domain.record.response.CourseRecordDetailGetRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api("코스 상세보기 API")
@Slf4j
@Controller
@RequestMapping("/api/course-detail")
public class CourseDetailController {

    @Autowired
    CourseDetailService courseDetailService;



    @ApiOperation(value = "코스 상세보기 - 사용자 생성 코스", notes = "코스에 대한 정보와 코스에 등록된 관광지에 대한 정보를 제공한다.")
    @GetMapping("/user/{courseId}")
    public ResponseEntity<CourseDetailGetRes> courseDetailUser (
            @ApiParam(value = "코스 구분 번호") @PathVariable("courseId") int courseId) {
        log.info("courseDetail - Call");

        List<CourseData> courseDataDetailList = courseDetailService.courseDataDetail(courseId);

        if(courseDataDetailList != null && !courseDataDetailList.isEmpty()) {
            return ResponseEntity
                            .status(200)
                            .body(CourseDetailGetRes
                            .of(200, "Success", courseDataDetailList));
        }else {
            log.error("courseId doesn't exist");
            return ResponseEntity
                            .status(403)
                            .body(CourseDetailGetRes
                            .of(403, "courseId doesn't exist", null));
        }
    }


    @ApiOperation(value = "코스 여행 레코드(일기) 조회")
    @GetMapping("/course-record/{courseId}")
    public ResponseEntity<CourseRecordDetailGetRes> courseRecordDetail(
            @ApiParam(value = "코스 구분 번호") @PathVariable("courseId") int courseId) {
        log.info("courseRecordDetail - Call");

        List<Record> courseRecordDetailList = courseDetailService.courseRecordDetail(courseId);

        if (courseRecordDetailList != null && !courseRecordDetailList.isEmpty()) {
            return ResponseEntity
                            .status(200)
                            .body(CourseRecordDetailGetRes
                            .of(200, "Success", courseRecordDetailList));
        } else {
            log.error("courseId doesn't exist");
            return ResponseEntity
                            .status(403)
                            .body(CourseRecordDetailGetRes
                            .of(403, "courseId doesn't exist", null));
        }
    }


    @ApiOperation(value = "코스 성향 분석")
    @GetMapping("/analysis/{courseId}")
    public ResponseEntity<CourseTourTestResultDetailGetRes> courseConnectionDetail(
            @ApiParam(value = "코스 구분 번호") @PathVariable("courseId") int courseId) {
        log.info("courseConnectionDetail - Call");

        List<CourseTourTestResultDetail> courseTourTestResultList =
                courseDetailService.courseTourTestResultDetail(courseId);

        if(courseTourTestResultList != null && !courseTourTestResultList.isEmpty()) {
            return ResponseEntity
                            .status(200)
                            .body(CourseTourTestResultDetailGetRes
                            .of(200, "Success", courseTourTestResultList));
        }else {
            log.error("Test result doesn't exist");
            return ResponseEntity
                            .status(403)
                            .body(CourseTourTestResultDetailGetRes
                            .of(403, "Test result doesn't exist", null));
        }
    }
}