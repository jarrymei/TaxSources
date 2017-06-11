package com.zhidi.servlet.taxer;

import com.zhidi.base.ResponseResult;
import com.zhidi.dao.impl.TaxerDaoImpl;
import com.zhidi.entity.Taxer;
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
 * Created by DELL on 2017/6/11.
 */
@WebServlet("/taxer/editTaxer.do")
public class EditTaxerServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        ResponseResult result = new ResponseResult();

        Map<String, String[]> params = req.getParameterMap();
        Taxer taxer = new Taxer();
        try {
            BeanUtils.populate(taxer, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        TaxerDaoImpl taxerDao = new TaxerDaoImpl();
        boolean state = taxerDao.edit(taxer);
        if (state) {
            result.setSuccess(true);
            result.setMsg("修改成功");
        } else {
            result.setSuccess(false);
            result.setMsg("修改失败");
        }

        JSONObject js = JSONObject.fromObject(result);

        PrintWriter writer = res.getWriter();
        writer.print(js.toString());
        writer.flush();
        writer.close();
    }
}
