package com.mall.wms.service;

import com.mall.wms.comm.CodeMsg;
import com.mall.wms.entity.UserEntity;
import com.mall.wms.entity.UserLoginEntity;
import com.mall.wms.mapper.UserLoginMapper;
import com.mall.wms.mapper.UserMapper;
import com.mall.wms.vo.GetUserInfoByIdIn;
import com.mall.wms.vo.IsHasUserIn;
import com.mall.wms.vo.LoginIn;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.mall.wms.comm.CodeMsg.*;
import static com.mall.wms.comm.GlobalVar.*;
import static com.mall.wms.util.MD5Util.encrypt;

import static com.mall.wms.comm.exceptionhandler.BizException.bizException;

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

    public CodeMsg login(LoginIn in) {
        String userName = in.getUserName();
        Integer role = in.getRole();
        int type = 0;
        if (role == 1) {
            if (userName.contains("@")) {
                if (!Pattern.compile(MAIL_REGULAR).matcher(userName).matches()) {
                    throw bizException(CODE_207);
                }
                type = 1;
            } else {
                if(!Pattern.compile(PHONE_REGULAR).matcher(userName).matches()){
                    throw bizException(CODE_206);
                }
                type = 2;
            }
        }
        UserEntity userEntity = userMapper.selectCustomerByUserNameAndRole(userName, role, type);
        if (Objects.isNull(userEntity)) {
            return CODE_201;
        }
        if (!encrypt(in.getPassword()).equals(userEntity.getPassword())) {
            return CODE_205;
        }
        httpSession.setAttribute("user", userEntity);
        userLoginMapper.insertSelective(new UserLoginEntity(userEntity.getId(), in.getIp()));
        return CODE_200;
    }

    public CodeMsg loginOut(){
        httpSession.removeAttribute("user");
        return CODE_200;
    }

    public CodeMsg updateUserInfo(UserEntity in){
        if(StringUtils.isNotBlank(in.getPassword())){
            in.setPassword(encrypt(in.getPassword()));
        }
        int row = userMapper.updateByPrimaryKeySelective(in);
        if(row<1){
            return CODE_202;
        }
        return CODE_200;
    }

    public CodeMsg addUser(UserEntity in){
        IsHasUserIn isHasUserIn = new IsHasUserIn();
        isHasUserIn.setMobile(in.getMobile());
        UserEntity entity = userMapper.selectUserByPhoneAndEmail(isHasUserIn);
        if(!Objects.isNull(entity)){
           throw bizException(CODE_306);
        }
        if(StringUtils.isNotBlank(in.getPassword())){
            in.setPassword(encrypt(in.getPassword()));
        }
        in.setHeadIcon(DEFAULT_HEAD);
        in.setRegTime(System.currentTimeMillis()/1000);
        int row = userMapper.insertSelective(in);
        if(row<1){
            return CODE_203;
        }
        return CODE_200;
    }

    public UserEntity getUserInfo(){
        UserEntity userEntity = (UserEntity) httpSession.getAttribute("user");
        if(Objects.isNull(userEntity)){
            throw bizException(CODE_208);
        }
        return userEntity;
    }

    public UserEntity getUserInfoById(GetUserInfoByIdIn in){
        UserEntity entity = userMapper.selectByPrimaryKey(in.getId());
        if(Objects.isNull(entity)){
            throw bizException(CODE_201);
        }
        entity.setHeadIcon(String.format(STATIC_RESOURCES_PREFIX, entity.getHeadIcon()));
        return entity;
    }

    public CodeMsg isHasUser(IsHasUserIn in){
        UserEntity entity = userMapper.selectUserByPhoneAndEmail(in);
        if(!Objects.isNull(entity)){
            if(in.getId()!=null && entity.getId().equals(in.getId())){
                if(in.getEmail()!=null&&in.getEmail().equals(entity.getMail())){
                    return CODE_200;
                }
                if (in.getMobile()!=null&&in.getMobile().equals(entity.getMobile())){
                    return CODE_200;
                }
            }else {
                throw bizException(CODE_306);
            }
        }
        return CODE_200;
    }

    public CodeMsg isLogin(){
        if(Objects.nonNull(httpSession.getAttribute("user"))){
            return CODE_200;
        }else{
            return LANDING_FAILURE;
        }
    }
}
