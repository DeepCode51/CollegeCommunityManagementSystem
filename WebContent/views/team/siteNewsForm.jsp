<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<%@ include file="/views/include/head.jsp"%>
<%@ include file="/views/include/validation.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#submitForm").validate({
			rules : {
				title : {
					required : true
				},
				acStartTime : {
					required : true
				},
				acEndTime : {
					required : true
				}
			},
			messages : {
				title : {
					required : "主题必填"
				},
				acStartTime : {
					required : "活动起始时间必填",
				},
				acEndTime : {
					required : "活动截止时间必填",
				}
			}
		})
	});
	function page(n){
		$("#pageNo").val(n);
		$("#searchForm").submit();
       	return false;
       }
</script>

<style type="text/css">
	.error{color:red;}
</style>

</head>

<body>
	<div class="container-fluid">

		<ul class="nav nav-tabs">
			<c:if test="${siteNews.newsType=='1'}">
			<li><a href="${adminPath}/siteNews?method=list&newsType=${siteNews.newsType}">新闻列表</a></li>
			<li class="active"><a href="${adminPath}/siteNews?method=form&id=${siteNews.id}&newsType=${siteNews.newsType}">新闻添加</a></li>
			</c:if>
			<c:if test="${siteNews.newsType=='2'}">
			<li><a href="${adminPath}/siteNews?method=list&newsType=${siteNews.newsType}">活动列表</a></li>
			<li class="active"><a href="${adminPath}/siteNews?method=form&id=${siteNews.id}&newsType=${siteNews.newsType}">活动添加</a></li>
			</c:if>
			<c:if test="${siteNews.newsType=='3'}">
			<li><a href="${adminPath}/siteNews?method=list&newsType=${siteNews.newsType}">风采列表</a></li>
			<li class="active"><a href="${adminPath}/siteNews?method=form&id=${siteNews.id}&newsType=${siteNews.newsType}">风采添加</a></li>
			</c:if>
		</ul>
		<br />
		<form class="form-horizontal" id="submitForm" role="form" action="${adminPath}/siteNews?method=save" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${siteNews.id}">
			 
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">新闻状态</label>
			 	<div class="col-sm-5">
			 		<select class="form-control" id="newsType" name="newsType">
			 			<option value="1" <c:if test="${siteNews.newsType=='1'}">selected="selected"</c:if> >新闻</option>
			 			<option value="2" <c:if test="${siteNews.newsType=='2'}">selected="selected"</c:if> >活动</option>
			 			<option value="3" <c:if test="${siteNews.newsType=='3'}">selected="selected"</c:if> >风采</option>
			 		</select>
		    	</div>
			 </div>
			 <div class="form-group">
			 <c:if test="${siteNews.newsType=='1'}"><label for="name" class="col-sm-3 control-label">新闻主题</label></c:if>
			 <c:if test="${siteNews.newsType=='2'}"><label for="name" class="col-sm-3 control-label">活动主题</label></c:if>
			 <c:if test="${siteNews.newsType=='3'}"><label for="name" class="col-sm-3 control-label">风采主题</label></c:if>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="title" name="title" placeholder="" value="${siteNews.title}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">所属社团</label>
			 	<div class="col-sm-5">
			 		<select class="form-control" id="belonTeam" name="belonTeam">
			 			<c:forEach items="${teamList }" var="team">
			 				<option value="${team.id }" <c:if test="${siteNews.belonTeam==team.id}">selected="selected"</c:if> >${team.teamName }</option>
			 			</c:forEach>
			 		</select>
		    	</div>
			 </div>
			  <c:if test="${siteNews.newsType=='2'}">
			  	 <div class="form-group">
				  	 <label for="name" class="col-sm-3 control-label">活动起始时间</label>
				  	 <div class="col-sm-5">
				 			<input type="text" class="form-control" id="acStartTime" name="acStartTime" 
				 			placeholder="" value="${siteNews.acStartTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" readonly="readonly">
			         </div>
			  	 </div>
			  	 
			  	  <div class="form-group">
				  	 <label for="name" class="col-sm-3 control-label">活动截止时间</label>
				  	 <div class="col-sm-5">
				 			<input type="text" class="form-control" id="acEndTime" name="acEndTime"
				 			 placeholder="" value="${siteNews.acEndTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" readonly="readonly">
			         </div>
			  	 </div>
			  </c:if>
			  
			 <div class="form-group">
			 	<c:if test="${siteNews.newsType=='1'}"><label for="name" class="col-sm-3 control-label">新闻主图</label></c:if>
			 	<c:if test="${siteNews.newsType=='2'}"><label for="name" class="col-sm-3 control-label">活动主图</label></c:if>
			 	<c:if test="${siteNews.newsType=='3'}"><label for="name" class="col-sm-3 control-label">风采主图</label></c:if>
			 	<div class="col-sm-5">
			 			<input type="hidden" id="pic" name="pic" value="${siteNews.pic}">
			 			<input type="file"  name="pic">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<c:if test="${siteNews.newsType=='1'}"><label for="name" class="col-sm-3 control-label">新闻简介</label></c:if>
			 	<c:if test="${siteNews.newsType=='2'}"><label for="name" class="col-sm-3 control-label">活动简介</label></c:if>
			 	<c:if test="${siteNews.newsType=='3'}"><label for="name" class="col-sm-3 control-label">风采简介</label></c:if>
			 	<div class="col-sm-5">
			 			<textarea rows="3" class="form-control" id="shortContent" name="shortContent">${siteNews.shortContent}</textarea>
		    	</div>
			 </div>
			 <div class="form-group">
			 	<c:if test="${siteNews.newsType=='1'}"><label for="name" class="col-sm-3 control-label">新闻内容</label></c:if>
			 	<c:if test="${siteNews.newsType=='2'}"><label for="name" class="col-sm-3 control-label">活动内容</label></c:if>
			 	<c:if test="${siteNews.newsType=='3'}"><label for="name" class="col-sm-3 control-label">风采详情</label></c:if>
			 	<div class="col-sm-5">
			 			<textarea rows="20" class="form-control xheditor" id="content" name="content">${siteNews.content}</textarea>
		    	</div>
			 </div>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">创建人</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="createUser" name="createUser" placeholder="请输入创建人" value="${siteNews.createUser}">
		    	</div>
			 </div>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">创建时间</label>
			 	<div class="col-sm-5">
			 			<input name="createTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${siteNews.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">是否推荐</label>
			 	<div class="col-sm-5">
			 		<select class="form-control" id="isRun" name="isRun">
			 			<option value="0" <c:if test="${siteNews.isRun=='0'}">selected="selected"</c:if> >否</option>
			 			<option value="1" <c:if test="${siteNews.isRun=='1'}">selected="selected"</c:if> >是</option>
			 		</select>
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">是否审核</label>
			 	<div class="col-sm-5">
			 		<select class="form-control" id="isAudit" name="isAudit">
			 			<option value="0" <c:if test="${siteNews.isAudit=='0'}">selected="selected"</c:if> >未审核</option>
			 			<option value="1" <c:if test="${siteNews.isAudit=='1'}">selected="selected"</c:if> >审核通过</option>
			 			<option value="2" <c:if test="${siteNews.isAudit=='2'}">selected="selected"</c:if> >审核未通过</option>
			 		</select>
		    	</div>
			 </div>
			  

			 <div class="form-group">
			    <label for="sort" class="col-sm-3 control-label"></label>
			    <div class="col-sm-5">
			      	<input type="submit" class="btn btn-success btn-sm" value="保存">
			      	<input type="button" class="btn btn-default btn-sm" value="返回" onclick="history.go(-1)">
			    </div>
			 </div>
		</form>
	</div>
</body>
</html>