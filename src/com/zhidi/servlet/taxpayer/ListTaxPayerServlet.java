package com.zhidi.servlet.taxpayer;

import com.zhidi.base.ResponseResult;
import com.zhidi.dao.impl.TaxPayerDaoImpl;
import com.zhidi.entity.TaxPayer;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2017/6/9.
 */
@WebServlet("/taxpayer/listTaxpayer.do")
public class ListTaxPayerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Integer pageNumber = StringUtils.isEmpty(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page"));
        Integer pageSize = StringUtils.isEmpty(req.getParameter("rows")) ? 10 : Integer.parseInt(req.getParameter("rows"));
        String payerName = req.getParameter("payerName");
        String payerCode = req.getParameter("payerCode");

        Map<String, String> params = new HashMap<String, String>();
        params.put("payerName", payerName);
        params.put("payerCode", payerCode);

        TaxPayerDaoImpl taxPayerDao = new TaxPayerDaoImpl();
        //分页条件查询
        List<TaxPayer> list = taxPayerDao.getSearchResult(TaxPayer.class, "tb_tax_payer", pageNumber, pageSize, params);
        //查询总条数
        int total = taxPayerDao.getCount("tb_tax_payer");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("rows", list);

        JSONObject js = JSONObject.fromObject(result);

        PrintWriter writer = res.getWriter();
        writer.print(js.toString());
        writer.flush();
        writer.close();

    }
}
