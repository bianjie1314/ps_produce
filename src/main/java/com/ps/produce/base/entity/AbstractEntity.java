package com.ps.produce.base.entity;

import java.util.Date;

public class AbstractEntity extends BaseEntity{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date updateDate;
	private Date createDate;
	private String remarks;
	public void preInsert(){
		
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public AbstractEntity(Long id) {
		this.id = id;
	}
	
	public AbstractEntity() {
	}
	

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
			return false;
		}

		AbstractEntity that = (AbstractEntity) obj;

		return this.id.equals(that.getId());
	}

	
	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}
}
