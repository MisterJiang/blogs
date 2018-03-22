<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>博客首页</title>
    <meta name="decorator" content="default"/>
    <style type="text/css">
        .fly-list li {
            height: 160px !important;
            padding: 15px 15px 15px 320px;
            border-bottom: 5px solid #F2F2F2;
        }
        .fly-avatar img {
            display: block;
            width: 20em;
            height: 11.5em;
            margin: 0;
            text-shadow: 5px 5px 5px #FF0000;
        }

        .fly-list-info {
            white-space: normal;
        }

        .fly-case-desc {
            height: 60px;
            line-height: 26px;
            color: #666;
            overflow: hidden;
            font-size: 15px;
            padding-bottom: 46px;
            text-indent: 24px;
            padding-top: 10px;
        }

        .fly-list li h2 a {
            font-size: 18px;
        }

        @media screen and (max-width: 750px) {

            .fly-list li {
                height: 340px !important;
                border-bottom: 5px solid #F2F2F2;
                position: relative;
                height: 45px;
                line-height: 22px;
                padding: 15px 30px 15px 30px;
            }
            .fly-avatar {
                position: relative;
                top:0;
                left:0;
                height: 200px;
                width: 100%;
            }
            .fly-avatar img {
                display: block;
                /*width: 23.4em;*/
                /*height: 13em;*/
                margin: auto;
                text-shadow: 5px 5px 5px #FF0000;
                background-color: #fff;
                border: 1px solid #ddd;
                line-height: 1.42857;
                padding: 4px;
                transition: border 0.2s ease-in-out 0s;
            }

            .fly-list li h2 {
                height: 30px;
                font-size: 0;
                line-height: 30px;
            }

            .fly-case-desc {
                height: 90px;
                line-height: 30px;
                color: #666;
                overflow: hidden;
                font-size: 15px;
                padding-bottom: 46px;
                text-indent: 24px;
                padding-top: 10px;
                verflow: hidden;
                text-overflow: ellipsis;
                display: -webkit-box;
                -webkit-line-clamp: 3;
                -webkit-box-orient: vertical;
                padding: 0;
            }

            .fly-list li h2 .layui-badge {
                top: -10px;
                height: 16px;
                line-height: 16px;
                padding: 0 5px;
                margin-right: 10px;
                font-size: 12px;
                border: 1px solid #5FB878;
                background: none;
                color: #5FB878;
            }
            .fly-list li h2 a {
                line-height: 40px;
            }
        }
    </style>

    <script type="text/javascript">
        $(function(){
            /*$.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js',function(){
                alert(remote_ip_info.country);//国家
                alert(remote_ip_info.province);//省份
                alert(remote_ip_info.city);//城市
            });*/
        })
    </script>
