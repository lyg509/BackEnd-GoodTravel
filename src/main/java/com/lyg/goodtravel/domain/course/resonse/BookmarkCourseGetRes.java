package com.lyg.goodtravel.domain.course.resonse;

import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.global.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(value = "BookmarkCourseGetRes", description = "북마크한 코스 응답")
public class BookmarkCourseGetRes extends BaseResponseBody {
    @ApiModelProperty(value = "북마크한 코스 정보")
    List<Course> list = null;

    public static BookmarkCourseGetRes of (Integer statusCode, String message, List<Course> list) {
        BookmarkCourseGetRes res = new BookmarkCourseGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setList(list);

        return res;
    }
}