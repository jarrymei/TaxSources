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
 * ��˰רԱservlet
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
        //��ȡ�������
        Integer page = StringUtils.isEmpty(req.getParameter("page")) ? null : Integer.parseInt(req.getParameter("page"));
        Integer rows = StringUtils.isEmpty(req.getParameter("rows")) ? null : Integer.parseInt(req.getParameter("rows"));
        String taxerName = req.getParameter("taxerName");
        //��ѯ����
        Map<String, String> map = new HashMap<String, String>();
        map.put("taxerName", taxerName);
        //���в�ѯ
        TaxerDaoImpl taxerDao = new TaxerDaoImpl();
        //��ѯ����������
        int total = taxerDao.getCount("tb_taxer");
        //��ѯ���
        List<Taxer> list =  taxerDao.getSearchResult(Taxer.class, "tb_taxer",page, rows, map);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("rows", list);
        resultMap.put("total", total);

        //����ѯ���ת��Ϊjson��ʽ
        JSONObject js = JSONObject.fromObject(resultMap);
        PrintWriter writer = res.getWriter();
        writer.print(js.toString());
        writer.flush();
        writer.close();
    }
}
