<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>Bootstrap 101 Template</title>

<!-- Bootstrap -->
<link href="../css/bootstrap.css" rel="stylesheet">
</head>
<body>
	<!-- 导航栏 -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">我的博客</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">首页</a></li>
					<li><a href="#">用户管理</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">类别管理 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">类别首页</a></li>
							<li><a href="#">添加类别</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">${user.uname } <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">个人中心</a></li>
							<li><a href="#">修改资料</a></li>
							<li><a href="#">退出登录</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	
	<div class="container" style = "width : 95%">
		<!-- 路径导航 -->
		<ol class="breadcrumb">
		  <li><a href="#">首页</a></li>
		  <li><a href="#">类别管理</a></li>
		  <li class="active">类别首页</li>
		</ol>
		
		<!-- 表格 -->
		<table class="table table-striped table-bordered table-hover table-condensed">
			<tr>
				<th style = "text-align:center">类别编号</th>
				<th style = "text-align:center">类别名</th>
				<th style = "text-align:center">操作</th>
			</tr>
			<c:forEach var = "type" items = "${types }" varStatus = "status">
				<tr align = "center" id = "tid${type.tid }">
					<td>${type.tid }</td>
					<td>${type.tname }</td>
					<td>
						<button class = "btn btn-info btn=sm" data-toggle="modal" data-target="#myModal" 
							onclick = "update(${type.tid})">修改</button>
						<button class = "btn btn-info btn=sm" onclick = "del(${type.tid})">删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改类名</h4>
				</div>
				<div class="modal-body">
					<input type = "text" class = "form-control" value = "" id = "uptinp" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" onclick = "upt()">修改</button>
				</div>
			</div>
		</div>
	</div>


	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script src="../js/jquery-1.12.4.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script src="../js/bootstrap.js"></script>
	
	<script>
		var tid = 1;
		function update(mytid){
			tid = mytid;
			if ( tid == 1 ) {
				alert("首页不能删改");
				return;
			}
			var tname = $("#tid"+tid+" td").eq(1).html();
			$("#uptinp").val( tname );
		}
		
		function upt(){
			var tname = $("#uptinp").val();
			if ( tname == null || tname == "" ) {
				alert("类名不能为空");
				return;
			}
			$.post("../typeServlet",{
				op : 'updateType',
				tid : tid,
				tname : tname
			},function(data){
				if ( data > 0 ) {
					location.reload();
				}
			});
		}
		
		function del(mytid){
			
		}
	</script>
</body>
</html>