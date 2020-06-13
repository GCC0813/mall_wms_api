package com.mall.wms.service;

import com.mall.wms.comm.CodeMsg;
import com.mall.wms.entity.UserEntity;
import com.mall.wms.mapper.UserMapper;
import com.mall.wms.vo.UserResiterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: haonan
 * @date:2020/3/15 14:36
 */
@Service
public class UserResgiterService {

    @Autowired
    private UserMapper userMapper;

    public CodeMsg resgiter(UserResiterIn in) {
        // 查找注册用户的手机号，邮箱是否存在
        List<UserEntity> userEntityList = userMapper.selectUserInfo(in.getMoblie(), in.getEmail());
        if (CollectionUtils.isEmpty(userEntityList)) {
            userMapper.insertUserInfo(in);
            return CodeMsg.CODE_200;
        } else {
            for (UserEntity userEntity : userEntityList) {
                if (userEntity.getMobile().equals(in.getMoblie())) {
                    return CodeMsg.CODE_301;
                }
                if (userEntity.getMail().equals(in.getEmail())) {
                    return CodeMsg.CODE_302;
                }
            }
        }
        return CodeMsg.CODE_200;
    }
}
