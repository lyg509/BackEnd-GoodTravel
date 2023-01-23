package com.lyg.goodtravel.domain.record.controller;

import com.lyg.goodtravel.domain.course.db.bean.VisitTouristName;
import com.lyg.goodtravel.domain.course.db.entity.CourseData;
import com.lyg.goodtravel.domain.record.db.entity.TagCode;
import com.lyg.goodtravel.domain.record.request.TagRegisterPostReq;
import com.lyg.goodtravel.domain.record.request.TourEndPostReq;
import com.lyg.goodtravel.domain.record.request.TourStartPostReq;
import com.lyg.goodtravel.domain.record.request.TouristVisitPostReq;
import com.lyg.goodtravel.domain.record.response.TagListGetRes;
import com.lyg.goodtravel.domain.record.response.TourIsStartGetRes;
import com.lyg.goodtravel.domain.record.response.TouristNameVisitGetRes;
import com.lyg.goodtravel.domain.record.service.TourService;
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

@Api("코스 시작,종료 기록 및 관광지 방문 API")
@Slf4j
@Controller
@RequestMapping("/api")
public class TourController {

    @Autowired
    TourService tourService;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;


    @ApiOperation(value = "여행 시작")
    @PostMapping("/tour-start")
    public ResponseEntity<? extends BaseResponseBody> courseStart(
            @RequestBody TourStartPostReq tourStartPostReq) {
        log.info("tourStartByUser - Call");

        if (tourService.courseStartByUser(tourStartPostReq) == SUCCESS) {
            return ResponseEntity
                    .status(201)
                    .body(BaseResponseBody.of(201, "Success"));
        } else
            return ResponseEntity.
                    status(404)
                    .body(BaseResponseBody
                            .of(403, "userId or courseId doesn't exist"));
    }

    @ApiOperation(value = "여행 종료")
    @PutMapping("/tour-end")
    public ResponseEntity<? extends BaseResponseBody> courseEnd(
            @RequestBody TourEndPostReq tourEndPostReq) {
        log.info("tourEndByUser - Call");

        if (tourService.courseEndByUser(tourEndPostReq) == SUCCESS) {
            return ResponseEntity.status(201).body(
                    BaseResponseBody.of(201, "Success"));
        } else
            return ResponseEntity
                    .status(404)
                    .body(BaseResponseBody
                            .of(403, "userId or courseId doesn't exist"));
    }

    @ApiOperation(value = "여행 시작 여부")
    @GetMapping("/tour-start/{userId}")
    public ResponseEntity<TourIsStartGetRes> courseIsStart(@ApiParam(value = "회원 구분 번호") @PathVariable("userId") int userId) {
        log.info("courseIsStart - Call");

        int courseId = tourService.courseIsStartByUser(userId);

        if(courseId != 0) {
            return ResponseEntity.status(200).body(TourIsStartGetRes.of(200, "Success", courseId));
        } else return ResponseEntity.status(200).body(TourIsStartGetRes.of(200, "Success", 0));
    }

    @ApiOperation(value = "코스에 대한 관광지 방문", notes = "코스에 등록 된 관광지를 방문하면 방문 등록(스탬프) 된다.")
    @PutMapping("/tour-stamp")
    public ResponseEntity<? extends BaseResponseBody> touristVisit(
            @RequestBody TouristVisitPostReq touristVisitPostReq) {
        log.info("tourEndByUser - Call");

        if (tourService.touristVisitByUser(touristVisitPostReq) == SUCCESS) {
            return ResponseEntity
                    .status(201)
                    .body(BaseResponseBody.of(201, "Success"));
        } else
            return ResponseEntity
                    .status(404)
                    .body(BaseResponseBody
                            .of(403, "There is no travel course in progress."));
    }

    @ApiOperation(value = "방문한 관광지 명 조회", notes = "사용자는 코스에 대한 방문한 관광지 조회가 가능하다.")
    @GetMapping("/tour-stamp/{userId}/{courseId}")
    public ResponseEntity<TouristNameVisitGetRes> touristNameVisit(
            @ApiParam(value = "회원 구분 번호") @PathVariable("userId") int userId,
            @ApiParam(value = "코스 구분 번호") @PathVariable("courseId") int courseId
    ) {
        log.info("touristVisit - Call");

        List<VisitTouristName> touristNameVisitList = tourService.touristNameVisitByUser(userId, courseId);

        if (touristNameVisitList != null && !touristNameVisitList.isEmpty()) {
            return ResponseEntity
                    .status(200)
                    .body(TouristNameVisitGetRes
                    .of(200, "Success", touristNameVisitList));
        } else {
            log.error("touristNameVisit - stamp doesn't exist");
            return ResponseEntity
                    .status(400).body(TouristNameVisitGetRes
                    .of(400, "stamp doesn't exist", null));
        }
    }


    @ApiOperation(value = "여행 코스 태그 목록", notes = "여행 레코드(일기) 작성 시, 여행에 맞는 태그를 선택한다.")
    @GetMapping("/tour-tag")
    public ResponseEntity<TagListGetRes> tagList () {
        log.info("tagList - Call");

        List<TagCode> tagList = tourService.tagList();

        if (tagList != null && !tagList.isEmpty()) {
            return ResponseEntity
                    .status(200)
                    .body(TagListGetRes.of(200, "Success", tagList));
        } else {
            log.error("tagList - tag doesn't exist");
            return ResponseEntity
                    .status(400)
                    .body(TagListGetRes.of(400, "tagList doesn't exist", null));
        }
    }



    @ApiOperation(value = "여행 코스 태그 등록")
    @PostMapping("/tour-tag")
    public ResponseEntity<? extends BaseResponseBody> tagRegister(@RequestBody TagRegisterPostReq tagRegisterPostReq) {
        log.info("tagRegister - Call");

        if(tourService.tagRegisterByUser(tagRegisterPostReq) == SUCCESS) {
            return ResponseEntity
                    .status(201)
                    .body(BaseResponseBody.of(201, "Success"));
        }else {
            log.error("Tag doesn't exist");
            return ResponseEntity
                    .status(404)
                    .body(BaseResponseBody.of(403, "Tag doesn't exist"));
        }
    }
}
