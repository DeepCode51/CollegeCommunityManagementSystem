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
			<li><a href="${adminPath}/user?method=list">系统用户列表</a></li>
			<li class="active"><a href="${adminPath}/user?method=form&id=${user.id}">系统用户添加</a></li>
		</ul>
		<br />
		<form class="form-horizontal" role="form" action="${adminPath}/user?method=save" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${user.id}">
			 
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">用户名</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名" value="${user.username}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">头像</label>
			 	<div class="col-sm-5">
			 			<input type="hidden" id="pic" name="pic" value="${user.pic}">
			 			<input type="file" name="pic">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">角色</label>
			 	<div class="col-sm-5">
			 		<select class="form-control" id="role" name="role">
				 		<c:forEach items="${roles }" var="role">
				 			<option value="${role.id }" <c:if test="${user.role==role.id}">selected="selected"</c:if> > ${role.roleName }</option>
				 		</c:forEach>
		 			</select>
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">是否锁定</label>
			 	<div class="col-sm-5">
			 		<select class="form-control" id="isBolck" name="isBolck">
			 			<option value="0" <c:if test="${user.isBolck=='0'}">selected="selected"</c:if> >未锁定</option>
			 			<option value="1" <c:if test="${user.isBolck=='1'}">selected="selected"</c:if> >锁定</option>
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