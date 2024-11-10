package com.so.team.service.impl;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.so.system.bean.User;
import com.so.system.dao.RoleDao;
import com.so.system.dao.UserDao;
import com.so.team.bean.Student;
import com.so.team.bean.TeamApplay;
import com.so.team.bean.Teams;
import com.so.team.dao.StudentDao;
import com.so.team.dao.TeamApplayDao;
import com.so.team.dao.TeamsDao;
import com.so.team.service.TeamApplayService;
import com.so.utils.DbUtil;
import com.so.utils.MD5;
import com.so.utils.Page;


/**
 * 社团申请DAO接口
 * @author admin
 * @version 2018-03-26
 */
public class TeamApplayServiceImpl  implements TeamApplayService  {
	
	TeamApplayDao teamApplayDao = new TeamApplayDao();
	TeamsDao teamsDao = new TeamsDao();
	StudentDao studentDao = new StudentDao();
	UserDao userDao = new UserDao();
	RoleDao roleDao = new RoleDao();
	
	@Override
	public int add(TeamApplay teamApplay) {
		try {
			Connection con =DbUtil.getDbUtil().getCon();
			Integer result =teamApplayDao.add(con, teamApplay);
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
			Connection con =DbUtil.getDbUtil().getCon();
			int delete = teamApplayDao.delete(con, id);
			DbUtil.closeCon(con);
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(TeamApplay teamApplay) {
		Connection con = null;
		try {
			con =DbUtil.getDbUtil().getCon();
			//开启事务，关闭自动提交
			con.setAutoCommit(false);
			int update = teamApplayDao.update(con, teamApplay);
			//如果是审核通过
			if ("1".equals(teamApplay.getApplayType())) {
				System.out.println("审核通过");
				//首先更新社团的社长名字
				String stuId = teamApplay.getApplayStu();
				Student student = studentDao.getById(con, stuId);
				String teamId = teamApplay.getApplayTeam();
				if (teamApplay.getApplayContent().contains("申请做社长")) {
					Teams teams = teamsDao.getById(con, teamId);
					teams.setBuildStu(student.getId());
					teamsDao.update(con, teams);
					//插入用户表数据
					String roleId = roleDao.getByRoleFlag("leader");
					User user = new User();
					user.setId(student.getId());
					user.setIsBolck("0");
					user.setPic(student.getPic());
					user.setUsername(student.getLoginName());
					user.setRole(roleId);
					userDao.add(con, user);
				}
				//像社团成员表加入数据
				teamsDao.addRef(con, stuId, teamId);
			}
			//提交事务
			con.commit();
			DbUtil.closeCon(con);
			return update;
		} catch (Exception e) {
			//出现异常 回滚数据
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
	public Page<TeamApplay> page(TeamApplay teamApplay, Page<TeamApplay> page) {
		try {
			Connection con =DbUtil.getDbUtil().getCon();
			int count = teamApplayDao.count(con,teamApplay);
			page.setCount(count);
			page.setPrev(page.getPageNo()-1);
			page.setNext(page.getPageNo() + 1);// 下一页
			page.setLast((count - 1) / page.getPageSize() + 1);// 总也数
			List<TeamApplay> list = teamApplayDao.list(con, teamApplay, page);
			page.setList(list);
			DbUtil.closeCon(con);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TeamApplay getById(String id) {
		try {
			Connection con =DbUtil.getDbUtil().getCon();
			TeamApplay teamApplay = teamApplayDao.getById(con, id);
			DbUtil.closeCon(con);
			return teamApplay;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<TeamApplay> findAll(TeamApplay teamApplay) {
		try {
			Connection con =DbUtil.getDbUtil().getCon();
			List<TeamApplay> list = teamApplayDao.findAll(con, teamApplay);
			DbUtil.closeCon(con);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
}