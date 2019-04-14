
package com.ps.produce.system.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.ps.produce.base.entity.AbstractEntity;
import com.ps.produce.support.utils.StringUtils;

/**
 * 用户Entity
 */
public class User extends AbstractEntity {

	private Office office;	// 归属部门
	private String username;// 登录名
	private String password;// 密码
	private String email;	// 邮箱
	private String phone;	// 电话
	private String userType;// 用户类型
	private String loginFlag;	// 是否允许登陆
    private String name;
	private long clazzId;
	private String salt;
	
	private Role role;	// 根据角色查询用户条件
	
	
	private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表

	public User() {
		super();
	}
	
	public User(Role role){
		super();
		this.role = role;
	}
    
	public long getClazzId() {
		return clazzId;
	}

	public void setClazzId(long clazzId) {
		this.clazzId = clazzId;
	}

	

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	@JsonIgnore
	public List<Long> getRoleIdList() {
		List<Long> roleIdList = Lists.newArrayList();
		for (Role role : roleList) {
			roleIdList.add(role.getId());
		}
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		roleList = Lists.newArrayList();
		for (Long roleId : roleIdList) {
			Role role = new Role();
			role.setId(roleId);
			roleList.add(role);
		}
	}
	
	/**
	 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
	 */
	public String getRoleNames() {
		List<String> list = Lists.newArrayList();
		for (Role r : roleList) {
			list.add(r.getName());
		}
		return StringUtils.join(list,",");
	}

	@Override
	public String toString() {
		return username;
	}

	public boolean isAdmin() {
		return super.getId()==1;
	}
}