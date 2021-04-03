package org.example.ioc.service;

/**
 * @author Klein
 * @Classname TransferService
 * @Description TODO
 * @Date 2021-04-03 12:19
 * @Created by Klein
 */
public interface TransferService {

    void transfer(String fromCardNo,String toCardNo,int money) throws Exception;
}
