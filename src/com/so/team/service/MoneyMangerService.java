package com.so.team.service;

import java.util.List;
import com.so.team.bean.MoneyManger;

import com.so.utils.Page;

/**
 * 财务管理DAO接口
 * @author so
 * @version V1.0
 */
public interface MoneyMangerService {
	
	//添加
	public int add(MoneyManger moneyManger);
	//删除
	public int delete(String id);
	//修改
	public int update(MoneyManger moneyManger);
	//查询分页
	public Page<MoneyManger> page(MoneyManger moneyManger,Page<MoneyManger> page);
	//根据ID查询
	public MoneyManger getById(String id);
	//查询所有
	public List<MoneyManger> findAll(MoneyManger moneyManger);
		
}