package com.zhidi.dao.impl;

import java.util.List;
import java.util.Map;

import com.zhidi.dao.BaseDao;
import com.zhidi.entity.TaxSource;
import com.zhidi.util.DBUtil;

/**
 * 税源基础信息dao
 * @author DELL
 *
 */
public class TaxSourceDaoImpl extends BaseDao<TaxSource>{

	@Override
	public List<TaxSource> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaxSource getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(TaxSource t) {
		int count = DBUtil.add(t, "tb_tax_source");
		return count == 1;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean edit(TaxSource t) {
		// TODO Auto-generated method stub
		return false;
	}

}
