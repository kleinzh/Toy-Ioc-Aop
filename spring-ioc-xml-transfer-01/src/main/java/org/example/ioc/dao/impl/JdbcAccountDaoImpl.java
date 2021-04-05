package org.example.ioc.dao.impl;

import org.example.ioc.dao.AccountDao;
import org.example.ioc.pojo.Account;
import org.example.ioc.utils.ConnectionUtils;
import org.example.ioc.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @Classname JdbcAccountDaoImpl
 * @Description TODO
 * @Date 2021-04-03 12:22
 * @Created by Klein
 */
public class JdbcAccountDaoImpl implements AccountDao {

    private ConnectionUtils connectionUtils;

    private String name;

    private String[] myArray;

    private Map<String, String> myMap;

    private Set<String> mySet;

    private Properties myProperties;

    public void setMyArray(String[] myArray) {
        this.myArray = myArray;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyProperties(Properties myProperties) {
        this.myProperties = myProperties;
    }

    public JdbcAccountDaoImpl(ConnectionUtils connectionUtils, String name) {
        this.connectionUtils = connectionUtils;
        this.name = name;
    }

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void init() {
        System.out.println("当前实例被创建....");
    }

    public void destroyed() {
        System.out.println("当前实例被销毁....");
    }

    @Override
    public Account queryAccountByCardNo(String cardNo) throws Exception {
        //从连接池获取连接
//        Connection con = DruidUtils.getInstance().getConnection();
        Connection con =connectionUtils.getCurrentThreadConn();
        String sql="select * from account where cardNo=?";
        PreparedStatement preparedStatement=con.prepareStatement(sql);
        preparedStatement.setString(1,cardNo);
        ResultSet resultSet=preparedStatement.executeQuery();
        Account account=new Account();
        while (resultSet.next()) {
            account.setMoney(resultSet.getInt("money"));
            account.setCardNo(resultSet.getString("cardNo"));
            account.setName(resultSet.getString("name"));
        }
        resultSet.close();
        preparedStatement.close();
//        con.close();
        return account;
    }

    @Override
    public int updateAccoutByCardNo(Account account) throws Exception {
        //从连接池获取连接
//        Connection con = DruidUtils.getInstance().getConnection();
        Connection con =connectionUtils.getCurrentThreadConn();
        String sql="update account set money=? where cardNo=?";
        PreparedStatement preparedStatement=con.prepareStatement(sql);
        preparedStatement.setInt(1,account.getMoney());
        preparedStatement.setString(2,account.getCardNo());
        int effectRecords=0;
        effectRecords=  preparedStatement.executeUpdate();
        preparedStatement.close();
//        con.close();
        return effectRecords;
    }
}
