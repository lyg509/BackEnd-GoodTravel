package com.lyg.goodtravel.domain.course.db.service;

import com.lyg.goodtravel.domain.course.db.entity.Bookmark;
import com.lyg.goodtravel.domain.course.db.entity.BookmarkID;
import com.lyg.goodtravel.domain.course.db.repository.BookmarkRepository;
import com.lyg.goodtravel.domain.course.db.repository.CourseDataRepository;
import com.lyg.goodtravel.domain.course.db.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseDataRepository courseDataRepository;

    @Autowired
    BookmarkRepository bookmarkRepository;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @Override
    public Bookmark bookmarkRegisterByUser(int userId, int courseId) {
        Bookmark bookmark = new Bookmark();

        bookmark.setUserId(userId);
        bookmark.setCourseId(courseId);

        return bookmarkRepository.save(bookmark);
    }

    @Override
    public int bookmarkRemoveByUser(int userId, int courseId) {
        BookmarkID bookmarkID = new BookmarkID();
        bookmarkID.setUserId(userId);
        bookmarkID.setCourseId(courseId);

        if(bookmarkRepository.findById(bookmarkID).isPresent()) {
            bookmarkRepository.deleteById(bookmarkID);

            return SUCCESS;
        }else return FAIL;
    }
}
