package com.mall.wms.controller;

import com.mall.wms.entity.GoodsEntity;
import com.mall.wms.service.CategoryTagService;
import com.mall.wms.vo.JsonOut;
import com.mall.wms.vo.ModifyCategoryOrTagStatusIn;
import com.qiniu.util.Json;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mall.wms.vo.JsonOut.ok;

/**
 * @author GCC
 * create on 2020/3/26 14:31
 */
@RestController
@RequestMapping("/category-tag")
@Log4j2
public class CategoryTagController {

    @Autowired
    private CategoryTagService categoryTagService;

    @PostMapping("category-tag-list")
    public JsonOut categoryTagList(){
        return ok(categoryTagService.categoryTagList());
    }

    @PostMapping("modify-status")
    public JsonOut modifyCategoryOrTagStatus(@RequestBody @Validated ModifyCategoryOrTagStatusIn in){
        return ok(categoryTagService.modifyCategoryOrTagStatus(in));
    }

}
