package com.so.team.service.impl;


import java.sql.Connection;
import java.util.List;

import com.so.team.bean.Message;
import com.so.team.bean.Student;
import com.so.team.dao.MessageDao;
import com.so.team.service.MessageService;
import com.so.utils.DbUtil;
import com.so.utils.Page;


/**
 * 留言板DAO接口
 * @author admin
 * @version 2018-03-26
 */
public class MessageServiceImpl  implements MessageService  {
	
	MessageDao messageDao = new MessageDao();
	
	@Override
	public int add(Message message) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Integer result =messageDao.add(con, message);
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
			int delete = messageDao.delete(con, id);
			DbUtil.closeCon(con);
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Message message) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int update = messageDao.update(con, message);
			DbUtil.closeCon(con);
			return update;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Page<Message> page(Message message, Page<Message> page) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int count = messageDao.count(con,message);
			page.setCount(count);
			page.setPrev(page.getPageNo()-1);
			page.setNext(page.getPageNo() + 1);// 下一页
			page.setLast((count - 1) / page.getPageSize() + 1);// 总也数
			List<Message> list = messageDao.list(con, message, page);
			page.setList(list);
			DbUtil.closeCon(con);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Message getById(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Message message = messageDao.getById(con, id);
			DbUtil.closeCon(con);
			return message;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Message> findAll(Message message) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			List<Message> list = messageDao.findAll(con, message);
			DbUtil.closeCon(con);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Student findByName(String name) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Student findByName = messageDao.findByName(con, name);
			DbUtil.closeCon(con);
			return findByName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
		
}