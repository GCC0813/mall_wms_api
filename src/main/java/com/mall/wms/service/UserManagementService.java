package com.mall.wms.service;

import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.exceptionhandler.BizException;
import com.mall.wms.entity.UserEntity;
import com.mall.wms.mapper.UserMapper;
import com.mall.wms.vo.EnAndDisIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.mall.wms.comm.CodeMsg.*;

@Service
public class UserManagementService {

    @Autowired
    private UserMapper userMapper;


    public List<?> getUserList(){
        return null;
    }

    public CodeMsg enAndDis(EnAndDisIn in){
        Integer userId = in.getUserId();
        UserEntity userEntity = userMapper.selectByPrimaryKey(userId);
        if(Objects.isNull(userEntity)){
            throw new BizException(CODE_201);
        }
        userEntity.setStatus(in.getType());
        int row = userMapper.updateByPrimaryKeySelective(userEntity);
        if(row < 1){
            throw new BizException(CODE_208);
        }
        return CODE_200;
    }
}
