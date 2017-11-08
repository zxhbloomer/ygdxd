package com.ygdxd.security.user;

import com.ygdxd.ase.ASE;
import com.ygdxd.exp.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Created by ygdxd_admin at 2017-11-07 下午1:13
 */
@Component
public class MyUserDetailsService implements UserDetailsService{

    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null){
            return null;
        }
        String password = null;
        try {
            password = ASE.UTF8.encode("ygdxd");
        } catch (BaseException e) {
            e.printStackTrace();
        }
        LOGGER.info("PASSWORD: "+password);
        return new Custom("ygdxd",username,password, AuthorityUtils.createAuthorityList("ADMIN"));
    }
}
