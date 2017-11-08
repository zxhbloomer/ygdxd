package com.ygdxd.security.token;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Created by ygdxd_admin at 2017-09-22 下午2:48
 */
public class MyTokenEnhancer implements TokenEnhancer{
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        final Object principal = oAuth2Authentication.getPrincipal();
        final Map<String, Object> map = new ConcurrentHashMap<>();

        if (principal instanceof User){
            User user = (User) principal;
            map.put("username",user.getUsername());
            Map<String, Map<String, Object>> _link = new HashMap<>();
            Map<String, Object> userInfoLink = new HashMap<>();
            userInfoLink.put("href",user.getAuthorities());
            _link.put("self", userInfoLink);
        }
        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(map);
        return oAuth2AccessToken;
    }
}
