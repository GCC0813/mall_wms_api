package com.mall.wms.service;

import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.exceptionhandler.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

import static com.mall.wms.comm.CodeMsg.CODE_212;
import static com.mall.wms.comm.CodeMsg.LANDING_FAILURE;

@Service
public class CommService {

    @Autowired
    HttpSession session;

    public Object getSessionAttr(String key){
        return session.getAttribute(key);
    }

    public Object isLogin(){
        Object o = session.getAttribute("user");
        if(Objects.isNull(o)){
            throw new BizException(LANDING_FAILURE);
        }
        return o;
    }
}
