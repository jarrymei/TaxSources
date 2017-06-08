package com.zhidi.servlet.system;

import com.zhidi.base.ResponseResult;
import com.zhidi.dao.impl.UserDaoImpl;
import com.zhidi.entity.User;
import com.zhidi.util.EncryptUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录servlet
 * Created by 梅佳杰 on 2017/6/8.
 */
@WebServlet("/login.do")
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
        String rem = req.getParameter("rem");

        ResponseResult result = new ResponseResult(false, "用户名或密码错误");
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getByName(username);
        if (user != null) {
            String validatePassword = user.getPassword();
            String ss = EncryptUtil.encryptMD5(password + user.getSalt());
            if (validatePassword.equals(ss)) {
                //登录成功将用户设置到session中
                HttpSession session = req.getSession();
                session.setAttribute("user", user);

                result.setSuccess(true);
                result.setMsg("登录成功");
            }
        }
        JSONObject js = JSONObject.fromObject(result);
        PrintWriter writer = res.getWriter();
        writer.print(js);
        writer.flush();
        writer.close();
    }


}
