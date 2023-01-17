package com.lyg.goodtravel.domain.record.response;

import com.lyg.goodtravel.domain.course.db.bean.VisitTouristName;
import com.lyg.goodtravel.domain.course.db.entity.CourseData;
import com.lyg.goodtravel.global.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(value = "TouristNameVisitGetRes", description = "방문한 관광지 명 응답")
public class TouristNameVisitGetRes extends BaseResponseBody {
    @ApiModelProperty(value = "방문한 관광지 명 정보")
    List<VisitTouristName> list = null;

    public static TouristNameVisitGetRes of (Integer statusCode, String message, List<VisitTouristName> list) {
        TouristNameVisitGetRes res = new TouristNameVisitGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setList(list);

        return res;
    }
}