</head>
<body>
<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md9">
            <%--<div class="fly-panel">
                图片展示
                <shiro:authenticated>用户已经登录显示此内容</shiro:authenticated>
                <shiro:hasRole name="superadmin">超级管理员角色登录显示此内容</shiro:hasRole>
                <shiro:hasRole name="admin">管理员角色登录显示此内容</shiro:hasRole>
                <shiro:hasRole name="normal">普通用户角色登录显示此内容</shiro:hasRole>
            </div>--%>
            <div class="fly-panel">
                <ul class="fly-list">
                    <c:forEach var="article" items="${pageList.list}">
                        <li>
                            <a href="${ctx}/article/detail/${article.id}${urlSuffix}" class="fly-avatar">
                                <img lay-src="http://tinywan-oss.oss-cn-shanghai.aliyuncs.com//uploads/article/5a6c86dc18669.png" alt="贤心">
                            </a>
                            <h2>
                                <a class="layui-badge">动态</a>
                                <a href="${ctx}/article/detail/${article.id}${urlSuffix}">${article.title}</a>
                            </h2>
                            <div class="fly-list-info">
                                <p class="fly-case-desc">
                                    ${article.textView}
                                </p>
                                <a href="javascript:vod(0);" link>
                                    <cite>贤心</cite>
                                    <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                    <i class="layui-badge fly-badge-vip">VIP3</i>
                                </a>
                                <span>刚刚</span>
                                <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> 60</span>
                                    <%--<span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>--%>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <c:choose>
                    <c:when test="${pageList.pages > 1}">
                        <div style="text-align: center">
                            <div class="laypage-main">
                                <a href="${ctx}/article/all/p2${urlSuffix}" class="laypage-next">查看更多</a>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div style="text-align: center">
                            <div class="laypage-main">
                                <a href="javascript:void(0)" class="laypage-next">没有更多</a>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="layui-col-md3">
            <div class="fly-panel">
                <h3 class="fly-panel-title">热门文章</h3>
                <%--<ul class="fly-panel-main fly-list-static">
                    <li>
                        <a href="http://fly.layui.com/jie/4281/" target="_blank">layui 的 GitHub 及 Gitee (码云) 仓库，欢迎Star</a>
                    </li>
                    <li>
                        <a href="http://fly.layui.com/jie/5366/" target="_blank">
                            layui 常见问题的处理和实用干货集锦
                        </a>
                    </li>
                    <li>
                        <a href="http://fly.layui.com/jie/4281/" target="_blank">layui 的 GitHub 及 Gitee (码云) 仓库，欢迎Star</a>
                    </li>
                    <li>
                        <a href="http://fly.layui.com/jie/5366/" target="_blank">
                            layui 常见问题的处理和实用干货集锦
                        </a>
                    </li>
                    <li>
                        <a href="http://fly.layui.com/jie/4281/" target="_blank">layui 的 GitHub 及 Gitee (码云) 仓库，欢迎Star</a>
                    </li>
                </ul>--%>
            </div>

            <dl class="fly-panel fly-list-one">
                <dt class="fly-panel-title">本周热议</dt>
                <dd>
                    <a href="jie/detail.html">基于 layui </a>
                    <span style="float:right"><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="jie/detail.html">基于 layui 的极简社区页面模版</a>
                    <span style="float:right"><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="jie/detail.html">基于 layui 的极简社区页面模版</a>
                    <span style="float:right"><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="jie/detail.html">基于 layui 的极简社区页面模版</a>
                    <span style="float:right"><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="jie/detail.html">基于 layui 的极简社区页面模版</a>
                    <span style="float:right"><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="jie/detail.html">基于 layui 的极简社区页面模版</a>
                    <span style="float:right"><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="jie/detail.html">基于 layui 的极简社区页面模版</a>
                    <span style="float:right"><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="jie/detail.html">基于 layui 的极简社区页面模版</a>
                    <span style="float:right"><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="jie/detail.html">基于 layui 的极简社区页面模版</a>
                    <span style="float:right"><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="jie/detail.html">基于 layui 的极简社区页面模版</a>
                    <span style="float:right"><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>

                <!-- 无数据时 -->
                <!--
                <div class="fly-none">没有相关数据</div>
                -->
            </dl>

            <%--<div class="fly-panel">
                <div class="fly-panel-title">
                    这里可作为广告区域
                </div>
                <div class="fly-panel-main">
                    <a href="http://layim.layui.com/?from=fly" target="_blank" class="fly-zanzhu" time-limit="2017.09.25-2099.01.01" style="background-color: #5FB878;">LayIM 3.0 - layui 旗舰之作</a>
                </div>
            </div>--%>

            <div class="fly-panel fly-link">
                <h3 class="fly-panel-title">友情链接</h3>
                <dl class="fly-panel-main">
                    <dd><a href="http://www.layui.com/" target="_blank">layui</a><dd>
                    <dd><a href="http://layim.layui.com/" target="_blank">WebIM</a><dd>
                    <dd><a href="http://layer.layui.com/" target="_blank">layer</a><dd>
                    <dd><a href="http://www.layui.com/laydate/" target="_blank">layDate</a><dd>
                    <dd><a href="mailto:xianxin@layui-inc.com?subject=%E7%94%B3%E8%AF%B7Fly%E7%A4%BE%E5%8C%BA%E5%8F%8B%E9%93%BE" class="fly-link">申请友链</a><dd>
                </dl>
            </div>

        </div>
    </div>
</div>
</body>
</html>
