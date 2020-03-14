package com.mall.wms.service;

import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.RedisOperation;
import com.mall.wms.comm.exceptionhandler.BizException;
import com.mall.wms.entity.UserEntity;
import com.mall.wms.entity.UserLoginEntity;
import com.mall.wms.mapper.UserLoginMapper;
import com.mall.wms.mapper.UserMapper;
import com.mall.wms.vo.LoginIn;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.mall.wms.comm.CodeMsg.*;
import static com.mall.wms.comm.GlobalVar.*;
import static com.mall.wms.util.MD5Util.encrypt;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    HttpSession httpSession;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private RedisOperation redisOperation;

    public CodeMsg login(LoginIn in){
        String userName = in.getUserName();
        Integer role = in.getRole();
        UserEntity userEntity = userMapper.selectAdminByUserNameAndRole(userName,role);
        if(role == 1){
            int type = 0;
            if(userName.contains("@")){
                if(!Pattern.compile(MAIL_REGULAR).matcher(userName).matches()){
                    throw new BizException(CODE_206);
                }
            }else {
                if(!Pattern.compile(PHONE_REGULAR).matcher(userName).matches()){
                    throw new BizException(CODE_207);
                }
                type = 1;
            }
           userEntity = userMapper.selectCustomerByUserNameAndRole(userName,role,type);
        }

        if(Objects.isNull(userEntity)){
            return CODE_201;
        }
        if(!encrypt(in.getPassword()).equals(userEntity.getPassword())){
            return CODE_205;
        }
        httpSession.setAttribute("user",userEntity);
        userLoginMapper.insertSelective(new UserLoginEntity(userEntity.getId(),in.getIp()));
        return CODE_200;
    }

    public CodeMsg loginOut(){
        httpSession.removeAttribute("user");
        return CODE_200;
    }

    public CodeMsg updateUserInfo(UserEntity in){
        int row = userMapper.updateByPrimaryKeySelective(in);
        if(row<1){
            return CODE_202;
        }
        return CODE_200;
    }

    public CodeMsg register(UserEntity in){
        int row = userMapper.insertSelective(in);
        if(row<1){
            return CODE_203;
        }
        return CODE_200;
    }

    public UserEntity getUserInfo(){
        UserEntity userEntity = (UserEntity) httpSession.getAttribute("user");
        if(Objects.isNull(userEntity)){
            throw new BizException(CODE_208);
        }
        return userEntity;
    }

}
