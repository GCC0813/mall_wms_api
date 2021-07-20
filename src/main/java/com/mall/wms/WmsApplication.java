package com.mall.wms;


import org.apache.logging.log4j.core.appender.db.jdbc.DataSourceConnectionSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author haonan
 * create on 2020/1/8
 * Application启动类
 */
@SpringBootApplication
@MapperScan(basePackages = "com.mall.wms.mapper")
public class WmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class,args);
    }

}
