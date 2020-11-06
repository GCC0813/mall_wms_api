package com.mall.wms;

import com.mall.wms.controller.AddressController;
import com.mall.wms.entity.LogisticsCompanyEntity;
import com.mall.wms.mapper.LogisticsCompanyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class WmsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    AddressController addressController;

    @Test
    void test() throws Exception{
        addressController.test();
    }

}
