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
			<c:if test="${newsType=='1'}">
				<li class="active"><a href="${adminPath}/siteNews?method=list&newsType=${newsType}">新闻列表</a></li>
				<li><a href="${adminPath}/siteNews?method=form&newsType=${newsType}">新闻添加</a></li>
			</c:if>
			<c:if test="${newsType=='2'}">
				<li class="active"><a href="${adminPath}/siteNews?method=list&newsType=${newsType}">活动列表</a></li>
				<li><a href="${adminPath}/siteNews?method=form&newsType=${newsType}">活动添加</a></li>
			</c:if>
			<c:if test="${newsType=='3'}">
				<li class="active"><a href="${adminPath}/siteNews?method=list&newsType=${newsType}">风采列表</a></li>
				<li><a href="${adminPath}/siteNews?method=form&newsType=${newsType}">风采添加</a></li>
			</c:if>
		</ul>
		<br />
		<c:if test="">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>提示!</strong> ${msg}
			</div>
		</c:if>
		<form class="form-inline" id="searchForm" action="${adminPath}/siteNews?method=list" method="post">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo }">
			
				<div class="form-group" style="display: none;">
				<label for="exampleInputName2">新闻状态</label>
					<input type="text" class="form-control" name="newsType" id="newsType" placeholder="请输入新闻状态" value="${newsType}">
				</div>
				<div class="form-group">
				<label for="exampleInputName2">标题</label>
					<input type="text" class="form-control" name="title" id="title" placeholder="请输入标题" value="${title}">
				</div>
				<div class="form-group" style="display: none;">
				<label for="exampleInputName2">创建人</label>
					<input type="text" class="form-control" name="createUser" id="createUser" placeholder="请输入创建人" value="${createUser}">
				</div>
				<div class="form-group">
				<label for="exampleInputName2">是否推荐</label>
					<select class="form-control" id="isRun" name="isRun">
						<option value="">-未选择-</option>
			 			<option value="0" <c:if test="${isRun=='0'}">selected="selected"</c:if> >否</option>
			 			<option value="1" <c:if test="${isRun=='1'}">selected="selected"</c:if> >是</option>
			 		</select>
				</div>
				<div class="form-group">
				<label for="exampleInputName2">所属社团</label>
					<select class="form-control" id="belonTeam" name="belonTeam">
						<option value="">-未选择-</option>
			 			<c:forEach items="${teamList }" var="team">
			 				<option value="${team.id }" <c:if test="${belonTeam==team.id}">selected="selected"</c:if> >${team.teamName }</option>
			 			</c:forEach>
			 		</select>
				</div>
				<div class="form-group">
				<label for="exampleInputName2">是否审核</label>
					<select class="form-control" id="isAudit" name="isAudit">
						<option value="">-未选择-</option>
			 			<option value="0" <c:if test="${isAudit=='0'}">selected="selected"</c:if> >未审核</option>
			 			<option value="1" <c:if test="${isAudit=='1'}">selected="selected"</c:if> >审核通过</option>
			 			<option value="2" <c:if test="${isAudit=='2'}">selected="selected"</c:if> >审核未通过</option>
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
			<c:if test="${newsType=='1'}">
				<tr>
					<th>序号</th>
					<th>新闻标题</th>
					<th>新闻主图</th>
					<!-- <th>新闻简介</th> -->
					<th>创建人</th>
					<th>创建时间</th>
					<th>是否推荐</th>
					<th>所属社团</th>
					<th>是否审核</th>
					<th>操作</th>
				</tr>
			</c:if>
			<c:if test="${newsType=='2'}">
				<tr>
					<th>序号</th>
					<th>活动主题</th>
					<th>宣传图</th>
					<th>活动开始时间</th>
					<th>活动结束时间</th>
					<!-- <th>活动简介</th> -->
					<th>提出人</th>
					<c:if test="${newsType=='1'}"><th>提出时间</th></c:if>
					<th>是否推荐</th>
					<th>所属社团</th>
					<th>是否审核</th>
					<th>操作</th>
				</tr>
			</c:if>
			<c:if test="${newsType=='3'}">
				<tr>
					<th>序号</th>
					<th>风采主题</th>
					<th>宣传图</th>
					<th>发布人</th>
					<th>所属社团</th>
					<th>是否审核</th>
					<th>操作</th>
				</tr>
			</c:if>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="siteNews" varStatus="num">
					<tr>
						<td>${num.index+1+page.pageSize*(page.pageNo-1) }</td>
						
						<td><a href="${adminPath}/siteNews?method=form&id=${siteNews.id}">
							${siteNews.title}
						</a></td>
						<td><img alt="" src="${siteNews.pic}" style="width: 60px;height: 60px;">
						<c:if test="${newsType=='2'}">
						<td>
							<fmt:formatDate value="${siteNews.acStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<fmt:formatDate value="${siteNews.acEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						</c:if>	
						</td>
						<%-- <td>
							${siteNews.shortContent}
						</td> --%>
						<td>
							${siteNews.createUser}
						</td>
						<c:if test="${newsType=='1'}">
						<td>
							<fmt:formatDate value="${siteNews.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						</c:if>
						<c:if test="${newsType!='3'}">
						<td>
							${siteNews.isRun=="1"?"是":"否"}
						</td>
						</c:if>
						<td>
							${fns:getFieldNameById(siteNews.belonTeam,'team_name', 'db_teams')}
						</td>
						<td>
							<c:if test="${siteNews.isAudit=='0'}"><span style="color: grey;">未审核</span></c:if>
							<c:if test="${siteNews.isAudit=='1'}"><span style="color: green;">审核通过</span></c:if>
							<c:if test="${siteNews.isAudit=='2'}"><span style="color: red;">审核不通过</span></c:if>
						</td>
						
						
						<td>
							<c:if test="${newsType=='2'}">
							<a href="${adminPath }/activeStu?method=list&activeId=${siteNews.id}">
								<button class="btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									申请列表
								</button>
							</a> 
							</c:if>
							<a href="${adminPath }/siteNews?method=form&id=${siteNews.id}">
								<button class="btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									编辑
								</button>
							</a> 
							<a href="${adminPath }/siteNews?method=delete&id=${siteNews.id}" onclick="return confirm('确认要删除吗？', this.href)">
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