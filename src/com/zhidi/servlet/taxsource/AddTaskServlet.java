package com.zhidi.servlet.taxsource;

import com.zhidi.base.ResponseResult;
import com.zhidi.dao.impl.TaxSourceDaoImpl;
import com.zhidi.entity.TaxSource;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by DELL on 2017/6/13.
 */
@WebServlet("/turce/aaxsoddTask.do")
public class AddTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        ResponseResult result = new ResponseResult();

        Map<String, String[]> params = req.getParameterMap();
        TaxSource taxSource = new TaxSource();
        try {
            BeanUtils.populate(taxSource, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        TaxSourceDaoImpl taxSourceDao = new TaxSourceDaoImpl();
        boolean state = taxSourceDao.add(taxSource);
        if (state) {
            result.setSuccess(true);
            result.setResult("添加成功");
        } else {
            result.setSuccess(false);
            result.setMsg("添加失败");
        }

        JSONObject js = JSONObject.fromObject(result);

        PrintWriter writer = res.getWriter();
        writer.print(js.toString());
        writer.flush();
        writer.close();

    }
}
