package com.zhidi.servlet.taxer;

import com.zhidi.dao.impl.TaxOrganDaoImpl;
import com.zhidi.dao.impl.TaxerDaoImpl;
import com.zhidi.entity.TaxOrgan;
import com.zhidi.entity.Taxer;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by DELL on 2017/6/11.
 */
@WebServlet("/taxer/taxerInfo.do")
public class TaxerInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");

        //��ȡ��˰רԱid
        Integer id = StringUtils.isEmpty(req.getParameter("id")) ? null : Integer.parseInt(req.getParameter("id"));
        String state = req.getParameter("state");
        TaxerDaoImpl taxerDao = new TaxerDaoImpl();
        //��ѯ��˰רԱ
        Taxer taxer = taxerDao.getById(id);
        //��ѯ˰�����
        TaxOrganDaoImpl taxOrganDao = new TaxOrganDaoImpl();
        List<TaxOrgan> taxOrganList = taxOrganDao.getAll();

        req.setAttribute("taxer", taxer);
        req.setAttribute("state", state);
        req.setAttribute("taxOrganList", taxOrganList);
        //ת�����鿴��˰רԱҳ��
        req.getRequestDispatcher("/manage/taxer/taxerInfo.jsp").forward(req, res);
    }
}
