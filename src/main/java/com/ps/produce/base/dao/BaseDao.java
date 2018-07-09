package com.ps.produce.base.dao;

import java.util.List;

import com.ps.produce.base.entity.query.model.PageBean;
/**
 * 基础Dao接口
 * */
public interface BaseDao<T, Q extends PageBean<T>> {
	/**
	 * 根据主键值获取对象
	 * @param id
	 * */
	public T get(Long id);

	/**
	 * 查找是否存在
	 * @param queryModel 查询条件
	 * */
	public int isExist(Q queryModel);

	/** 
	 * 保存
	 * @param entity 保存对象
	 * @throws Exception
	 * */
	public int save(T entity) ;

	/** 
	 * 更新 
	 * @param entity 修改对象
	 * @throws Exception
	 * */
	public int update(T entity) ;

	/**
	 * 删除 
	 * @param entity 删除对象
	 * @throws Exception
	 * */
	public int delete(T entity) ;

	/**
	 * 分页查询
	 * @param queryModel 查询条件
	 *  */
	public List<T> findByPage(Q pageBean);

	/**
	 * 统计
	 * @param queryModel 查询条件
	 *  */
	public int count(Q queryModel);

	/**
	 * 查询
	 * @param queryModel 查询条件
	 *  */
	public List<T> query(Q queryModel);
}