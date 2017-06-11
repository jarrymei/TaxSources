package com.zhidi.dao;

import com.zhidi.util.BeanUtils;
import com.zhidi.util.DBUtil;
import org.apache.commons.lang.StringUtils;

import java.util.*;

import static com.zhidi.util.DBUtil.query;

/**
 * dao通用接口
 * @author 梅佳杰
 *
 * @param <T>
 */
public abstract class BaseDao<T> {

	/**
	 * 查询所有
	 * @return
	 */
	public abstract List<T> getAll();
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public abstract T getById(Integer id);
	
	/**
	 * 添加
	 * @param t
	 * @return
	 */
	public abstract boolean add(T t);
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	public abstract boolean delete(Integer id);
	
	/**
	 * 修改
	 * @param t
	 * @return
	 */
	public abstract boolean edit(T t);

	/**
	 * 分页条件查询
	 * @param pageNumber 当前页码
	 * @param pageSize 一页容量
	 * @param params 查询条件参数
	 * @return 结果集合
	 */
	public List<T> getSearchResult(Class<T> cls, String tableName ,int pageNumber, int pageSize, Map<String, String> params) {
		StringBuilder sql = new StringBuilder("select * from "+ tableName +" where 1=1");
		if (params != null && !params.isEmpty()) {
			Set<String> set = params.keySet();
			for (Iterator<String> it = set.iterator(); it.hasNext();) {
				//获取字段名
				String key = it.next();
				//获取值
				String value = params.get(key);
				if (value != null && StringUtils.isNotEmpty(value.trim()))
					sql.append(" and "+ key +"='"+value.trim()+"'");
			}
		}
		sql.append(" limit ?,?");
		//调用工具类查询
		List<T> resultList = new ArrayList<T>();
		List<Map<String, String>> list = query(sql.toString(), (pageNumber-1)*pageSize, pageSize);
		T t = null;

		if (list != null && !list.isEmpty()) {
			for (Map<String, String> map : list) {
				try {
					t = cls.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				BeanUtils.parse(map, t);
				resultList.add(t);
			}
		}
		return resultList;
	}

	/**
	 * 获取数据总条数
	 * @param tableName
	 * @return
	 */
	public int getCount(String tableName) {
		int count = 0;
		String sql = "select count(1) c from "+ tableName +"";
		List<Map<String, String>> list = DBUtil.query(sql);
		if (list != null && !list.isEmpty()) {
			count = Integer.parseInt(list.get(0).get("c"));
		}
		return count;
	};
	
}
