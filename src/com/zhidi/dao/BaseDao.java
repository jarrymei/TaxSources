package com.zhidi.dao;

import com.zhidi.util.BeanUtils;
import com.zhidi.util.DBUtil;
import org.apache.commons.lang.StringUtils;

import java.util.*;

import static com.zhidi.util.DBUtil.query;

/**
 * daoͨ�ýӿ�
 * @author ÷�ѽ�
 *
 * @param <T>
 */
public abstract class BaseDao<T> {

	/**
	 * ��ѯ����
	 * @return
	 */
	public abstract List<T> getAll();
	
	/**
	 * ����id��ѯ
	 * @param id
	 * @return
	 */
	public abstract T getById(Integer id);
	
	/**
	 * ���
	 * @param t
	 * @return
	 */
	public abstract boolean add(T t);
	
	/**
	 * ����idɾ��
	 * @param id
	 * @return
	 */
	public abstract boolean delete(Integer id);
	
	/**
	 * �޸�
	 * @param t
	 * @return
	 */
	public abstract boolean edit(T t);

	/**
	 * ��ҳ������ѯ
	 * @param pageNumber ��ǰҳ��
	 * @param pageSize һҳ����
	 * @param params ��ѯ��������
	 * @return �������
	 */
	public List<T> getSearchResult(Class<T> cls, String tableName ,int pageNumber, int pageSize, Map<String, String> params) {
		StringBuilder sql = new StringBuilder("select * from "+ tableName +" where 1=1");
		if (params != null && !params.isEmpty()) {
			Set<String> set = params.keySet();
			for (Iterator<String> it = set.iterator(); it.hasNext();) {
				//��ȡ�ֶ���
				String key = it.next();
				//��ȡֵ
				String value = params.get(key);
				if (value != null && StringUtils.isNotEmpty(value.trim()))
					sql.append(" and "+ key +"='"+value.trim()+"'");
			}
		}
		sql.append(" limit ?,?");
		//���ù������ѯ
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
	 * ��ȡ����������
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
