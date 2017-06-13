package com.zhidi.servlet.taxsource;

import com.zhidi.dao.impl.TaxPayerDaoImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by lx on 2017/6/13.
 */
@WebServlet("/taxsource/ajaxTaxPayer.do")
public class AjaxLoadTaxPayerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");

        String payerCode = req.getParameter("payerCode");
        TaxPayerDaoImpl taxPayerDao = new TaxPayerDaoImpl();
        Map<String, String> result = taxPayerDao.getMapById(null, payerCode);

        JSONObject js = JSONObject.fromObject(result);

        PrintWriter writer = res.getWriter();
        writer.print(js.toString());
        writer.flush();
        writer.close();
    }
}
