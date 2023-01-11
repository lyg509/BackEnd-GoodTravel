package com.lyg.goodtravel.domain.record.service;

import com.lyg.goodtravel.domain.record.db.entity.Record;
import com.lyg.goodtravel.domain.record.db.repository.RecordRepository;
import com.lyg.goodtravel.domain.record.db.repository.TourRepository;
import com.lyg.goodtravel.domain.record.request.RecordModifyPostReq;
import com.lyg.goodtravel.domain.record.request.RecordRegisterPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    TourRepository tourRepository;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @Override
    public int recordRegisterByUser(RecordRegisterPostReq recordRegisterPostReq) {

        int userId = recordRegisterPostReq.getUserId();
        int courseId = recordRegisterPostReq.getCourseId();
        String contents = recordRegisterPostReq.getRecordContent();

        Record record = new Record();

        //코스 완주 이력이 있을 때, 여행 레코드 작성 가능
        if (tourRepository.existsTourByUserIdAndCourseId(userId, courseId)) {
            record.setCourseId(courseId);
            record.setUserId(userId);
            record.setRecordContent(contents);

            recordRepository.save(record);

            return SUCCESS;
        } else return FAIL;
    }

    @Override
    public int recordModifyByUser(RecordModifyPostReq recordModifyPostReq) {
        //여행 레코드가 존재하면 수정. 존재하지 않으면 -1 반환
        if (recordRepository.findById(recordModifyPostReq.getRecordId()).isPresent()) {
            int recordId = recordRepository.findById(recordModifyPostReq.getRecordId()).get().getRecordId();
            String recordContent = recordModifyPostReq.getRecordContent();

            recordRepository.recordModifyByUser(recordId, recordContent);

            return SUCCESS;
        }
        return FAIL;
    }
}
