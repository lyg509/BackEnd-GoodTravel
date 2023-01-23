package com.lyg.goodtravel.domain.record.response;

import com.lyg.goodtravel.global.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TourIsStartGetRes", description = "여행 시작 여부 응답")
public class TourIsStartGetRes extends BaseResponseBody {
    @ApiModelProperty(value = "여행 시작 여부 정보")
    int courseId = 0;

    public static TourIsStartGetRes of(Integer statusCode, String message, int courseId) {
        TourIsStartGetRes res = new TourIsStartGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setCourseId(courseId);

        return res;
    }

}