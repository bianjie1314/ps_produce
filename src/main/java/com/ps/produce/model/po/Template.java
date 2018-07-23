package com.ps.produce.model.po;

import java.util.Date;

public class Template {
	
    private String id;

    private String productType;

    private String sku;

    private String productName;

    private Date createTime=new Date();

    private String tempImgs;

   


	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTempImgs() {
        return tempImgs;
    }

    public void setTempImgs(String tempImgs) {
        this.tempImgs = tempImgs == null ? null : tempImgs.trim();
    }
}