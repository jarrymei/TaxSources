package com.zhidi.servlet.system;

import com.google.code.kaptcha.Constants;
import com.zhidi.base.ResponseResult;
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
@WebServlet("/validateCaptcha")
public class ValidateCaptchaServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        String captcha = req.getParameter("captcha");
        ResponseResult result = new ResponseResult(false, "ÑéÖ¤Âë´íÎó");
        HttpSession session = req.getSession();
        String validateCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (validateCaptcha.equalsIgnoreCase(captcha)) {
            result.setSuccess(true);
            result.setMsg("");
        }
        JSONObject js = JSONObject.fromObject(result);
        PrintWriter writer = res.getWriter();
        writer.print(js.toString());
        writer.flush();
        writer.close();
    }
}
