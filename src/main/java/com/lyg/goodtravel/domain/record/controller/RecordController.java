package com.lyg.goodtravel.domain.record.controller;

import com.lyg.goodtravel.domain.record.db.entity.Record;
import com.lyg.goodtravel.domain.record.request.RecordModifyPostReq;
import com.lyg.goodtravel.domain.record.request.RecordRegisterPostReq;
import com.lyg.goodtravel.domain.record.response.RecordWriteListGetRes;
import com.lyg.goodtravel.domain.record.service.RecordService;
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

@Api("여행 레코드(일기) API")
@Slf4j
@Controller
@RequestMapping("/api/record")
public class RecordController {

    @Autowired
    RecordService recordService;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @ApiOperation(value = "여행 레코드(일기) 등록")
    @PostMapping("")
    public ResponseEntity<? extends BaseResponseBody> recordRegister(
            RecordRegisterPostReq recordRegisterPostReq) {

        log.info("recordRegister - Call");

        if (recordService.recordRegisterByUser(recordRegisterPostReq) == SUCCESS) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        } else return ResponseEntity.status(404).body(BaseResponseBody.of(403, "There is " +
                "no completed course."));
    }

    @ApiOperation(value = "여행 레코드(일기) 수정")
    @PutMapping("")
    public ResponseEntity<? extends BaseResponseBody> recordModify(
            @RequestBody RecordModifyPostReq recordRegisterPostReq) {

        log.info("recordModify = Call");

        if (recordService.recordModifyByUser(recordRegisterPostReq) == SUCCESS) {
            return ResponseEntity.status(201).body(
                    BaseResponseBody.of(201, "SUCCESS"));
        } else
            log.error("qnaQuestionModify - This questionId doesn't exist.");
            return ResponseEntity.status(404).body(
                    BaseResponseBody.of(403, "There is" +
                "no completed course.l"));
    }

    @ApiOperation(value = "사용자가 작성한 여행 레코드(일기) 조회")
    @GetMapping("/{userId}/{courseId}")
    public ResponseEntity<RecordWriteListGetRes> recordWriteList (
            @ApiParam(value = "회원 구분 번호") @PathVariable("userId") int userId,
            @ApiParam(value = "코스 구분 번호") @PathVariable("courseId") int courseId) {
        log.info("recordWriteList - Call");

        List<Record> recordWriteList = recordService.recordWriteListByUser(userId, courseId);

        if (recordWriteList != null && !recordWriteList.isEmpty()) {
            return ResponseEntity.status(200).body(
                    RecordWriteListGetRes.of(200, "Success", recordWriteList));
        }else {
            log.error("recordWriteList - Record doesn't exist.");
            return ResponseEntity.status(400).body(
                    RecordWriteListGetRes.of(400, "Record doesn't exist", null));
        }
    }
}
