<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/views/include/taglib.jsp"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言</title>
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
			<li><a href="${adminPath}/message?method=list">留言板列表</a></li>
			<li class="active"><a href="${adminPath}/message?method=form&id=${message.id}">留言板添加</a></li>
		</ul>
		<br />
		<form class="form-horizontal" role="form" action="${adminPath}/message?method=save" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${message.id}">
			 
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">留言内容</label>
			 	<div class="col-sm-5">
			 			<textarea rows="3" class="form-control" id="content" name="content">${message.content}</textarea>
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">留言时间</label>
			 	<div class="col-sm-5">
			 			<input name="createTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${message.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">留言学生</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="createStu" name="createStu" placeholder="请输入留言学生" value="${message.createStu}">
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