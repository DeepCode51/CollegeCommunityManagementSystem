package com.so.team.service;

import java.util.List;
import com.so.team.bean.Student;

import com.so.utils.Page;

/**
 * 学生DAO接口
 * @author admin
 * @version 2018-03-26
 */
public interface StudentService {
	
	//添加
	public int add(Student student);
	//删除
	public int delete(String id);
	//修改
	public int update(Student student);
	//查询分页
	public Page<Student> page(Student student,Page<Student> page);
	//根据ID查询
	public Student getById(String id);
	//查询所有
	public List<Student> findAll(Student student);
		
}