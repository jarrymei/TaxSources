package com.zhidi.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhidi.dao.BaseDao;
import com.zhidi.entity.TaxOrgan;
import com.zhidi.util.DBUtil;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 税务机关dao
 * @author DELL
 *
 */
public class TaxOrganDaoImpl extends BaseDao<TaxOrgan>{

	@Override
	public List<TaxOrgan> getAll() {
		List<Map<String, String>> list = DBUtil.query("select * from tb_tax_organ");
		List<TaxOrgan> taxOrganList = new ArrayList<TaxOrgan>();
		if (list != null && !list.isEmpty()) {
			for (Map<String, String> map : list) {
				TaxOrgan taxOrgan = new TaxOrgan();
				try {
					BeanUtils.populate(taxOrgan, map);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				taxOrganList.add(taxOrgan);
			}
		}
		return taxOrganList;
	}

	@Override
	public TaxOrgan getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(TaxOrgan t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean edit(TaxOrgan t) {
		// TODO Auto-generated method stub
		return false;
	}
}
