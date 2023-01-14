package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.entity.Tourist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TouristService {
    Page<Tourist> touristSearchByUser(String keywords, Pageable pageable);
}