<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>高校社团管理系统</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<link href="${adminPath}/front/css/styles.css" rel="stylesheet">
<link rel="shortcut icon" href="${adminPath}/front/favicon.ico">
</head>
<body>
<%@ include file="/front/top.jsp"%>


<div class="warp">
  <%-- <div class="banner">
    <!-- <img alt="" src="${adminPath}/front/images/banner.jpg" width="100%" /> -->
    <div class="change-box">
      <div class="img-box ads-one active"><img alt="" src="${adminPath}/front/images/87.jpg" width="100%" /></div>
      <div class="img-box ads-two"><img alt="" src="${adminPath}/front/images/3.jpg" width="100%"/></div>
      <div class="img-box ads-three"><img alt="" src="${adminPath}/front/images/6.jpg" width="100%" /></div>
    </div>
    <ul class="changebtn clearfix">
      <li><a class="banbtn active" href="javascript:;"></a></li>
      <li><a class="banbtn" href="javascript:;"></a></li>
      <li><a class="banbtn" href="javascript:;"></a></li>
    </ul>
  </div> --%>
  <div class="hotmsg clearfix">
    <h2>学校公告：</h2>
    <p>
      学校社团活动火热进行中，欢迎各位同学活跃报名。
    </p>
  </div>
  <div class="news-moudle clearfix">
    <div class="news-info mr15">
      <div class="title">最新活动</div>
      <ul>
      	<c:forEach items="${actives.list }" var="active" varStatus="num">
      		<li>
      		<c:if test="${num.index<3 }"><a href="${adminPath }/f?action=detail&id=${active.id }">${active.title }<img src="${adminPath}/front/images/hot.gif" /></a></c:if>
      		<c:if test="${num.index>=3 }"><a href="${adminPath }/f?action=detail&id=${active.id }">${active.title }</a></c:if>
      		 
          <%-- <span><fmt:formatDate value="${active.createTime}" pattern="yyyy-MM-dd"/></span> --%> 
            </li>
      	</c:forEach>
      </ul>
    </div>
    <div class="news-info">
      <div class="title">最新新闻</div>
      <ul>
      	<c:forEach items="${news.list }" var="news" varStatus="num">
      		<li>
      		<c:if test="${num.index<3 }"> <a href="${adminPath }/f?action=detail&id=${news.id }">${news.title }<img src="${adminPath}/front/images/hot.gif" /></a></c:if>
      		<c:if test="${num.index>=3 }"> <a href="${adminPath }/f?action=detail&id=${news.id }">${news.title }</a></c:if>
      		
      		
            <%-- <span><fmt:formatDate value="${news.createTime}" pattern="yyyy-MM-dd"/></span> --%>
            </li>
      	</c:forEach>
      </ul>
    </div>
    
    <div class="news-info">
      <div class="title">社团风采</div>
      <ul>
      	<c:forEach items="${pageFengcai.list }" var="news" varStatus="num">
      		<li>
      		<c:if test="${num.index<3 }"> <a href="${adminPath }/f?action=detail&id=${news.id }">${news.title }<img src="${adminPath}/front/images/hot.gif" /></a></c:if>
      		<c:if test="${num.index>=3 }"> <a href="${adminPath }/f?action=detail&id=${news.id }">${news.title }</a></c:if>
      		
      		
            <%-- <span><fmt:formatDate value="${news.createTime}" pattern="yyyy-MM-dd"/></span> --%>
            </li>
      	</c:forEach>
      </ul>
    </div>
  </div>
  
   <div class="show-box">
    <div class="title">社团列表</div>
    <div class="box-list" id="Marquee_xxx">
      <ul class="img-lists clearfix">
      	<c:forEach items="${teams }" var="team">
      		<li>
	          <img alt="" src="${pageContext.request.contextPath }${team.pic }" width="100%" />
	          <a href="${adminPath }/f?action=teamDetail&id=${team.id}" class="">${team.teamName }</a>
	        </li>
      	</c:forEach>
      </ul>
    </div>
  </div>
  
  <div class="show-box">
    <div class="title">推荐活动</div>
    <div class="box-list" id="Marquee_xxx">
      <ul class="img-lists clearfix">
      	<c:forEach items="${runNews }" var="news">
      		<li>
	          <img alt="" src="${news.pic }" width="100%" />
	          <a href="${adminPath }/f?action=detail&id=${news.id }" class="">${news.title }</a>
	        </li>
      	</c:forEach>
      </ul>
    </div>
  </div>
  
</div>
<%@ include file="/front/footer.jsp"%>
<script type="text/javascript" src="${adminPath}/front/js/jquery.min.js"></script>
<script src="${adminPath}/front/js/jquery.kxbdmarquee.js"></script>
<script type="text/javascript">
$(function(){
    $('#Marquee_x').kxbdMarquee({ 'direction':'left','isEqual':'true','loop':0 });
    $('#Marquee_xx').kxbdMarquee({ 'direction':'left','isEqual':'true','loop':0 });
    $('#Marquee_xxx').kxbdMarquee({ 'direction':'left','isEqual':'true','loop':0 });
});
var changeBox = $(".change-box"),
    imgBox = $(".img-box"),
    changeBtn = $(".changebtn"),
    banBtn = $(".banbtn"),
    picBox = $('.pic-box li'),
    picBoxPagenum = $('.pic-box-pagenum span'),
    speed = 4000;
// 定时器
var timeout = setInterval(autorun,speed);
// banner 滚动逻辑
function run(index){
      imgBox.removeClass('active').eq(index).addClass('active');
      banBtn.removeClass('active').eq(index).addClass('active');
      clearInterval(timeout);
      timeout = setInterval(autorun,speed);
}
function autorun(){
  var _indexDiv = changeBox.find('.active').index(),
      _indexBtn = changeBtn.find('.active').index(),
      _lenDiv = imgBox.length,
      _lenBtn = banBtn.length,
      autoindex = _indexDiv+1;
  autoindex = autoindex > 2? 0 : autoindex;
  imgBox.removeClass('active').eq(autoindex).addClass('active');
  banBtn.removeClass('active').eq(autoindex).addClass('active');
}
// 点击切换
banBtn.each(function(){
  $(this).on("click",function(){
    var index = banBtn.index();
    run(index);
  });
});

// 新闻区域图文切换
picBoxPagenum.each(function(){
  $(this).on('click',function(){
    $(this).siblings('span').removeClass('act');
    $(this).addClass('act');
    var index = $(this).index();
    $(this).parents('.news-pic').find('.pic-box li').removeClass('act');
    $(this).parents('.news-pic').find('.pic-box li').eq(index).addClass('act');
  });
});

</script>
</body>
</html>