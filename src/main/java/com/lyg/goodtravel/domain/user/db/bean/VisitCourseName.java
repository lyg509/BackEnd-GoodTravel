package com.lyg.goodtravel.domain.user.db.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VisitCourseName {
    private int courseId;
    private double touristLat;
    private double touristLng;
}