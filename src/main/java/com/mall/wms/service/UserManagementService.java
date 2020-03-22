package com.mall.wms.service;

import com.github.pagehelper.PageHelper;
import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.exceptionhandler.BizException;
import com.mall.wms.entity.UserEntity;
import com.mall.wms.mapper.UserMapper;
import com.mall.wms.vo.UserOperationIn;
import com.mall.wms.vo.UserListIn;
import com.mall.wms.vo.UserListOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public CodeMsg userOperation(UserOperationIn in){
        Integer userId = in.getUserId();
        UserEntity userEntity = userMapper.selectByPrimaryKey(userId);
        if(Objects.isNull(userEntity)){
            throw new BizException(CODE_201);
        }
        Integer type = in.getType();
        int row = userMapper.updateStatusById(type,userId);
        if(row < 1){
            if(type==3){
                throw new BizException(CODE_303);
            }else if (type==1 || type==0){
                throw new BizException(CODE_208);
            }
        }
        return CODE_200;
    }
}
/*
6228272576201216174*/
