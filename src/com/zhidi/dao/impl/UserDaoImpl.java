package com.zhidi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhidi.dao.BaseDao;
import com.zhidi.entity.User;
import com.zhidi.util.BeanUtils;
import com.zhidi.util.DBUtil;

/**
 * 用户dao
 * @author 梅佳杰
 *
 */
public class UserDaoImpl extends BaseDao<User> {

	@Override
	public List<User> getAll() {

		return null;
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean edit(User t) {
		int count = DBUtil.edit(t, "tb_user", t.getId());
		if (count == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	public User getByName(String username) {
		List<Map<String, String>> list = DBUtil.query("select * from tb_user where username=?", username);
		Map<String, String> map = new HashMap<String, String>();
		User user = null;
		if (list != null && list.size() == 1) {
			map = list.get(0);
			user = new User();
			BeanUtils.parse(map, user);
		}
		
		return user;
	}

}
