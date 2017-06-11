package com.zhidi.servlet.taxer;

import com.zhidi.dao.impl.TaxOrganDaoImpl;
import com.zhidi.entity.TaxOrgan;

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
@WebServlet("/taxer/toAddTaxer.do")
public class ToAddTaxerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        TaxOrganDaoImpl taxOrganDao = new TaxOrganDaoImpl();
        List<TaxOrgan> list = taxOrganDao.getAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/manage/taxer/addTaxer.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
