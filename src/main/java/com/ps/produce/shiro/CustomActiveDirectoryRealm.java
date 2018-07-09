package com.ps.produce.shiro;

import java.util.List;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.activedirectory.ActiveDirectoryRealm;
import org.apache.shiro.realm.ldap.LdapContextFactory;
import org.apache.shiro.realm.ldap.LdapUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.ps.produce.support.ISecurityUtils;
import com.ps.produce.system.entity.Menu;
import com.ps.produce.system.entity.Role;
import com.ps.produce.system.entity.User;
import com.ps.produce.system.service.UserService;



public class CustomActiveDirectoryRealm extends ActiveDirectoryRealm {
	protected UserService userService;	

	private static Logger log = LoggerFactory.getLogger(CustomActiveDirectoryRealm.class);

	  /**
	   * This is called during the log in process.
	   * Authenticate but also store the roles/groups on a custom principal
	   */
	 protected AuthenticationInfo queryForAuthenticationInfo(AuthenticationToken token, LdapContextFactory ldapContextFactory) throws NamingException {

	        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

	        // Binds using the username and password provided by the user.
	        LdapContext ctx = null;
	        try {
	            ctx = ldapContextFactory.getLdapContext(upToken.getUsername(), String.valueOf(upToken.getPassword()));
	        } finally {
	            LdapUtils.closeContext(ctx);
	        }

	        return buildAuthenticationInfo(upToken.getUsername(), upToken.getPassword());
	    }

	 
	    protected AuthenticationInfo buildAuthenticationInfo(String username, char[] password) {
	    	User user = new User();
	    	user.setUsername(username);
	    	userService.saveUser(user);
	 
	        return new SimpleAuthenticationInfo(new ShiroUser(user.getId(),user.getOsUserId(),user.getUsername(),user.getOsUsername()), password, getName());
	   
	    	
			
	    }


	    protected AuthorizationInfo buildAuthorizationInfo(Set<String> roleNames) {
	        return new SimpleAuthorizationInfo(roleNames);
	    }
	    
	    protected AuthorizationInfo queryForAuthorizationInfo(PrincipalCollection principals, LdapContextFactory ldapContextFactory) throws NamingException {

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
//				info.addStringPermission("users");
//				permissions.add("users");
				shiroUser.setPermissions(permissions);
				shiroUser.setOffice(user.getOffice());
				shiroUser.setRoles(user.getRoleList());

				//用户角色
				for (Role role : user.getRoleList()) {
					info.addRole(role.getName());
				}
			}
			return info;
	    }


		public void setUserService(UserService userService) {
			this.userService = userService;
		}

	}
