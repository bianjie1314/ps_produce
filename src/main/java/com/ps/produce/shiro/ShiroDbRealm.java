
package com.ps.produce.shiro;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.google.common.collect.Lists;
import com.ps.produce.support.Encodes;
import com.ps.produce.support.ISecurityUtils;
import com.ps.produce.system.entity.Menu;
import com.ps.produce.system.entity.Office;
import com.ps.produce.system.entity.Role;
import com.ps.produce.system.entity.User;
import com.ps.produce.system.service.UserService;

public class ShiroDbRealm extends AuthorizingRealm {

	protected UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.findByLoginName(token.getUsername());
		if (user != null) {
			byte[] salt = Encodes.decodeHex(user.getSalt());
			return new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getUsername()),user.getPassword(), ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		User user = userService.findByLoginName(shiroUser.getUsername());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		if (user != null) {
			List<Menu> list = ISecurityUtils.getMenuList();
			List<String> permissions = Lists.newArrayList(); 
			for (Menu menu : list){
				if (StringUtils.isNotBlank(menu.getPermission())){
					// 添加基于Permission的权限信息
					for (String permission : StringUtils.split(menu.getPermission(),",")){
						info.addStringPermission(permission);
						permissions.add(permission);
					}
				}
			}
			// 添加用户权限
//			info.addStringPermission("user");
//			permissions.add("user");
			shiroUser.setPermissions(permissions);
			
			//用户角色
			for (Role role : user.getRoleList()) {
				info.addRole(role.getName());
			}
			shiroUser.setOffice(user.getOffice());
			shiroUser.setRoles(user.getRoleList());
			
		}
		return info;
	}

	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(userService.HASH_ALGORITHM);
		matcher.setHashIterations(userService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

	public void setAccountService(UserService userService) {
		this.userService = userService;
	}

	
}
