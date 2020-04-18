package com.mall.wms.vo;

import com.mall.wms.entity.GoodsCategoryEntity;
import com.mall.wms.entity.GoodsTagEntity;
import com.mall.wms.util.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Data
public class CategoryTagListOut {

    private List<CategoryTagOut> categoryTagList;

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class CategoryTagOut extends CategoryTag{

        private List<CategoryTag> tags;

        public CategoryTagOut() {
        }

        public CategoryTagOut(GoodsCategoryEntity g, MultiValueMap<Integer,CategoryTag> multiValueMap) {
            super(g);
            this.tags = multiValueMap.get(g.getId());
       }
    }

    @Data
    public static class CategoryTag{

        private Integer id;

        private String name;

        private String remark;

        private String creatTime;

        private Integer checkStatus;

        private String checkStatusStr;

        private Integer status;

        private String statusStr;

        private Integer isDelete;

        private String isDeleteStr;

        public CategoryTag() {
        }

        public CategoryTag(GoodsTagEntity g) {
            this.id = g.getId();
            this.name = g.getName();
            this.remark = g.getRemark();
            this.creatTime = DateUtil.date2Format(g.getCreateTime(),"yyyy年MM月dd日 HH:mm:ss");
            this.checkStatus = g.getCheckStatus().intValue();
            //0:未审核 1:审核通过
            this.checkStatusStr = checkStatus==0?"未审核":"已审核";
            //1:启用，0未启用
            this.status=g.getStatus();
            this.statusStr = status==1?"已启用":"未启用";
            this.isDelete = g.getDeleteFlag().intValue();
            //0:未删除 1:已删除
            this.isDeleteStr = isDelete==0?"未删除":"已删除";
        }
        public CategoryTag(GoodsCategoryEntity g) {
            this.id = g.getId();
            this.name = g.getName();
            this.remark = g.getRemark();
            this.creatTime = DateUtil.date2Format(g.getCreateTime(),"yyyy年MM月dd日 HH:mm:ss");
            this.checkStatus = g.getCheckStatus().intValue();
            //0:未审核 1:审核通过
            this.checkStatusStr = checkStatus==0?"未审核":"已审核";
            //1:启用，0未启用
            this.status=g.getStatus();
            this.statusStr = status==1?"已启用":"未启用";
            this.isDelete = g.getDeleteFlag().intValue();
            //0:未删除 1:已删除
            this.isDeleteStr = isDelete==0?"未删除":"已删除";
        }
    }

    public CategoryTagListOut() {
    }

    public CategoryTagListOut(List<CategoryTagOut> categoryTagList) {
        this.categoryTagList = categoryTagList;
    }
}
