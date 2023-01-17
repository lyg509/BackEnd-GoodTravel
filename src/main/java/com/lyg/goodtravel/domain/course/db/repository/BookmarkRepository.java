package com.lyg.goodtravel.domain.course.db.repository;

import com.lyg.goodtravel.domain.course.db.entity.Bookmark;
import com.lyg.goodtravel.domain.course.db.entity.BookmarkID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkID> {

    @Query("select count(b) from Bookmark b")
    int countBookmarks();

    @Query("select b.courseId from Bookmark b where b.courseId = :courseId")
    int countBookmarksByCourseId(int courseId);
}