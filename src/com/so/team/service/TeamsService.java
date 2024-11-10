package com.so.team.service;

import java.util.List;
import com.so.team.bean.Teams;

import com.so.utils.Page;

/**
 * 社团DAO接口
 * @author admin
 * @version 2018-03-26
 */
public interface TeamsService {
	
	//添加
	public int add(Teams teams);
	//删除
	public int delete(String id);
	//修改
	public int update(Teams teams);
	//查询分页
	public Page<Teams> page(Teams teams,Page<Teams> page);
	//根据ID查询
	public Teams getById(String id);
	//查询所有
	public List<Teams> findAll(Teams teams);
		
}