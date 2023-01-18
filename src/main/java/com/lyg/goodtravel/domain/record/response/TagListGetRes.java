package com.lyg.goodtravel.domain.record.response;

import com.lyg.goodtravel.domain.record.db.entity.TagCode;
import com.lyg.goodtravel.global.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(value = "TagListGetRes", description = "여행 코스 태그 응답")
public class TagListGetRes extends BaseResponseBody {
    @ApiModelProperty(value = "여행 코스 태그 정보")
    List<TagCode> list = null;

    public static TagListGetRes of (Integer statusCode, String message, List<TagCode> list) {
        TagListGetRes res = new TagListGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setList(list);

        return res;
    }

}