<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<%@ include file="/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		
	});
	function page(n){
		$("#pageNo").val(n);
		$("#searchForm").submit();
       	return false;
       }
</script>
</head>

<body>
	<div class="container-fluid">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${adminPath}/message?method=list">留言板列表</a></li>
			<%-- <li><a href="${adminPath}/message?method=form">留言板添加</a></li> --%>
		</ul>
		<br />
		<c:if test="">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>提示!</strong> ${msg}
			</div>
		</c:if>
		<form class="form-inline" id="searchForm" action="${adminPath}/message?method=list" method="post">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo }">
			
				<div class="form-group">
				<label for="exampleInputName2">留言学生</label>
					<input type="text" class="form-control" name="createStu" id="createStu" placeholder="请输入留言学生" value="${createStu}">
				</div>
				<div class="form-group">
				<label for="exampleInputName2">审核状态</label>
					<input type="text" class="form-control" name="auditType" id="auditType" placeholder="请输入审核状态" value="${auditType}">
				</div>
			
			<button type="submit" class="btn btn-primary btn-sm">
				<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
				查询
			</button>
		</form>
		<br />
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>序号</th>
					<th>留言内容</th>
					<th>留言时间</th>
					<th>留言学生</th>
					<th>审核状态</th>
					<th>回复内容</th>
					<th>回复时间</th>
					<th>回复人</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="message" varStatus="num">
					<tr>
						<td>${num.index+1+page.pageSize*(page.pageNo-1) }</td>
						
						<td><a href="${adminPath}/message?method=form&id=${message.id}">
							${message.content}
						</a></td>
						<td>
							<fmt:formatDate value="${message.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							${fns:getFieldNameById(message.createStu,'stu_name', 'db_student')}
						</td>
						<td>
							${message.auditType}
						</td>
						<td>
							${message.replay}
						</td>
						<td>
							<fmt:formatDate value="${message.replayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							${message.replayUser}
						</td>
						
						
						<td>
							<a href="${adminPath }/message?method=form&id=${message.id}">
								<button class="btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									编辑
								</button>
							</a> 
							<a href="${adminPath }/message?method=delete&id=${message.id}" onclick="return confirm('确认要删除吗？', this.href)">
								<button class="btn btn-danger btn-xs">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									删除
								</button>
							</a> 
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		
		<ul class="pagination pagination-sm">
			<c:if test="${page.pageNo!=1 }">
				<li><a href="javascript:void(0)" onclick="page(1)">首页</a></li>
				<li><a href="javascript:void(0)" onclick="page(${page.prev})">&laquo;</a></li>
			</c:if>
			<c:if test="${page.pageNo==1 }">
				<li class="disabled"><a href="#">首页</a></li>
				<li class="disabled"><a href="#">&laquo;</a></li>
			</c:if>
			
			<li class="active"><a href="#">${page.pageNo }</a></li>
			
			<c:if test="${page.pageNo!=page.last }">
			<li><a href="javascript:void(0)" onclick="page(${page.next})">&raquo;</a></li>
			<li><a href="javascript:void(0)" onclick="page(${page.last})">尾页</a></li>
			</c:if>
			<c:if test="${page.pageNo==page.last }">
			<li class="disabled"><a href="#">&raquo;</a></li>
			<li class="disabled"><a href="#">尾页</a></li>
			</c:if>
			<li class="disabled"><a href="#">总页数<span style="color: red;">${page.last}</span></a></li>
			<li class="disabled"><a href="#">当前第<span style="color: red;">${page.pageNo}</span>页</a></li>
			<li class="disabled"><a href="#">共<span style="color: red;">${page.count}</span>条记录</a></li>
		</ul>
	</div>
</body>
</html>