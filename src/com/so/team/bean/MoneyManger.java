package com.so.team.bean;

import java.util.Date;
/**
 * 财务管理Entity
 * @author so
 * @version V1.0
 */
public class MoneyManger {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String actId;		// 活动编号
	private String type;		// 交易类型
	private Double money;		// 交易金额
	private String remark;		// 交易备注
	
	private String sqlStr;
	
	public MoneyManger() {
		super();
	}

	public MoneyManger(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	
}