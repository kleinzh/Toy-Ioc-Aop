package org.example.ioc;

import org.example.ioc.dao.AccountDao;
import org.example.ioc.utils.ConnectionUtils;
import org.example.ioc.utils.SpringConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(SpringConfig.class);

        AccountDao accountDao= (AccountDao) applicationContext.getBean("jdbcAccountDaoImpl");

        System.out.println(accountDao);

        AccountDao accountDao1= (AccountDao) applicationContext.getBean("jdbcAccountDaoImpl");

        System.out.println(accountDao1);

        applicationContext.close();
    }

}
