package com.so.team.service.impl;


import java.sql.Connection;
import java.util.List;

import com.so.team.bean.SiteNews;
import com.so.team.dao.SiteNewsDao;
import com.so.team.service.SiteNewsService;
import com.so.utils.DbUtil;
import com.so.utils.Page;


/**
 * 新闻活动DAO接口
 * @author admin
 * @version 2018-03-26
 */
public class SiteNewsServiceImpl  implements SiteNewsService  {
	
	SiteNewsDao siteNewsDao = new SiteNewsDao();
	
	@Override
	public int add(SiteNews siteNews) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Integer result =siteNewsDao.add(con, siteNews);
			DbUtil.closeCon(con);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int delete = siteNewsDao.delete(con, id);
			DbUtil.closeCon(con);
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(SiteNews siteNews) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int update = siteNewsDao.update(con, siteNews);
			DbUtil.closeCon(con);
			return update;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Page<SiteNews> page(SiteNews siteNews, Page<SiteNews> page) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int count = siteNewsDao.count(con,siteNews);
			page.setCount(count);
			page.setPrev(page.getPageNo()-1);
			page.setNext(page.getPageNo() + 1);// 下一页
			page.setLast((count - 1) / page.getPageSize() + 1);// 总也数
			List<SiteNews> list = siteNewsDao.list(con, siteNews, page);
			page.setList(list);
			DbUtil.closeCon(con);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SiteNews getById(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			SiteNews siteNews = siteNewsDao.getById(con, id);
			DbUtil.closeCon(con);
			return siteNews;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<SiteNews> findAll(SiteNews siteNews) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			List<SiteNews> list = siteNewsDao.findAll(con, siteNews);
			DbUtil.closeCon(con);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
}