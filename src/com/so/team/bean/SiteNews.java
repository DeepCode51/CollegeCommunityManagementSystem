package com.so.team.bean;

import java.util.Date;
/**
 * 新闻活动Entity
 * @author admin
 * @version 2018-03-26
 */
public class SiteNews {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String newsType;		// 新闻状态
	private String title;		// 标题
	private String pic;		// 新闻主图
	private String shortContent;		// 简介
	private String content;		// 新闻内容
	private String createUser;		// 创建人
	private Date createTime;		// 创建时间
	private String isRun;		// 是否推荐
	private String belonTeam;		// 所属社团
	private String isAudit;		// 是否审核
	
	private Date acStartTime; 
	private Date acEndTime;
	private String isCanApply;
	
	private String teamName;
	private String sqlStr;
	
	public SiteNews() {
		super();
	}

	public SiteNews(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	public String getShortContent() {
		return shortContent;
	}

	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getIsRun() {
		return isRun;
	}

	public void setIsRun(String isRun) {
		this.isRun = isRun;
	}
	
	public String getBelonTeam() {
		return belonTeam;
	}

	public void setBelonTeam(String belonTeam) {
		this.belonTeam = belonTeam;
	}
	
	public String getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	

	public Date getAcStartTime() {
		return acStartTime;
	}

	public void setAcStartTime(Date acStartTime) {
		this.acStartTime = acStartTime;
	}

	public Date getAcEndTime() {
		return acEndTime;
	}

	public void setAcEndTime(Date acEndTime) {
		this.acEndTime = acEndTime;
	}

	public String getIsCanApply() {
		return isCanApply;
	}

	public void setIsCanApply(String isCanApply) {
		this.isCanApply = isCanApply;
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