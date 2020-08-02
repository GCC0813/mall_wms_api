package com.mall.wms.controller;

import com.mall.wms.service.AddressService;
import com.mall.wms.vo.JsonOut;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
