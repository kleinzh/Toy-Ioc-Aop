package org.example.ioc.factory;

import org.example.ioc.utils.ConnectionUtils;

/**
 * @author Klein
 * @Classname CreateBeanFactory
 * @Description TODO
 * @Date 2021-04-04 22:27
 * @Created by Klein
 */
public class CreateBeanFactory {

    public static ConnectionUtils getInstanceStatic() {
        return new ConnectionUtils();
    }

    public  ConnectionUtils getInstance() {
        return new ConnectionUtils();
    }

}
