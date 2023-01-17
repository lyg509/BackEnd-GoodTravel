package com.lyg.goodtravel.domain.course.db.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseDetail {
    private String courseName;
    private String courseContent;
    private String courseDistance;
    private String courseDays;
    private int courseHits;
}
