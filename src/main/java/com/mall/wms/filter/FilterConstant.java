package com.mall.wms.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.wms.vo.JsonOut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Component
public class FilterConstant {

    private static String rootPath;

    @Value("${server.servlet.context-path}")
    public void setRootPath(String rootPath) {
        FilterConstant.rootPath = rootPath;
    }


    public static boolean isSpecialAPI(String url) {
        // 只允许内部服务器访问的接口
        String[] innerURL = {"/user/login","/upload/getQiniuToken",
                "/order/list","/user/isLogin","/category-tag/abc","/category-tag/category-tag-list"};
        for (String s : innerURL) {
            if(url.startsWith(rootPath  + s)) {
                return true;
            }
        }
        return false;
    }

    public static void outBusinessErr(HttpServletResponse resp, JsonOut out, ObjectMapper mapper) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);// 200
        resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        resp.getWriter().write(mapper.writeValueAsString(out));
    }
}
