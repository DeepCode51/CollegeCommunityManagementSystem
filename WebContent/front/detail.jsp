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
 <%--  <div class="ads">
    <img alt="" src="${adminPath}/front/images/banner2.jpg" width="100%"/>
  </div> --%><br>
  <div class="listpage clearfix">
    <div class="lmenu">
    <c:if test="${news.newsType=='1' }"><h2>新闻列表</h2></c:if>
       <c:if test="${news.newsType=='2' }"><h2>活动列表</h2></c:if>
      <ul class="menus">
      	<c:forEach items="${leftNews.list }" var="news">
      	 <li><a class="menulink" href="${adminPath }/f?action=detail&id=${news.id }">${news.title }</a></li>
      	</c:forEach>
      </ul>
    </div>
    <div class="rcon">
      <ol class="breadcrumb">
        <li><a href="#">首页</a></li>
        <c:if test="${news.newsType=='1' }">
        <li class="active"> - 最新新闻- 新闻详情</li>
        </c:if>
         <c:if test="${news.newsType=='2' }">
        <li class="active"> - 最新活动- 活动内容</li>
        </c:if>
        
      </ol>
      <div class="newscon">
        <div class="news-tit mt20">${news.title }</div>
        <div class="news-remark">来源：<span class="mr40">${news.createUser }</span>
        发布时间：<span class="mr40"><fmt:formatDate value="${news.createTime}" pattern="yyyy-MM-dd"/><!-- </span>浏览次数：<span>328<span>次</span></span> -->
        </div>
        <div class="news-info">
          	<img alt="" src="${pageContext.request.contextPath }${news.pic }" style="width: 80%"><br>
          	${news.content }
        </div>
      </div>
       <c:if test="${news.newsType=='2' }">
        <span style="font-size: 26px;color: green;">活动申请</span>
         <c:if test="${flogin!=null }">
        <form class="form-horizontal" role="form" action="${adminPath}/f?action=applayAct" method="post">
			<input type="hidden" name="id" value="${activeStu.id}">
			 
			 <div class="form-group"  style="display: none">
			 	<label for="name" class="col-sm-3 control-label">活动编号</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="activeId" name="activeId" placeholder="请输入活动编号" value="${news.id}">
		    	</div>
			 </div>
			 <div class="form-group" style="display: none">
			 	<label for="name" class="col-sm-3 control-label">学生编号</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="stuId" name="stuId" placeholder="请输入学生编号" value="${flogin.id}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">学生姓名</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="stuName" name="stuName" placeholder="请输入学生姓名" value="${flogin.stuName}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">联系电话</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="phone" name="phone" placeholder="请输入联系电话" value="${flogin.phone}">
		    	</div>
			 </div>
			 <div class="form-group" style="display: none">
			 	<label for="name" class="col-sm-3 control-label">报名状态</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="type" name="type" placeholder="请输入报名状态" value="1">
		    	</div>
			 </div>
			 <div class="form-group" style="display: none">
			 	<label for="name" class="col-sm-3 control-label">是否按时参加</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="isArrive" name="isArrive" placeholder="请输入是否正常出息" value="0">
		    	</div>
			 </div>
			 <div class="form-group" style="display: none">
			 	<label for="name" class="col-sm-3 control-label"></label>
			 	<div class="col-sm-5">
			 		<span style="color: green;">${msg }</span>
		    	</div>
			 </div>
			  

			 <div class="form-group">
			    <label for="sort" class="col-sm-3 control-label"></label>
			    <div class="col-sm-5">
			      	<input type="submit" class="btn btn-success btn-sm" value="申请">
			      	<input type="button" class="btn btn-default btn-sm" value="返回" onclick="history.go(-1)">
			    </div>
			 </div>
		</form>
		</c:if>
		<c:if test="${flogin==null }"><span style="color: grey;">请先登录！</span></c:if>
		</c:if>
      
    </div>
  </div>
</div>
<%@ include file="/front/footer.jsp" %>
<script type="text/javascript" src="${adminPath}/front/js/jquery.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>