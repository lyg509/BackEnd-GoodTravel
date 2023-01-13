package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.entity.Bookmark;
import com.lyg.goodtravel.domain.course.db.entity.BookmarkID;
import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.course.db.repository.BookmarkRepository;
import com.lyg.goodtravel.domain.course.db.repository.CourseDataRepository;
import com.lyg.goodtravel.domain.course.db.repository.CourseRepository;
import com.lyg.goodtravel.domain.course.db.repository.CourseRepositorySpp;
import com.lyg.goodtravel.domain.course.request.BookmarkRegisterPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseDataRepository courseDataRepository;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Autowired
    CourseRepositorySpp courseRepositorySpp;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @Override
    public Bookmark bookmarkRegisterByUser(BookmarkRegisterPostReq bookmarkRegisterPostReq) {
        Bookmark bookmark = new Bookmark();

        bookmark.setUserId(bookmarkRegisterPostReq.getUserId());
        bookmark.setCourseId(bookmarkRegisterPostReq.getCourseId());

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

    @Override
    public List<Course> bookmarkCourse(int userId) {
        return courseRepositorySpp.findBookmarkCourse(userId);
    }
}
