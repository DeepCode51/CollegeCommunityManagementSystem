package com.so.system.service.impl;


import java.sql.Connection;
import java.util.List;

import com.so.system.bean.Role;
import com.so.system.dao.RoleDao;
import com.so.system.service.RoleService;
import com.so.utils.DbUtil;
import com.so.utils.Page;


/**
 * 角色DAO接口
  * @author DEMOCXY-S0
 * @version V1.0
 */
public class RoleServiceImpl  implements RoleService  {
	
	RoleDao roleDao = new RoleDao();
	
	@Override
	public int add(Role role) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Integer result =roleDao.add(con, role);
			DbUtil.closeCon(con);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int delete = roleDao.delete(con, id);
			DbUtil.closeCon(con);
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Role role) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int update = roleDao.update(con, role);
			DbUtil.closeCon(con);
			return update;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Page<Role> page(Role role, Page<Role> page) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int count = roleDao.count(con,role);
			page.setCount(count);
			page.setPrev(page.getPageNo()-1);
			page.setNext(page.getPageNo() + 1);// 下一页
			page.setLast((count - 1) / page.getPageSize() + 1);// 总也数
			List<Role> list = roleDao.list(con, role, page);
			page.setList(list);
			DbUtil.closeCon(con);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Role getById(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Role role = roleDao.getById(con, id);
			DbUtil.closeCon(con);
			return role;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Role> findAll(Role role) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			List<Role> list = roleDao.findAll(con, role);
			DbUtil.closeCon(con);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
}