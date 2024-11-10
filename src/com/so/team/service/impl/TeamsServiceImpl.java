package com.so.team.service.impl;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.so.system.bean.User;
import com.so.system.dao.RoleDao;
import com.so.system.dao.UserDao;
import com.so.team.bean.Student;
import com.so.team.bean.Teams;
import com.so.team.dao.StudentDao;
import com.so.team.dao.TeamsDao;
import com.so.team.service.TeamsService;
import com.so.utils.DbUtil;
import com.so.utils.Page;


/**
 * 社团DAO接口
 * @author admin
 * @version 2018-03-26
 */
public class TeamsServiceImpl  implements TeamsService  {
	
	TeamsDao teamsDao = new TeamsDao();
	UserDao userDao = new UserDao();
	RoleDao roleDao = new RoleDao();
	StudentDao studentDao = new StudentDao();
	
	@Override
	public int add(Teams teams) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Integer result =teamsDao.add(con, teams);
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
			int delete = teamsDao.delete(con, id);
			DbUtil.closeCon(con);
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Teams teams) {
		Connection con = null;
		try {
			con = DbUtil.getDbUtil().getCon();
			con.setAutoCommit(false);
			int update = teamsDao.update(con, teams);
			//如何审核通过，插入用户数据
			if ("1".equals(teams.getAuditType())) {
				//首先判断该用户是否已经存在系统用户
				User byId = userDao.getById(con, teams.getBuildStu());
				if (byId!=null) {
					//如果不等于null，说明该用户已经可以登录后台
					System.out.println("已申请过社团。无需重复添加后台账号！");
				}else{
					//否则添加对应的系统用户数据
					//插入用户表数据
					String roleId = roleDao.getByRoleFlag("leader");
					User user = new User();
					Student student = studentDao.getById(con, teams.getBuildStu());
					user.setId(teams.getBuildStu());
					user.setIsBolck("0");
					user.setPic(student.getPic());
					user.setUsername(student.getLoginName());
					user.setRole(roleId);
					userDao.add(con, user);
				}
			}
			con.commit();
			DbUtil.closeCon(con);
			return update;
		} catch (Exception e) {
			if (con!=null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Page<Teams> page(Teams teams, Page<Teams> page) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int count = teamsDao.count(con,teams);
			page.setCount(count);
			page.setPrev(page.getPageNo()-1);
			page.setNext(page.getPageNo() + 1);// 下一页
			page.setLast((count - 1) / page.getPageSize() + 1);// 总也数
			List<Teams> list = teamsDao.list(con, teams, page);
			page.setList(list);
			DbUtil.closeCon(con);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Teams getById(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Teams teams = teamsDao.getById(con, id);
			DbUtil.closeCon(con);
			return teams;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Teams> findAll(Teams teams) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			List<Teams> list = teamsDao.findAll(con, teams);
			DbUtil.closeCon(con);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
}