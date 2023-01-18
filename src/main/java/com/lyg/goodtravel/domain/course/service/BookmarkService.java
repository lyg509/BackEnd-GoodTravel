package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.bean.BookmarkCourse;
import com.lyg.goodtravel.domain.course.db.entity.Bookmark;
import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.course.request.BookmarkRegisterPostReq;

import java.util.List;

public interface BookmarkService {
    Bookmark bookmarkRegisterByUser(BookmarkRegisterPostReq bookmarkRegisterPostReq);
    int bookmarkRemoveByUser(int userId, int courseId);
    List<BookmarkCourse> bookmarkCourse(int userId);
}