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
 * Created by lx on 2017/6/8.
 */
@WebServlet("/editPassword.do")
public class EditPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        ResponseResult result = new ResponseResult(false, "修改失败");
        String password = req.getParameter("newPassword");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        password = EncryptUtil.encryptMD5(password + user.getSalt());
        user.setPassword(password);
        UserDaoImpl userDao = new UserDaoImpl();
        boolean state = userDao.edit(user);
        if (state) {
            result.setSuccess(true);
            result.setMsg("修改成功");
        }
        JSONObject js = JSONObject.fromObject(result);
        PrintWriter writer = resp.getWriter();
        writer.print(js.toString());
        writer.flush();
        writer.close();
    }
}
