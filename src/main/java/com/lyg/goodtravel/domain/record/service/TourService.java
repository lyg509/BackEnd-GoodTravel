package com.lyg.goodtravel.domain.record.service;

import com.lyg.goodtravel.domain.record.db.entity.Tour;

public interface TourService {
    int courseStartByUser(int userId, int courseId);
    int courseEndByUser(int userId, int courseId);
    int touristVisitByUser(int userId, int courseId, int courseDataId);
}
