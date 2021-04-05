package org.example.ioc.service.impl;

import org.example.ioc.dao.AccountDao;
import org.example.ioc.pojo.Account;
import org.example.ioc.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @Classname TransferServiceImpl
 * @Description TODO
 * @Date 2021-04-03 12:27
 * @Created by Klein
 */
@Component("transferService")
public class TransferServiceImpl implements TransferService {

    /**
     * AutoWired按照类型注入
     * 如果按照类型无法唯一锁定对象，那么可以 @Qualifier
     */
    @Autowired
    @Qualifier("jdbcAccountDaoImpl")
    private AccountDao accountDao;

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {

        Account fromCard = accountDao.queryAccountByCardNo(fromCardNo);
        Account toCard = accountDao.queryAccountByCardNo(toCardNo);
//        if (fromCard != null && fromCard.getMoney() >= money && toCard != null) {
            toCard.setMoney(toCard.getMoney() + money);
            fromCard.setMoney(fromCard.getMoney() - money);
            int effectFromRecords = accountDao.updateAccoutByCardNo(fromCard);
            int effectToRecords = accountDao.updateAccoutByCardNo(toCard);
//        } else {
//            throw new Exception("账户不存在或者余额不足");
//        }

    }
}
