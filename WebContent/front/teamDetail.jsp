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
<%@ include file="/front/top.jsp"%>
<div class="warp">
  <%-- <div class="ads">
    <img alt="" src="${adminPath}/front/images/banner2.jpg" width="100%"/>
  </div> --%><br>
  <div class="listpage clearfix">
    <div class="lmenu">
       <h2>社团列表</h2>
      <ul class="menus">
      	<c:forEach items="${teamList }" var="team">
      	 <li><a class="menulink" href="${adminPath }/f?action=teamDetail&id=${team.id }">${team.teamName }</a></li>
      	</c:forEach>
      </ul>
    </div>
      <c:if test="${team!=null }">
    <div class="rcon">
      <ol class="breadcrumb">
        <li><a href="#">首页</a></li>
        <li class="active"> - 社团列表- 社团详情</li>
        
      </ol>
      <div class="newscon">
        <div class="news-tit mt20">${team.teamName }</div>
        <div class="news-remark">社长：<span class="mr40">
        	<c:if test="${team.buildStu==null || team.buildStu=='' }"><a href="#" onclick="applayLeader('${team.id}')">没有社长？申请社长？</a></c:if>
        	<c:if test="${team.buildStu!=null&& team.buildStu!='' }">${team.studentName}&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="applayTeam('${team.id}')">申请加入社团</a></c:if>
        </span>
        成立时间：<span class="mr40"><fmt:formatDate value="${team.buildTime}" pattern="yyyy-MM-dd"/><!-- </span>浏览次数：<span>328<span>次</span></span> -->
        </div>
        <div class="news-info">
          	<img alt="" src="${pageContext.request.contextPath }${team.pic }" style="width: 40%"><br>
          	${team.teamIntro }
        </div>
      </div>
    </div>
      </c:if>
     <c:if test="${team==null }"> <div class="rcon"> <div class="newscon">
      	<p>请点击左侧对应的社团查看社团介绍！</p></div></div>
      </c:if>
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