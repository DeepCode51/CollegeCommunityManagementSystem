package com.so.team.service;

import java.util.List;
import com.so.team.bean.SiteNews;

import com.so.utils.Page;

/**
 * 新闻活动DAO接口
 * @author admin
 * @version 2018-03-26
 */
public interface SiteNewsService {
	
	//添加
	public int add(SiteNews siteNews);
	//删除
	public int delete(String id);
	//修改
	public int update(SiteNews siteNews);
	//查询分页
	public Page<SiteNews> page(SiteNews siteNews,Page<SiteNews> page);
	//根据ID查询
	public SiteNews getById(String id);
	//查询所有
	public List<SiteNews> findAll(SiteNews siteNews);
		
}