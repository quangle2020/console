package com.quanglv.service.impl;


import com.quanglv.constant.AppConstant;
import com.quanglv.domain.first.Users;
import com.quanglv.repository.first.UsersRepository;
import com.quanglv.service.dto.UserPrincipalOauth2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userCustomService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users userEntity = usersRepository.findByUsernameAndStatus(username, AppConstant.STATUS.ACTIVE_STATUS);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserPrincipalOauth2.createPrincipalOauth2(userEntity);
    }
}
