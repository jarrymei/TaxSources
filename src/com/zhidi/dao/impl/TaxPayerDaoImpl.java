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
		int count = DBUtil.add(t, "tb_tax_payer");
		if (count == 1) {
			return true;
		}
		return false;
	}
}
