<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>登录</title>  
<%@ include file="/views/include/head.jsp"%>
</head>
<body>
<div class="container">
    <%-- <h1>欢迎使用${projectName }</h1> --%>
    <div class="row">
    	<div class="col-sm-2 col-md-2">
    		
    		<div style="height: 100px;width: 100%;background-color:#FFF5EE;font-size: 26px;color: green;text-align: center;vertical-align: middle;">
    			<a href="${adminPath}/teams?method=list">社团总数</a><br>
    			${Tsize }
    		</div>
    	</div>
    	
    	<!--  <div class="col-sm-2 col-md-2">
    		
    		<div style="height: 100px;width: 100%;background-color:#C1FFC1;font-size: 26px;color: green;text-align: center;vertical-align: middle;">
    			<a href="${adminPath}/siteNews?method=list&newsType=2">活动总数</a><br>
    			${acNum }
    		</div>
    	</div> -->
    	
    	 <!--  <div class="col-sm-2 col-md-2">
    		<div style="height: 100px;width: 100%;background-color:#E3E3E3;font-size: 26px;color: green;text-align: center;vertical-align: middle;">
    			社团总收入<br>
    			￥${shouru }
    		</div>
    	</div>
    	
    	<div class="col-sm-2 col-md-2">
    		<div style="height: 100px;width: 100%;background-color:#C0FF3E;font-size: 26px;color: green;text-align: center;vertical-align: middle;">
    			社团总支出<br>
    			￥${zhichu }
    		</div>
    	</div> -->
    </div>
    <hr>
    <div class="row">
    	<div class="col-sm-6 col-md-6">
    		<div class="panel panel-default">
			  <div class="panel-heading">最近活动</div>
			  <ul class="list-group">
			  	<c:forEach items="${actives}" var="active" varStatus="num">
			    <li class="list-group-item"><a href="${adminPath }/f?action=detail&id=${active.id }" target="blank">${active.title }</a></li>
			    </c:forEach>
			  </ul>
			</div>
    	</div>
    	<div class="col-sm-6 col-md-6">
    		<div class="panel panel-default">
			  <div class="panel-heading">最近新闻</div>
			  <ul class="list-group">
			  	<c:forEach items="${news}" var="active" varStatus="num">
			    <li class="list-group-item"><a href="${adminPath }/f?action=detail&id=${active.id }" target="blank">${active.title }</a></li>
			    </c:forEach>
			  </ul>
			</div>
    	</div>
    </div>
</body>
</html>