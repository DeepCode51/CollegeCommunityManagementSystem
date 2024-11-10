package com.so.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.so.system.bean.Menu;
import com.so.system.bean.MenuRole;
import com.so.utils.DbUtil;

/**
 * 系统菜单角色DAO接口
 * @author DEMOCXY-S0
 * @version V1.0
 */
public class MenuRoleDao {

	
	/**
	 * 添加
	 * @param con
	 * @param systemMenuRole
	 * @return
	 * @throws Exception
	 */
	public int add(MenuRole systemMenuRole){
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con =DbUtil.getDbUtil().getCon();
			String sql="insert into sys_menu_role values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,systemMenuRole.getRoleId());
			pstmt.setString(2,systemMenuRole.getMenuId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) {
					pstmt.close();
				}
				if (con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	/**
	 * 删除
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(String roleId){
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con =DbUtil.getDbUtil().getCon();
			String sql="delete from sys_menu_role where role_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, roleId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) {
					pstmt.close();
				}
				if (con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	
	/**
	 * 查询角色所有的菜单权限
	 * @param con
	 * @param systemMenuRole
	 * @return
	 * @throws Exception
	 */
	public List<String> getByRoleId(String roleId){
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbUtil.getDbUtil().getCon();
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append("select menu_id from sys_menu_role where role_id = ?");
			String sql=sqlBuffer.toString();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, roleId);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				list.add(rs.getString("menu_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) {
					pstmt.close();
				}
				if (con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 获取用户的显示菜单
	 * @param roleId
	 * @param parentId
	 * @return
	 */
	public List<Menu> getUserMenus(String roleId,String parentId){
		List<Menu> list = new ArrayList<Menu>();
		Menu menu = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbUtil.getDbUtil().getCon();
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append("select * from sys_menu "
					+ "where is_show='1' and parent_id = ? "
					+ "and  id in "
					+ "(  select menu_id from sys_menu_role where role_id = ? ) "
					+ "order by sort");
			String sql=sqlBuffer.toString();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, parentId);
			pstmt.setString(2, roleId);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				menu = new Menu();
				menu.setId(rs.getString("id"));
				menu.setName(rs.getString("name"));
				menu.setHref(rs.getString("href"));
				menu.setTarget(rs.getString("target"));
				menu.setIsShow(rs.getString("is_show"));
				menu.setSort(rs.getString("sort"));
				menu.setParentId(rs.getString("parent_id"));
				menu.setParentIds(rs.getString("parent_ids"));
				menu.setRemarks(rs.getString("remarks"));
				list.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) {
					pstmt.close();
				}
				if (con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}