package com.so.team.service;

import java.util.List;
import com.so.team.bean.ActiveStu;

import com.so.utils.Page;

/**
 * 活动申请DAO接口
 * @author admin
 * @version 2018-03-26
 */
public interface ActiveStuService {
	
	//添加
	public int add(ActiveStu activeStu);
	//删除
	public int delete(String id);
	//修改
	public int update(ActiveStu activeStu);
	//查询分页
	public Page<ActiveStu> page(ActiveStu activeStu,Page<ActiveStu> page);
	//根据ID查询
	public ActiveStu getById(String id);
	//查询所有
	public List<ActiveStu> findAll(ActiveStu activeStu);
		
}