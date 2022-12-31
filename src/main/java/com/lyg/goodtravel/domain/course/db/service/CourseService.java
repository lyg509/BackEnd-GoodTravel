package com.lyg.goodtravel.domain.course.db.service;

import com.lyg.goodtravel.domain.course.db.entity.Bookmark;

public interface CourseService {
    Bookmark bookmarkRegisterByUser(int courseId, int userId);
    int bookmarkRemoveByUser(int courseId, int userId);
}
