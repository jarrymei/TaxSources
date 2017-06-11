package com.zhidi.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zhidi.dao.BaseDao;
import com.zhidi.entity.Taxer;
import com.zhidi.util.DBUtil;

/**
 * 税务人员信息dao
 * @author 梅佳杰
 *
 */
public class TaxerDaoImpl extends BaseDao<Taxer>{

	@Override
	public List<Taxer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Taxer getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Taxer t) {
		int count = DBUtil.add(t, "tb_taxer");
		if (count >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean edit(Taxer t) {
		// TODO Auto-generated method stub
		return false;
	}

}
