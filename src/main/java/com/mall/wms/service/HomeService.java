package com.mall.wms.service;

import com.mall.wms.entity.ManagementListEntity;
import com.mall.wms.entity.UserEntity;
import com.mall.wms.mapper.ManagementListMapper;
import com.mall.wms.vo.HomeInfoOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GCC
 * create on 2020/3/12 22:17
 */
@Service
public class HomeService {

    @Autowired
    private CommService commService;

    @Autowired
    private ManagementListMapper managementListMapper;

    public HomeInfoOut getHomeInfo(){
        List<ManagementListEntity> managementList = managementListMapper.selectAll();
        /*Map<Integer,ManagementListEntity> map =managementList.stream().collect
                (Collectors.toMap(ManagementListEntity::getLastId, Function.identity()));*/
        /*MultiValueMap<>*/

        managementList.forEach(m->{
            managementList.forEach(a->{
                if(m.getId().equals(a.getLastId())){

                }
            });
        });




        UserEntity user =(UserEntity) commService.isLogin();
        return new HomeInfoOut(user);
    }

}
