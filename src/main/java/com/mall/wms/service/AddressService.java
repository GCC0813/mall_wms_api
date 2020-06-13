package com.mall.wms.service;

import com.mall.wms.vo.AddressOut;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GCC
 * create on 2020/4/24 15:16
 */
@Service("AddressService")
public class AddressService {

    public List<AddressOut> provinceList(){
        return new ArrayList<>();
    }
}
