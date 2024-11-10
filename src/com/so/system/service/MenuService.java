package com.so.system.service;

import java.util.List;

import com.so.system.bean.Menu;
import com.so.utils.Page;

/**
 * 菜单DAO接口
  * @author DEMOCXY-S0
 * @version V1.0
 */
public interface MenuService {
	
	//添加
	public int add(Menu menu);
	//删除
	public int delete(String id);
	//修改
	public int update(Menu menu);
	//查询分页
	public Page<Menu> page(Menu menu,Page<Menu> page);
	//根据ID查询
	public Menu getById(String id);
	//查询所有
	public List<Menu> findAll(Menu menu);
		
}