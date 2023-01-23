package com.lyg.goodtravel.domain.user.db.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseNameVisitDetail {
    private int courseId;
    private String courseName;
    private double touristLat;
    private double touristLng;
}