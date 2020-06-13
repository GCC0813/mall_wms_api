package com.mall.wms.controller;

import com.mall.wms.service.HomeService;
import com.mall.wms.vo.JsonOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.mall.wms.vo.JsonOut.ok;

/**
 * @author haonan
 * create on 2020/3/12 22:14
 */
@RestController
@RequestMapping("home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @PostMapping("get-home-info")
    public JsonOut getHomeInfo(){
        return ok(homeService.getHomeInfo());
    }

    @PostMapping("my-desktop-info")
    public JsonOut myDesktopInfo(){
        return ok(Collections.singletonMap("rows",
                homeService.myDesktopInfo()));
    }

    @PostMapping("get-statistics")
    public JsonOut getStatistics(){
        return ok(homeService.getStatistics());
    }
}
