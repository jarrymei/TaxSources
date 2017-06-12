package com.zhidi.servlet.taxsource;

import com.zhidi.dao.impl.TaxPayerDaoImpl;
import com.zhidi.entity.TaxPayer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DELL on 2017/6/13.
 */
@WebServlet("/taxsource/toAddTask.do")
public class ToAddTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        TaxPayerDaoImpl taxPayerDao = new TaxPayerDaoImpl();
        TaxPayer taxPayer = taxPayerDao.getById(id);

        req.setAttribute("taxPayer", taxPayer);
        req.getRequestDispatcher("/manage/taxsource/addTask.jsp").forward(req, res);
    }
}
