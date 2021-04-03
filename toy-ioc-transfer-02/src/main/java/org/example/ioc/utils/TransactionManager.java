package org.example.ioc.utils;

import java.sql.SQLException;

/**
 * @author Klein
 * @Classname TransactionManager
 * @Description 事务管理器类 事务管理器类：负责手动事务的开启、提交、回滚
 * @Date 2021-04-03 20:53
 * @Created by Klein
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    // 开启事务
    public void beginTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConn().setAutoCommit(false);
    }

    // 提交事务
    public void commit() throws SQLException {
        connectionUtils.getCurrentThreadConn().commit();
    }

    // 回滚事务
    public void rollback() throws SQLException {
        connectionUtils.getCurrentThreadConn().rollback();
    }
}
