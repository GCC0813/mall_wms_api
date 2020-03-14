package com.mall.wms.comm.exceptionhandler;

import lombok.Data;
import lombok.ToString;

/**
 * @author GCC
 * 异常model
 */

@ToString
@Data
public class UnifiedExceptionReturn {
    private Integer code;
    private String msg;
    private String status;

    public UnifiedExceptionReturn(Integer code, String msg, String status) {
        this.code = code;
        this.msg = msg;
        this.status = status;
    }
}


