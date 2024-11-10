package com.so.team.bean;

import java.util.Date;
/**
 * 留言板Entity
 * @author admin
 * @version 2018-03-26
 */
public class Message {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String content;		// 留言内容
	private Date createTime;		// 留言时间
	private String createStu;		// 留言学生
	private String auditType;		// 审核状态
	private String replay;		// 回复内容
	private Date replayTime;		// 回复时间
	private String replayUser;		// 回复人
	
	public Message() {
		super();
	}

	public Message(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getCreateStu() {
		return createStu;
	}

	public void setCreateStu(String createStu) {
		this.createStu = createStu;
	}
	
	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	
	public String getReplay() {
		return replay;
	}

	public void setReplay(String replay) {
		this.replay = replay;
	}
	
	public Date getReplayTime() {
		return replayTime;
	}

	public void setReplayTime(Date replayTime) {
		this.replayTime = replayTime;
	}
	
	public String getReplayUser() {
		return replayUser;
	}

	public void setReplayUser(String replayUser) {
		this.replayUser = replayUser;
	}
	
}