package com.lyg.goodtravel.domain.record.db.repository;

import com.lyg.goodtravel.domain.record.db.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Record r set r.recordContent = :recordContent where r.recordId = recordId")
    int recordModifyByUser(int recordId, String recordContent);
}
