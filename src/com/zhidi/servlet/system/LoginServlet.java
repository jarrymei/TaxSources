package com.zhidi.servlet.system;

import com.zhidi.base.ResponseResult;
import com.zhidi.dao.impl.UserDaoImpl;
import com.zhidi.entity.User;
import com.zhidi.util.EncryptUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ��¼servlet
 * Created by ÷�ѽ� on 2017/6/8.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        super.doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("rem");

        ResponseResult result = new ResponseResult(false, "�û������������");
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getByName(username);
        if (user != null) {
            String validatePassword = user.getPassword();
            String ss = EncryptUtil.encryptMD5(password + user.getSalt());
            if (validatePassword.equals(ss)) {
                //��ס���룬���������õ�cookie��
                if ("on".equals(remember)) {
                    Cookie cookie = new Cookie("username", username);
                    cookie.setMaxAge(60*60*24*7);
                    res.addCookie(cookie);
                }
                //��¼�ɹ����û����õ�session��
                HttpSession session = req.getSession();
                session.setAttribute("user", user);

                result.setSuccess(true);
                result.setMsg("��¼�ɹ�");
            }
        }
        JSONObject js = JSONObject.fromObject(result);
        PrintWriter writer = res.getWriter();
        writer.print(js);
        writer.flush();
        writer.close();
    }


}
