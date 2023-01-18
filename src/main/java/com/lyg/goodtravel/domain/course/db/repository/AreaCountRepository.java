package com.lyg.goodtravel.domain.course.db.repository;

import com.lyg.goodtravel.domain.course.db.entity.AreaCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaCountRepository extends JpaRepository<AreaCount, Integer> {
}
