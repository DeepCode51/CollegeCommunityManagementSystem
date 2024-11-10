<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<%@ include file="/views/include/head.jsp"%>
<link href="${ctxStatic}/treeTable/themes/vsStyle/treeTable.min.css" rel="stylesheet" type="text/css" />
<script src="${ctxStatic}/treeTable/jquery.treeTable.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var option = {
		        theme:'default',
		        expandLevel : 2,
		        beforeExpand : function($treeTable, id) {
		            //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
		            if ($('.' + id, $treeTable).length) { return; }
		           /*  //这里的html可以是ajax请求
		            var html = '<tr id="8" pId="6"><td>5.1</td><td>可以是ajax请求来的内容</td></tr>'
		                     + '<tr id="9" pId="6"><td>5.2</td><td>动态的内容</td></tr>';

		            $treeTable.addChilds(html); */
		        },
		        onSelect : function($treeTable, id) {
		            window.console && console.log('onSelect:' + id);
		        }

		    };
		    $('#contentTable').treeTable(option);
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
			<li class="active"><a href="${adminPath}/menu?method=list">菜单列表</a></li>
			<li><a href="${adminPath}/menu?method=form">菜单添加</a></li>
		</ul>
		<br>
		<table id="contentTable" class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>单菜名称</th>
					<th>访问地址</th>
					<th>目标</th>
					<th>是否显示</th>
					<th>排序</th>
					<!-- <th>父级ID</th> -->
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="menu" varStatus="num">
					<tr id="${menu.id }" pId="${menu.parentId }">
						<td><a href="${adminPath}/menu?method=form&id=${menu.id}">
							${menu.name}
						</a></td>
						<td>
							${menu.href}
						</td>
						<td>
							${menu.target}
						</td>
						<td>
							${menu.isShow=="1"?"是":"否"}
						</td>
						<td>
							${menu.sort}
						</td>
						<%-- <td>
							${menu.parentId}
						</td> --%>
						<td>
							${menu.remarks}
						</td>
						
						
						<td>
							<c:if test="${menu.parentId=='0' }">
								<a href="${adminPath }/menu?method=form&parentId=${menu.id}">
								<button class="btn btn-info btn-xs">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									添加下级
								</button>
							</a> 
							</c:if>
							<a href="${adminPath }/menu?method=form&id=${menu.id}">
								<button class="btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									编辑
								</button>
							</a> 
							<c:if test="${menu.parentId=='0' }">
							<a href="${adminPath }/menu?method=delete&id=${menu.id}" onclick="return confirm('提示：删除一级菜单会同时删除二级菜单，确认要删除吗？', this.href)">
								<button class="btn btn-danger btn-xs">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									删除
								</button>
							</a> 
							</c:if>
							<c:if test="${menu.parentId!='0' }">
							<a href="${adminPath }/menu?method=delete&id=${menu.id}" onclick="return confirm('确认要删除吗？', this.href)">
								<button class="btn btn-danger btn-xs">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									删除
								</button>
							</a> 
							</c:if>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
	</div>
</body>
</html>