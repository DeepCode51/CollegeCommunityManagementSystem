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
       <h2>社团申请</h2>
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
        	<form class="form-horizontal" role="form" action="${adminPath}/f?action=applayBuildTeam" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${teams.id}">
			 
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">社团名字</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="teamName" name="teamName" placeholder="请输入社团名字" value="${teams.teamName}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">社团宣传图</label>
			 	<div class="col-sm-5">
			 			<input type="hidden" id="pic" name="pic" value="${teams.pic}">
			 			<input type="file"  name="pic">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">社团介绍</label>
			 	<div class="col-sm-8">
			 			<textarea rows="10" class="form-control xheditor" id="teamIntro" name="teamIntro">${teams.teamIntro}</textarea>
		    	</div>
			 </div>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">创建人（社长）</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="buildStu" name="buildStu" placeholder="请输入创建人（社长）" value="${flogin.id}">
		    	</div>
			 </div>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">审核状态</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="auditType" name="auditType" placeholder="请输入审核状态" value="0">
		    	</div>
			 </div>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">成员数</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="members" name="members" placeholder="请输入成员数" value="0">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">备注</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="remark" name="remark" placeholder="请输入备注" value="${teams.remark}">
		    	</div>
			 </div>
			  

			 <div class="form-group">
			    <label for="sort" class="col-sm-3 control-label"></label>
			    <div class="col-sm-5">
			      	<input type="submit" class="btn btn-success btn-sm" value="提交申请">
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
				alert("已提交过申请，请耐心等待审核结果！");
			}else{
				alert("申请成功，请耐心等待审核结果。");
			}
		})
	}
</script>
</body>
</html>