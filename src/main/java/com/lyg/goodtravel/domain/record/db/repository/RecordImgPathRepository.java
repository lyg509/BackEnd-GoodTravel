package com.lyg.goodtravel.domain.record.db.repository;

import com.lyg.goodtravel.domain.record.db.entity.RecordImgPath;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordImgPathRepository extends JpaRepository<RecordImgPath, Integer> {
    RecordImgPath findRecordImgPathByFileIdAndRecordIdAndCourseId(int fileId, int recordId, int courseId);
}
