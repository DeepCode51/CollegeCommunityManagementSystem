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
			<li><a href="${adminPath}/teams?method=list">社团列表</a></li>
			<li class="active"><a href="${adminPath}/teams?method=form&id=${teams.id}">社团添加</a></li>
		</ul>
		<br />
		<form class="form-horizontal" role="form" action="${adminPath}/teams?method=save" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${teams.id}">
			 
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">社团名字</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="teamName" name="teamName" placeholder="请输入社团名字" value="${teams.teamName}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">社团宣传图</label>
			 	<div class="col-sm-5">
			 			<input type="hidden" id="pic" name="pic" value="${teams.pic}">
			 			<input type="file"  name="pic">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">社团介绍</label>
			 	<div class="col-sm-8">
			 			<textarea rows="10" class="form-control xheditor" id="teamIntro" name="teamIntro">${teams.teamIntro}</textarea>
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">成立时间</label>
			 	<div class="col-sm-5">
			 			<input name="buildTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${teams.buildTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
		    	</div>
			 </div>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">创建人（社长）</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="buildStu" name="buildStu" placeholder="请输入创建人（社长）" value="${teams.buildStu}">
		    	</div>
			 </div>
			 <c:if test="${login.role2.roleFlag=='admin' }">
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">审核状态</label>
			 	<div class="col-sm-5">
			 		<select class="form-control" id="auditType" name="auditType" >
			 			<option value="0" <c:if test="${teams.auditType=='0'}">selected="selected"</c:if> >未审核</option>
			 			<option value="1" <c:if test="${teams.auditType=='1'}">selected="selected"</c:if> >审核通过</option>
			 			<option value="2" <c:if test="${teams.auditType=='2'}">selected="selected"</c:if> >审核不通过</option>
			 		</select>
		    	</div>
			 </div>
			 </c:if>
			 <c:if test="${login.role2.roleFlag=='leader' }">
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">审核状态</label>
			 	<div class="col-sm-5">
			 		<select class="form-control" id="auditType" name="auditType" >
			 			<option value="0" <c:if test="${teams.auditType=='0'}">selected="selected"</c:if> >未审核</option>
			 			<option value="1" <c:if test="${teams.auditType=='1'}">selected="selected"</c:if> >审核通过</option>
			 			<option value="2" <c:if test="${teams.auditType=='2'}">selected="selected"</c:if> >审核不通过</option>
			 		</select>
		    	</div>
			 </div>
			 </c:if>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">成员数</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="members" name="members" placeholder="请输入成员数" value="${teams.members}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">备注</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="remark" name="remark" placeholder="请输入备注" value="${teams.remark}">
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