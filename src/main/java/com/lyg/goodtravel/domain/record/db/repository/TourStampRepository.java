package com.lyg.goodtravel.domain.record.db.repository;

import com.lyg.goodtravel.domain.record.db.entity.TourStamp;
import com.lyg.goodtravel.domain.record.db.entity.TourStampID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface TourStampRepository extends JpaRepository<TourStamp, TourStampID> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update TourStamp t set t.isStamp = 1 where t.userId = :userId and t.courseId = :courseId and t.courseDataId = :courseDataId")
    int touristVisitByUser(int userId, int courseId, int courseDataId);

    @Query("select count(s) from TourStamp s where s.isStamp = true and s.userId = :userId and s.courseId = :courseId")
    int isStampByUserIdandCourseId(int userId, int courseId);


    @Query("select count(s.courseId) from TourStamp s where s.courseId = :courseId")
    int countTourStamp(int courseId);

    @Query("select count(s.isStamp) from TourStamp s where s.isStamp = true and s.courseId = :courseId")
    int countTourStampByCourseId(int courseId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("delete from TourStamp t where t.userId = :userId and t.courseId = :courseId")
    int deleteTourStampByUserIdAndCourseId(int userId, int courseId);
}