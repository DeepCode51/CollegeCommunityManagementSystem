package com.so.team.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.so.team.bean.TeamApplay;

import com.so.utils.Page;

/**
 * 社团申请DAO接口
 * @author admin
 * @version 2018-03-26
 */
public class TeamApplayDao {

	
	/**
	 * 添加
	 * @param con
	 * @param teamApplay
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,TeamApplay teamApplay)throws Exception{
		teamApplay.setId(UUID.randomUUID().toString().replace("-", ""));
		String sql="insert into db_team_applay values(?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,teamApplay.getId());
		pstmt.setString(2,teamApplay.getApplayTeam());
		pstmt.setString(3,teamApplay.getApplayStu());
		pstmt.setString(4,teamApplay.getApplayContent());
		pstmt.setTimestamp(5, Timestamp.valueOf(teamApplay.getApplayTime().toLocaleString()));
		pstmt.setString(6,teamApplay.getApplayType());
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
		String sql="delete from db_team_applay where id=?";
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
	public int update(Connection con,TeamApplay teamApplay)throws Exception{
		String sql="update db_team_applay set applay_team=?,applay_stu=?,applay_content=?,applay_time=?,applay_type=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(6,teamApplay.getId());
		pstmt.setString(1,teamApplay.getApplayTeam());
		pstmt.setString(2,teamApplay.getApplayStu());
		pstmt.setString(3,teamApplay.getApplayContent());
		pstmt.setTimestamp(4,(Timestamp)teamApplay.getApplayTime());
		pstmt.setString(5,teamApplay.getApplayType());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param teamApplay
	 * @return
	 * @throws Exception
	 */
	public List<TeamApplay> list(Connection con,TeamApplay teamApplay,Page<TeamApplay> page)throws Exception{
		List<TeamApplay> list = new ArrayList<TeamApplay>();
		TeamApplay entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_team_applay a "
				+ "left join db_teams b on b.id = a.applay_team "
				+ "left join db_student c on c.id = a.applay_stu "
				+ "where 1=1");
		//拼接分页条件
		String applayTeam =  teamApplay.getApplayTeam();
		if(applayTeam != null && applayTeam !=""){
			sqlBuffer.append(" and a.applay_team = '"+applayTeam+"'");
		}
		
		String applayStu =  teamApplay.getApplayStu();
		if(applayStu != null && applayStu !=""){
			sqlBuffer.append(" and a.applay_stu = '"+applayStu+"'");
		}
		
		String applayType =  teamApplay.getApplayType();
		if(applayType != null && applayType !=""){
			sqlBuffer.append(" and a.applay_type = '"+applayType+"'");
		}
		String sqlStr = teamApplay.getSqlStr();
		if (sqlStr!=null && sqlStr !="") {
			sqlBuffer.append(sqlStr);
		}
		
		String sql=page.pageSql(sqlBuffer, page.getPageNo(), page.getPageSize());
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new TeamApplay();
			entity.setId(rs.getString("id"));
			entity.setApplayTeam(rs.getString("applay_team"));
			entity.setApplayStu(rs.getString("applay_stu"));
			entity.setApplayContent(rs.getString("applay_content"));
			entity.setApplayTime(rs.getTimestamp("applay_time"));
			entity.setApplayType(rs.getString("applay_type"));
			entity.setApplayTeamName(rs.getString("team_name"));
			entity.setApplayStuName(rs.getString("stu_name"));
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * 查询所有
	 * @param con
	 * @param teamApplay
	 * @return
	 * @throws Exception
	 */
	public List<TeamApplay> findAll(Connection con,TeamApplay teamApplay)throws Exception{
		List<TeamApplay> list = new ArrayList<TeamApplay>();
		TeamApplay entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_team_applay where 1=1");
		//拼接分页条件
		String applayTeam =  teamApplay.getApplayTeam();
		if(applayTeam != null && applayTeam !=""){
			sqlBuffer.append(" and applay_team = '"+applayTeam+"'");
		}
		
		String applayStu =  teamApplay.getApplayStu();
		if(applayStu != null && applayStu !=""){
			sqlBuffer.append(" and applay_stu = '"+applayStu+"'");
		}
		
		String applayType =  teamApplay.getApplayType();
		if(applayType != null && applayType !=""){
			sqlBuffer.append(" and applay_type = '"+applayType+"'");
		}
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new TeamApplay();
			entity.setId(rs.getString("id"));
			entity.setApplayTeam(rs.getString("applay_team"));
			entity.setApplayStu(rs.getString("applay_stu"));
			entity.setApplayContent(rs.getString("applay_content"));
			entity.setApplayTime(rs.getTimestamp("applay_time"));
			entity.setApplayType(rs.getString("applay_type"));
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
	public TeamApplay getById(Connection con,String id)throws Exception{
		TeamApplay teamApplay=null;
		String sql="select * from db_team_applay where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			teamApplay=new TeamApplay();
			teamApplay.setId(rs.getString("id"));
			teamApplay.setApplayTeam(rs.getString("applay_team"));
			teamApplay.setApplayStu(rs.getString("applay_stu"));
			teamApplay.setApplayContent(rs.getString("applay_content"));
			teamApplay.setApplayTime(rs.getTimestamp("applay_time"));
			teamApplay.setApplayType(rs.getString("applay_type"));
		}
		return teamApplay;
	}
	
	//获取总数 分页使用
	public int count(Connection con,TeamApplay teamApplay)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from db_team_applay a where 1=1");
		//拼接分页条件
		String applayTeam =  teamApplay.getApplayTeam();
		if(applayTeam != null && applayTeam !=""){
			sqlBuffer.append(" and a.applay_team = '"+applayTeam+"'");
		}
		
		String applayStu =  teamApplay.getApplayStu();
		if(applayStu != null && applayStu !=""){
			sqlBuffer.append(" and a.applay_stu = '"+applayStu+"'");
		}
		
		String applayType =  teamApplay.getApplayType();
		if(applayType != null && applayType !=""){
			sqlBuffer.append(" and a.applay_type = '"+applayType+"'");
		}
		String sqlStr = teamApplay.getSqlStr();
		if (sqlStr!=null && sqlStr !="") {
			sqlBuffer.append(sqlStr);
		}
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
		}
		return count;
	}
	
	
}