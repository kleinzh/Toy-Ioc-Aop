package org.example.ioc;

import org.example.ioc.service.TransferService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname AopTest
 * @Description TODO
 * @Date 2021-04-11 20:19
 * @Created by Klein
 */
public class AopTest {

    @Test
    public void testXmlAop() throws Exception {
        /**
         * 通过读取classpath下的xml文件来启动容器
         */
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        TransferService transferService=(TransferService) applicationContext.getBean(TransferService.class);

        transferService.transfer("6029621011001","6029621011000",1000);
    }
}