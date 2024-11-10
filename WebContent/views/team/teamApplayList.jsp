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
			<li class="active"><a href="${adminPath}/teamApplay?method=list">社团申请列表</a></li>
			<%-- <li><a href="${adminPath}/teamApplay?method=form">社团申请添加</a></li> --%>
		</ul>
		<br />
		<c:if test="">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>提示!</strong> ${msg}
			</div>
		</c:if>
		<form class="form-inline" id="searchForm" action="${adminPath}/teamApplay?method=list" method="post">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo }">
			
				<div class="form-group">
				<label for="exampleInputName2">申请社团编号</label>
					<select class="form-control" id="applayTeam" name="applayTeam">
						<option value="">-未选择-</option>
			 			<c:forEach items="${teamList }" var="team">
			 				<option value="${team.id }" <c:if test="${applayTeam==team.id}">selected="selected"</c:if> >${team.teamName }</option>
			 			</c:forEach>
			 		</select>
				</div>
				<%-- 
				<div class="form-group">
				<label for="exampleInputName2">申请学生</label>
					<input type="text" class="form-control" name="applayStu" id="applayStu" placeholder="请输入申请学生" value="${applayStu}">
				</div> --%>
				<div class="form-group">
				<label for="exampleInputName2">申请状态</label>
					<select class="form-control" id="applayType" name="applayType" >
						<option value="">-未选择-</option>
			 			<option value="0" <c:if test="${applayType=='0'}">selected="selected"</c:if> >未审核</option>
			 			<option value="1" <c:if test="${applayType=='1'}">selected="selected"</c:if> >审核通过</option>
			 			<option value="2" <c:if test="${applayType=='2'}">selected="selected"</c:if> >审核不通过</option>
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
					<th>申请社团编号</th>
					<th>申请学生</th>
					<th>申请理由</th>
					<th>申请时间</th>
					<th>申请状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="teamApplay" varStatus="num">
					<tr>
						<td>${num.index+1+page.pageSize*(page.pageNo-1) }</td>
						
						<td><a href="${adminPath}/teamApplay?method=form&id=${teamApplay.id}">
							${teamApplay.applayTeamName}
						</a></td>
						<td>
							${teamApplay.applayStuName}
						</td>
						<td>
							${teamApplay.applayContent}
						</td>
						<td>
							<fmt:formatDate value="${teamApplay.applayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<c:if test="${teamApplay.applayType=='0'}"><span style="color: grey;">未审核</span></c:if>
							<c:if test="${teamApplay.applayType=='1'}"><span style="color: green;">审核通过</span></c:if>
							<c:if test="${teamApplay.applayType=='2'}"><span style="color: red;">审核不通过</span></c:if>
						</td>
						
						
						<td>
						<c:if test="${teamApplay.applayType=='0'}">
							<a href="${adminPath }/teamApplay?method=form&id=${teamApplay.id}">
								<button class="btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									审核
								</button>
							</a> 
						</c:if>
							<a href="${adminPath }/teamApplay?method=delete&id=${teamApplay.id}" onclick="return confirm('确认要删除吗？', this.href)">
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