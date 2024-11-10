<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<%@ include file="/views/include/head.jsp"%>
<%@ include file="/views/include/validation.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#submitForm").validate({
			rules : {
								actId: {
					required : true
				},
				type: {
					required : true
				},
				money: {
					required : true,
					number:true
				},
				remark: {
					required : true
				},
			},
			messages : {
				actId: {
					required : "活动编号必填"
				},
				type: {
					required : "交易类型必填"
				},
				money: {
					required : "交易金额必填",
					number:"必须输入合法数字"
				},
				remark: {
					required : "交易备注必填"
				},
			}
		})
	});
</script>

<style type="text/css">
	.error{  color:red; }
</style>
</head>

<body>
	<div class="container-fluid">

		<ul class="nav nav-tabs">
			<li><a href="${adminPath}/moneyManger?method=list">财务管理列表</a></li>
			<li class="active"><a href="${adminPath}/moneyManger?method=form&id=${moneyManger.id}">财务管理添加</a></li>
		</ul>
		<br />
		<form class="form-horizontal"  id="submitForm" role="form" action="${adminPath}/moneyManger?method=save" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${moneyManger.id}">
			 
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">活动编号<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
			 		<select class="form-control" id="actId" name="actId"><option value="">-未选择-</option>
			 			<c:forEach items="${findAll }" var="siteNews">
			 				<option value="${siteNews.id }" <c:if test="${moneyManger.actId==siteNews.id}">selected</c:if> >${siteNews.title }</option>
			 			</c:forEach>
			 		</select>
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">交易类型<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
			 		<select class="form-control" id="type" name="type">
			 		<option value="">-未选择-</option>
			 				<option value="1" <c:if test="${moneyManger.type=='1'}">selected</c:if> >支出</option>	
			 				<option value="2" <c:if test="${moneyManger.type=='2'}">selected</c:if> >收入</option>	
			 		</select>
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">交易金额<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="money" name="money" placeholder="请输入交易金额" value="${moneyManger.money}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">交易备注<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<textarea rows="3" class="form-control" id="remark" name="remark">${moneyManger.remark}</textarea>
		    	</div>
			 </div>
			  

			 <div class="form-group">
			    <label for="sort" class="col-sm-3 control-label"></label>
			    <div class="col-sm-5">
			      	<input type="submit" class="btn btn-success btn-sm" value="保存">
			      	<input type="button" class="btn btn-default btn-sm" value="返回" onclick="history.go(-1)">
			    </div>
			 </div>
		</form>
	</div>
</body>
</html>