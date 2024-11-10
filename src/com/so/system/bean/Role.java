package com.so.system.bean;

import java.util.Date;
/**
 * 角色Entity
 * @author DEMOCXY-S0
 * @version V1.0
 */
public class Role {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String roleName;		// 角色名称
	private String roleFlag;		// 角色标志
	private String introduce;		// 备注
	
	public Role() {
		super();
	}

	public Role(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleFlag() {
		return roleFlag;
	}

	public void setRoleFlag(String roleFlag) {
		this.roleFlag = roleFlag;
	}
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
}