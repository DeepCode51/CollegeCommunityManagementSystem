package com.so.utils;

import java.sql.Connection;
import java.util.Date;
import java.util.UUID;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.so.team.bean.SiteNews;
import com.so.team.dao.SiteNewsDao;

/**
 * 数据库连接工具
 * @version 2018-12-24
 * @author suyan
 *
 */
public class DbUtil {
	
	private DbUtil(){
		System.out.println("初始化连接池DB....");
	}
	
	private volatile static DbUtil dbUtil = null;
	
	private static ComboPooledDataSource cpds = null;
	
	public static DbUtil getDbUtil() throws Exception {
		if (dbUtil==null) {
			//给类加锁，防止线程并发
			synchronized (DbUtil.class) {
				if (dbUtil==null) {
					//创建实例
					dbUtil = new DbUtil();
					//创建实例
					cpds = new ComboPooledDataSource();
					//加载驱动
					cpds.setDriverClass(PropertiesUtil.getValue("jdbcName"));
					//设置连接地址
					cpds.setJdbcUrl(PropertiesUtil.getValue("dbUrl"));
					//设置数据库用户名
					cpds.setUser(PropertiesUtil.getValue("dbUserName"));
					//设置数据库密码
					cpds.setPassword(PropertiesUtil.getValue("dbPassword"));
					//初始化连接数
					cpds.setInitialPoolSize(Integer.parseInt(PropertiesUtil.getValue("InitialPoolSize")));
					//设置连接池中连接对象的每次增长个数
					cpds.setAcquireIncrement(Integer.parseInt(PropertiesUtil.getValue("AcquireIncrement")));
					//最大连接数
					cpds.setMaxPoolSize(Integer.parseInt(PropertiesUtil.getValue("MaxPoolSize")));
					//最小连接数
					cpds.setMinPoolSize(Integer.parseInt(PropertiesUtil.getValue("MinPoolSize")));
				}
				
			}
		}
		return dbUtil;
	}

	public Connection getCon()throws Exception{
		/*Class.forName(PropertiesUtil.getValue("jdbcName"));
		Connection con=DriverManager.getConnection(PropertiesUtil.getValue("dbUrl"), PropertiesUtil.getValue("dbUserName"), PropertiesUtil.getValue("dbPassword"));
		*/
		return cpds.getConnection();
	}
	
	public static void closeCon(Connection con)throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		try {
				Connection con = DbUtil.getDbUtil().getCon();
				System.out.println("链接成功");
				//造数据
				SiteNewsDao siteNewsDao = new SiteNewsDao();
				SiteNews byId = siteNewsDao.getById(con, "90315e320b054cfcb1421c8ff39f7aac");
				for (int i= 10;i<20;i++) {
					SiteNews siteNews = byId;
					siteNews.setId(UUID.randomUUID().toString().replace("-", ""));
					siteNews.setTitle(byId.getTitle()+i);
					siteNews.setCreateTime(new Date());
					siteNewsDao.add(con, siteNews);
				}
				
				
				con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}
