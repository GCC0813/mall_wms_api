package com.mall.wms.service;

import com.github.pagehelper.PageHelper;
import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.exceptionhandler.BizException;
import com.mall.wms.entity.UserEntity;
import com.mall.wms.mapper.UserMapper;
import com.mall.wms.vo.EnAndDisIn;
import com.mall.wms.vo.UserListIn;
import com.mall.wms.vo.UserListOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mall.wms.comm.CodeMsg.*;

@Service
public class UserManagementService {

    @Autowired
    private UserMapper userMapper;


    public UserListOut getUserList(UserListIn in){
        PageHelper.startPage(in.getPage(),in.getPageSize());
        List<UserEntity> users = userMapper.selectAllByCondition(in);
        List<UserListOut.UserOut> outs = new ArrayList<>();
        if(!CollectionUtils.isEmpty(users)){
            users.forEach(u->{
                outs.add(new UserListOut.UserOut(u));
            });
        }
        return new UserListOut(outs.size(),outs);
    }

    public CodeMsg enAndDis(EnAndDisIn in){
        Integer userId = in.getUserId();
        UserEntity userEntity = userMapper.selectByPrimaryKey(userId);
        if(Objects.isNull(userEntity)){
            throw new BizException(CODE_201);
        }
        int row = userMapper.updateStatusById(in.getType(),in.getUserId());
        if(row < 1){
            throw new BizException(CODE_208);
        }
        return CODE_200;
    }
}
