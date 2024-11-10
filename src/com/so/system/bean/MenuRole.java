package com.so.system.bean;

/**
 * 权限Entity
 * @author DEMOCXY-S0
 * @version V1.0
 */
public class MenuRole {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String roleId;		// 角色编号
	private String menuId;		// 菜单编号
	
	public MenuRole() {
		super();
	}

	public MenuRole(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}