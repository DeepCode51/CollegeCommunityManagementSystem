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
			<li class="active"><a href="${adminPath}/activeStu?method=list">活动申请列表</a></li>
			<%-- <li><a href="${adminPath}/activeStu?method=form">活动申请添加</a></li> --%>
		</ul>
		<br />
		<c:if test="">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>提示!</strong> ${msg}
			</div>
		</c:if>
		<form class="form-inline" id="searchForm" action="${adminPath}/activeStu?method=list" method="post">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo }">
			
				<div class="form-group" style="display: none;">
				<label for="exampleInputName2">活动编号</label>
					<input type="text" class="form-control" name="activeId" id="activeId" placeholder="请输入活动编号" value="${activeId}">
				</div>
				<div class="form-group" style="display: none;">
				<label for="exampleInputName2">学生编号</label>
					<input type="text" class="form-control" name="stuId" id="stuId" placeholder="请输入学生编号" value="${stuId}">
				</div>
				<div class="form-group">
				<label for="exampleInputName2">学生姓名</label>
					<input type="text" class="form-control" name="stuName" id="stuName" placeholder="请输入学生姓名" value="${stuName}">
				</div>
				<div class="form-group" style="display: none;">
				<label for="exampleInputName2">报名状态</label>
					<input type="text" class="form-control" name="type" id="type" placeholder="请输入报名状态" value="${type}">
				</div>
				<div class="form-group">
				<label for="exampleInputName2">是否正常出息</label>
					<select class="form-control" id="isArrive" name="isArrive" >
						<option value="">-未选择-</option>
			 			<option value="0" <c:if test="${isArrive=='0'}">selected="selected"</c:if> >未知</option>
			 			<option value="1" <c:if test="${isArrive=='1'}">selected="selected"</c:if> >按时参加</option>
			 			<option value="2" <c:if test="${isArrive=='2'}">selected="selected"</c:if> >未按时参加</option>
			 		</select>
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
					<th>活动编号</th>
					<!-- <th>学生编号</th> -->
					<th>学生姓名</th>
					<th>联系电话</th>
					<th>报名状态</th>
					<th>是否正常出息</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="activeStu" varStatus="num">
					<tr>
						<td>${num.index+1+page.pageSize*(page.pageNo-1) }</td>
						
						<td><a href="${adminPath}/activeStu?method=form&id=${activeStu.id}">
							${activeStu.activeName}
						</a></td>
						<%-- <td>
							${activeStu.stuId}
						</td> --%>
						<td>
							${activeStu.stuName}
						</td>
						<td>
							${activeStu.phone}
						</td>
						<td>
							<%-- ${activeStu.type} --%>报名成功
						</td>
						<td>
							<c:if test="${activeStu.isArrive=='0'}"><span style="color: grey;">未知</span></c:if>
							<c:if test="${activeStu.isArrive=='1'}"><span style="color: green;">按时参加</span></c:if>
							<c:if test="${activeStu.isArrive=='2'}"><span style="color: red;">未按时参加</span></c:if>
						</td>
						
						
						<td>
							<a href="${adminPath }/activeStu?method=form&id=${activeStu.id}">
								<button class="btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									编辑
								</button>
							</a> 
							<a href="${adminPath }/activeStu?method=delete&id=${activeStu.id}" onclick="return confirm('确认要删除吗？', this.href)">
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