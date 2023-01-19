package com.lyg.goodtravel.domain.record.service;

import com.lyg.goodtravel.domain.course.db.bean.VisitTouristName;
import com.lyg.goodtravel.domain.course.db.entity.CourseData;
import com.lyg.goodtravel.domain.record.db.entity.TagCode;
import com.lyg.goodtravel.domain.record.request.TagRegisterPostReq;
import com.lyg.goodtravel.domain.record.request.TourEndPostReq;
import com.lyg.goodtravel.domain.record.request.TourStartPostReq;
import com.lyg.goodtravel.domain.record.request.TouristVisitPostReq;

import java.util.List;

public interface TourService {
    int courseStartByUser(TourStartPostReq tourStartPostReq);
    int courseEndByUser(TourEndPostReq tourEndPostReq);
    boolean courseIsStartByUser(int userId, int courseId);

    int touristVisitByUser(TouristVisitPostReq touristVisitPostReq);

    List<VisitTouristName> touristNameVisitByUser(int userId, int courseId);

    int tagRegisterByUser(TagRegisterPostReq tagRegisterPostReq);
    List<TagCode> tagList();
}
