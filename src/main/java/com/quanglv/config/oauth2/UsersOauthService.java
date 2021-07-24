package com.quanglv.config.oauth2;


import com.quanglv.constant.AppConstant;
import com.quanglv.domain.first.Users;
import com.quanglv.repository.first.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UsersOauthService implements UserDetailsService {

    static final Logger logger = LoggerFactory.getLogger(UsersOauthService.class);

    @Autowired
    private UsersRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        repo.findAll();

        Users domain = repo.findByUsernameAndStatus(username, AppConstant.STATUS.ACTIVE_STATUS);
        if (domain == null) {
            throw new UsernameNotFoundException("Tên đăng nhập hoặc mật khẩu không đúng.");
        }

        return UserPrincipalOauth2.create(domain);
    }
}
