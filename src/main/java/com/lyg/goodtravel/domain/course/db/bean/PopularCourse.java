package com.lyg.goodtravel.domain.course.db.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PopularCourse {
    private int courseId;
    private String courseName;

    private int touristId;
    private int fileId;
}