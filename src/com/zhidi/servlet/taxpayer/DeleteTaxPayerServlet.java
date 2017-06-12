package com.zhidi.servlet.taxpayer;

import com.zhidi.base.ResponseResult;
import com.zhidi.dao.impl.TaxPayerDaoImpl;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by DELL on 2017/6/12.
 */
@WebServlet("/taxpayer/deleteTaxPayer.do")
public class DeleteTaxPayerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        ResponseResult result = new ResponseResult();
        Integer id = StringUtils.isEmpty(req.getParameter("id")) ? null :Integer.parseInt(req.getParameter("id"));
        TaxPayerDaoImpl taxPayerDao = new TaxPayerDaoImpl();
        boolean state = taxPayerDao.delete(id);
        if (state) {
            result.setSuccess(true);
            result.setMsg("É¾³ý³É¹¦");
        } else {
            result.setSuccess(false);
            result.setMsg("É¾³ýÊ§°Ü");
        }

        JSONObject js = JSONObject.fromObject(result);

        PrintWriter writer = res.getWriter();
        writer.print(js.toString());
        writer.flush();
        writer.close();
    }
}
