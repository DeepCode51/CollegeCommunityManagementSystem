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
			<li><a href="${adminPath}/menu?method=list">菜单列表</a></li>
			<li class="active"><a href="${adminPath}/menu?method=form&id=${menu.id}">菜单添加</a></li>
		</ul>
		<br />
		<form class="form-horizontal" role="form" action="${adminPath}/menu?method=save" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${menu.id}">
			 
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">单菜名称</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="name" name="name" placeholder="请输入单菜名称" value="${menu.name}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">访问地址</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="href" name="href" placeholder="请输入访问地址" value="${menu.href}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">目标</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="target" name="target" placeholder="请输入目标" value="${menu.target}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">是否显示</label>
			 	<div class="col-sm-5">
			 		是<input type="radio" name="isShow" value="1" checked="checked"> 否<input type="radio" name="isShow" value="0" <c:if test="${menu.isShow==0}">checked="checked"</c:if>>
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">排序</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="sort" name="sort" placeholder="请输入排序" value="${menu.sort}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">父级ID</label>
			 	<div class="col-sm-5">
			 		<select class="form-control" id="parentId" name="parentId">
			 			<option value="0">请选择</option>
			 			<c:forEach items="${parentMenus }" var="menu1">
			 				<option value="${menu1.id }" <c:if test="${menu.parentId==menu1.id}">selected="selected"</c:if> >${menu1.name }</option>
			 			</c:forEach>
			 		</select>
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">备注</label>
			 	<div class="col-sm-5">
			 			<textarea rows="3" class="form-control" id="remarks" name="remarks">${menu.remarks}</textarea>
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