package com.zhidi.servlet.taxer;

import com.zhidi.base.ResponseResult;
import com.zhidi.dao.impl.TaxerDaoImpl;
import com.zhidi.entity.Taxer;
import com.zhidi.entity.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import java.util.Map;

/**
 * Created by DELL on 2017/6/10.
 */
@WebServlet("/taxer/addTaxer.do")
public class AddTaxerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //获取表单的map集合
        ResponseResult result = new ResponseResult();
        Map<String, String[]> paramMap = req.getParameterMap();
        Taxer taxer = new Taxer();
        try {
            org.apache.commons.beanutils.BeanUtils.populate(taxer, paramMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        taxer.setRecordUserId(user.getId());
        TaxerDaoImpl taxerDao = new TaxerDaoImpl();
        taxerDao.add(taxer);
        result.setSuccess(true);
        result.setMsg("添加成功");
        JSONObject js = JSONObject.fromObject(result);

        PrintWriter writer = res.getWriter();
        writer.print(js.toString());
        writer.flush();
        writer.close();
    }
}
