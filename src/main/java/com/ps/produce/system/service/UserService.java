package com.ps.produce.system.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.ps.produce.base.entity.BaseEntity;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.base.service.BaseService;
import com.ps.produce.exception.BusinessException;
import com.ps.produce.shiro.ShiroUser;
import com.ps.produce.support.CipherUtil;
import com.ps.produce.support.Digests;
import com.ps.produce.support.Encodes;
import com.ps.produce.support.ISecurityUtils;
import com.ps.produce.support.Password;
import com.ps.produce.system.dao.RoleDao;
import com.ps.produce.system.dao.UserDao;
import com.ps.produce.system.entity.Office;
import com.ps.produce.system.entity.User;

@Service
public class UserService extends BaseService<UserDao, User> {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	public User findByLoginName(String username) {
		User user = userDao.findByLoginName(username);
		if (user != null) {
			user.setRoleList(roleDao.findUserRoleList(user.getId()));
		}
		return user;
	}

	public PageBean<User> findUsers(PageBean<User> userPageBean) {
		User user = userDao.get(ISecurityUtils.getCurrUserId());
		if (user != null) {
			user.setRoleList(roleDao.findUserRoleList(user.getId()));
		}
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		userPageBean.getSqlMap().put("dsf", BaseService.dataScopeFilter(ISecurityUtils.getCurrUser(), "o", "a"));
		List<User> users = userDao.findByPage(userPageBean);
		userPageBean.setContent(users);
		return userPageBean;
	}

	public List<User> findUsers() {
		PageBean<User> userPageBean = new PageBean<User>();
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		userPageBean.getSqlMap().put("dsf", BaseService.dataScopeFilter(ISecurityUtils.getCurrUser(), "o", "a"));
		List<User> users = userDao.findByPage(userPageBean);
		return users;
	}

	public List<User> findUsersAll() {
		PageBean<User> userPageBean = new PageBean<User>();
		List<User> users = userDao.findByPage(userPageBean);
		return users;
	}

	public void saveUser(User user) {
		User old = userDao.findByLoginName(user.getUsername());
		if (old == null) {
			user.preInsert();
			userDao.insert(user);
		} else {
			user.setId(old.getId());
			user.setOsUserId(old.getOsUserId());
			user.setOsUsername(old.getOsUsername());
		}

	}

	

	/**
	 * 修改用户
	 * 
	 * @param user
	 */
	public void updateUser(User user) {
		Date now = new Date();
		user.setUpdateDate(now);
		if (user.getId() != null) {
			userDao.update(user);
		}

		// 删除角色关联
		userDao.deleteUserRole(user);
		// 重新赋权限
		if (CollectionUtils.isNotEmpty(user.getRoleList())) {
			userDao.insertUserRole(user);
		}
	}

	/**
	 * 批量删除用户
	 * 
	 * @param ids
	 */
	public void deleteBatch(String ids) {
		userDao.deleteBatch(Arrays.asList(ids.split(",")), BaseEntity.DEL_FLAG_DELETE);
	}

	/**
	 * 通过用户名模糊查询获取用户
	 * 
	 * @param username
	 * @return
	 */
	public List<User> getUserAll(String username) {
		return userDao.getUserAll(username);
	}

	public void add(User user) {
		user.preInsert();
		this.entryptPassword(user);
		this.userDao.insert(user);

	}

	public void changePassword(Long id, Password password) {
		User oldUser = this.userDao.getById(id);
		byte[] salt = Encodes.decodeHex(oldUser.getSalt());
		byte[] hashPassword = Digests.sha1(password.getOldPassword().getBytes(), salt, HASH_INTERATIONS);
		String oldPassword = Encodes.encodeHex(hashPassword);
		if (!oldPassword.equals(oldUser.getPassword())) {
			throw new BusinessException("旧密码不正确");
		}
		oldUser.setPassword(password.getNewPassword());
		entryptPassword(oldUser);
		userDao.updatePassword(oldUser);

	}
	
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(8);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}
}
