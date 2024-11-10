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
			<li class="active"><a href="#">修改密码</a></li>
		</ul>
		<br />
		<form class="form-horizontal" role="form" action="${adminPath}/user?method=repassword" method="post" >
			<input type="hidden" name="id" value="${login.id}">
			 
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">用户名</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="username" readonly="readonly" name="username" placeholder="请输入用户名" value="${login.username}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">旧密码</label>
			 	<div class="col-sm-5">
			 			<input type="password" class="form-control" id="password" name="password" placeholder="请输入旧密码" value="${password}">
		    	</div>
			 </div>
			<div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">新密码</label>
			 	<div class="col-sm-5">
			 			<input type="password" class="form-control" id="password" name="password1" placeholder="请输入新密码" value="${password1}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">确认密码</label>
			 	<div class="col-sm-5">
			 			<input type="password" class="form-control" id="password" name="password2" placeholder="请输入确认密码" value="${password2}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label"></label>
			 	<div class="col-sm-5">
		 			 <span style="color: red;">${msg }</span>
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