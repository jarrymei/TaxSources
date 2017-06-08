package com.zhidi.servlet.system;

import com.zhidi.base.ResponseResult;
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
 * Created by lx on 2017/6/8.
 */
@WebServlet("/validatePassword.do")
public class ValidatePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=utf-8");
        ResponseResult result = new ResponseResult(false, "密码错误");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String validatePwd = EncryptUtil.encryptMD5(password + user.getSalt());
        if (validatePwd.equals(user.getPassword())) {
            result.setSuccess(true);
            result.setMsg("验证成功");
        }
        JSONObject js = JSONObject.fromObject(result);
        PrintWriter writer = res.getWriter();
        writer.print(js.toString());
        writer.flush();
        ;
        writer.close();
    }
}
