package org.example.ioc.factory;

import org.example.ioc.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

/**
 * @Classname ProxyFactory
 * @Description 代理类为了管理此次service调用相关得操作在一个连接一个事务里面
 * @Date 2021-04-03 20:55
 * @Created by Klein
 */
public class ProxyFactory  {

    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Object getJdkProxy(Object object) {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(),

                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result=null;

                        try {
                            transactionManager.beginTransaction();
                            result=method.invoke(object,args);
                            transactionManager.commit();
                        } catch (Exception throwables) {
                            throwables.printStackTrace();
                            transactionManager.rollback();
                            throw  throwables.getCause();
                        }

                        return result;
                    }
                });
    }
}
