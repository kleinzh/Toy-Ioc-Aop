package org.example.ioc.servlet;

import org.example.ioc.factory.BeanFactory;
import org.example.ioc.factory.ProxyFactory;
import org.example.ioc.pojo.Result;
import org.example.ioc.service.TransferService;
import org.example.ioc.service.impl.TransferServiceImpl;
import org.example.ioc.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname TransferServlet
 * @Description TODO
 * @Date 2021-04-03 12:23
 * @Created by Klein
 */
@WebServlet(name = "transferServlet", urlPatterns = "/transferServlet")
public class TransferServlet extends HttpServlet {

    /**
     * 1.实例化service对象
     */
//    private TransferService transferService = (TransferService) BeanFactory.getBean("transferService");
    private ProxyFactory proxyFactory = (ProxyFactory) BeanFactory.getBean("proxyFactory");
    private TransferService transferService = (TransferService) proxyFactory.getJdkProxy(BeanFactory.getBean("transferService"));
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求体的字符编码
        req.setCharacterEncoding("UTF-8");

        String fromCardNo = req.getParameter("fromCardNo");
        String toCardNo = req.getParameter("toCardNo");
        String moneyStr = req.getParameter("money");
        int money = Integer.parseInt(moneyStr);

        Result result = new Result();
        try {
            transferService.transfer(fromCardNo, toCardNo, money);
            result.setSuccess(true);
        } catch (Exception exception) {
            result.setSuccess(false);
            result.setMessage(exception.getMessage());
        }
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(JsonUtils.object2Json(result));
    }
}
