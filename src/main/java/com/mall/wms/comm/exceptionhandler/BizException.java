package com.mall.wms.comm.exceptionhandler;

import com.mall.wms.comm.CodeMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author haonan
 * 业务异常定义
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException {

    private Integer code;
    private String msg;
    private Object data;

    public BizException(CodeMsg c){
        this.code = c.getCode();
        this.msg = c.getMsg();
        this.data=new HashMap<>(1);
    }

    public static BizException bizException;

    public static BizException bizException(CodeMsg c){
        if(Objects.isNull(bizException)){
            bizException = new BizException(c);
        }
        return bizException;
    }
}
