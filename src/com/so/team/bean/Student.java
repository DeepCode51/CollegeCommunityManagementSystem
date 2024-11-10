package com.so.team.bean;

import java.util.Date;
/**
 * 学生Entity
 * @author admin
 * @version 2018-03-26
 */
public class Student {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String loginName;		// 登录名称
	private String loginPassword;		// 登录密码
	private Date regitTime;		// 注册时间
	private String auditType;		// 审核状态
	private String stuName;		// 学生姓名
	private String sex;		// 性别
	private String schoolRoom;		// 学院
	private String classRoom;		// 班级
	private String phone;		// 联系电话
	private String pic;		// 头像
	private String remark;		// 备注
	
	public Student() {
		super();
	}

	public Student(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	
	public Date getRegitTime() {
		return regitTime;
	}

	public void setRegitTime(Date regitTime) {
		this.regitTime = regitTime;
	}
	
	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	
	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getSchoolRoom() {
		return schoolRoom;
	}

	public void setSchoolRoom(String schoolRoom) {
		this.schoolRoom = schoolRoom;
	}
	
	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}