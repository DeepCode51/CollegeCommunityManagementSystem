package com.so.team.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.so.team.bean.Student;
import com.so.utils.DateUtils;
import com.so.utils.Page;

/**
 * 学生DAO接口
 * @author admin
 * @version 2018-03-26
 */
public class StudentDao {

	
	/**
	 * 添加
	 * @param con
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,Student student)throws Exception{
		student.setId(UUID.randomUUID().toString().replace("-", ""));
		String sql="insert into db_student values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,student.getId());
		pstmt.setString(2,student.getLoginName());
		pstmt.setString(3,student.getLoginPassword());
		pstmt.setTimestamp(4,null);
		pstmt.setString(5,student.getAuditType());
		pstmt.setString(6,student.getStuName());
		pstmt.setString(7,student.getSex());
		pstmt.setString(8,student.getSchoolRoom());
		pstmt.setString(9,student.getClassRoom());
		pstmt.setString(10,student.getPhone());
		pstmt.setString(11,student.getPic());
		pstmt.setString(12,student.getRemark());
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
		String sql="delete from db_student where id=?";
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
	public int update(Connection con,Student student)throws Exception{
		String sql="update db_student set login_name=?,login_password=?,regit_time=?,audit_type=?,stu_name=?,sex=?,school_room=?,class_room=?,phone=?,pic=?,remark=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(12,student.getId());
		pstmt.setString(1,student.getLoginName());
		pstmt.setString(2,student.getLoginPassword());
		pstmt.setTimestamp(3,(Timestamp)student.getRegitTime());
		pstmt.setString(4,student.getAuditType());
		pstmt.setString(5,student.getStuName());
		pstmt.setString(6,student.getSex());
		pstmt.setString(7,student.getSchoolRoom());
		pstmt.setString(8,student.getClassRoom());
		pstmt.setString(9,student.getPhone());
		pstmt.setString(10,student.getPic());
		pstmt.setString(11,student.getRemark());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public List<Student> list(Connection con,Student student,Page<Student> page)throws Exception{
		List<Student> list = new ArrayList<Student>();
		Student entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_student where 1=1");
		//拼接分页条件
		String loginName =  student.getLoginName();
		if(loginName != null && loginName !=""){
			sqlBuffer.append(" and login_name = '"+loginName+"'");
		}
		
		String loginPassword =  student.getLoginPassword();
		if(loginPassword != null && loginPassword !=""){
			sqlBuffer.append(" and login_password = '"+loginPassword+"'");
		}
		
		String auditType =  student.getAuditType();
		if(auditType != null && auditType !=""){
			sqlBuffer.append(" and audit_type = '"+auditType+"'");
		}
		
		String stuName =  student.getStuName();
		if(stuName != null && stuName !=""){
			sqlBuffer.append(" and stu_name = '"+stuName+"'");
		}
		
		String sex =  student.getSex();
		if(sex != null && sex !=""){
			sqlBuffer.append(" and sex = '"+sex+"'");
		}
		
		String schoolRoom =  student.getSchoolRoom();
		if(schoolRoom != null && schoolRoom !=""){
			sqlBuffer.append(" and school_room = '"+schoolRoom+"'");
		}
		
		String classRoom =  student.getClassRoom();
		if(classRoom != null && classRoom !=""){
			sqlBuffer.append(" and class_room = '"+classRoom+"'");
		}
		
		String sql=page.pageSql(sqlBuffer, page.getPageNo(), page.getPageSize());
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new Student();
			entity.setId(rs.getString("id"));
			entity.setLoginName(rs.getString("login_name"));
			entity.setLoginPassword(rs.getString("login_password"));
			entity.setRegitTime(rs.getTimestamp("regit_time"));
			entity.setAuditType(rs.getString("audit_type"));
			entity.setStuName(rs.getString("stu_name"));
			entity.setSex(rs.getString("sex"));
			entity.setSchoolRoom(rs.getString("school_room"));
			entity.setClassRoom(rs.getString("class_room"));
			entity.setPhone(rs.getString("phone"));
			entity.setPic(rs.getString("pic"));
			entity.setRemark(rs.getString("remark"));
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * 查询所有
	 * @param con
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public List<Student> findAll(Connection con,Student student)throws Exception{
		List<Student> list = new ArrayList<Student>();
		Student entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_student where 1=1");
		//拼接分页条件
		String loginName =  student.getLoginName();
		if(loginName != null && loginName !=""){
			sqlBuffer.append(" and login_name = '"+loginName+"'");
		}
		
		String loginPassword =  student.getLoginPassword();
		if(loginPassword != null && loginPassword !=""){
			sqlBuffer.append(" and login_password = '"+loginPassword+"'");
		}
		
		String auditType =  student.getAuditType();
		if(auditType != null && auditType !=""){
			sqlBuffer.append(" and audit_type = '"+auditType+"'");
		}
		
		String stuName =  student.getStuName();
		if(stuName != null && stuName !=""){
			sqlBuffer.append(" and stu_name = '"+stuName+"'");
		}
		
		String sex =  student.getSex();
		if(sex != null && sex !=""){
			sqlBuffer.append(" and sex = '"+sex+"'");
		}
		
		String schoolRoom =  student.getSchoolRoom();
		if(schoolRoom != null && schoolRoom !=""){
			sqlBuffer.append(" and school_room = '"+schoolRoom+"'");
		}
		
		String classRoom =  student.getClassRoom();
		if(classRoom != null && classRoom !=""){
			sqlBuffer.append(" and class_room = '"+classRoom+"'");
		}
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new Student();
			entity.setId(rs.getString("id"));
			entity.setLoginName(rs.getString("login_name"));
			entity.setLoginPassword(rs.getString("login_password"));
			entity.setRegitTime(rs.getTimestamp("regit_time"));
			entity.setAuditType(rs.getString("audit_type"));
			entity.setStuName(rs.getString("stu_name"));
			entity.setSex(rs.getString("sex"));
			entity.setSchoolRoom(rs.getString("school_room"));
			entity.setClassRoom(rs.getString("class_room"));
			entity.setPhone(rs.getString("phone"));
			entity.setPic(rs.getString("pic"));
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
	public Student getById(Connection con,String id)throws Exception{
		Student student=null;
		String sql="select * from db_student where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			student=new Student();
			student.setId(rs.getString("id"));
			student.setLoginName(rs.getString("login_name"));
			student.setLoginPassword(rs.getString("login_password"));
			student.setRegitTime(rs.getTimestamp("regit_time"));
			student.setAuditType(rs.getString("audit_type"));
			student.setStuName(rs.getString("stu_name"));
			student.setSex(rs.getString("sex"));
			student.setSchoolRoom(rs.getString("school_room"));
			student.setClassRoom(rs.getString("class_room"));
			student.setPhone(rs.getString("phone"));
			student.setPic(rs.getString("pic"));
			student.setRemark(rs.getString("remark"));
		}
		return student;
	}
	
	//获取总数 分页使用
	public int count(Connection con,Student student)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from db_student where 1=1");
		//拼接分页条件
		String loginName =  student.getLoginName();
		if(loginName != null && loginName !=""){
			sqlBuffer.append(" and login_name = '"+loginName+"'");
		}
		
		String loginPassword =  student.getLoginPassword();
		if(loginPassword != null && loginPassword !=""){
			sqlBuffer.append(" and login_password = '"+loginPassword+"'");
		}
		
		String auditType =  student.getAuditType();
		if(auditType != null && auditType !=""){
			sqlBuffer.append(" and audit_type = '"+auditType+"'");
		}
		
		String stuName =  student.getStuName();
		if(stuName != null && stuName !=""){
			sqlBuffer.append(" and stu_name = '"+stuName+"'");
		}
		
		String sex =  student.getSex();
		if(sex != null && sex !=""){
			sqlBuffer.append(" and sex = '"+sex+"'");
		}
		
		String schoolRoom =  student.getSchoolRoom();
		if(schoolRoom != null && schoolRoom !=""){
			sqlBuffer.append(" and school_room = '"+schoolRoom+"'");
		}
		
		String classRoom =  student.getClassRoom();
		if(classRoom != null && classRoom !=""){
			sqlBuffer.append(" and class_room = '"+classRoom+"'");
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