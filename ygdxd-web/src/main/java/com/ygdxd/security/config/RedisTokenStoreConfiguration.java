package com.ygdxd.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author Created by ygdxd_admin at 2017-09-22 下午1:12
 */
@Configuration
public class RedisTokenStoreConfiguration {

    @Bean
    @Autowired
    public RedisTokenStore tokenstore(JedisConnectionFactory connectionFactory){
        return new RedisTokenStore(connectionFactory);
    }
}
