<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--禁止浏览器缩放-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta content="application/xhtml+xml;charset=UTF-8" http-equiv="Content-Type" />
    <!--清除浏览器缓存-->
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
    <meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">
    <!--iPhone 手机上设置手机号码不被显示为拨号链接）-->
    <meta content="telephone=no, address=no" name="format-detection" />
    <!--IOS私有属性，可以添加到主屏幕-->
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <!--屏幕顶部条的颜色-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />

    <title>手机webApp</title>
    <!-- 重置样式 -->
    <link type="text/css" href="${ctxStatic}/app/css/reset.css" rel="stylesheet" />
    <!-- 主样式 -->
    <link type="text/css" href="${ctxStatic}/app/css/common.css" rel="stylesheet" />
    <!-- Jquery库 -->
    <script type="text/javascript" src="${ctxStatic}/app/js/jquery-2.1.4.min.js"></script>
    <!-- 手机触摸 -->
    <script type="text/javascript" src="${ctxStatic}/app/js/hammer.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/app/js/common.js"></script>
    <!--让IE8，IE9，支持Html5和Css3-->
    <!--[if lte IE 8]>
    <script src="${ctxStatic}/app/js/selectivizr.js"></script>
    <script src="${ctxStatic}/app/js/selectivizr-development.js"></script>

    <![endif]-->
    <!--[if lt IE 9]>
    <script src="${ctxStatic}/app/js/css3-mediaqueries.js"></script>
    <script src="${ctxStatic}/app/js/html5shiv.js"></script>
    <![endif]-->




</head>
<body>
<!--页面容器-->
<div class="page-container min-height">
    <!--头部-->
    <div id="head">
        基于 layui 的极简社区页面模版
    </div>

    <!--幻灯片-->
    <div id="banner" class="position-relative">
        <%--<ul>
            <li><a href="#" title=""><img src="http://tinywan-oss.oss-cn-shanghai.aliyuncs.com//uploads/article/5a6c86dc18669.png" alt="" title="" /></a></li>
            <li><a href="#" title=""><img src="http://tinywan-oss.oss-cn-shanghai.aliyuncs.com//uploads/article/5a6c86dc18669.png" alt="" title="" /></a></li>
            <li><a href="#" title=""><img src="http://tinywan-oss.oss-cn-shanghai.aliyuncs.com//uploads/article/5a6c86dc18669.png" alt="" title="" /></a></li>
        </ul>--%>
    </div>

    <!--主体-->
    <div id="main">
       <%-- <!--方块菜单-->
        <div class="menu min-height">
            基于 layui 的极简社区页面模版基于 layui 的极简社区页面模版基于 layui 的极简社区页面模版基于 layui 的极简社区页面模版基于 layui 的极简社区页面模版基于 layui 的极简社区页面模版
        </div>

        <!--描述-->
        <div class="copyright clear">
            基于 layui 的极简社区页面模版基于 layui 的极简社区页面模版基于 layui 的极简社区页面模版基于 layui 的极简社区页面模版
        </div>--%>
        <input type="button" value="按钮"/>


    </div>

    <!--页脚-->
    <div id="footer" class="position-fixed">
        基于 layui 的极简社区页面模版基于 layui <br/>的极简社区页面模版基于 layui 的极简社区页面模版基于 layui 的极简社区页面模版
    </div>
</div>
</body>
</html>