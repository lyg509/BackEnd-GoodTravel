package com.lyg.goodtravel.domain.course.db.repository;

import com.lyg.goodtravel.domain.course.db.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Course c set c.courseHits = c.courseHits + 1 where c.courseId = :courseId")
    int courseHitsByCourseId(int courseId);

}
