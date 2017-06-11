package com.zhidi.servlet.taxer;

import com.zhidi.dao.impl.TaxerDaoImpl;
import com.zhidi.entity.Taxer;
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
 * 办税专员servlet
 * Created by DELL on 2017/6/10.
 */
@WebServlet("/taxer/listTaxer.do")
public class ListTaxerServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        //获取请求参数
        Integer page = StringUtils.isEmpty(req.getParameter("page")) ? null : Integer.parseInt(req.getParameter("page"));
        Integer rows = StringUtils.isEmpty(req.getParameter("rows")) ? null : Integer.parseInt(req.getParameter("rows"));
        String taxerName = req.getParameter("taxerName");
        //查询参数
        Map<String, String> map = new HashMap<String, String>();
        map.put("taxerName", taxerName);
        //进行查询
        TaxerDaoImpl taxerDao = new TaxerDaoImpl();
        //查询数据总条数
        int total = taxerDao.getCount("tb_taxer");
        //查询结果
        List<Taxer> list =  taxerDao.getSearchResult(Taxer.class, "tb_taxer",page, rows, map);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("rows", list);
        resultMap.put("total", total);

        //将查询结果转化为json格式
        JSONObject js = JSONObject.fromObject(resultMap);
        PrintWriter writer = res.getWriter();
        writer.print(js.toString());
        writer.flush();
        writer.close();
    }
}
