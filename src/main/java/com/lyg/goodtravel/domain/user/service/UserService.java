package com.lyg.goodtravel.domain.user.service;

import com.lyg.goodtravel.domain.user.db.bean.AreaAnalysisDetail;
import com.lyg.goodtravel.domain.user.db.bean.CourseNameVisitDetail;
import com.lyg.goodtravel.domain.user.db.entity.User;
import com.lyg.goodtravel.domain.user.request.UserModifyPutReq;
import com.lyg.goodtravel.domain.user.request.UserRegisterPostReq;

import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
    User findByEmail(String userEmail);
    User createUser(UserRegisterPostReq userRegisterInfo);
    User updateUser(UserModifyPutReq userModifyPutReq);
    List<CourseNameVisitDetail> courseNameVisitDetail(int userId);
    List<AreaAnalysisDetail> areaAnalysisDetail(int userId);
}
