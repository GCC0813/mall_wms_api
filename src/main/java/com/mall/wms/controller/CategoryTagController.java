package com.mall.wms.controller;

import com.mall.wms.service.CategoryTagService;
import com.mall.wms.vo.JsonOut;
import com.mall.wms.vo.ModifyCategoryOrTagStatusIn;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    @Qualifier("oneRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @PostMapping("category-tag-list")
    public JsonOut categoryTagList(){
        return ok(categoryTagService.categoryTagList());
    }

    @PostMapping("modify-status")
    public JsonOut modifyCategoryOrTagStatus(@RequestBody @Validated ModifyCategoryOrTagStatusIn in){
        return ok(categoryTagService.modifyCategoryOrTagStatus(in));
    }

    @GetMapping("abc")
    public void abc(String a){
        rabbitTemplate.convertAndSend("qqq",a);
    }

}
