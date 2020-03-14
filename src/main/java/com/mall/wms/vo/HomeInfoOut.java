
package com.mall.wms.vo;

import com.mall.wms.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class HomeInfoOut {

    private Integer userId;

    private String userName;

    public HomeInfoOut() {
    }
    public HomeInfoOut(UserEntity entity) {
        this.userId = entity.getId();
        this.userName=entity.getNick();
    }
}