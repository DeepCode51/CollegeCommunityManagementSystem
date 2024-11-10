package com.so.system.service.impl;


import java.sql.Connection;
import java.util.List;

import com.so.system.bean.Menu;
import com.so.system.dao.MenuDao;
import com.so.system.service.MenuService;
import com.so.utils.DbUtil;
import com.so.utils.Page;


/**
 * 菜单DAO接口
 * @author DEMOCXY-S0
 * @version V1.0
 */
public class MenuServiceImpl  implements MenuService  {
	
	MenuDao menuDao = new MenuDao();
	
	@Override
	public int add(Menu menu) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Integer result =menuDao.add(con, menu);
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
			con.setAutoCommit(false);
			int delete = menuDao.delete(con, id);
			List<Menu> parentMenu = menuDao.getParentMenu(id);
			for (Menu menu : parentMenu) {
				menuDao.delete(con, menu.getId());
			}
			con.commit();
			DbUtil.closeCon(con);
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Menu menu) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int update = menuDao.update(con, menu);
			DbUtil.closeCon(con);
			return update;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Page<Menu> page(Menu menu, Page<Menu> page) {
		page.setPageSize(100);
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int count = menuDao.count(con,menu);
			page.setCount(count);
			page.setPrev(page.getPageNo()-1);
			page.setNext(page.getPageNo() + 1);// 下一页
			page.setLast((count - 1) / page.getPageSize() + 1);// 总也数
			List<Menu> list = menuDao.list(con, menu, page);
			page.setList(list);
			DbUtil.closeCon(con);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Menu getById(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Menu menu = menuDao.getById(con, id);
			DbUtil.closeCon(con);
			return menu;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Menu> findAll(Menu menu) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			List<Menu> list = menuDao.findAll(con, menu);
			DbUtil.closeCon(con);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
}