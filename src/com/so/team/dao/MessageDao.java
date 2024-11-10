package com.so.team.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.so.team.bean.Message;
import com.so.team.bean.Student;
import com.so.utils.Page;

/**
 * 留言板DAO接口
 * @author admin
 * @version 2018-03-26
 */
public class MessageDao {

	
	/**
	 * 添加
	 * @param con
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public int add(Connection con,Message message)throws Exception{
		message.setId(UUID.randomUUID().toString().replace("-", ""));
		String sql="insert into db_message values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,message.getId());
		pstmt.setString(2,message.getContent());
		pstmt.setTimestamp(3, Timestamp.valueOf(message.getCreateTime().toLocaleString()));
		pstmt.setString(4,message.getCreateStu());
		pstmt.setString(5,message.getAuditType());
		pstmt.setString(6,message.getReplay());
		pstmt.setTimestamp(7, null);
		pstmt.setString(8,message.getReplayUser());
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
		String sql="delete from db_message where id=?";
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
	public int update(Connection con,Message message)throws Exception{
		String sql="update db_message set content=?,create_time=?,create_stu=?,audit_type=?,replay=?,replay_time=?,replay_user=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(8,message.getId());
		pstmt.setString(1,message.getContent());
		pstmt.setTimestamp(2,(Timestamp)message.getCreateTime());
		pstmt.setString(3,message.getCreateStu());
		pstmt.setString(4,message.getAuditType());
		pstmt.setString(5,message.getReplay());
		pstmt.setTimestamp(6,(Timestamp)message.getReplayTime());
		pstmt.setString(7,message.getReplayUser());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public List<Message> list(Connection con,Message message,Page<Message> page)throws Exception{
		List<Message> list = new ArrayList<Message>();
		Message entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_message where 1=1");
		//拼接分页条件
		String createStu =  message.getCreateStu();
		if(createStu != null && createStu !=""){
			sqlBuffer.append(" and create_stu = '"+createStu+"'");
		}
		
		String auditType =  message.getAuditType();
		if(auditType != null && auditType !=""){
			sqlBuffer.append(" and audit_type = '"+auditType+"'");
		}
		
		String sql=page.pageSql(sqlBuffer, page.getPageNo(), page.getPageSize());
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new Message();
			entity.setId(rs.getString("id"));
			entity.setContent(rs.getString("content"));
			entity.setCreateTime(rs.getTimestamp("create_time"));
			entity.setCreateStu(rs.getString("create_stu"));
			entity.setAuditType(rs.getString("audit_type"));
			entity.setReplay(rs.getString("replay"));
			entity.setReplayTime(rs.getTimestamp("replay_time"));
			entity.setReplayUser(rs.getString("replay_user"));
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * 查询所有
	 * @param con
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public List<Message> findAll(Connection con,Message message)throws Exception{
		List<Message> list = new ArrayList<Message>();
		Message entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_message where 1=1");
		//拼接分页条件
		String createStu =  message.getCreateStu();
		if(createStu != null && createStu !=""){
			sqlBuffer.append(" and create_stu = '"+createStu+"'");
		}
		
		String auditType =  message.getAuditType();
		if(auditType != null && auditType !=""){
			sqlBuffer.append(" and audit_type = '"+auditType+"'");
		}
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new Message();
			entity.setId(rs.getString("id"));
			entity.setContent(rs.getString("content"));
			entity.setCreateTime(rs.getTimestamp("create_time"));
			entity.setCreateStu(rs.getString("create_stu"));
			entity.setAuditType(rs.getString("audit_type"));
			entity.setReplay(rs.getString("replay"));
			entity.setReplayTime(rs.getTimestamp("replay_time"));
			entity.setReplayUser(rs.getString("replay_user"));
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
	public Message getById(Connection con,String id)throws Exception{
		Message message=null;
		String sql="select * from db_message where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			message=new Message();
			message.setId(rs.getString("id"));
			message.setContent(rs.getString("content"));
			message.setCreateTime(rs.getTimestamp("create_time"));
			message.setCreateStu(rs.getString("create_stu"));
			message.setAuditType(rs.getString("audit_type"));
			message.setReplay(rs.getString("replay"));
			message.setReplayTime(rs.getTimestamp("replay_time"));
			message.setReplayUser(rs.getString("replay_user"));
		}
		return message;
	}
	
	//获取总数 分页使用
	public int count(Connection con,Message message)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from db_message where 1=1");
		//拼接分页条件
		String createStu =  message.getCreateStu();
		if(createStu != null && createStu !=""){
			sqlBuffer.append(" and create_stu = '"+createStu+"'");
		}
		
		String auditType =  message.getAuditType();
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
	
	
	//留言 根据姓名查询id
	public Student findByName(Connection con,String name)throws Exception{
		
		Student student=null;
		String sql="select * from db_student where stu_name = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			student= new Student();
			student.setId(rs.getString("id"));
		}
		return student;
		
	}
	
	
}