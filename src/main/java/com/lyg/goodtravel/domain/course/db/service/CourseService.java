package com.lyg.goodtravel.domain.course.db.service;

import com.lyg.goodtravel.domain.course.db.entity.Bookmark;
import com.lyg.goodtravel.domain.course.request.BookmarkRegisterPostReq;

public interface CourseService {
    Bookmark bookmarkRegisterByUser(BookmarkRegisterPostReq bookmarkRegisterPostReq);
    int bookmarkRemoveByUser(int courseId, int userId);
}
