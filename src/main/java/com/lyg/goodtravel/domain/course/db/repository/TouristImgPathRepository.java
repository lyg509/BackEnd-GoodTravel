package com.lyg.goodtravel.domain.course.db.repository;

import com.lyg.goodtravel.domain.course.db.entity.TouristImgPath;
import com.lyg.goodtravel.domain.course.db.entity.TouristImgPathID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristImgPathRepository extends JpaRepository<TouristImgPath, TouristImgPathID> {

    TouristImgPath findTouristImgPathByFileIdAndTouristId(int fileId, int touristId);
}
