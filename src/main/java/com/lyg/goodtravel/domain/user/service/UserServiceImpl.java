package com.lyg.goodtravel.domain.user.service;

import com.lyg.goodtravel.domain.user.db.bean.VisitCourseName;
import com.lyg.goodtravel.domain.user.db.entity.User;
import com.lyg.goodtravel.domain.user.db.repository.UserRepository;
import com.lyg.goodtravel.domain.user.db.repository.UserRepositorySpp;
import com.lyg.goodtravel.domain.user.request.UserModifyPutReq;
import com.lyg.goodtravel.domain.user.request.UserRegisterPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositorySpp userRepositorySpp;

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Cacheable(value = "findByEmail", key="#userEmail")
    public User findByEmail(String userEmail) {
        User user = userRepositorySpp.findByEmail(userEmail);
        return user;
    }


    @Override
    public User createUser(UserRegisterPostReq userRegisterInfo) {
        User user = new User();
        if(findByEmail(userRegisterInfo.getUserEmail())==null){
            user.setUserEmail(userRegisterInfo.getUserEmail());
            user.setUserName(userRegisterInfo.getUserName());
            user.setUserPassword(passwordEncoder.encode(userRegisterInfo.getUserPassword()));
            user.setTourTestId(7);
            return userRepository.save(user);
        }else {
            return null;
        }
    }

    @Override
    public User updateUser(UserModifyPutReq userModifyPutReq) {
        User user = new User();
        user.setUserEmail(userModifyPutReq.getUserEmail());
        user.setUserName(userModifyPutReq.getUserName());
        user.setUserPassword(passwordEncoder.encode(userModifyPutReq.getUserPassword()));
        user.setTourTestId(userModifyPutReq.getTourTestId());
        return userRepository.save(user);
    }

    @Override
    public List<VisitCourseName> visitCourseName(int userId){return  userRepositorySpp.findVisitTouristByUserId(userId);}

}