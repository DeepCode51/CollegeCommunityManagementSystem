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
<link rel="shortcut icon" href="${adminPath}/front/favicon.ico">
</head>
<body>
<%@ include file="/views/include/head.jsp"%>
<%@ include file="/front/top.jsp"%>
<div class="warp">
  <br>
  <div class="listpage clearfix">
    <div class="lmenu">
       <h2>个人中心</h2>
      <ul class="menus">
      	<li><a class="menulink" href="${adminPath }/front/applayTeam.jsp">成立社团</a></li>
      	<li><a class="menulink" href="${adminPath }/f?action=getMyTeam&id=${flogin.id }">我的社团</a></li>
      	<li><a class="menulink" href="${adminPath }/f?action=getMyTeamApplay&id=${flogin.id }">申请加入社团</a></li>
      	<li><a class="menulink" href="${adminPath }/f?action=getMyActApplay&id=${flogin.id }">申请参加活动</a></li>
      </ul>
    </div>
    <div class="rcon">
      <ol class="breadcrumb">
        <li><a href="#">首页</a></li>
        <li class="active"> - 社团列表- 社团详情</li>
        
      </ol>
      <div class="newscon">
        	<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>序号</th>
					<th>活动名称</th>
					<!-- <th>学生编号</th> -->
					<th>学生姓名</th>
					<th>联系电话</th>
					<th>报名状态</th>
					<th>是否按时参加</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${acts.list }" var="activeStu" varStatus="num">
					<tr>
						<td>${num.index+1+page.pageSize*(page.pageNo-1) }</td>
						
						<td><a href="${adminPath }/f?action=detail&id=${activeStu.activeId }">
							${activeStu.activeName}
						</a></td>
						<%-- <td>
							${activeStu.stuId}
						</td> --%>
						<td>
							${activeStu.stuName}
						</td>
						<td>
							${activeStu.phone}
						</td>
						<td>
							<%-- ${activeStu.type} --%>报名成功
						</td>
						<td>
							<c:if test="${activeStu.isArrive=='0'}"><span style="color: grey;">未知</span></c:if>
							<c:if test="${activeStu.isArrive=='1'}"><span style="color: green;">按时参加</span></c:if>
							<c:if test="${activeStu.isArrive=='2'}"><span style="color: red;">未按时参加</span></c:if>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
      </div>
    </div>
  </div>
</div>
<%@ include file="/front/footer.jsp" %>
<script type="text/javascript" src="${adminPath}/front/js/jquery.min.js"></script>
<script type="text/javascript">
	function applayLeader(id){
		$.post("${adminPath}/f?action=applayLeader&id="+id,function(data){
			if(data==1){
				alert("请先登录");
			}else if(data==2){
				alert("已提交过申请，请耐心等待审核结果！");
			}else{
				alert("申请成功，请耐心等待审核结果。");
			}
		})
	}
	function applayTeam(id){
		$.post("${adminPath}/f?action=applayTeam&id="+id,function(data){
			if(data==1){
				alert("请先登录");
			}else if(data==2){
				alert("已提交过申请，请耐心等待审核结果！");
			}else if(data==3){
				alert("已提交过申请，请耐心等待审核结果！");//已是该社团成员，不能重复申请
			}else{
				alert("申请成功，请耐心等待审核结果。");
			}
		})
	}
</script>
</body>
</html>