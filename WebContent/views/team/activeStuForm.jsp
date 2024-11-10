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
			<li><a href="${adminPath}/activeStu?method=list">活动申请列表</a></li>
			<li class="active"><a href="${adminPath}/activeStu?method=form&id=${activeStu.id}">活动申请添加</a></li>
		</ul>
		<br />
		<form class="form-horizontal" role="form" action="${adminPath}/activeStu?method=save" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${activeStu.id}">
			 
			 <div class="form-group"  style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">活动编号</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="activeId" name="activeId" placeholder="请输入活动编号" value="${activeStu.activeId}">
		    	</div>
			 </div>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">学生编号</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="stuId" name="stuId" placeholder="请输入学生编号" value="${activeStu.stuId}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">学生姓名</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="stuName" name="stuName" placeholder="请输入学生姓名" value="${activeStu.stuName}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">联系电话</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="phone" name="phone" placeholder="请输入联系电话" value="${activeStu.phone}">
		    	</div>
			 </div>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">报名状态</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="type" name="type" placeholder="请输入报名状态" value="${activeStu.type}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">是否正常出息</label>
			 	<div class="col-sm-5">
			 		<select class="form-control" id="isArrive" name="isArrive" >
			 			<option value="0" <c:if test="${activeStu.isArrive=='0'}">selected="selected"</c:if> >未知</option>
			 			<option value="1" <c:if test="${activeStu.isArrive=='1'}">selected="selected"</c:if> >按时参加</option>
			 			<option value="2" <c:if test="${activeStu.isArrive=='2'}">selected="selected"</c:if> >未按时参加</option>
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