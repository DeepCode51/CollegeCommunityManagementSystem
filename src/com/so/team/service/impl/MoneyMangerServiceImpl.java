package com.so.team.service.impl;


import java.sql.Connection;
import java.util.List;

import com.so.utils.Page;

import com.so.utils.DbUtil;
import com.so.utils.PropertiesUtil;
import com.so.team.bean.MoneyManger;
import com.so.team.dao.MoneyMangerDao;
import com.so.team.service.MoneyMangerService;


/**
 * 财务管理DAO接口
 * @author so
 * @version V1.0
 */
public class MoneyMangerServiceImpl  implements MoneyMangerService  {
	
	MoneyMangerDao moneyMangerDao = new MoneyMangerDao();
	
	@Override
	public int add(MoneyManger moneyManger) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Integer result =moneyMangerDao.add(con, moneyManger);
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
			int delete = moneyMangerDao.delete(con, id);
			DbUtil.closeCon(con);
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(MoneyManger moneyManger) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int update = moneyMangerDao.update(con, moneyManger);
			DbUtil.closeCon(con);
			return update;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Page<MoneyManger> page(MoneyManger moneyManger, Page<MoneyManger> page) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int count = moneyMangerDao.count(con,moneyManger);
			page.setCount(count);
			page.setPrev(page.getPageNo()-1);
			page.setNext(page.getPageNo() + 1);// 下一页
			page.setLast((count - 1) / page.getPageSize() + 1);// 总也数
			List<MoneyManger> list = moneyMangerDao.list(con, moneyManger, page);
			page.setList(list);
			DbUtil.closeCon(con);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MoneyManger getById(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			MoneyManger moneyManger = moneyMangerDao.getById(con, id);
			DbUtil.closeCon(con);
			return moneyManger;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<MoneyManger> findAll(MoneyManger moneyManger) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			List<MoneyManger> list = moneyMangerDao.findAll(con, moneyManger);
			DbUtil.closeCon(con);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
}