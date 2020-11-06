package com.mall.wms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.exceptionhandler.BizException;
import com.mall.wms.entity.GoodsEntity;
import com.mall.wms.entity.LogisticsCompanyEntity;
import com.mall.wms.mapper.LogisticsCompanyMapper;
import com.mall.wms.service.AddressService;
import com.mall.wms.vo.JsonOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mall.wms.vo.JsonOut.ok;

/**
 * @author haonan
 * create on 2020/4/24 15:16
 */
@RestController
@RequestMapping("addr")
public class AddressController {

    @Resource(name = "AddressService")
    AddressService addressService;

    @PostMapping("province-list")
    public JsonOut provinceList(){
        return ok(addressService.provinceList());
    }



}
