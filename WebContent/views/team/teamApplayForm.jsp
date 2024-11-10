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
			<li><a href="${adminPath}/teamApplay?method=list">社团申请列表</a></li>
			<li class="active"><a href="${adminPath}/teamApplay?method=form&id=${teamApplay.id}">社团申请添加</a></li>
		</ul>
		<br />
		<form class="form-horizontal" role="form" action="${adminPath}/teamApplay?method=save" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${teamApplay.id}">
			 
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">申请社团编号</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="applayTeam" name="applayTeam" placeholder="请输入申请社团编号" value="${teamApplay.applayTeam}">
		    	</div>
			 </div>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">申请学生</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="applayStu" name="applayStu" placeholder="请输入申请学生" value="${teamApplay.applayStu}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">申请理由</label>
			 	<div class="col-sm-5">${teamApplay.applayContent}
			 			<input type="hidden" class="form-control" id="applayContent" name="applayContent" placeholder="请输入申请理由" value="${teamApplay.applayContent}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">申请时间</label>
			 	<div class="col-sm-5"><fmt:formatDate value="${teamApplay.applayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			 			<input name="applayTime" type="hidden" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${teamApplay.applayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">申请状态</label>
			 	<div class="col-sm-5">
			 		<select class="form-control" id="applayType" name="applayType" >
			 			<option value="0" <c:if test="${teamApplay.applayType=='0'}">selected="selected"</c:if> >未审核</option>
			 			<option value="1" <c:if test="${teamApplay.applayType=='1'}">selected="selected"</c:if> >审核通过</option>
			 			<option value="2" <c:if test="${teamApplay.applayType=='2'}">selected="selected"</c:if> >审核不通过</option>
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