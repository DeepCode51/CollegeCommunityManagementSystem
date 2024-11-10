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
<%@ include file="/views/include/validation.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		/* $("#submitForm").validate({
			rules : {
				content : {
					required : true
				}
			},
			messages : {
				content : {
					required : "留言内容必填"
				}
			}
		}) */
		
	});
	
	</script>
<%@ include file="/front/top.jsp"%>
<div class="warp">
   <br>
  <div class="" style="text-align: center;">
   		<form class="form-horizontal" id="submitForm" role="form" action="${adminPath}/f?action=saveMsg" method="post">
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">留言内容</label>
			 	<div class="col-sm-5">
			 			<textarea rows="3" class="form-control" id="content" name="content"></textarea>
		    	</div>
			 </div>
			 <div class="form-group"  style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">留言学生</label>
			 	<div class="col-sm-5">
			 			<input type="text" readonly="readonly" class="form-control" id="createStu" name="createStu" placeholder="请输入留言学生" value="${flogin.id}">
		    	</div>
			 </div>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">审核状态</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="auditType" name="auditType" placeholder="请输入审核状态" value="0">
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
</div>
<%@ include file="/front/footer.jsp" %>
<script type="text/javascript" src="${adminPath}/front/js/jquery.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>