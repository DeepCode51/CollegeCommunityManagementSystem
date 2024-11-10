package com.so.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.so.system.bean.Role;
import com.so.system.bean.User;
import com.so.utils.DbUtil;
import com.so.utils.MD5;
import com.so.utils.Page;
import com.so.utils.StringUtils;

/**
 * 系统用户DAO接口
 * @author DEMOCXY-S0
 * @version V1.0
 */
public class UserDao {

	
	/**
	 * 添加
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,User user)throws Exception{
		if (StringUtils.isEmpty(user.getId())) {
			user.setId(UUID.randomUUID().toString().replace("-", ""));
		}
		String sql="insert into sys_user values(?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,user.getId());
		pstmt.setString(2,user.getUsername());
		if (StringUtils.isEmpty(user.getPassword())) {
			pstmt.setString(3,MD5.Encrypt("123456"));
		}else{
			pstmt.setString(3,MD5.Encrypt(user.getPassword()));
		}
		pstmt.setString(4,user.getPic());
		pstmt.setString(5,user.getRole());
		pstmt.setString(6,user.getIsBolck());
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
		String sql="delete from sys_user where id=?";
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
	public int update(Connection con,User user)throws Exception{
		String sql="update sys_user set username=?,pic=?,role=?,is_bolck=?,password=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(6,user.getId());
		pstmt.setString(1,user.getUsername());
		pstmt.setString(2,user.getPic());
		pstmt.setString(3,user.getRole());
		pstmt.setString(4,user.getIsBolck());
		pstmt.setString(5,user.getPassword());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<User> list(Connection con,User user,Page<User> page)throws Exception{
		List<User> list = new ArrayList<>();
		User entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select a.*,b.role_name from sys_user a left join sys_role b on b.id = a.role where 1=1 and a.id != '1'");
		//拼接分页条件
		String username =  user.getUsername();
		if(username != null && username !=""){
			sqlBuffer.append(" and a.username = '"+username+"'");
		}
		String sql=page.pageSql(sqlBuffer, page.getPageNo(), page.getPageSize());
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new User();
			entity.setId(rs.getString("id"));
			entity.setUsername(rs.getString("username"));
			entity.setPassword(rs.getString("password"));
			entity.setPic(rs.getString("pic"));
			entity.setRole(rs.getString("role"));
			entity.setIsBolck(rs.getString("is_bolck"));
			entity.setRoleName(rs.getString("role_name"));
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * 查询所有
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<User> findAll(Connection con,User user)throws Exception{
		List<User> list = new ArrayList<>();
		User entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from sys_user where 1=1");
		//拼接分页条件
			String username =  user.getUsername();
			if(username != null && username !=""){
				sqlBuffer.append(" and username = '"+username+"'");
			}
			String role = user.getRole();
			if (role!=null && role!="") {
				sqlBuffer.append(" and role = '"+role+"'");
			}
			
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new User();
			entity.setId(rs.getString("id"));
			entity.setUsername(rs.getString("username"));
			entity.setPassword(rs.getString("password"));
			entity.setPic(rs.getString("pic"));
			entity.setRole(rs.getString("role"));
			entity.setIsBolck(rs.getString("is_bolck"));
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
	public User getById(Connection con,String id)throws Exception{
		User user=null;
		String sql="select * from sys_user where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			user=new User();
			user.setId(rs.getString("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setPic(rs.getString("pic"));
			user.setRole(rs.getString("role"));
			user.setIsBolck(rs.getString("is_bolck"));
		}
		return user;
	}
	
	//获取总数 分页使用
	public int count(Connection con,User user)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from sys_user where 1=1");
		//拼接分页条件
			String username =  user.getUsername();
			if(username != null && username !=""){
				sqlBuffer.append(" and username = '"+username+"'");
			}
			
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
		}
		return count;
	}
	
	/**
	 * 用户登录
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User login(String username,String password){
		User user=null;
		Connection con = null;
		try {
			con = DbUtil.getDbUtil().getCon();
			String sql="select a.*,b.* from sys_user a "
					+ "left join sys_role b on a.role = b.id "
					+ "where a.username = ? and a.password = ?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				user=new User();
				user.setId(rs.getString("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setPic(rs.getString("pic"));
				user.setRole(rs.getString("role"));
				user.setIsBolck(rs.getString("is_bolck"));
				Role role = new Role();
				role.setRoleFlag(rs.getString("role_flag"));
				role.setRoleName(rs.getString("role_name"));
				user.setRole2(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return user;
	}
	
}