package com.ygdxd.Test;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by ygdxd_admin at 2017-09-20 下午3:01
 */
public class Generator {

    private static final Logger _log = LoggerFactory.getLogger(Generator.class);

    private File getFile(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        /**
         * getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
         */
        URL url = classLoader.getResource(fileName);
        /**
         * url.getFile() 得到这个文件的绝对路径
         */
        _log.info("读取配置文件：{}", url.getFile());
        File file = new File(url.getFile());
        return file;
    }

    public void generator() throws Exception{
        List<String> warnings = new ArrayList<>();
        File file = getFile("mybatis-generator.xml");
        ConfigurationParser configurationParser = new ConfigurationParser(warnings);
        Configuration configuration = configurationParser.parseConfiguration(file);
        DefaultShellCallback defaultShellCallback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, defaultShellCallback, warnings);
        myBatisGenerator.generate(null);
        for (String s : warnings){
            _log.warn("warnings: {}", s);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("--------------------");
        Generator generator = new Generator();
        generator.generator();
    }
}
