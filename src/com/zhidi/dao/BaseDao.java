package com.zhidi.dao;

import java.util.List;

/**
 * daoͨ�ýӿ�
 * @author ÷�ѽ�
 *
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * ��ѯ����
	 * @return
	 */
	public List<T> getAll();
	
	/**
	 * ����id��ѯ
	 * @param id
	 * @return
	 */
	public T getById(Integer id);
	
	/**
	 * ���
	 * @param t
	 * @return
	 */
	public boolean add(T t);
	
	/**
	 * ����idɾ��
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
	/**
	 * �޸�
	 * @param t
	 * @return
	 */
	public boolean edit(T t);


	
}
