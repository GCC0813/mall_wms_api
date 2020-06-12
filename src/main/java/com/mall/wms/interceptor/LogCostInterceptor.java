package com.mall.wms.interceptor;

import com.mall.wms.comm.CodeMsg;
import com.mall.wms.comm.exceptionhandler.BizException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GCC
 * create on 2020/6/11 11:27
 */
public class LogCostInterceptor implements HandlerInterceptor {
    long start = System.currentTimeMillis()/1000;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(Integer.parseInt(request.getHeader("a"))==1){
            throw new BizException(CodeMsg.CODE_304);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor cost="+(System.currentTimeMillis()/1000-start));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


    public static void main(String[] args) {
      int[] a = {1,3,99,4,6,6,7,10,9};
      int b = 0;
      int c= 0;
      for (int i=0;i<a.length;i++){
         if(a[i]>b){
             c=b;
             b=a[i];
         }else if(a[i]>c){
             c=a[i];
         }
      }
        System.out.println(b);
        System.out.println(c);
    }
}
