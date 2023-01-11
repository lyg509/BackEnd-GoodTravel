package com.lyg.goodtravel.domain.record.controller;

import com.lyg.goodtravel.domain.record.request.RecordModifyPostReq;
import com.lyg.goodtravel.domain.record.request.RecordRegisterPostReq;
import com.lyg.goodtravel.domain.record.service.RecordService;
import com.lyg.goodtravel.global.model.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "SUCCESS"));
        } else
            log.error("qnaQuestionModify - This questionId doesn't exist.");
            return ResponseEntity.status(404).body(BaseResponseBody.of(403, "There is" +
                "no completed course.l"));
    }

}
