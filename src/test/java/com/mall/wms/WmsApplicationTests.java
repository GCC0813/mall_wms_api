package com.mall.wms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;


@SpringBootTest
class WmsApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    public void test(){
        while (true) {
            System.out.println(dataSource.getClass());
        }
    }


}
