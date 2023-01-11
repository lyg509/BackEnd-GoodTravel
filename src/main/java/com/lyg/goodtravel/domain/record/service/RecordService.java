package com.lyg.goodtravel.domain.record.service;

import com.lyg.goodtravel.domain.record.request.RecordRegisterPostReq;

public interface RecordService {
    int recordRegisterByUser(RecordRegisterPostReq recordRegisterPostReq);
}
