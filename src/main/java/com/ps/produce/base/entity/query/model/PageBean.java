package com.ps.produce.base.entity.query.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.common.collect.Maps;
import com.ps.produce.base.entity.BaseEntity;
import com.ps.produce.system.entity.User;


/**
 * 分页参数
 * @author 黄燕针
 */
public class PageBean<E> extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2962815267157436206L;
	/**
	 * 本页的数据列表
	 */
	private List<E> content = new ArrayList<E>();
	/**
	 * 当前页码
	 */
	private int pageNo=1;
	/**
	 * 每页显示多少条数据
	 */
	private int pageSize = 10;
	/**
	 * 总记录数
	 */
	private int totalCount;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 页码列表的开始索引（从1开始）
	 */
	@JsonIgnore
	private int beginPageIndex;

	/**
	 * 页码列表的结束索引
	 */
	@JsonIgnore
	private int endPageIndex;
	/**
	 * 排序列名
	 */
	private String sort;
	/**
	 * 排序方式   desc asc
	 */
	private String order;

	/**
	 *  sql
	 * @return
	 */
	private Map<String, String> sqlMap = Maps.newHashMap();
	
	private User user;
	
	public PageBean() {
		
	}
	
	public PageBean(int iDisplayStart,int iDisplayLength,String sortCol,String sortType,Long userId) {
		Integer pageNumber = (iDisplayStart / iDisplayLength) + 1;
		this.pageNo = pageNumber;
		this.pageSize = iDisplayLength;
		this.sort = sortCol;
		this.order = sortType;
		User user = new User();
		user.setId(userId);
		this.user = user;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	
	/**
	 * 获取mysql数据库开始索引(从0开始)
	 */
	@JsonIgnore
	public int getBeginIndex() {

		return (pageNo-1)*pageSize;
	}

	public List<E> getContent() {
		return content;
	}

	public void setContent(List<E> content) {
		this.content = content;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public Map<String, String> getSqlMap() {
		return sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}

	public void setTotalCount(Integer totalCount) {
		if(totalCount==null)
			totalCount=0;
		totalPage = (totalCount + pageSize - 1) / pageSize;
		
		totalPage = (totalCount + pageSize - 1) / pageSize;
		if (totalPage <= 20) {// 总页数不多于20页，则全部显示
			beginPageIndex = 1;
			endPageIndex = totalPage;
		} else {
			// 当前页附近的共20个页码（前9个 + 当前页 + 后10个）
			beginPageIndex = pageNo - 9;
			endPageIndex = pageNo + 10;
			// 当前面的页码不足9个时，则显示前20个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 20;
			}
			// 当后面的页码不足10个时，则显示后20个页码
			if (endPageIndex > totalPage) {
				endPageIndex = totalPage;
				beginPageIndex = totalPage - 20 + 1;
			}
		}
		
		if(pageNo <= 0){
			pageNo = 1;
		}else if(pageNo >= totalPage && totalPage!=0){
			pageNo = totalPage;
		}
		this.totalCount = totalCount;
	}
}
