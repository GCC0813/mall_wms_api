package com.mall.wms.vo;

import com.mall.wms.entity.UserEntity;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.mall.wms.comm.GlobalVar.STATIC_RESOURCES_PREFIX;
import static com.mall.wms.util.DateUtil.date2Format;

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

        private String regTime;

        private Integer status;


        public UserOut() {
        }


        public UserOut(UserEntity u) {
            this.id = u.getId();
            this.nick = u.getNick();
            this.headIcon = String.format(STATIC_RESOURCES_PREFIX,u.getHeadIcon());
            this.sex = u.getSex() == 0 ? "男" : u.getSex() == 1 ? "女" : "未知";
            this.mobile = u.getMobile();
            this.email = u.getMail();
            this.level = String.format("V%s", u.getLevel());
            this.address = (StringUtils.isNotBlank(u.getProvince()) ? u.getProvince() : "") +
                    (StringUtils.isNotBlank(u.getCity()) ? u.getCity() : "") + (StringUtils.isNotBlank(u.getDistrict()) ? u.getDistrict() : "");
           this.regTime=date2Format(u.getCreateTime(),"yyyy年MM月dd日");
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
