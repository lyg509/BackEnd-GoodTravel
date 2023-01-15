package com.lyg.goodtravel.domain.course.response;

import com.lyg.goodtravel.domain.course.db.bean.CourseDetail;
import com.lyg.goodtravel.global.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(value = "CourseDetailGetRes", description = "코스 상세보기 응답")
public class CourseDetailGetRes extends BaseResponseBody {
    @ApiModelProperty(value = "코스 상세보기 정보")
    List<CourseDetail> courseDetailList = null;

    public static CourseDetailGetRes of (
            Integer statusCode, String message, List<CourseDetail> courseDetailList) {
        CourseDetailGetRes res = new CourseDetailGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setCourseDetailList(courseDetailList);

        return res;
    }
}