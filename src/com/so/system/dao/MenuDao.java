package com.so.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.so.system.bean.Menu;
import com.so.utils.DbUtil;
import com.so.utils.Page;

/**
 * 系统菜单DAO接口
 * @author DEMOCXY-S0
 * @version V1.0
 */
public class MenuDao {

	
	/**
	 * 添加
	 * @param con
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,Menu menu)throws Exception{
		menu.setId(UUID.randomUUID().toString().replace("-", ""));
		String sql="insert into sys_menu values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,menu.getId());
		pstmt.setString(2,menu.getName());
		pstmt.setString(3,menu.getHref());
		pstmt.setString(4,menu.getTarget());
		pstmt.setString(5,menu.getIsShow());
		pstmt.setString(6,menu.getSort());
		pstmt.setString(7,menu.getParentId());
		pstmt.setString(8,menu.getParentIds());
		pstmt.setString(9,menu.getRemarks());
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
		String sql="delete from sys_menu where id=?";
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
	public int update(Connection con,Menu menu)throws Exception{
		String sql="update sys_menu set name=?,href=?,target=?,is_show=?,sort=?,parent_id=?,parent_ids=?,remarks=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(9,menu.getId());
		pstmt.setString(1,menu.getName());
		pstmt.setString(2,menu.getHref());
		pstmt.setString(3,menu.getTarget());
		pstmt.setString(4,menu.getIsShow());
		pstmt.setString(5,menu.getSort());
		pstmt.setString(6,menu.getParentId());
		pstmt.setString(7,menu.getParentIds());
		pstmt.setString(8,menu.getRemarks());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	public List<Menu> list(Connection con,Menu menu,Page<Menu> page)throws Exception{
		List<Menu> list = new ArrayList<Menu>();
		Menu entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from sys_menu where 1=1");
		//拼接分页条件
		String name =  menu.getName();
		if(name != null && name !=""){
			sqlBuffer.append(" and name = '"+name+"'");
		}
		
		String isShow =  menu.getIsShow();
		if(isShow != null && isShow !=""){
			sqlBuffer.append(" and is_show = '"+isShow+"'");
		}
		sqlBuffer.append(" order by sort");
		String sql=page.pageSql(sqlBuffer, page.getPageNo(), page.getPageSize());
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new Menu();
			entity.setId(rs.getString("id"));
			entity.setName(rs.getString("name"));
			entity.setHref(rs.getString("href"));
			entity.setTarget(rs.getString("target"));
			entity.setIsShow(rs.getString("is_show"));
			entity.setSort(rs.getString("sort"));
			entity.setParentId(rs.getString("parent_id"));
			entity.setParentIds(rs.getString("parent_ids"));
			entity.setRemarks(rs.getString("remarks"));
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * 查询所有
	 * @param con
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	public List<Menu> findAll(Connection con,Menu menu)throws Exception{
		List<Menu> list = new ArrayList<Menu>();
		Menu entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from sys_menu where 1=1");
		//拼接分页条件
		String name =  menu.getName();
		if(name != null && name !=""){
			sqlBuffer.append(" and name = '"+name+"'");
		}
		
		String isShow =  menu.getIsShow();
		if(isShow != null && isShow !=""){
			sqlBuffer.append(" and is_show = '"+isShow+"'");
		}
			
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new Menu();
			entity.setId(rs.getString("id"));
			entity.setName(rs.getString("name"));
			entity.setHref(rs.getString("href"));
			entity.setTarget(rs.getString("target"));
			entity.setIsShow(rs.getString("is_show"));
			entity.setSort(rs.getString("sort"));
			entity.setParentId(rs.getString("parent_id"));
			entity.setParentIds(rs.getString("parent_ids"));
			entity.setRemarks(rs.getString("remarks"));
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
	public Menu getById(Connection con,String id)throws Exception{
		Menu menu=null;
		String sql="select * from sys_menu where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			menu=new Menu();
			menu.setId(rs.getString("id"));
			menu.setName(rs.getString("name"));
			menu.setHref(rs.getString("href"));
			menu.setTarget(rs.getString("target"));
			menu.setIsShow(rs.getString("is_show"));
			menu.setSort(rs.getString("sort"));
			menu.setParentId(rs.getString("parent_id"));
			menu.setParentIds(rs.getString("parent_ids"));
			menu.setRemarks(rs.getString("remarks"));
		}
		return menu;
	}
	
	//获取总数 分页使用
	public int count(Connection con,Menu menu)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from sys_menu where 1=1");
		//拼接分页条件
			String name =  menu.getName();
			if(name != null && name !=""){
				sqlBuffer.append(" and name = '"+name+"'");
			}
			
			String isShow =  menu.getIsShow();
			if(isShow != null && isShow !=""){
				sqlBuffer.append(" and is_show = '"+isShow+"'");
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
	 * 获取所有的一级父级菜单
	 * @return
	 */
	public List<Menu> getParentMenu(String parentId){
		List<Menu> list = new ArrayList<Menu>();
		Connection con = null;
		try {
			con = DbUtil.getDbUtil().getCon();
			Menu menu = null;
			String sql="select * from sys_menu where parent_id = ?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, parentId);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				menu=new Menu();
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
		}finally{
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
}