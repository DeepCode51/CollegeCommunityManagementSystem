package com.so.system.bean;

import java.io.Serializable;
/**
 * 菜单Entity
 * @author DEMOCXY-S0
 * @version V1.0
 */

public class Menu implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;		// 单菜名称
	private String href;		// 访问地址
	private String target;		// 目标
	private String isShow;		// 是否显示
	private String sort;		// 排序
	private String parentId;		// 父级ID
	private String parentIds;		// 级父ids
	private String remarks;
	
	public Menu() {
		super();
	}

	public Menu(String id){
		this.id = id;
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
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	private String isCheck;//是否选择

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
}