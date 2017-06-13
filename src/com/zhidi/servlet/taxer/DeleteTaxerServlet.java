package com.zhidi.servlet.taxer;

import com.zhidi.base.ResponseResult;
import com.zhidi.dao.impl.TaxerDaoImpl;
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
 * 删除taxer的servlet
 * Created by 梅佳杰 on 2017/6/11.
 */
@WebServlet("/taxer/deleteTaxer.do")
public class DeleteTaxerServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        ResponseResult result = new ResponseResult();
        Integer id = StringUtils.isEmpty(req.getParameter("id")) ? null : Integer.parseInt(req.getParameter("id"));
        TaxerDaoImpl taxerDao = new TaxerDaoImpl();

        //查询tb_tax_source是否有对应的子记录，如果有对应的子记录就不能删除，就返回一个提示信息：此办税专员有对应的任务，不能删除
        boolean isHave = taxerDao.isHaveTaxOrgan(id);

        if (!isHave) {
            //调用删除的方法
            boolean state = taxerDao.delete(id);
            //设置响应信息
            if (state) {
                result.setSuccess(true);
                result.setMsg("删除成功");
            } else {
                result.setSuccess(false);
                result.setMsg("删除失败");
            }
        } else {
            result.setSuccess(false);
            result.setMsg("存在对应子记录，不能删除");
        }

        JSONObject js = JSONObject.fromObject(result);
        PrintWriter writer = res.getWriter();
        writer.print(js.toString());
        writer.flush();
        writer.close();
    }
}
