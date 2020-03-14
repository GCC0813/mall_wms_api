
package com.mall.wms.vo;

import com.mall.wms.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class HomeInfoOut {

    private List<Management> managementList;

    private Integer userId;

    private String userName;


    @Data
    private class Management{

        private Integer id;

        private String name;

        private List<Management> managements;

        public Management() {
        }

        public Management(Management management,List<Management> managements) {
            this.id = management.id;
            this.name = management.name;
            this.managements = managements;
        }
    }

    public HomeInfoOut() {
    }
    public HomeInfoOut(UserEntity entity) {
        this.userId = entity.getId();
        this.userName=entity.getNick();
    }
}