package com.so.team.service;

import java.util.List;
import com.so.team.bean.Message;
import com.so.team.bean.Student;
import com.so.utils.Page;

/**
 * 留言板DAO接口
 * @author admin
 * @version 2018-03-26
 */
public interface MessageService {
	
	//添加
	public int add(Message message);
	//删除
	public int delete(String id);
	//修改
	public int update(Message message);
	//查询分页
	public Page<Message> page(Message message,Page<Message> page);
	//根据ID查询
	public Message getById(String id);
	//查询所有
	public List<Message> findAll(Message message);
	
	//根据留言名字 查询学生id
	public Student findByName(String name);
		
}