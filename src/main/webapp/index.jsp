<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/main.css" rel="stylesheet" />
<script src = "js/jquery-1.12.4.min.js" type="text/javascript"></script>
</head>
<body>
	<header>
	    <div class="backimg"><img src="images/IMG_0293.jpg"></div>
	    <div class="logo"><span></span><img src="images/00002637.png"></div>
	</header>
	<nav>
	    <div class="menu">
	    	<c:forEach var = "type" items = "${types }" varStatus = "status">
	    		<c:if test="${type.tid == tid }">
	    			<a href="javascript:void(0)" onclick = "changeType(${type.tid})" class="focus">${type.tname }</a>
	    		</c:if>
	    		<c:if test="${type.tid != tid }">
	    			<a href="javascript:void(0)" onclick = "changeType(${type.tid})" >${type.tname }</a>
	    		</c:if>
	    	</c:forEach>
	    </div>
	</nav>

	<main class="clear">
	    <div class="mainLeft">
	    	<c:forEach var = "content" items = "${contents }" varStatus = "status">
		        <div class="listBox">
		            <h1>${content.title }</h1>
		            <p class="colDefault">
				                作者：<span class="colInfo">${content.uname }</span> -
				                时间：<span class="colInfo">${content.addtime }</span> -
				                阅读：<span class="colInfo">${content.views }</span> -
				                评论：<span class="colInfo">0</span>
		            </p>
		            <dfn><p>${content.desc }</p></dfn>
		            <div class="function"><a href="">阅读全文</a></div>
		        </div>
	 		</c:forEach>
	
	        <div class="pager">
	            <ul class="clear">
	                <li class="previous">
	                    <a href="javascript:void(0)" onclick="changePage(-1)">上一页</a>
	                </li>
	                <li>
	                    <strong>
	                    	<span class = "mypage">${page }</span> /
	                    	<span class = "mypages">${pages }</span>
	                    </strong>
	                </li>
	                <li class="next">
	                    <a href="javascript:void(0)" onclick="changePage(1)">下一页</a>
	                </li>
	            </ul>
	        </div>
	    </div>
	
	    <div class="mainRight">
	    	<c:if test="${empty user }">
		        <div class="rightBox" id="loginBox">
		            <div class="title"><span>登录</span></div>
		            <div class="line"><span class="colDark">用户名：</span>
		            <input id = "loginuname" name="username" type="text"><em></em></div>
		            <div class="line"><span class="colDark">密码：</span>
		            <input id = "loginpwd" name="password" type="password"><em></em></div>
		            <div class="line"><span class="colDark"></span>
		            <button onclick = "login()">登 录</button></div>
		            					<!-- javascript:void(0);取消a标签的默认行为：跳转网页 -->
		            <p class="textRight">还没注册？
		            <a href="javascript:void(0);" onclick="showview('registerBox', 'loginBox')" 
		            class="colMint">马上注册</a>　</p>
		            <p class="colWarning textCenter"></p>
		        </div>
		        <div class="rightBox" id="registerBox" style="display:none">
		            <div class="title"><span>注册</span></div>
		            <div class="line"><span class="colDark">用户名：</span>
		            <input name="username" id = "reguname" type="text"></div>
		            <div class="line"><span class="colDark">密码：</span>
		            <input name="password" id = "regpwd" type="password"></div>
		            <div class="line"><span class="colDark">确认：</span>
		            <input name="repassword" id = "rpwd" type="password"></div>
		            <div class="line"><span class="colDark"></span>
		            <button onclick="reg()">注 册</button></div>
		            <p class="textRight">已有账号？
		            <a href="javascript:void(0);" onclick="showview('loginBox', 'registerBox')" 
		            class="colMint">马上登录</a>　</p>
		            <p class="colWarning textCenter"></p>
		        </div>
	        </c:if>
	        <c:if test="${not empty user }">
		        <div class="rightBox" id="userInfo">
		            <div class="title"><span>用户信息</span></div>
		            <c:if test="${user.isadmin == 0 }">
			            <p class="userName">
			            <span class="colDark">尊敬的用户：${user.uname }，您好</span></p>
		            </c:if>
		            <c:if test="${user.isadmin == 1 }">
			            <p class="adminInfo">
			            	<span class="colDanger">您好，管理员：${user.uname }</span>
			            	<a href="back/main.jsp">进入管理界面</a>
			            </p>
		            </c:if>
		            <p><span class="colDanger">欢迎光临我的博客！</span></p>
		            <p><span class="colDark">
		            <a href="javascript:void(0);" id="logout">退出</a></span></p>
		        </div>
	        </c:if>
	        <div class="rightBox">
	            <div class="title"><span>社区</span></div>
	            <p><a href="" target="_blank" class="colDanger">百度一下</a></p>
	            <p><a href="" target="_blank" class="colDanger">你就知道</a></p>
	        </div>
	    </div>
	</main>

	<div class="copyright textCenter">Copyright © baidu.com 版权所有 | 京xxx备xxxxxxxxx号</div>
	
	<script>
		function changeType(tid){
			$.post("/blogmybatis/contentServlet",{
				op : 'changeType',
				tid : tid
			},function(data){
				if ( data == 0 ) {
					alert("该分类暂无数据");
				} else if ( data == 1 ) {
					location.reload();			//重新加载页面
				}
			});
		}
	
		//分页
		function changePage(num){
			//先判断页数的合理性
			var page = parseInt($(".mypage").html());
			var pages = parseInt($(".mypages").html());
			
			if ( (page+num) <= 0 || (page+num) > pages ) {
				
				//说明是第一页，不分页     ||  超过最大页
				return;
			}
			$.post("/blogmybatis/contentServlet",{
				op : 'changePage',
				page : page+num
			},function(data){
				if ( data == 0 ) {
					location.href = "error.html";
				} else if ( data == 1 ) {
					location.reload();    //重新加载页面
				}
			});
		}
		
		function showview(show, hide){
			$("#"+show).css("display", "block");
			$("#"+hide).css("display", "none");
		}
		
		$("#logout").on("click",function(){
			$.post("/blogmybatis/userServlet",{
				op : 'logout'
			},function(data){
				location.reload();
			});
		});
		
		function login(){
			var uname = $("#loginuname").val();
			var pwd = $("#loginpwd").val();
			
			if ( uname == "" || pwd == "" ) {
				$(".textCenter").eq(0).html("登录信息不能为空");
			} else {
				$.post("/blogmybatis/userServlet",{
					op : 'login',
					uname : uname,
					pwd : pwd
				},function(data){
					if ( data == 0 ) {
						$(".textCenter").eq(0).html( "用户名或者密码错误。。。" );
					} else if ( data == 1 ) {
						$(".textCenter").eq(0).html( "登录成功。。。" );
						setTimeout(function(){
							//重新加载页面
							location.reload();
						},1500);
						
					}
				});
			}
		}
		
		function reg(){
			var uname = $("#reguname").val();
			var pwd = $("#regpwd").val();
			var rpwd = $("#rpwd").val();
			
			if ( uname == "" || pwd == "" || rpwd == "" ) {
				$(".textCenter").eq(1).html("注册信息不能为空");
			} else if ( pwd != rpwd ) {
				$(".textCenter").eq(1).html("两次输入的密码不一致");
			} else {
				$.post("/blogmybatis/userServlet",{
					op : 'reg',
					uname : uname,
					pwd : pwd
				},function(data){
					if ( data > 0 ) {
						$(".textCenter").eq(1).html( "注册成功，即将跳转到登录。。。" );
						setTimeout(function(){
							showview("loginBox", "registerBox");
							$(".textCenter").eq(1).html( "" );
						},1500);
					}
				});
			}
		}
	</script>
</body>
</html>