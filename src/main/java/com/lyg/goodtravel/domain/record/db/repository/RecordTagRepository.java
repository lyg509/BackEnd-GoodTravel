package com.lyg.goodtravel.domain.record.db.repository;

import com.lyg.goodtravel.domain.record.db.entity.RecordTag;
import com.lyg.goodtravel.domain.record.db.entity.RecordTagID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordTagRepository extends JpaRepository<RecordTag, RecordTagID> {
}
