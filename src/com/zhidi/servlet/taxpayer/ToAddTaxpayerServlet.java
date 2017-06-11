package com.zhidi.servlet.taxpayer;

import com.zhidi.dao.impl.IndustryDaoImpl;
import com.zhidi.dao.impl.TaxOrganDaoImpl;
import com.zhidi.dao.impl.TaxerDaoImpl;
import com.zhidi.entity.Industry;
import com.zhidi.entity.TaxOrgan;
import com.zhidi.entity.Taxer;

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
@WebServlet("/taxpayer/toAddTaxpayer.do")
public class ToAddTaxpayerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        IndustryDaoImpl industryDao = new IndustryDaoImpl();
        TaxOrganDaoImpl taxOrganDao = new TaxOrganDaoImpl();
        TaxerDaoImpl taxerDao = new TaxerDaoImpl();
        //查询所有行业代码
        List<Industry> industryList = industryDao.getAll();
        //查询所有税务机关
        List<TaxOrgan> taxOrganList = taxOrganDao.getAll();
        //办税专员
        List<Taxer> taxerList = taxerDao.getAll();
        req.setAttribute("industryList", industryList);
        req.setAttribute("taxOrganList", taxOrganList);
        req.setAttribute("taxerList", taxerList);
        req.getRequestDispatcher("/manage/taxpayer/addTaxpayer.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
