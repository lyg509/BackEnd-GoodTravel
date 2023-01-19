package com.lyg.goodtravel.domain.record.service;

import com.lyg.goodtravel.domain.record.db.entity.Record;
import com.lyg.goodtravel.domain.record.request.RecordModifyPostReq;
import com.lyg.goodtravel.domain.record.request.RecordRegisterPostReq;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

public interface RecordService {
    int recordRegisterByUser(RecordRegisterPostReq recordRegisterPostReq, MultipartHttpServletRequest request) throws IOException;
    int recordModifyByUser(RecordModifyPostReq recordModifyPostReq);
    List<Record> recordWriteListByUser(int userId, int courseId);
}
