package com.mall.wms.controller;

import com.mall.wms.vo.JsonOut;
import com.qiniu.util.Json;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mall.wms.vo.JsonOut.ok;

/**
 * @author GCC
 * create on 2020/3/26 14:31
 */
@RestController
@RequestMapping("/category")
@Log4j2
public class CategoryController {

    @PostMapping("list")
    public JsonOut categoryList(){
        return ok("");
    }


}
