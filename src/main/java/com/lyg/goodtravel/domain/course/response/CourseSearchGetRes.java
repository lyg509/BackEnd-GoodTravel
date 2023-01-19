package com.lyg.goodtravel.domain.course.response;

import com.lyg.goodtravel.domain.course.db.bean.CourseInfo;
import com.lyg.goodtravel.domain.course.db.bean.CourseSearch;
import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.global.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@ApiModel(value = "CourseSearchGetRes", description = "코스 검색 응답")
public class CourseSearchGetRes extends BaseResponseBody {
    @ApiModelProperty(value = "코스 정보")
    Page<CourseInfo> list = null;

    public static CourseSearchGetRes of (
            Integer statusCode,
            String message,
            Page<CourseInfo> list) {

        CourseSearchGetRes res = new CourseSearchGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setList(list);

        return res;
    }
}
