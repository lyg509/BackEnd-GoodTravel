package com.lyg.goodtravel.domain.user.response;

import com.lyg.goodtravel.domain.user.db.bean.DateAnalysisDetail;
import com.lyg.goodtravel.global.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(value = "DateAnalysisRes", description = "방문한 월별 분석")
public class DateAnalysisRes extends BaseResponseBody {
    @ApiModelProperty(value = "방문한 월별 분석")
    List<DateAnalysisDetail> list = null;

    public static DateAnalysisRes of(
            Integer statusCode,
            String message,
            List<DateAnalysisDetail> list) {

        DateAnalysisRes res = new DateAnalysisRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setList(list);

        return res;
    }
}
