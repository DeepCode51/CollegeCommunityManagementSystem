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
			<li class="active"><a href="${adminPath}/teams?method=list">社团列表</a></li>
			<%-- <li><a href="${adminPath}/teams?method=form">社团添加</a></li> --%>
		</ul>
		<br />
		<c:if test="">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>提示!</strong> ${msg}
			</div>
		</c:if>
		<form class="form-inline" id="searchForm" action="${adminPath}/teams?method=list" method="post">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo }">
			
				<div class="form-group">
				<label for="exampleInputName2">社团名字</label>
					<input type="text" class="form-control" name="teamName" id="teamName" placeholder="请输入社团名字" value="${teamName}">
				</div>
				<div class="form-group">
				<label for="exampleInputName2">创建人（社长）</label>
					<input type="text" class="form-control" name="buildStu" id="buildStu" placeholder="请输入创建人（社长）" value="${buildStu}">
				</div>
				<div class="form-group">
				<label for="exampleInputName2">审核状态</label>
					<select class="form-control" id="auditType" name="auditType" >
						<option value="">-未选择-</option>
			 			<option value="0" <c:if test="${auditType=='0'}">selected="selected"</c:if> >未审核</option>
			 			<option value="1" <c:if test="${auditType=='1'}">selected="selected"</c:if> >审核通过</option>
			 			<option value="2" <c:if test="${auditType=='2'}">selected="selected"</c:if> >审核不通过</option>
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
					<th>社团名字</th>
					<th>社团宣传图</th>
					<th>成立时间</th>
					<th>创建人（社长）</th>
					<th>审核状态</th>
					<th>成员数</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="teams" varStatus="num">
					<tr>
						<td>${num.index+1+page.pageSize*(page.pageNo-1) }</td>
						
						<td><a href="${adminPath}/teams?method=form&id=${teams.id}">
							${teams.teamName}
						</a></td>
						<td><img alt="" src="/team${teams.pic}" style="width: 60px;height: 60px;">
						</td>
						<td>
							<fmt:formatDate value="${teams.buildTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							${teams.studentName}
						</td>
						<td>
							<c:if test="${teams.auditType=='0'}"><span style="color: grey;">未审核</span></c:if>
							<c:if test="${teams.auditType=='1'}"><span style="color: green;">审核通过</span></c:if>
							<c:if test="${teams.auditType=='2'}"><span style="color: red;">审核不通过</span></c:if>
						</td>
						<td>
							${teams.members}
						</td>
						<td>
							${teams.remark}
						</td>
						
						
						<td>
							<a href="${adminPath }/teams?method=form&id=${teams.id}">
								<button class="btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									编辑
								</button>
							</a> 
							<a href="${adminPath }/teams?method=delete&id=${teams.id}" onclick="return confirm('确认要删除吗？', this.href)">
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