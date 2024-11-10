package com.so.team.service.impl;


import java.sql.Connection;
import java.util.List;

import com.so.team.bean.ActiveStu;
import com.so.team.dao.ActiveStuDao;
import com.so.team.service.ActiveStuService;
import com.so.utils.DbUtil;
import com.so.utils.Page;


/**
 * 活动申请DAO接口
 * @author admin
 * @version 2018-03-26
 */
public class ActiveStuServiceImpl  implements ActiveStuService  {
	
	ActiveStuDao activeStuDao = new ActiveStuDao();
	
	@Override
	public int add(ActiveStu activeStu) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Integer result =activeStuDao.add(con, activeStu);
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
			int delete = activeStuDao.delete(con, id);
			DbUtil.closeCon(con);
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(ActiveStu activeStu) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int update = activeStuDao.update(con, activeStu);
			DbUtil.closeCon(con);
			return update;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Page<ActiveStu> page(ActiveStu activeStu, Page<ActiveStu> page) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int count = activeStuDao.count(con,activeStu);
			page.setCount(count);
			page.setPrev(page.getPageNo()-1);
			page.setNext(page.getPageNo() + 1);// 下一页
			page.setLast((count - 1) / page.getPageSize() + 1);// 总也数
			List<ActiveStu> list = activeStuDao.list(con, activeStu, page);
			page.setList(list);
			DbUtil.closeCon(con);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ActiveStu getById(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			ActiveStu activeStu = activeStuDao.getById(con, id);
			DbUtil.closeCon(con);
			return activeStu;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<ActiveStu> findAll(ActiveStu activeStu) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			List<ActiveStu> list = activeStuDao.findAll(con, activeStu);
			DbUtil.closeCon(con);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
}