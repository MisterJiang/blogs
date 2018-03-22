<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
	<head>
		<title><sitemesh:title/> - blogs</title>
		<%@include file="/WEB-INF/views/include/head.jsp" %>
		<sitemesh:head/>
		<script type="text/javascript">
			$(document).ready(function() {
				layui.use('code', function(){ //加载code模块
					layui.code(); //引用code方法
				});

				layui.use('flow', function(){
					var flow = layui.flow;
					//当你执行这样一个方法时，即对页面中的全部带有lay-src的img元素开启了懒加载（当然你也可以指定相关img）
					flow.lazyimg();
				});

				var navigation = '${navigation}';
				if(navigation!='' && navigation != null){
					$("#" + navigation).addClass("layui-this");
				}

				var navigationType = '${navigationType}';
				if(navigationType!='' && navigationType != null){
					$("#" + navigationType).addClass("layui-this");
				}
			});
		</script>
	</head>
	<body>
	<div class="fly-header layui-bg-black">
		<div class="layui-container">
			<a class="fly-logo" href="${ctx}/">
				<img src="${ctxStatic}/res/images/logo.png" alt="layui">
			</a>
			<ul class="layui-nav fly-nav layui-hide-xs">
				<li id="index" class="layui-nav-item">
					<a href="${ctx}/">首页</a>
				</li>
				<li id="article" class="layui-nav-item">
					<a href="${ctx}/article/all/p1${urlSuffix}">文章</a>
				</li>
				<li id="time" class="layui-nav-item">
					<a href="${ctx}/time${urlSuffix}">时光轴</a>
				</li>
				<c:choose>
					<c:when test="${sessionScope.sessionInfo.userName != null}">
						<li class="layui-nav-item" style="float:right">
							<a href="#">
								<img src="${ctxStatic}/res/images/logo.png" class="layui-nav-img">
								${sessionScope.sessionInfo.name}
							</a>
							<dl class="layui-nav-child">
								<dd><a href="${ctx}/article/publish">发布文章</a></dd>
								<shiro:hasAnyRoles name="superadmin,admin">
									<dd><a href="${ctx}/admin/index">后台管理</a></dd>
								</shiro:hasAnyRoles>
								<dd><a href="${ctx}/login/layout">退出</a></dd>
							</dl>
						</li>
					</c:when>
					<c:otherwise>
						<li id="login" class="layui-nav-item" style="float:right">
							<a href="${ctx}/login">登录</a>
						</li>
						<li id="reg" class="layui-nav-item" style="float:right">
							<a href="${ctx}/login/reg">注册</a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
	<div class="fly-panel fly-column">
		<div class="layui-container">
			<ul class="layui-clear">
				<li class="layui-hide-xs layui-this"><a href="/">首页</a></li>
				<li id="java"><a href="${ctx}/article/java/p1${urlSuffix}">JAVA</a></li>
				<li id="sql"><a href="${ctx}/article/sql/p1${urlSuffix}">数据库&nbsp;&nbsp;<span class="layui-badge-dot"></span></a></li>
				<li id="html"><a href="${ctx}/article/html/p1${urlSuffix}">HTML</a></li>
				<!-- 用户登入后显示 -->
				<c:choose>
					<c:when test="${sessionScope.sessionInfo.userName != null}">
						<li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li>
						<li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="user/index.html">我发表的贴</a></li>
						<li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="user/index.html#collection">我收藏的贴</a></li>
					</c:when>
				</c:choose>
			</ul>
			<div class="fly-column-right layui-hide-xs" style="padding-right: 25px;">
				<span class="fly-search"><i class="layui-icon"></i></span>
				<a href="${ctx}/article/publish" class="layui-btn">发表文章</a>
			</div>
		</div>
	</div>
	<sitemesh:body/>
	<div class="fly-footer">
		<p><a href="http://fly.layui.com/" target="_blank">Fly社区</a> 2017 &copy; <a href="http://www.layui.com/" target="_blank">layui.com 出品</a></p>
		<p>
			<a href="http://fly.layui.com/jie/3147/" target="_blank">付费计划</a>
			<a href="http://www.layui.com/template/fly/" target="_blank">获取Fly社区模版</a>
			<a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>
		</p>
	</div>
	</body>
</html>
