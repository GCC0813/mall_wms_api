package com.mall.wms.vo;


import com.mall.wms.comm.CodeMsg;
import lombok.Data;

import java.util.HashMap;
import java.util.Objects;

import static com.mall.wms.comm.CodeMsg.CODE_200;

/**
 * @author haonan
 * create on 2020/1/8 14:29
 */
@Data
public class JsonOut<T> {
    private int code;
    private String msg;
    private T data;

    public JsonOut(CodeMsg codeMsg){
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
        this.data = (T) new HashMap<>();
    }
    public JsonOut(T data){
        //String face = "｡◕‿◕｡";
        this.code = CODE_200.getCode();
        this.msg = CODE_200.getMsg();
        if(!Objects.isNull(data)){
            this.data = data;
        }
    }

    public static <T> JsonOut<T> ok(T data) {
        return new JsonOut<>(data);
    }
}
