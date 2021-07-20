package com.mall.wms.controller;

import com.mall.wms.comm.CodeMsg;
import com.mall.wms.vo.JsonOut;
import com.sun.org.apache.bcel.internal.classfile.Code;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/test")
public class TestController {

    @Qualifier("oneRabbitTemplate")
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("test")
    @ResponseBody
    public JsonOut<CodeMsg> sendMsg(@Param("msg")String msg){
        rabbitTemplate.convertAndSend("test1", msg);
        return JsonOut.ok(CodeMsg.CODE_200);
    }



    @RabbitListener(queues = "test1",containerFactory = "oneFactory")
    public void testConsumer(Message message){
        System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
    }


}
