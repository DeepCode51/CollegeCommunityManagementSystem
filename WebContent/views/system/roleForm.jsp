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
			<li><a href="${adminPath}/role?method=list">角色列表</a></li>
			<li class="active"><a href="${adminPath}/role?method=form&id=${role.id}">角色添加</a></li>
		</ul>
		<br />
		<form class="form-horizontal" role="form" action="${adminPath}/role?method=save" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${role.id}">
			 
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">角色名称</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="roleName" name="roleName" placeholder="请输入角色名称" value="${role.roleName}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">角色标志</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="roleFlag" name="roleFlag" placeholder="请输入角色标志" value="${role.roleFlag}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">备注</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="introduce" name="introduce" placeholder="请输入备注" value="${role.introduce}">
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