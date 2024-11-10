package com.so.team.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.so.utils.Page;

import com.so.team.bean.MoneyManger;

/**
 * 财务管理DAO接口
 * @author so
 * @version V1.0
 */
public class MoneyMangerDao {

	
	/**
	 * 添加
	 * @param con
	 * @param moneyManger
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,MoneyManger moneyManger)throws Exception{
		moneyManger.setId(UUID.randomUUID().toString());
		String sql="insert into money_manger values(?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,moneyManger.getId());
		pstmt.setString(2,moneyManger.getActId());
		pstmt.setString(3,moneyManger.getType());
		pstmt.setDouble(4,moneyManger.getMoney());
		pstmt.setString(5,moneyManger.getRemark());
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
		String sql="delete from money_manger where id=?";
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
	public int update(Connection con,MoneyManger moneyManger)throws Exception{
		String sql="update money_manger set act_id=?,type=?,money=?,remark=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(5,moneyManger.getId());
		pstmt.setString(1,moneyManger.getActId());
		pstmt.setString(2,moneyManger.getType());
		pstmt.setDouble(3,moneyManger.getMoney());
		pstmt.setString(4,moneyManger.getRemark());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param moneyManger
	 * @return
	 * @throws Exception
	 */
	public List<MoneyManger> list(Connection con,MoneyManger moneyManger,Page<MoneyManger> page)throws Exception{
		List<MoneyManger> list = new ArrayList<MoneyManger>();
		MoneyManger entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from money_manger a where 1=1");
		//拼接分页条件
		String actId =  moneyManger.getActId();
		if(actId != null && actId !=""){
			sqlBuffer.append(" and a.act_id = '"+actId+"'");
		}
		
		String type =  moneyManger.getType();
		if(type != null && type !=""){
			sqlBuffer.append(" and a.type = '"+type+"'");
		}
		
		String sqlStr =  moneyManger.getSqlStr();
		if(sqlStr != null && sqlStr !=""){
			sqlBuffer.append(sqlStr);
		}
		
		String sql=page.pageSql(sqlBuffer, page.getPageNo(), page.getPageSize());
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = getDictFormDb(rs);
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * 查询所有
	 * @param con
	 * @param moneyManger
	 * @return
	 * @throws Exception
	 */
	public List<MoneyManger> findAll(Connection con,MoneyManger moneyManger)throws Exception{
		List<MoneyManger> list = new ArrayList<MoneyManger>();
		MoneyManger entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from money_manger a where 1=1");
		//拼接分页条件
		String actId =  moneyManger.getActId();
		if(actId != null && actId !=""){
			sqlBuffer.append(" and a.act_id = '"+actId+"'");
		}
		
		String type =  moneyManger.getType();
		if(type != null && type !=""){
			sqlBuffer.append(" and a.type = '"+type+"'");
		}
		
		String sqlStr =  moneyManger.getSqlStr();
		if(sqlStr != null && sqlStr !=""){
			sqlBuffer.append(sqlStr);
		}
		
		String sql=sqlBuffer.toString();
		System.out.println(sql);
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = getDictFormDb(rs);
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
	public MoneyManger getById(Connection con,String id)throws Exception{
		MoneyManger moneyManger=null;
		String sql="select * from money_manger where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			moneyManger=getDictFormDb(rs);
		}
		return moneyManger;
	}
	
	//获取总数 分页使用
	public int count(Connection con,MoneyManger moneyManger)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from money_manger a where 1=1");
		//拼接分页条件
		String actId =  moneyManger.getActId();
		if(actId != null && actId !=""){
			sqlBuffer.append(" and a.act_id = '"+actId+"'");
		}
		
		String type =  moneyManger.getType();
		if(type != null && type !=""){
			sqlBuffer.append(" and a.type = '"+type+"'");
		}
		
		String sqlStr =  moneyManger.getSqlStr();
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
	
	private MoneyManger getDictFormDb(ResultSet rs) throws SQLException{
		MoneyManger moneyManger=new MoneyManger();
		moneyManger.setId(rs.getString("id"));
		moneyManger.setActId(rs.getString("act_id"));
		moneyManger.setType(rs.getString("type"));
		moneyManger.setMoney(rs.getDouble("money"));
		moneyManger.setRemark(rs.getString("remark"));
		return moneyManger;
	}
	
}