package com.so.system.service;

import java.util.List;

import com.so.system.bean.Role;
import com.so.utils.Page;

/**
 * 角色DAO接口
 * @author DEMOCXY-S0
 * @version V1.0
 */
public interface RoleService {
	
	//添加
	public int add(Role role);
	//删除
	public int delete(String id);
	//修改
	public int update(Role role);
	//查询分页
	public Page<Role> page(Role role,Page<Role> page);
	//根据ID查询
	public Role getById(String id);
	//查询所有
	public List<Role> findAll(Role role);
		
}