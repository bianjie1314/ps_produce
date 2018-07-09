package com.ps.produce.support;

import java.io.Serializable;


public class FileRespon implements Serializable {
    
	private static final long serialVersionUID = -2578084556128376255L;
	private String id;
    private String name;
    private String thumbnailFilename;
    private String newFilename;
    private String contentType;
    private Long size;
    private Long thumbnailSize;
 
    private String url;
    
    private String thumbnailUrl;
    private String deleteUrl;
    private String deleteType;
    private Integer purpose;
    private String error;
    
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumbnailFilename() {
		return thumbnailFilename;
	}
	public void setThumbnailFilename(String thumbnailFilename) {
		this.thumbnailFilename = thumbnailFilename;
	}
	public String getNewFilename() {
		return newFilename;
	}
	public void setNewFilename(String newFilename) {
		this.newFilename = newFilename;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Long getThumbnailSize() {
		return thumbnailSize;
	}
	public void setThumbnailSize(Long thumbnailSize) {
		this.thumbnailSize = thumbnailSize;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public String getDeleteUrl() {
		return deleteUrl;
	}
	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}
	public String getDeleteType() {
		return deleteType;
	}
	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}
	public Integer getPurpose() {
		return purpose;
	}
	public void setPurpose(Integer purpose) {
		this.purpose = purpose;
	}
    
}
