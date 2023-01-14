package com.lyg.goodtravel.domain.record.service;

import com.lyg.goodtravel.domain.record.db.entity.Record;
import com.lyg.goodtravel.domain.record.request.RecordModifyPostReq;
import com.lyg.goodtravel.domain.record.request.RecordRegisterPostReq;

import java.util.List;

public interface RecordService {
    int recordRegisterByUser(RecordRegisterPostReq recordRegisterPostReq);
    int recordModifyByUser(RecordModifyPostReq recordRegisterPostReq);
    List<Record> recordWriteListByUser(int userId, int courseId);
}
