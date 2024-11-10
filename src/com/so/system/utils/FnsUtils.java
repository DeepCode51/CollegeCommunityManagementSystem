package com.so.system.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.so.utils.DbUtil;


public class FnsUtils {
	
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args) {
		List<Map<String, Object>> selectListFromTable = selectTableList("college_name", "sc_college_info");
		for (Map<String, Object> map : selectListFromTable) {
			System.out.println(map.get("id"));
			System.out.println(map.get("college_name"));
			String fieldNameById = getFieldNameById("EF143FC22B9E4BBEBA7CAD6C44A0912C", "college_name", "sc_college_info");
			System.out.println("test:"+fieldNameById);
		}
	}
	
	/**
	 * 根据id 字段名，表名返回字段值
	 * @param id
	 * @param field
	 * @param tableName
	 * @return
	 */
	public static String getFieldNameById(String id,String field,String tableName){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rString = "";
		try {
			con = DbUtil.getDbUtil().getCon();
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("select "+field);
			stringBuffer.append(" from "+tableName);
			stringBuffer.append(" where id='"+id+"'");
			String sql = stringBuffer.toString();
			System.out.println(sql);
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				rString = rs.getString(field);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
		return rString;
	}
	
	/**
	 * 根据字段，表名查出指定表的集合数据
	 * @param field 查询字段
	 * @param tableName 表名
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> selectTableList(String field,String tableName){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			con = DbUtil.getDbUtil().getCon();
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("select id");
			stringBuffer.append(","+field);
			stringBuffer.append(" from "+tableName);
			String sql = stringBuffer.toString();
			System.out.println(sql);
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", rs.getString("id"));
				map.put(field, rs.getString(field));
				list.add(map);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 关闭资源
	 * @param con
	 * @param pstmt
	 * @param rs
	 */
	private static void close(Connection con,PreparedStatement pstmt,ResultSet rs){
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
