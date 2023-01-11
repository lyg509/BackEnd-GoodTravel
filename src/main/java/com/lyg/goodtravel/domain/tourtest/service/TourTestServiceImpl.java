package com.lyg.goodtravel.domain.tourtest.service;

import com.lyg.goodtravel.domain.tourtest.repository.TourTestRepository;
import com.lyg.goodtravel.domain.tourtest.request.TourTestResultPostReq;
import com.lyg.goodtravel.domain.user.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourTestServiceImpl implements TourTestService {

    @Autowired
    TourTestRepository tourTestRepository;

    @Autowired
    UserRepository userRepository;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;


    @Override
    public int tourTestResultByUser(TourTestResultPostReq tourTestResultPostReq) {
        // 회원이 존재하면
        if(userRepository.findById(tourTestResultPostReq.getUserId()).isPresent()) {
            return userRepository.tourTestResultByUserId(
                    tourTestResultPostReq.getUserId(),
                    tourTestResultPostReq.getTourTestId());
        }else return FAIL;
    }
}