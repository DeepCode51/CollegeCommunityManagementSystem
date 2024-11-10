package com.so.team.bean;

import java.util.Date;
/**
 * 活动申请Entity
 * @author admin
 * @version 2018-03-26
 */
public class ActiveStu {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String activeId;		// 活动编号
	private String stuId;		// 学生编号
	private String stuName;		// 学生姓名
	private String phone;		// 联系电话
	private String type;		// 报名状态
	private String isArrive;		// 是否正常出息
	
	private String activeName;
	
	private String sqlStr;
	
	public ActiveStu() {
		super();
	}

	public ActiveStu(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getActiveId() {
		return activeId;
	}

	public void setActiveId(String activeId) {
		this.activeId = activeId;
	}
	
	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	
	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getIsArrive() {
		return isArrive;
	}

	public void setIsArrive(String isArrive) {
		this.isArrive = isArrive;
	}

	/**
	 * @return the activeName
	 */
	public String getActiveName() {
		return activeName;
	}

	/**
	 * @param activeName the activeName to set
	 */
	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	
	
	
}