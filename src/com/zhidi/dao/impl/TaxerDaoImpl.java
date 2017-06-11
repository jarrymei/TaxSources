package com.zhidi.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhidi.dao.BaseDao;
import com.zhidi.entity.Taxer;
import com.zhidi.util.DBUtil;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 税务人员信息dao
 * @author 梅佳杰
 *
 */
public class TaxerDaoImpl extends BaseDao<Taxer>{

	@Override
	public List<Taxer> getAll() {
		List<Map<String, String>> list = DBUtil.query("select * from tb_taxer");
		List<Taxer> taxerList = new ArrayList<Taxer>();
		if (list != null & !list.isEmpty()) {
			for (Map<String, String> map : list) {
				Taxer taxer = new Taxer();
				try {
					BeanUtils.populate(taxer, map);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				taxerList.add(taxer);
			}
		}
		return taxerList;
	}

	@Override
	public Taxer getById(Integer id) {
		List<Map<String, String>> list = DBUtil.query("select * from tb_taxer where id=?", id);
		Taxer taxer = null;
		if (list != null && list.size() == 1) {
			taxer = new Taxer();
			Map<String, String> map = list.get(0);
			try {
				BeanUtils.populate(taxer, map);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return taxer;
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
		int count = DBUtil.update("delete from tb_taxer where id=?", id);
		if (count >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean edit(Taxer t) {
		int count = DBUtil.edit(t, "tb_taxer", t.getId());
		if (count >= 0) {
			return true;
		}
		return false;
	}

}
