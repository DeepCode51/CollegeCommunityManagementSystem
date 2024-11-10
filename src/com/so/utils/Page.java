/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.so.utils;

import java.util.ArrayList;
import java.util.List;


/**
 * 分页类
 * @author ThinkGem
 * @version 2013-7-2
 * @param <T>
 */
public class Page<T> {
	
	private int pageNo = 1; // 当前页码
	private int pageSize = Integer.parseInt(PropertiesUtil.getValue("pageSize")); // 页面大小，每页显示数量
	
	private long count;// 总记录数
	
	private int first;// 首页索引
	private int last;// 尾页索引
	private int prev;// 上一页索引
	private int next;// 下一页索引
	
	private boolean firstPage;//是否是第一页
	private boolean lastPage;//是否是最后一页

	private int length = 8;// 显示页面长度
	private int slider = 1;// 前后显示页面长度
	
	private List<T> list = new ArrayList<T>();

	public Page() {
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getSlider() {
		return slider;
	}

	public void setSlider(int slider) {
		this.slider = slider;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageSize=" + pageSize + ", count=" + count + ", first=" + first + ", last="
				+ last + ", prev=" + prev + ", next=" + next + ", firstPage=" + firstPage + ", lastPage=" + lastPage
				+ ", length=" + length + ", slider=" + slider + ", list=" + list + "]";
	}
	
	/**
	 * 根据数据库类型拼接不同的分页sql语句
	 * @param sqlBuffer 拼接的sql语句
	 * @param pageNo 当前页
	 * @param pageSize 每页显示的记录
	 * @return 
	 */
	public String pageSql(StringBuffer sqlBuffer,int pageNo,int pageSize){
		StringBuffer stringBuffer = new StringBuffer();
		String dbType = PropertiesUtil.getValue("dbType");
		if ("oracle".equals(dbType)) {
			stringBuffer.append("select * from(select aa.*,rownum rn from( ");
			stringBuffer.append(sqlBuffer.toString());
			stringBuffer.append(") aa where rownum<"+pageNo*(pageSize+1)+" ) where rn>"+(pageNo-1)*pageSize);
		}else if ("mySql".equals(dbType)) {
			stringBuffer.append(sqlBuffer.toString());
			stringBuffer.append(" limit "+((pageNo-1)*pageSize)+","+pageSize);
		}else if ("mssql".equals(dbType)) {
			stringBuffer.append(sqlBuffer.toString());
		}
		//输出sql到控制台
		System.out.println(stringBuffer.toString());
		return stringBuffer.toString();
	}
	
	
	

	
	
}
