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
			<li class="active"><a href="${adminPath}/menu?method=form">权限设置</a></li>
		</ul>
		<br />
		<div style="">
		<form class="form-inline" id="searchForm" action="${adminPath}/menu?method=saveMenuRole" method="post">
			<input type="hidden" name="roleId" value="${roleId }">
			<ul>
				<c:forEach items="${list }" var="map">
					<li><input type="checkbox" name="menuIds" value="${map.parentMenu.id }" <c:if test="${map.parentMenu.isCheck==1 }">checked="checked"</c:if> > ${map.parentMenu.name }</li>
					<ul>
						<c:forEach items="${map.menus }" var="menu">
							<li><input type="checkbox" name="menuIds" value="${menu.id }" <c:if test="${menu.isCheck==1 }">checked="checked"</c:if>>${menu.name }</li>
						</c:forEach>
					</ul>
				</c:forEach>
			</ul>
			<span style="color: green;">${msg }</span>
			<button type="submit" class="btn btn-primary btn-xs">保存</button>
		</form>
		</div>
		<br />
	</div>
</body>
</html>