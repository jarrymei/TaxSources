package com.zhidi.dao;

import java.util.List;

/**
 * dao通用接口
 * @author 梅佳杰
 *
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * 查询所有
	 * @return
	 */
	public List<T> getAll();
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public T getById(Integer id);
	
	/**
	 * 添加
	 * @param t
	 * @return
	 */
	public boolean add(T t);
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
	/**
	 * 修改
	 * @param t
	 * @return
	 */
	public boolean edit(T t);


	
}
