<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="t-line"></div>
<div class="header">
  <div class="tools clearfix">
      <p class="welcome"><img alt="" src="${adminPath}/front/images/kok.jpg" height="60"/></p>
      <div class="usearea">
      	<c:if test="${flogin!=null }">
      		<a href="${adminPath }/f?action=userInfo">个人中心</a>
      		 <a href="${adminPath }/f?action=logout">安全退出</a>
      	</c:if>
      	
      	<c:if test="${flogin==null }">
        <a href="${adminPath }/front/login.jsp">登录</a>
        <a href="${adminPath }/front/regit.jsp">注册</a>
      	</c:if>
      </div>
  </div>
  <div class="nav-bg">
    <ul class="nav">
      <li><a <c:if test="${method=='index' }">class="active"</c:if> href="${adminPath }/f?action=index">首页</a></li>
     <li><a <c:if test="${method=='teamDetail' }">class="active"</c:if> href="${adminPath }/f?action=teamDetail">社团列表</a></li>
      <li><a <c:if test="${newsType=='3' }">class="active"</c:if> href="${pageContext.request.contextPath }/f?action=siteNewsList&newsType=3">社团风采</a></li>
      <li><a <c:if test="${newsType=='2' }">class="active"</c:if> href="${pageContext.request.contextPath }/f?action=siteNewsList&newsType=2">社团活动</a></li>
      <li><a <c:if test="${newsType=='1' }">class="active"</c:if> href="${pageContext.request.contextPath }/f?action=siteNewsList&newsType=1">新闻列表</a></li>
   <c:if test="${flogin!=null }">  <li><a href="${adminPath }/front/sendMsg.jsp">留言</a></li> </c:if>
    </ul>
  </div>
</div>