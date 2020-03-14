package com.mall.wms.vo;

import com.mall.wms.entity.UserEntity;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
public class UserListOut {

    private Integer userCount;

    private List<UserOut> userout;


    @Data
    public static class UserOut {
        private Integer id;

        private String nick;

        private String headIcon;

        private String sex;

        private String mobile;

        private String email;

        private String level;

        private String address;

        private Integer status;


        public UserOut() {
        }


        public UserOut(UserEntity u) {
            this.id = u.getId();
            this.nick = u.getNick();
            this.headIcon = u.getHeadIcon();
            this.sex = u.getSex() == 0 ? "男" : u.getSex() == 1 ? "女" : "未知";
            this.mobile = u.getMobile();
            this.email = u.getMail();
            this.level = String.format("V%s", u.getLevel());
            this.address = (StringUtils.isNotBlank(u.getProvince()) ? u.getProvince() : "") +
                    (StringUtils.isNotBlank(u.getCity()) ? u.getCity() : "") + (StringUtils.isNotBlank(u.getDistrict()) ? u.getDistrict() : "");
            this.status = u.getStatus();
        }
    }

    public UserListOut() {
    }

    public UserListOut(Integer userCount, List<UserOut> userout) {
        this.userCount = userCount;
        this.userout = userout;
    }
}
