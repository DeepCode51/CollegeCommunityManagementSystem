package com.so.team.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.so.team.bean.SiteNews;

import com.so.utils.Page;
import com.so.utils.StringUtils;

/**
 * 新闻活动DAO接口
 * @author admin
 * @version 2018-03-26
 */
public class SiteNewsDao {

	
	/**
	 * 添加
	 * @param con
	 * @param siteNews
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public int add(Connection con,SiteNews siteNews)throws Exception{
		siteNews.setId(UUID.randomUUID().toString().replace("-", ""));
		String sql="insert into db_site_news values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,siteNews.getId());
		pstmt.setString(2,siteNews.getNewsType());
		pstmt.setString(3,siteNews.getTitle());
		pstmt.setString(4,siteNews.getPic());
		pstmt.setString(5,siteNews.getShortContent());
		pstmt.setString(6,siteNews.getContent());
		pstmt.setString(7,siteNews.getCreateUser());
		pstmt.setTimestamp(8, Timestamp.valueOf(siteNews.getCreateTime().toLocaleString()));
		pstmt.setString(9,siteNews.getIsRun());
		pstmt.setString(10,siteNews.getBelonTeam());
		pstmt.setString(11,siteNews.getIsAudit());
		if (siteNews.getAcStartTime() == null || StringUtils.isEmpty(siteNews.getAcStartTime().toString())) {
			pstmt.setTimestamp(12, null);
		}else {
			pstmt.setTimestamp(12, Timestamp.valueOf( siteNews.getAcStartTime().toLocaleString()));
		}
		if (siteNews.getAcEndTime() == null || StringUtils.isEmpty(siteNews.getAcEndTime().toString())) {
			pstmt.setTimestamp(13, null);
		}else {
			pstmt.setTimestamp(13, Timestamp.valueOf(siteNews.getAcEndTime().toLocaleString()));
		}
		pstmt.setString(14, siteNews.getIsCanApply());
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
		String sql="delete from db_site_news where id=?";
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
	public int update(Connection con,SiteNews siteNews)throws Exception{
		String sql="update db_site_news set news_type=?,title=?,pic=?,short_content=?,"
				+ "content=?,create_user=?,create_time=?,is_run=?,belon_team=?,"
				+ "is_audit=?,ac_start_time=?,ac_end_time=?,is_can_apply=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(14,siteNews.getId());
		pstmt.setString(1,siteNews.getNewsType());
		pstmt.setString(2,siteNews.getTitle());
		pstmt.setString(3,siteNews.getPic());
		pstmt.setString(4,siteNews.getShortContent());
		pstmt.setString(5,siteNews.getContent());
		pstmt.setString(6,siteNews.getCreateUser());
		pstmt.setTimestamp(7,(Timestamp)siteNews.getCreateTime());
		pstmt.setString(8,siteNews.getIsRun());
		pstmt.setString(9,siteNews.getBelonTeam());
		pstmt.setString(10,siteNews.getIsAudit());
		if (siteNews.getAcStartTime() == null || StringUtils.isEmpty(siteNews.getAcStartTime().toString())) {
				pstmt.setTimestamp(11, null);
		}else {
			pstmt.setTimestamp(11, Timestamp.valueOf( siteNews.getAcStartTime().toLocaleString()));
		}
		if (siteNews.getAcEndTime() == null || StringUtils.isEmpty(siteNews.getAcEndTime().toString())) {
				pstmt.setTimestamp(12, null);
		}else {
			pstmt.setTimestamp(12, Timestamp.valueOf(siteNews.getAcEndTime().toLocaleString()));
		}
		pstmt.setString(13,siteNews.getIsCanApply());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param siteNews
	 * @return
	 * @throws Exception
	 */
	public List<SiteNews> list(Connection con,SiteNews siteNews,Page<SiteNews> page)throws Exception{
		List<SiteNews> list = new ArrayList<SiteNews>();
		SiteNews entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_site_news a "
				+ "where 1=1");
		//拼接分页条件
		String newsType =  siteNews.getNewsType();
		if(newsType != null && newsType !=""){
			sqlBuffer.append(" and a.news_type = '"+newsType+"'");
		}
		
		String title =  siteNews.getTitle();
		if(title != null && title !=""){
			sqlBuffer.append(" and a.title like '%"+title+"%'");
		}
		
		String createUser =  siteNews.getCreateUser();
		if(createUser != null && createUser !=""){
			sqlBuffer.append(" and a.create_user = '"+createUser+"'");
		}
		
		String isRun =  siteNews.getIsRun();
		if(isRun != null && isRun !=""){
			sqlBuffer.append(" and a.is_run = '"+isRun+"'");
		}
		
		String belonTeam =  siteNews.getBelonTeam();
		if(belonTeam != null && belonTeam !=""){
			sqlBuffer.append(" and a.belon_team = '"+belonTeam+"'");
		}
		
		String isAudit =  siteNews.getIsAudit();
		if(isAudit != null && isAudit !=""){
			sqlBuffer.append(" and a.is_audit = '"+isAudit+"'");
		}
		
		String isCanApply =  siteNews.getIsCanApply();
		if(isCanApply != null && isCanApply !=""){
			sqlBuffer.append(" and a.is_can_apply = '"+isCanApply+"'");
		}
		
		String sqlStr = siteNews.getSqlStr();
		if (sqlStr!=null && sqlStr !="") {
			sqlBuffer.append(sqlStr);
		}
		
