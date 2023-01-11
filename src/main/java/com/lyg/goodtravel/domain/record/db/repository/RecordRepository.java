package com.lyg.goodtravel.domain.record.db.repository;

import com.lyg.goodtravel.domain.record.db.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
}
