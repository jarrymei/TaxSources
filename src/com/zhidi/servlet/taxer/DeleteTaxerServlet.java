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
 * ɾ��taxer��servlet
 * Created by ÷�ѽ� on 2017/6/11.
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

        //��ѯtb_tax_source�Ƿ��ж�Ӧ���Ӽ�¼������ж�Ӧ���Ӽ�¼�Ͳ���ɾ�����ͷ���һ����ʾ��Ϣ���˰�˰רԱ�ж�Ӧ�����񣬲���ɾ��
        boolean isHave = taxerDao.isHaveTaxOrgan(id);

        if (!isHave) {
            //����ɾ���ķ���
            boolean state = taxerDao.delete(id);
            //������Ӧ��Ϣ
            if (state) {
                result.setSuccess(true);
                result.setMsg("ɾ���ɹ�");
            } else {
                result.setSuccess(false);
                result.setMsg("ɾ��ʧ��");
            }
        } else {
            result.setSuccess(false);
            result.setMsg("���ڶ�Ӧ�Ӽ�¼������ɾ��");
        }

        JSONObject js = JSONObject.fromObject(result);
        PrintWriter writer = res.getWriter();
        writer.print(js.toString());
        writer.flush();
        writer.close();
    }
}
