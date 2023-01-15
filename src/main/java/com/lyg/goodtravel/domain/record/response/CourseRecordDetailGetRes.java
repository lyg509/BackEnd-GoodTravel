package com.lyg.goodtravel.domain.record.response;


import com.lyg.goodtravel.domain.record.db.entity.Record;
import com.lyg.goodtravel.global.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(value = "CourseRecordDetailGetRes")
public class CourseRecordDetailGetRes extends BaseResponseBody {
    @ApiModelProperty(value = "사용자가 작성한 여행 레코드 조회 정보")
    List<Record> list = null;

    public static CourseRecordDetailGetRes of (Integer statusCode, String message, List<Record> list) {
        CourseRecordDetailGetRes res = new CourseRecordDetailGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setList(list);

        return res;
    }
}