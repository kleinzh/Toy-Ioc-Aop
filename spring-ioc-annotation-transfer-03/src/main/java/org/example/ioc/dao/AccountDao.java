package org.example.ioc.dao;

import org.example.ioc.pojo.Account;

/**
 * @Classname AccountDao
 * @Description TODO
 * @Date 2021-04-03 12:19
 * @Created by Klein
 */
public interface AccountDao {

    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccoutByCardNo(Account account) throws Exception;
}
