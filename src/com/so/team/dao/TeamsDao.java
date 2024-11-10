package com.so.team.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.so.team.bean.Teams;

import com.so.utils.Page;
import com.so.utils.StringUtils;

/**
 * 社团DAO接口
 * @author admin
 * @version 2018-03-26
 */
public class TeamsDao {

	
	/**
	 * 添加
	 * @param con
	 * @param teams
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,Teams teams)throws Exception{
		teams.setId(UUID.randomUUID().toString().replace("-", ""));
		String sql="insert into db_teams values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,teams.getId());
		pstmt.setString(2,teams.getTeamName());
		pstmt.setString(3,teams.getPic());
		pstmt.setString(4,teams.getTeamIntro());
		if (!(teams.getBuildTime()!=null)) {
			pstmt.setTimestamp(5, new Timestamp(new Date().getTime()));
		}else{
			pstmt.setTimestamp(5, Timestamp.valueOf(teams.getBuildTime().toLocaleString()));
		}
		pstmt.setString(6,teams.getBuildStu());
		pstmt.setString(7,teams.getAuditType());
		pstmt.setInt(8,teams.getMembers());
		pstmt.setString(9,teams.getRemark());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 删除
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con,String id)throws Exception{
		String sql="delete from db_teams where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 更新
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con,Teams teams)throws Exception{
		String sql="update db_teams set team_name=?,pic=?,team_intro=?,build_time=?,build_stu=?,audit_type=?,members=?,remark=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(9,teams.getId());
		pstmt.setString(1,teams.getTeamName());
		pstmt.setString(2,teams.getPic());
		pstmt.setString(3,teams.getTeamIntro());
		pstmt.setTimestamp(4,(Timestamp)teams.getBuildTime());
		pstmt.setString(5,teams.getBuildStu());
		pstmt.setString(6,teams.getAuditType());
		pstmt.setInt(7,teams.getMembers());
		pstmt.setString(8,teams.getRemark());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param teams
	 * @return
	 * @throws Exception
	 */
	public List<Teams> list(Connection con,Teams teams,Page<Teams> page)throws Exception{
		List<Teams> list = new ArrayList<Teams>();
		Teams entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_teams a "
				+ "left join db_student b on b.id = a.build_stu "
				+ "where 1=1");
		//拼接分页条件
		String teamName =  teams.getTeamName();
		if(teamName != null && teamName !=""){
			sqlBuffer.append(" and a.team_name = '"+teamName+"'");
		}
		
		String buildStu =  teams.getBuildStu();
		if(buildStu != null && buildStu !=""){
			sqlBuffer.append(" and a.build_stu = '"+buildStu+"'");
		}
		
		String auditType =  teams.getAuditType();
		if(auditType != null && auditType !=""){
			sqlBuffer.append(" and a.audit_type = '"+auditType+"'");
		}
		
		String sql=page.pageSql(sqlBuffer, page.getPageNo(), page.getPageSize());
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new Teams();
			entity.setId(rs.getString("id"));
			entity.setTeamName(rs.getString("team_name"));
			entity.setPic(rs.getString("pic"));
			entity.setTeamIntro(rs.getString("team_intro"));
			entity.setBuildTime(rs.getTimestamp("build_time"));
			entity.setBuildStu(rs.getString("build_stu"));
			entity.setAuditType(rs.getString("audit_type"));
			entity.setMembers(rs.getInt("members"));
			entity.setRemark(rs.getString("remark"));
			entity.setStudentName(rs.getString("stu_name"));
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * 查询所有
	 * @param con
	 * @param teams
	 * @return
	 * @throws Exception
	 */
	public List<Teams> findAll(Connection con,Teams teams)throws Exception{
		List<Teams> list = new ArrayList<Teams>();
		Teams entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_teams where 1=1");
		//拼接分页条件
		String teamName =  teams.getTeamName();
		if(teamName != null && teamName !=""){
			sqlBuffer.append(" and team_name = '"+teamName+"'");
		}
		
		String buildStu =  teams.getBuildStu();
		if(buildStu != null && buildStu !=""){
			sqlBuffer.append(" and build_stu = '"+buildStu+"'");
		}
		
		String auditType =  teams.getAuditType();
		if(auditType != null && auditType !=""){
			sqlBuffer.append(" and audit_type = '"+auditType+"'");
		}
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new Teams();
			entity.setId(rs.getString("id"));
			entity.setTeamName(rs.getString("team_name"));
			entity.setPic(rs.getString("pic"));
			entity.setTeamIntro(rs.getString("team_intro"));
			entity.setBuildTime(rs.getTimestamp("build_time"));
			entity.setBuildStu(rs.getString("build_stu"));
			entity.setAuditType(rs.getString("audit_type"));
			entity.setMembers(rs.getInt("members"));
			entity.setRemark(rs.getString("remark"));
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * id查询
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Teams getById(Connection con,String id)throws Exception{
		Teams teams=null;
		String sql="select * from db_teams a "
				+ "left join db_student b on b.id = a.build_stu "
				+ " where a.id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			teams=new Teams();
			teams.setId(rs.getString("id"));
			teams.setTeamName(rs.getString("team_name"));
			teams.setPic(rs.getString("pic"));
			teams.setTeamIntro(rs.getString("team_intro"));
			teams.setBuildTime(rs.getTimestamp("build_time"));
			teams.setBuildStu(rs.getString("build_stu"));
			teams.setAuditType(rs.getString("audit_type"));
			teams.setMembers(rs.getInt("members"));
			teams.setRemark(rs.getString("remark"));
			teams.setStudentName(rs.getString("stu_name"));
		}
		return teams;
	}
	
	//获取总数 分页使用
	public int count(Connection con,Teams teams)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from db_teams where 1=1");
		//拼接分页条件
		String teamName =  teams.getTeamName();
		if(teamName != null && teamName !=""){
			sqlBuffer.append(" and team_name = '"+teamName+"'");
		}
		
		String buildStu =  teams.getBuildStu();
		if(buildStu != null && buildStu !=""){
			sqlBuffer.append(" and build_stu = '"+buildStu+"'");
		}
		
		String auditType =  teams.getAuditType();
		if(auditType != null && auditType !=""){
			sqlBuffer.append(" and audit_type = '"+auditType+"'");
		}
		
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
		}
		return count;
	}
	
	
	public int addRef(Connection con,String stuId,String teamsId)throws Exception{
		String sql="insert into db_stu_tem values(?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,stuId);
		pstmt.setString(2,teamsId);
		return pstmt.executeUpdate();
	}
	
}