package com.mall.wms.controller;

import com.qiniu.util.Auth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("upload")
public class UploadController {

private static final String ACCESS_KEY="p7KdGBmda3d7jpOlY_8-CLPKbpa1GUJ2Wt_azSq9";
private static final String SECRET_KEY="vmYjRWfMU5XJ6ze5FkwpxbB2PW35U3khfep-JHey";
private static final String BUCKET_NAME="haonan666";

    @GetMapping("getQiniuToken")
    public Map<String,String> getQiniuToken(){
        long deadLine =System.currentTimeMillis()/1000L+3600;
        Auth auth=Auth.create(ACCESS_KEY,SECRET_KEY);
        String token = auth.uploadTokenWithDeadline(BUCKET_NAME,null, deadLine,null,true);
        return new HashMap<String, String>(){{
            put("uptoken",token);
        }};
    }
}
