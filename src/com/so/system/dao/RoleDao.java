package com.so.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.so.system.bean.Role;
import com.so.utils.DbUtil;
import com.so.utils.Page;

/**
 * 系统角色DAO接口
 * @author DEMOCXY-S0
 * @version V1.0
 */
public class RoleDao {

	
	/**
	 * 添加
	 * @param con
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,Role role)throws Exception{
		role.setId(UUID.randomUUID().toString().replace("-", ""));
		String sql="insert into sys_role values(?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,role.getId());
		pstmt.setString(2,role.getRoleName());
		pstmt.setString(3,role.getRoleFlag());
		pstmt.setString(4,role.getIntroduce());
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
		String sql="delete from sys_role where id=?";
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
	public int update(Connection con,Role role)throws Exception{
		String sql="update sys_role set role_name=?,role_flag=?,introduce=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(4,role.getId());
		pstmt.setString(1,role.getRoleName());
		pstmt.setString(2,role.getRoleFlag());
		pstmt.setString(3,role.getIntroduce());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<Role> list(Connection con,Role role,Page<Role> page)throws Exception{
		List<Role> list = new ArrayList<>();
		Role entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from sys_role where 1=1");
		//拼接分页条件
		String roleName =  role.getRoleName();
		if(roleName != null && roleName !=""){
			sqlBuffer.append(" and role_name = '"+roleName+"'");
		}
		String sql=page.pageSql(sqlBuffer, page.getPageNo(), page.getPageSize());
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new Role();
			entity.setId(rs.getString("id"));
			entity.setRoleName(rs.getString("role_name"));
			entity.setRoleFlag(rs.getString("role_flag"));
			entity.setIntroduce(rs.getString("introduce"));
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * 查询所有
	 * @param con
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<Role> findAll(Connection con,Role role)throws Exception{
		List<Role> list = new ArrayList<>();
		Role entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from sys_role where 1=1");
		//拼接分页条件
			String roleName =  role.getRoleName();
			if(roleName != null && roleName !=""){
				sqlBuffer.append(" and role_name = '"+roleName+"'");
			}
			
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new Role();
			entity.setId(rs.getString("id"));
			entity.setRoleName(rs.getString("role_name"));
			entity.setRoleFlag(rs.getString("role_flag"));
			entity.setIntroduce(rs.getString("introduce"));
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
	public Role getById(Connection con,String id)throws Exception{
		Role role=null;
		String sql="select * from sys_role where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			role=new Role();
			role.setId(rs.getString("id"));
			role.setRoleName(rs.getString("role_name"));
			role.setRoleFlag(rs.getString("role_flag"));
			role.setIntroduce(rs.getString("introduce"));
		}
		return role;
	}
	
	public String getByRoleFlag(String roleFlag){
		Connection con = null;
		
		PreparedStatement pstmt = null;
		String roleId=null;
		String sql="select id from sys_role where role_flag = ?";
		try {
			con = DbUtil.getDbUtil().getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, roleFlag);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				roleId = rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return roleId;
	}
	
	//获取总数 分页使用
	public int count(Connection con,Role role)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from sys_role where 1=1");
		//拼接分页条件
			String roleName =  role.getRoleName();
			if(roleName != null && roleName !=""){
				sqlBuffer.append(" and role_name = '"+roleName+"'");
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