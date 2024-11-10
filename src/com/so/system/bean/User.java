package com.so.system.bean;

import java.util.Date;
/**
 * 系统用户Entity
 * @author DEMOCXY-S0
 * @version V1.0
 */
public class User {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;		// 用户名
	private String password;		// 密码
	private String pic;		// 头像
	private String role;		// 角色
	private String isBolck;		// 是否锁定
	
	public User() {
		super();
	}

	public User(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getIsBolck() {
		return isBolck;
	}

	public void setIsBolck(String isBolck) {
		this.isBolck = isBolck;
	}
	
	private String roleName;//角色名称

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	private Role role2;

	public Role getRole2() {
		return role2;
	}

	public void setRole2(Role role2) {
		this.role2 = role2;
	}
}