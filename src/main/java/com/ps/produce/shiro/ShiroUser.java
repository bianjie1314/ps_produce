package com.ps.produce.shiro;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.ps.produce.system.entity.Office;
import com.ps.produce.system.entity.Role;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class ShiroUser implements Serializable {
	private static final long serialVersionUID = -7925207867724622649L;
	private Long id;
	private String username;
	private String name;
	@JsonIgnore
	private String salt;
	private int type;
	private List<Role> roles;
	private List<String> permissions;
	private Office office;
	
	  public ShiroUser(Long id,  String username,String name) {
	        this.id = id;
	        this.username = username;
	        this.name=name;
	    }
	  
	  public ShiroUser(Long id,  String username) {
	        this.id = id;
	        this.username = username;
	        
	    }

	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	


	


	


	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 重载hashCode,只计算username;
	 */
	@Override
	public int hashCode() {
		return username.hashCode();
	}

	/**
	 * 重载equals,只计算username;
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ShiroUser other = (ShiroUser) obj;
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}
	
	public boolean isAdmin() {
		return this.getId()==1;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	
}