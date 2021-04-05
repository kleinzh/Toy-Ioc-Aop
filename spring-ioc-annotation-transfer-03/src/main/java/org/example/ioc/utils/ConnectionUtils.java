package org.example.ioc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Classname ConnectionUtils
 * @Description TODO
 * @Date 2021-04-03 20:49
 * @Created by Klein
 */
@Component("connectionUtils")
public class ConnectionUtils {

    @Autowired
    private DataSource dataSource;
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
            connection=dataSource.getConnection();
            threadLocal.set(connection);
        }
        return  connection;
    }
}
