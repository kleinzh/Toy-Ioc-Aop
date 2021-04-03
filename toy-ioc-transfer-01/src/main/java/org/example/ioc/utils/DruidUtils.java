package org.example.ioc.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;

/**
 * @Classname DruidUtils
 * @Description TODO
 * @Date 2021-04-03 12:43
 * @Created by Klein
 */
public class DruidUtils {

    private DruidUtils() {}

    private static DruidDataSource druidDataSource=new DruidDataSource();

    static {
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/bank");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123qwe");
    }

    public static DruidDataSource getInstance() {
        return druidDataSource;
    }
}
