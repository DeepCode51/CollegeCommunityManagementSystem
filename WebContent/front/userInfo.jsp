<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>内蒙古民族大学学生社团管理</title>
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
        	<form class="form-horizontal" role="form" action="${adminPath }/f?action=regit" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${flogin.id}">
			 
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">登录名称</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" readonly="readonly"  id="loginName" name="loginName" placeholder="请输入登录名称" value="${flogin.loginName}">
		    	</div>
			 </div>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">登录密码</label>
			 	<div class="col-sm-5">
			 			<input type="password" class="form-control" id="loginPassword" name="loginPassword" placeholder="请输入登录密码" value="${flogin.loginPassword}">
		    	</div>
			 </div>
			 <%-- <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">注册时间</label>
			 	<div class="col-sm-5">
			 			<input name="regitTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${flogin.regitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
		    	</div>
			 </div> --%>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">审核状态</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control"  id="auditType" name="auditType" placeholder="请输入审核状态" value="0">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">学生姓名</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control"  id="stuName" name="stuName" placeholder="请输入学生姓名" value="${flogin.stuName}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">性别</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control"  id="sex" name="sex" placeholder="请输入性别" value="${flogin.sex}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">学院</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control"  id="schoolRoom" name="schoolRoom" placeholder="请输入学院" value="${flogin.schoolRoom}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">班级</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control"  id="classRoom" name="classRoom" placeholder="请输入班级" value="${flogin.classRoom}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">联系电话</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control"  id="phone" name="phone" placeholder="请输入联系电话" value="${flogin.phone}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">头像</label>
			 	<div class="col-sm-5">
			 			<input type="hidden" id="pic" name="pic" value="${flogin.pic}">
			 			<input type="file"  name="pic">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">个人说明</label>
			 	<div class="col-sm-5">
			 			<textarea rows="3" class="form-control"  id="remark" name="remark">${flogin.remark}</textarea>
		    	</div>
			 </div>
			  <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label"></label>
			 	<div class="col-sm-5">
			 			${msg }
		    	</div>
			 </div>

			 <div class="form-group">
			    <label for="sort" class="col-sm-3 control-label"></label>
			    <div class="col-sm-5">
			      	<input type="submit" class="btn btn-success btn-sm" value="更新信息">
			      	<input type="button" class="btn btn-default btn-sm" value="返回" onclick="history.go(-1)">
			    </div>
			 </div>
		</form>
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