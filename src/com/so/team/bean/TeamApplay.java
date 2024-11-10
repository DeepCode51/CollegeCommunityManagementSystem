package com.so.team.bean;

import java.util.Date;
/**
 * 社团申请Entity
 * @author admin
 * @version 2018-03-26
 */
public class TeamApplay {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String applayTeam;		// 申请社团编号
	private String applayStu;		// 申请学生
	private String applayContent;		// 申请理由
	private Date applayTime;		// 申请时间
	private String applayType;		// 申请状态
	
	private String applayStuName;
	private String applayTeamName;
	private String sqlStr;
	
	public TeamApplay() {
		super();
	}

	public TeamApplay(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getApplayTeam() {
		return applayTeam;
	}

	public void setApplayTeam(String applayTeam) {
		this.applayTeam = applayTeam;
	}
	
	public String getApplayStu() {
		return applayStu;
	}

	public void setApplayStu(String applayStu) {
		this.applayStu = applayStu;
	}
	
	public String getApplayContent() {
		return applayContent;
	}

	public void setApplayContent(String applayContent) {
		this.applayContent = applayContent;
	}
	
	public Date getApplayTime() {
		return applayTime;
	}

	public void setApplayTime(Date applayTime) {
		this.applayTime = applayTime;
	}
	
	public String getApplayType() {
		return applayType;
	}

	public void setApplayType(String applayType) {
		this.applayType = applayType;
	}

	/**
	 * @return the applayStuName
	 */
	public String getApplayStuName() {
		return applayStuName;
	}

	/**
	 * @param applayStuName the applayStuName to set
	 */
	public void setApplayStuName(String applayStuName) {
		this.applayStuName = applayStuName;
	}

	/**
	 * @return the applayTeamName
	 */
	public String getApplayTeamName() {
		return applayTeamName;
	}

	/**
	 * @param applayTeamName the applayTeamName to set
	 */
	public void setApplayTeamName(String applayTeamName) {
		this.applayTeamName = applayTeamName;
	}

	/**
	 * @return the sqlStr
	 */
	public String getSqlStr() {
		return sqlStr;
	}

	/**
	 * @param sqlStr the sqlStr to set
	 */
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	
}