		sqlBuffer.append(" order by a.create_time desc ");
		String sql=page.pageSql(sqlBuffer, page.getPageNo(), page.getPageSize());
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new SiteNews();
			entity.setId(rs.getString("id"));
			entity.setNewsType(rs.getString("news_type"));
			entity.setTitle(rs.getString("title"));
			entity.setPic(rs.getString("pic"));
			entity.setShortContent(rs.getString("short_content"));
			entity.setContent(rs.getString("content"));
			entity.setCreateUser(rs.getString("create_user"));
			entity.setCreateTime(rs.getTimestamp("create_time"));
			entity.setIsRun(rs.getString("is_run"));
			entity.setBelonTeam(rs.getString("belon_team"));
			entity.setIsAudit(rs.getString("is_audit"));
			entity.setAcStartTime(rs.getTimestamp("ac_start_time"));
			entity.setAcEndTime(rs.getTimestamp("ac_end_time"));
			entity.setTeamName(rs.getString("is_can_apply"));
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * 查询所有
	 * @param con
	 * @param siteNews
	 * @return
	 * @throws Exception
	 */
	public List<SiteNews> findAll(Connection con,SiteNews siteNews)throws Exception{
		List<SiteNews> list = new ArrayList<SiteNews>();
		SiteNews entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_site_news where 1=1");
		//拼接分页条件
		String newsType =  siteNews.getNewsType();
		if(newsType != null && newsType !=""){
			sqlBuffer.append(" and news_type = '"+newsType+"'");
		}
		
		String title =  siteNews.getTitle();
		if(title != null && title !=""){
			sqlBuffer.append(" and title = '"+title+"'");
		}
		
		String createUser =  siteNews.getCreateUser();
		if(createUser != null && createUser !=""){
			sqlBuffer.append(" and create_user = '"+createUser+"'");
		}
		
		String isRun =  siteNews.getIsRun();
		if(isRun != null && isRun !=""){
			sqlBuffer.append(" and is_run = '"+isRun+"'");
		}
		
		String belonTeam =  siteNews.getBelonTeam();
		if(belonTeam != null && belonTeam !=""){
			sqlBuffer.append(" and belon_team = '"+belonTeam+"'");
		}
		
		String isAudit =  siteNews.getIsAudit();
		if(isAudit != null && isAudit !=""){
			sqlBuffer.append(" and is_audit = '"+isAudit+"'");
		}
		
		String isCanApply =  siteNews.getIsCanApply();
		if(isCanApply != null && isCanApply !=""){
			sqlBuffer.append(" and a.is_can_apply = '"+isCanApply+"'");
		}
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new SiteNews();
			entity.setId(rs.getString("id"));
			entity.setNewsType(rs.getString("news_type"));
			entity.setTitle(rs.getString("title"));
			entity.setPic(rs.getString("pic"));
			entity.setShortContent(rs.getString("short_content"));
			entity.setContent(rs.getString("content"));
			entity.setCreateUser(rs.getString("create_user"));
			entity.setCreateTime(rs.getTimestamp("create_time"));
			entity.setIsRun(rs.getString("is_run"));
			entity.setBelonTeam(rs.getString("belon_team"));
			entity.setIsAudit(rs.getString("is_audit"));
			entity.setAcStartTime(rs.getTimestamp("ac_start_time"));
			entity.setAcEndTime(rs.getTimestamp("ac_end_time"));
			entity.setTeamName(rs.getString("is_can_apply"));
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
	public SiteNews getById(Connection con,String id)throws Exception{
		SiteNews siteNews=null;
		String sql="select * from db_site_news where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			siteNews=new SiteNews();
			siteNews.setId(rs.getString("id"));
			siteNews.setNewsType(rs.getString("news_type"));
			siteNews.setTitle(rs.getString("title"));
			siteNews.setPic(rs.getString("pic"));
			siteNews.setShortContent(rs.getString("short_content"));
			siteNews.setContent(rs.getString("content"));
			siteNews.setCreateUser(rs.getString("create_user"));
			siteNews.setCreateTime(rs.getTimestamp("create_time"));
			siteNews.setIsRun(rs.getString("is_run"));
			siteNews.setBelonTeam(rs.getString("belon_team"));
			siteNews.setIsAudit(rs.getString("is_audit"));
			siteNews.setAcStartTime(rs.getTimestamp("ac_start_time"));
			siteNews.setAcEndTime(rs.getTimestamp("ac_end_time"));
			siteNews.setTeamName(rs.getString("is_can_apply"));
		}
		return siteNews;
	}
	
	//获取总数 分页使用
	public int count(Connection con,SiteNews siteNews)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from db_site_news a where 1=1");
		//拼接分页条件
		String newsType =  siteNews.getNewsType();
		if(newsType != null && newsType !=""){
			sqlBuffer.append(" and a.news_type = '"+newsType+"'");
		}
		
		String title =  siteNews.getTitle();
		if(title != null && title !=""){
			sqlBuffer.append(" and a.title = '"+title+"'");
		}
		
		String createUser =  siteNews.getCreateUser();
		if(createUser != null && createUser !=""){
			sqlBuffer.append(" and a.create_user = '"+createUser+"'");
		}
		
		String isRun =  siteNews.getIsRun();
		if(isRun != null && isRun !=""){
			sqlBuffer.append(" and a.is_run = '"+isRun+"'");
		}
		
		String belonTeam =  siteNews.getBelonTeam();
		if(belonTeam != null && belonTeam !=""){
			sqlBuffer.append(" and a.belon_team = '"+belonTeam+"'");
		}
		
		String isAudit =  siteNews.getIsAudit();
		if(isAudit != null && isAudit !=""){
			sqlBuffer.append(" and a.is_audit = '"+isAudit+"'");
		}
		String isCanApply =  siteNews.getIsCanApply();
		if(isCanApply != null && isCanApply !=""){
			sqlBuffer.append(" and a.is_can_apply = '"+isCanApply+"'");
		}
		
		String sqlStr = siteNews.getSqlStr();
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