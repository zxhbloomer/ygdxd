package com.ygdxd.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by ygdxd_admin at 2017-11-06 下午10:07
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DruidConfiguration {

    @Bean
    public DruidDataSource getDataSource(DataSourceProperties properties){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        //设置初始化连接池的连接数量
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(3);
        dataSource.setMaxActive(20);

        dataSource.setMaxWait(60000);
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(300000);

        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        // 打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        // 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
//        dataSource.addFilters("stat,wall,log4j");
        // 通过connectProperties属性来打开mergeSql功能；慢SQL记录
        dataSource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000");
        return dataSource;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("allow", "127.0.0.1");
        initParameters.put("loginUsername", "ygdxd");
        initParameters.put("loginPassword", "ygdxd");
        bean.setInitParameters(initParameters);

        return bean;
    }
}
