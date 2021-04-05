package org.example.ioc;

import org.example.ioc.dao.AccountDao;
import org.example.ioc.utils.ConnectionUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;

/**
 * @Classname IocTest
 * @Description TODO
 * @Date 2021-04-04 17:56
 * @Created by Klein
 */
public class IocTest {

    @Test
    public void testScopeIoc() {

        /**
         * 通过读取classpath下的xml文件来启动容器
         */
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        AccountDao accountDao= (AccountDao) applicationContext.getBean("accountDao");

        System.out.println(accountDao);

        AccountDao accountDao1= (AccountDao) applicationContext.getBean("accountDao");

        System.out.println(accountDao1);

        applicationContext.close();
    }

    @Test
    public void testCreateBeanIoc() {

        /**
         * 通过读取classpath下的xml文件来启动容器
         */
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        ConnectionUtils connectionUtils= (ConnectionUtils) applicationContext.getBean("connectionUtils");

        System.out.println(connectionUtils);


    }
}
