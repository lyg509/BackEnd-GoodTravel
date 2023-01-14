package com.lyg.goodtravel.global.auth;

import com.lyg.goodtravel.domain.user.db.entity.User;
import com.lyg.goodtravel.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 현재 액세스 토큰으로 부터 인증된 유저의 상세정보(활성화 여부, 만료, 롤 등) 관련 서비스 정의.
 */
@Component
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public TestUserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userService.findByEmail(userEmail);
        if(user != null) {
            TestUserDetails userDetails = new TestUserDetails(user);
            return userDetails;
        }
        return null;
    }
}
