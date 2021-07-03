package com.company.service;

import com.company.dao.impl.UserRepositoryCustom;
import com.company.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Qualifier("userDaoImpl1")
    private UserRepositoryCustom userRepositoryCustom;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepositoryCustom.findByEmail(email);

        if(user!=null){
            org.springframework.security.core.userdetails.User.UserBuilder builder =
                    org.springframework.security.core.userdetails.User.withUsername(email);

            builder.disabled(false);
            builder.password(user.getPassword());

            String[] authoritiesArr = new String[]{"USER","ROLE_USER","ADMIN","ROLE_ADMIN"};
            builder.authorities(authoritiesArr);
            return builder.build();
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }

    }
}
