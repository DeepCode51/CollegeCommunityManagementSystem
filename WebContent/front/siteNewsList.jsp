<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>内蒙古民族大学-社团管理</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<link href="${adminPath}/front/css/styles.css" rel="stylesheet">
<link rel="shortcut icon" href="${adminPath}/front/favicon.ico">
<script type="text/javascript" src="${adminPath }/static/js/jquery-1.12.4.min.js"></script>
<link href="${adminPath }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${adminPath }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
function page(n){
	$("#pageNo").val(n);
	$("#searchForm").submit();
   	return false;
   }
</script>
</head>
<body>
<%@ include file="/front/top.jsp"%>
<div class="warp">
  <%-- <div class="ads">
    <img alt="" src="${adminPath}/front/images/banner2.jpg" width="100%"/>
  </div> --%><br>
  <form class="form-inline" id="searchForm" action="http://localhost:8080/team/f?action=siteNewsList" method="post">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo }">
				<div class="form-group" style="display: none;">
				<label for="exampleInputName2">新闻状态</label>
					<input type="text" class="form-control" name="newsType" id="newsType" placeholder="请输入新闻状态" value="${newsType}">
				</div>
				<div class="form-group">
				<label for="exampleInputName2">标题</label>
					<input type="text" class="form-control" name="title" id="title" placeholder="请输入标题" value="${title}">
				</div>
			
			<button type="submit" class="btn btn-primary btn-sm">
				<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
				查询
			</button>
		</form><br>
  <div class="listpage clearfix">
   	<table class="table table-hover table-bordered">
			<thead>
			<c:if test="${newsType=='1'}">
				<tr>
					<th>新闻标题</th>
					<th>发布人</th>
					<th>发布时间</th>
				</tr>
			</c:if>
			<c:if test="${newsType=='2'}">
				<tr>
					<th>活动主题</th>
					<th>活动开始时间</th>
					<th>活动结束时间</th>
					<th>提出人</th>
					<th>所属社团</th>
				</tr>
			</c:if>
			<c:if test="${newsType=='3'}">
				<tr>
					<th>风采主题</th>
					<th>发布人</th>
				</tr>
			</c:if>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="siteNews" varStatus="num">
					<tr>
						
						<td><a href="${adminPath}/f?action=detail&id=${siteNews.id}">
							${siteNews.title}
						</a></td>
						<c:if test="${newsType=='2'}">
						<td>
							<fmt:formatDate value="${siteNews.acStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<fmt:formatDate value="${siteNews.acEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						</c:if>	
						<td>
							${siteNews.createUser}
						</td>
						<c:if test="${newsType=='1'}">
						<td>
							<fmt:formatDate value="${siteNews.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						</c:if>
						<c:if test="${newsType=='2'}">
						<td>
						<a href="${adminPath }/f?action=teamDetail&id=${siteNews.belonTeam}" target="_blank" title="查看社团详情">
							${fns:getFieldNameById(siteNews.belonTeam,'team_name', 'db_teams')}
						</a>
						</td>
						</c:if>
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
<%@ include file="/front/footer.jsp" %>
<script type="text/javascript" src="${adminPath}/front/js/jquery.min.js"></script>
<script type="text/javascript">
	function applayLeader(id){
		$.post("${adminPath}/f?action=applayLeader&id="+id,function(data){
			if(data==1){
				alert("请先登录");
			}else if(data==2){
				alert("已提交过申请，请耐心等待审核结果！");
			}else{
				alert("申请成功，请耐心等待审核结果。");
			}
		})
	}
	function applayTeam(id){
		$.post("${adminPath}/f?action=applayTeam&id="+id,function(data){
			if(data==1){
				alert("请先登录");
			}else if(data==2){
				alert("已提交过申请，请耐心等待审核结果！");
			}else if(data==3){
				alert("已提交过申请，请耐心等待审核结果！");//已是该社团成员，不能重复申请
			}else{
				alert("申请成功，请耐心等待审核结果。");
			}
		})
	}
</script>
</body>
</html>