package com.lyg.goodtravel.domain.tourtest.controller;

import com.lyg.goodtravel.domain.tourtest.request.TourTestResultPostReq;
import com.lyg.goodtravel.domain.tourtest.service.TourTestService;
import com.lyg.goodtravel.global.model.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


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
    public ResponseEntity<? extends BaseResponseBody> tourTestResultByUser(@RequestBody TourTestResultPostReq tourTestResultPostReq) {

        log.info("tourTestResultByUser - Call");

        if(tourTestService.tourTestResultByUser(tourTestResultPostReq) == SUCCESS) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        }else return ResponseEntity.status(404).body(BaseResponseBody.of(201, "userId doesn't exist"));
    }
}
