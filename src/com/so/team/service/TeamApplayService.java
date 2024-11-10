package com.so.team.service;

import java.util.List;
import com.so.team.bean.TeamApplay;

import com.so.utils.Page;

/**
 * 社团申请DAO接口
 * @author admin
 * @version 2018-03-26
 */
public interface TeamApplayService {
	
	//添加
	public int add(TeamApplay teamApplay);
	//删除
	public int delete(String id);
	//修改
	public int update(TeamApplay teamApplay);
	//查询分页
	public Page<TeamApplay> page(TeamApplay teamApplay,Page<TeamApplay> page);
	//根据ID查询
	public TeamApplay getById(String id);
	//查询所有
	public List<TeamApplay> findAll(TeamApplay teamApplay);
		
}