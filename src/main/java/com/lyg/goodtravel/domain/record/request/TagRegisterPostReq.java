package com.lyg.goodtravel.domain.record.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@ApiModel(value = "TagRegisterPostReq", description = "여행 레코드 태그 등록 시 필요한 정보")
public class TagRegisterPostReq {
    @ApiModelProperty(value = "여행 레코드(일기) 구분 번호", example = "1")
    private int recordId;

    @ApiModelProperty(value = "코스 구분 번호", example = "1")
    private int courseId;

    private Map<Integer, Integer> tag;

    @ApiModelProperty(value = "태그 선택 여부", example = "true")
    private boolean isSelect;
}
