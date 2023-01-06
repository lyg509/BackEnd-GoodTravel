package com.lyg.goodtravel.domain.tourtest.repository;

import com.lyg.goodtravel.domain.tourtest.db.entity.TourTest;
import com.lyg.goodtravel.domain.tourtest.db.entity.TourTestID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourTestRepository extends JpaRepository<TourTest, TourTestID> {

}
