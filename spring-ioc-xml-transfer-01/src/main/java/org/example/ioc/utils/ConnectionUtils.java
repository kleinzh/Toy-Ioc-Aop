package org.example.ioc.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Classname ConnectionUtils
 * @Description TODO
 * @Date 2021-04-03 20:49
 * @Created by Klein
 */
public class ConnectionUtils {

    /**
     * 存储当前线程的连接
     */
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * 从当前线程获取连接
     * */
    public Connection getCurrentThreadConn() throws SQLException {

        Connection connection=threadLocal.get();

        if(connection==null) {
            connection=DruidUtils.getInstance().getConnection();
            threadLocal.set(connection);
        }
        return  connection;
    }
}
