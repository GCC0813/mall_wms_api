package com.mall.wms.test;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author GCC
 * create on 2020/5/7 16:11
 */
public class ProxyTest {

    public static void main(String[] args) throws Exception {
        UserManagerImpl userManagerImpl = new UserManagerImpl();
        Object o = getProXy(userManagerImpl);
        System.out.println(o.getClass().getName());
        System.out.println(o instanceof UserManager);
        System.out.println(o.getClass().getClassLoader());


       /* userManagerImpl.addUser("ksaldk","sadsad");
        UserManager userManager = (UserManager) getProXy(userManagerImpl);
        userManager.addUser("哈哈哈","12321");*/
    }




    private static Object getProXy(final Object target) throws Exception{
        Class proxyClazz  = Proxy.getProxyClass(target.getClass().getClassLoader(),target.getClass().getInterfaces());
        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
        Object proxy = constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName()+"方法开始执行。。。");
                Object result = method.invoke(target,args);
                System.out.println(result);
                System.out.println(method.getName()+"方法执行结束。。。");
                return result;
            }
        });
        return proxy;
    }

}
