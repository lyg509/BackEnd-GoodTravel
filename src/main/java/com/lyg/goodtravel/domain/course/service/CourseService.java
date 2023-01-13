package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.entity.Bookmark;
import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.course.request.BookmarkRegisterPostReq;
import com.lyg.goodtravel.domain.course.request.CourseHitsPostReq;

import java.util.List;

public interface CourseService {
    int courseHits(CourseHitsPostReq courseHitsPostReq);
}
