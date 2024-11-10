package com.so.system.service.impl;


import java.sql.Connection;
import java.util.List;

import com.so.system.bean.User;
import com.so.system.dao.UserDao;
import com.so.system.service.UserService;
import com.so.utils.DbUtil;
import com.so.utils.Page;


/**
 * 系统用户DAO接口
 * @author DEMOCXY-S0
 * @version V1.0
 */
public class UserServiceImpl  implements UserService  {
	
	UserDao userDao = new UserDao();
	
	@Override
	public int add(User user) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Integer result =userDao.add(con, user);
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
			int delete = userDao.delete(con, id);
			DbUtil.closeCon(con);
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(User user) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int update = userDao.update(con, user);
			DbUtil.closeCon(con);
			return update;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Page<User> page(User user, Page<User> page) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int count = userDao.count(con,user);
			page.setCount(count);
			page.setPrev(page.getPageNo()-1);
			page.setNext(page.getPageNo() + 1);// 下一页
			page.setLast((count - 1) / page.getPageSize() + 1);// 总也数
			List<User> list = userDao.list(con, user, page);
			page.setList(list);
			DbUtil.closeCon(con);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getById(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			User user = userDao.getById(con, id);
			DbUtil.closeCon(con);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<User> findAll(User user) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			List<User> list = userDao.findAll(con, user);
			DbUtil.closeCon(con);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
}