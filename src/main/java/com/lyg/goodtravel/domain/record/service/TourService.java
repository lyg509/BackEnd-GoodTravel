package com.lyg.goodtravel.domain.record.service;

import com.lyg.goodtravel.domain.course.db.entity.CourseData;
import com.lyg.goodtravel.domain.record.request.TagRegisterPostReq;
import com.lyg.goodtravel.domain.record.request.TourEndPostReq;
import com.lyg.goodtravel.domain.record.request.TouristVisitPostReq;

import java.util.List;

public interface TourService {
    int courseStartByUser(int userId, int courseId);
    int courseEndByUser(TourEndPostReq tourEndPostReq);
    int touristVisitByUser(TouristVisitPostReq touristVisitPostReq);
    List<CourseData> touristNameVisitByUser(int userId, int courseId);
    int tagRegisterByUser(TagRegisterPostReq tagRegisterPostReq);
}
