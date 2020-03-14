package com.mall.wms.vo;

import com.mall.wms.entity.UserLoginEntity;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoOut {

    private String userName;

    private String sex;

    private Integer age;

    private String mobile;

    private String email;

    private String jurisdiction;

    private String registerTime;

    private List<UserLoginEntity> loginRecord;

}
