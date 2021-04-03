package org.example.ioc.service.impl;

import org.example.ioc.dao.AccountDao;
import org.example.ioc.dao.impl.JdbcAccountDaoImpl;
import org.example.ioc.factory.BeanFactory;
import org.example.ioc.pojo.Account;
import org.example.ioc.service.TransferService;
import org.example.ioc.utils.TransactionManager;

/**
 * @Classname TransferServiceImpl
 * @Description TODO
 * @Date 2021-04-03 12:27
 * @Created by Klein
 */
public class TransferServiceImpl implements TransferService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {

        Account fromCard = accountDao.queryAccountByCardNo(fromCardNo);
        Account toCard = accountDao.queryAccountByCardNo(toCardNo);
//        if (fromCard != null && fromCard.getMoney() >= money && toCard != null) {
            toCard.setMoney(toCard.getMoney() + money);
            fromCard.setMoney(fromCard.getMoney() - money);
            int effectFromRecords = accountDao.updateAccoutByCardNo(fromCard);
            int c = 1/0;
            int effectToRecords = accountDao.updateAccoutByCardNo(toCard);
//        } else {
//            throw new Exception("账户不存在或者余额不足");
//        }

    }
}
