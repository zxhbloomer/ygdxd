package com.ygdxd.security.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author Created by ygdxd_admin at 2017-11-07 下午4:50
 */
@Configuration
public class Oauth2Configuration {


    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        private RedisTokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId("ygdxd").stateless(true).tokenStore(tokenStore);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    // Since we want the protected resources to be accessible in the UI as well we need
                    // session creation to be allowed (it's disabled by default in 2.0.6)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .requestMatchers().anyRequest()
                    .and()
                    .anonymous()
                    .and()
                    .authorizeRequests()
//                    .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
                    .antMatchers("/ygdxd/**").authenticated();//配置order访问控制，必须认证过后才可以访问

        }




    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


        @Autowired
        private AuthenticationManager manager;

        @Autowired
        private RedisTokenStore redisTokenStore;

        @Autowired
        private DruidDataSource dataSource;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(clientDetailsService());// 客户端 或者 password 模式 使用默认配置

        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security.allowFormAuthenticationForClients()
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("isAuthenticated()");
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(redisTokenStore)
                    .authenticationManager(manager);
        }

        private ClientDetailsService clientDetailsService() {
            // TODO Auto-generated method stub
            return new JdbcClientDetailsService(dataSource);
        }
    }

}
