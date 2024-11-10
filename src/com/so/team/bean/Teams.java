package com.so.team.bean;

import java.util.Date;
/**
 * 社团Entity
 * @author admin
 * @version 2018-03-26
 */
public class Teams {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String teamName;		// 社团名字
	private String pic;		// 社团宣传图
	private String teamIntro;		// 社团介绍
	private Date buildTime;		// 成立时间
	private String buildStu;		// 创建人（社长）
	private String auditType;		// 审核状态
	private Integer members;		// 成员数
	private String remark;		// 备注
	
	private String studentName;
	
	public Teams() {
		super();
	}

	public Teams(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	public String getTeamIntro() {
		return teamIntro;
	}

	public void setTeamIntro(String teamIntro) {
		this.teamIntro = teamIntro;
	}
	
	public Date getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}
	
	public String getBuildStu() {
		return buildStu;
	}

	public void setBuildStu(String buildStu) {
		this.buildStu = buildStu;
	}
	
	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	
	public Integer getMembers() {
		return members;
	}

	public void setMembers(Integer members) {
		this.members = members;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
}