package com.lyg.goodtravel.domain.course.db.repository;

import com.lyg.goodtravel.domain.course.db.entity.Course;
import com.lyg.goodtravel.domain.course.db.entity.QBookmark;
import com.lyg.goodtravel.domain.course.db.entity.QCourse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepositorySpp {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QCourse qCourse = QCourse.course;
    QBookmark qBookmark = QBookmark.bookmark;

    public List<Course> findBookmarkCourse (int userId) {
        return jpaQueryFactory.select(qCourse).from(qCourse)
                .leftJoin(qBookmark).on(qBookmark.courseId.eq(qCourse.courseId))
                .where(qBookmark.userId.eq(userId))
                .fetch();
    }
}
