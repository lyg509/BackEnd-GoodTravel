package com.lyg.goodtravel.domain.course.db.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@IdClass(CourseDataID.class)
@Table(name = "course_data")
@ApiModel(value = "CourseData", description = "코스에 등록된 관광지 정보")
public class CourseData {
    @ApiModelProperty(value = "코스에 등록된 관광지 순차 번호", example = "1")
    @Id
    @Column(name = "course_data_id")
    private int courseDataId;

    @ApiModelProperty(value = "코스 구분 코드", required = true, example = "3")
    @Id
    @Column(name = "course_id")
    private int courseId;

    @ApiModelProperty(value = "관광지 구분 코드", required = true, example = "3")
    @Column(name = "tourist_id")
    private int touristId;

    @ApiModelProperty(value = "코스에 등록된 관광지 명", required = true, example = "관광지1")
    @Column(name = "course_data_name")
    private String courseDataName;

    @ApiModelProperty(value = "코스에 등록된 관광지 주소", required = true, example = "부산광역시")
    @Column(name = "course_address")
    private String courseAddress;

    @ApiModelProperty(value = "코스에 등록된 관광지 위도", required = true, example = "34.4324")
    @Column(name = "course_lat")
    private double courseLat;

    @ApiModelProperty(value = "코스에 등록된 관광지 경도", required = true, example = "38.4324")
    @Column(name = "course_lng")
    private double courseLng;

    @ManyToOne
    @JoinColumn(name = "course_id", updatable = false, insertable = false)
    private Course course;

}