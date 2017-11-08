package com.ygdxd.security.user;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author Created by ygdxd_admin at 2017-11-07 下午1:26
 */
@Getter
public class Custom extends User{


    private static final long serialVersionUID = -2713372412446893833L;

    private String id;

    public Custom(String id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }

    public Custom(String id,String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
    }
}
