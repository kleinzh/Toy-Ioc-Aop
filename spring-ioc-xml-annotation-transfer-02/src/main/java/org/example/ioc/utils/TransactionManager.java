package org.example.ioc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * @author Klein
 * @Classname TransactionManager
 * @Description 事务管理器类 事务管理器类：负责手动事务的开启、提交、回滚
 * @Date 2021-04-03 20:53
 * @Created by Klein
 */
@Component("transactionManager")
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

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
