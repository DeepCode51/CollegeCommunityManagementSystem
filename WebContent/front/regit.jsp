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
<%-- <link rel="shortcut icon" href="${adminPath}/front/favicon.ico"> --%>
</head>
<body>
<%@ include file="/views/include/head.jsp"%>
<%@ include file="/front/top.jsp"%>
<div class="warp">
   <br>
  <div class="" style="text-align: center;">
  		<h2>学生注册</h2>
   		<form class="form-horizontal" role="form" action="${adminPath}/f?action=regit" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${student.id}">
			 
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">登录名称</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="loginName" name="loginName" placeholder="请输入登录名称" value="${student.loginName}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">登录密码</label>
			 	<div class="col-sm-5">
			 			<input type="password" class="form-control" id="loginPassword" name="loginPassword" placeholder="请输入登录密码" value="${student.loginPassword}">
		    	</div>
			 </div>
			 <%-- <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">注册时间</label>
			 	<div class="col-sm-5">
			 			<input name="regitTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${student.regitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
		    	</div>
			 </div> --%>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">审核状态</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="auditType" name="auditType" placeholder="请输入审核状态" value="0">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">学生姓名</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="stuName" name="stuName" placeholder="请输入学生姓名" value="${student.stuName}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">性别</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="sex" name="sex" placeholder="请输入性别" value="${student.sex}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">学院</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="schoolRoom" name="schoolRoom" placeholder="请输入学院" value="${student.schoolRoom}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">班级</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="classRoom" name="classRoom" placeholder="请输入班级" value="${student.classRoom}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">联系电话</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="phone" name="phone" placeholder="请输入联系电话" value="${student.phone}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">头像</label>
			 	<div class="col-sm-5">
			 			<input type="hidden" id="pic" name="pic" value="${student.pic}">
			 			<input type="file"  name="pic">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">个人说明</label>
			 	<div class="col-sm-5">
			 			<textarea rows="3" class="form-control" id="remark" name="remark">${student.remark}</textarea>
		    	</div>
			 </div>
			  <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label"></label>
			 	<div class="col-sm-5">
			 			${msg }
		    	</div>
			 </div>

			 <div class="form-group">
			    <label for="sort" class="col-sm-3 control-label"></label>
			    <div class="col-sm-5">
			      	<input type="submit" class="btn btn-success btn-sm" value="注册">
			      	<input type="button" class="btn btn-default btn-sm" value="返回" onclick="history.go(-1)">
			    </div>
			 </div>
		</form>
  </div>
</div>
<%@ include file="/front/footer.jsp" %>
<script type="text/javascript" src="${adminPath}/front/js/jquery.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>