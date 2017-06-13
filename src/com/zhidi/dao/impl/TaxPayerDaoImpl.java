package com.zhidi.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.zhidi.dao.BaseDao;
import com.zhidi.entity.TaxPayer;
import com.zhidi.util.DBUtil;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 纳税人信息dao
 * @author DELL
 *
 */
public class TaxPayerDaoImpl extends BaseDao<TaxPayer>{

	@Override
	public List<TaxPayer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaxPayer getById(Integer id) {
		List<Map<String, String>> list = DBUtil.query("select * from tb_tax_payer where id=?", id);
		TaxPayer taxPayer = null;
		if (list != null && list.size() == 1) {
			taxPayer = new TaxPayer();
			try {
				BeanUtils.populate(taxPayer, list.get(0));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return taxPayer;
	}

	@Override
	public boolean add(TaxPayer t) {
		int count = DBUtil.add(t, "tb_tax_payer");
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		int count = DBUtil.update("delete from tb_tax_payer where id=?", id);
		if (count == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean edit(TaxPayer t) {
		int count = DBUtil.edit(t, "tb_tax_payer", t.getId());
		if (count == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 根据id查询关联结果
	 * @param id
	 * @return
	 */
	public Map<String, String> getMapById(Integer id, String payerCode) {
		String sql = "SELECT ttp.*, tto.organName, ti.industryName,tu.username  FROM tb_tax_payer ttp \n" +
				"LEFT JOIN tb_tax_organ tto ON ttp.taxOrganId=tto.id \n" +
				"LEFT JOIN tb_industry ti ON ttp.industryId=ti.id\n" +
				"LEFT JOIN tb_user tu ON ttp.userId=tu.id\n" +
				"WHERE 1=1";

		if (id != null) {
			sql += " and ttp.id='"+ id +"'";
		}
		if (payerCode != null) {
			sql += " and ttp.payerCode='"+ payerCode +"'";
		}
		List<Map<String, String>> list = DBUtil.query(sql);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}
