package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.entity.Bookmark;
import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.course.request.BookmarkRegisterPostReq;

import java.util.List;

public interface CourseService {
    Bookmark bookmarkRegisterByUser(BookmarkRegisterPostReq bookmarkRegisterPostReq);
    int bookmarkRemoveByUser(int courseId, int userId);
    List<Course> bookmarkCourse(int userId);
}
