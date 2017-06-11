package com.zhidi.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhidi.dao.BaseDao;
import com.zhidi.entity.Industry;
import com.zhidi.util.DBUtil;
import org.apache.commons.beanutils.BeanUtils;

/**
 * ÐÐÒµ´úÂëdao
 * @author Ã·¼Ñ½Ü
 *
 */
public class IndustryDaoImpl extends BaseDao<Industry>{

	@Override
	public List<Industry> getAll() {
		List<Map<String, String>> list = DBUtil.query("select * from tb_industry");
		List<Industry> industrys = new ArrayList<Industry>();
		if (list != null && !list.isEmpty()) {
			for (Map<String, String> map : list) {
				Industry industry = new Industry();
				try {
					BeanUtils.populate(industry, map);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				industrys.add(industry);
			}
		}
		return industrys;
	}

	@Override
	public Industry getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Industry t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean edit(Industry t) {
		// TODO Auto-generated method stub
		return false;
	}

}
