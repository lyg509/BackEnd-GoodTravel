package com.lyg.goodtravel.domain.record.service;

import com.lyg.goodtravel.domain.record.db.entity.Record;
import com.lyg.goodtravel.domain.record.db.repository.RecordRepository;
import com.lyg.goodtravel.domain.record.db.repository.TourRepository;
import com.lyg.goodtravel.domain.record.db.repository.TourStampRepository;
import com.lyg.goodtravel.domain.record.request.RecordModifyPostReq;
import com.lyg.goodtravel.domain.record.request.RecordRegisterPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    TourStampRepository tourStampRepository;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @Override
    public int recordRegisterByUser(RecordRegisterPostReq recordRegisterPostReq) {

        int userId = recordRegisterPostReq.getUserId();
        int courseId = recordRegisterPostReq.getCourseId();
        String contents = recordRegisterPostReq.getRecordContent();

        Record record = new Record();

        // 코스 시작 이력 여부 확인, 스탬프가 하나라도 찍혀 있을 때 작성 가능 하게 설정 --> 여행 레코드 작성 활성화
        if(tourRepository.existsTourByUserIdAndCourseId(userId, courseId) && tourStampRepository.isStampByUserIdAndCourseId(userId, courseId) > 0) {
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

    @Override
    public List<Record> recordWriteListByUser(int userId, int courseId) {
        return recordRepository.findRecordByUserIdAndCourseId(userId, courseId);
    }
}
