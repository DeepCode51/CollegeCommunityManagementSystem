package com.so.team.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.so.team.bean.ActiveStu;

import com.so.utils.Page;

/**
 * 活动申请DAO接口
 * @author admin
 * @version 2018-03-26
 */
public class ActiveStuDao {

	
	/**
	 * 添加
	 * @param con
	 * @param activeStu
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,ActiveStu activeStu)throws Exception{
		activeStu.setId(UUID.randomUUID().toString().replace("-", ""));
		String sql="insert into db_active_stu values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,activeStu.getId());
		pstmt.setString(2,activeStu.getActiveId());
		pstmt.setString(3,activeStu.getStuId());
		pstmt.setString(4,activeStu.getStuName());
		pstmt.setString(5,activeStu.getPhone());
		pstmt.setString(6,activeStu.getType());
		pstmt.setString(7,activeStu.getIsArrive());
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
		String sql="delete from db_active_stu where id=?";
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
	public int update(Connection con,ActiveStu activeStu)throws Exception{
		String sql="update db_active_stu set active_id=?,stu_id=?,stu_name=?,phone=?,type=?,is_arrive=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(7,activeStu.getId());
		pstmt.setString(1,activeStu.getActiveId());
		pstmt.setString(2,activeStu.getStuId());
		pstmt.setString(3,activeStu.getStuName());
		pstmt.setString(4,activeStu.getPhone());
		pstmt.setString(5,activeStu.getType());
		pstmt.setString(6,activeStu.getIsArrive());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param activeStu
	 * @return
	 * @throws Exception
	 */
	public List<ActiveStu> list(Connection con,ActiveStu activeStu,Page<ActiveStu> page)throws Exception{
		List<ActiveStu> list = new ArrayList<ActiveStu>();
		ActiveStu entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_active_stu a "
				+ "left join db_site_news b on a.active_id = b.id "
				+ "where 1=1");
		//拼接分页条件
		String activeId =  activeStu.getActiveId();
		if(activeId != null && activeId !=""){
			sqlBuffer.append(" and a.active_id = '"+activeId+"'");
		}
		
		String stuId =  activeStu.getStuId();
		if(stuId != null && stuId !=""){
			sqlBuffer.append(" and a.stu_id = '"+stuId+"'");
		}
		
		String stuName =  activeStu.getStuName();
		if(stuName != null && stuName !=""){
			sqlBuffer.append(" and a.stu_name = '"+stuName+"'");
		}
		
		String type =  activeStu.getType();
		if(type != null && type !=""){
			sqlBuffer.append(" and a.type = '"+type+"'");
		}
		
		String isArrive =  activeStu.getIsArrive();
		if(isArrive != null && isArrive !=""){
			sqlBuffer.append(" and a.is_arrive = '"+isArrive+"'");
		}
		
		String sqlStr =  activeStu.getSqlStr();
		if(sqlStr != null && sqlStr !=""){
			sqlBuffer.append(sqlStr);
		}
		
		String sql=page.pageSql(sqlBuffer, page.getPageNo(), page.getPageSize());
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new ActiveStu();
			entity.setId(rs.getString("id"));
			entity.setActiveId(rs.getString("active_id"));
			entity.setStuId(rs.getString("stu_id"));
			entity.setStuName(rs.getString("stu_name"));
			entity.setPhone(rs.getString("phone"));
			entity.setType(rs.getString("type"));
			entity.setIsArrive(rs.getString("is_arrive"));
			entity.setActiveName(rs.getString("title"));
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * 查询所有
	 * @param con
	 * @param activeStu
	 * @return
	 * @throws Exception
	 */
	public List<ActiveStu> findAll(Connection con,ActiveStu activeStu)throws Exception{
		List<ActiveStu> list = new ArrayList<ActiveStu>();
		ActiveStu entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_active_stu where 1=1");
		//拼接分页条件
		String activeId =  activeStu.getActiveId();
		if(activeId != null && activeId !=""){
			sqlBuffer.append(" and active_id = '"+activeId+"'");
		}
		
		String stuId =  activeStu.getStuId();
		if(stuId != null && stuId !=""){
			sqlBuffer.append(" and stu_id = '"+stuId+"'");
		}
		
		String stuName =  activeStu.getStuName();
		if(stuName != null && stuName !=""){
			sqlBuffer.append(" and stu_name = '"+stuName+"'");
		}
		
		String type =  activeStu.getType();
		if(type != null && type !=""){
			sqlBuffer.append(" and type = '"+type+"'");
		}
		
		String isArrive =  activeStu.getIsArrive();
		if(isArrive != null && isArrive !=""){
			sqlBuffer.append(" and is_arrive = '"+isArrive+"'");
		}
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new ActiveStu();
			entity.setId(rs.getString("id"));
			entity.setActiveId(rs.getString("active_id"));
			entity.setStuId(rs.getString("stu_id"));
			entity.setStuName(rs.getString("stu_name"));
			entity.setPhone(rs.getString("phone"));
			entity.setType(rs.getString("type"));
			entity.setIsArrive(rs.getString("is_arrive"));
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
	public ActiveStu getById(Connection con,String id)throws Exception{
		ActiveStu activeStu=null;
		String sql="select * from db_active_stu where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			activeStu=new ActiveStu();
			activeStu.setId(rs.getString("id"));
			activeStu.setActiveId(rs.getString("active_id"));
			activeStu.setStuId(rs.getString("stu_id"));
			activeStu.setStuName(rs.getString("stu_name"));
			activeStu.setPhone(rs.getString("phone"));
			activeStu.setType(rs.getString("type"));
			activeStu.setIsArrive(rs.getString("is_arrive"));
		}
		return activeStu;
	}
	
	//获取总数 分页使用
	public int count(Connection con,ActiveStu activeStu)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from db_active_stu a where 1=1");
		//拼接分页条件
		String activeId =  activeStu.getActiveId();
		if(activeId != null && activeId !=""){
			sqlBuffer.append(" and a.active_id = '"+activeId+"'");
		}
		
		String stuId =  activeStu.getStuId();
		if(stuId != null && stuId !=""){
			sqlBuffer.append(" and a.stu_id = '"+stuId+"'");
		}
		
		String stuName =  activeStu.getStuName();
		if(stuName != null && stuName !=""){
			sqlBuffer.append(" and a.stu_name = '"+stuName+"'");
		}
		
		String type =  activeStu.getType();
		if(type != null && type !=""){
			sqlBuffer.append(" and a.type = '"+type+"'");
		}
		
		String isArrive =  activeStu.getIsArrive();
		if(isArrive != null && isArrive !=""){
			sqlBuffer.append(" and a.is_arrive = '"+isArrive+"'");
		}
		
		String sqlStr =  activeStu.getSqlStr();
		if(sqlStr != null && sqlStr !=""){
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