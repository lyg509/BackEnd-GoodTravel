package com.lyg.goodtravel.domain.course.db.repository;

import com.lyg.goodtravel.domain.tourtest.db.entity.TourTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TourTestRepository extends JpaRepository<TourTest, Integer> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update TourTest t set t.tourTestResult = t.tourTestResult + 1 where t.tourTestId = :tourTestId")
    int tourTestResultCountByUser(int tourTestId); // update/delete 문은 return type이 int/Integer이어야함.


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update TourTest t set t.tourTestResult = t.tourTestResult - 1 where t.tourTestId = :tourTestId")
    int tourTestBeforeCountByUser(int tourTestId); // update/delete 문은 return type이 int/Integer이어야함.


    @Query("select t from TourTest t " +
            "left join User u on t.tourTestId = u.tourTestId " +
            "left join Tour r on r.userId = u.userId " +
            "left join Course c on c.courseId = r.courseId " +
            "where r.isStart = true and c.courseId = :courseId " +
            "group by t.tourTestId")
    List<TourTest> courseTourTestResultDetailByCourseId(int courseId);
}