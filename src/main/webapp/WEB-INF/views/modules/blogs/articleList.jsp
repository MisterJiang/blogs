<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>文章列表</title>
  <meta name="decorator" content="default"/>
</head>
<body>
<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md9 content detail">
        <div class="fly-panel">
          <c:choose>
            <c:when test="${pageList.pages>0}">
              <ul class="fly-list">
                <c:forEach var="article" items="${pageList.list}">
                  <li>
                    <a href="user/home.html" class="fly-avatar">
                      <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="贤心">
                    </a>
                    <h2>
                      <a class="layui-badge">动态</a>
                      <a href="${ctx}/article/detail/${article.id}${urlSuffix}">${article.title}</a>
                    </h2>
                    <div class="fly-list-info">
                      <a href="user/home.html" link>
                        <cite>贤心</cite>
                        <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                        <i class="layui-badge fly-badge-vip">VIP3</i>
                      </a>
                      <span>刚刚</span>

                      <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> 60</span>
                      <span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
                            <span class="fly-list-nums">
                              <i class="iconfont icon-pinglun1" title="回答"></i> 66
                            </span>
                    </div>
                    <div class="fly-list-badge">
                      <span class="layui-badge layui-bg-black">置顶</span>
                      <span class="layui-badge layui-bg-red">精帖</span>
                    </div>
                  </li>
                </c:forEach>
              </ul>
              <!--分页jsp-->
              <%@ include file="/WEB-INF/views/include/page.jsp"%>
            </c:when>
            <c:otherwise>
            <div style="text-align: center;">
              <blockquote class="layui-elem-quote">
                还没有发表过文章，赶紧去写文章吧 <a href="${ctx}/article/publish" class="layui-btn">发表文章</a>
              </blockquote>
            </div>
            </c:otherwise>
          </c:choose>
        </div>
    </div>
    <div class="layui-col-md3">
      <dl class="fly-panel fly-list-one">
        <dt class="fly-panel-title">本周热议</dt>
        <dd>
          <a href="">基于 layui 的极简社区页面模版</a>
          <span><i class="iconfont icon-pinglun1"></i> 16</span>
        </dd>
        <dd>
          <a href="">基于 layui 的极简社区页面模版</a>
          <span><i class="iconfont icon-pinglun1"></i> 16</span>
        </dd>
        <dd>
          <a href="">基于 layui 的极简社区页面模版</a>
          <span><i class="iconfont icon-pinglun1"></i> 16</span>
        </dd>
        <dd>
          <a href="">基于 layui 的极简社区页面模版</a>
          <span><i class="iconfont icon-pinglun1"></i> 16</span>
        </dd>
        <dd>
          <a href="">基于 layui 的极简社区页面模版</a>
          <span><i class="iconfont icon-pinglun1"></i> 16</span>
        </dd>
        <dd>
          <a href="">基于 layui 的极简社区页面模版</a>
          <span><i class="iconfont icon-pinglun1"></i> 16</span>
        </dd>
        <dd>
          <a href="">基于 layui 的极简社区页面模版</a>
          <span><i class="iconfont icon-pinglun1"></i> 16</span>
        </dd>
        <dd>
          <a href="">基于 layui 的极简社区页面模版</a>
          <span><i class="iconfont icon-pinglun1"></i> 16</span>
        </dd>
        <dd>
          <a href="">基于 layui 的极简社区页面模版</a>
          <span><i class="iconfont icon-pinglun1"></i> 16</span>
        </dd>
        <dd>
          <a href="">基于 layui 的极简社区页面模版</a>
          <span><i class="iconfont icon-pinglun1"></i> 16</span>
        </dd>

        <!-- 无数据时 -->
        <!--
        <div class="fly-none">没有相关数据</div>
        -->
      </dl>

      <div class="fly-panel">
        <div class="fly-panel-title">
          这里可作为广告区域
        </div>
        <div class="fly-panel-main">
          <a href="http://layim.layui.com/?from=fly" target="_blank" class="fly-zanzhu" time-limit="2017.09.25-2099.01.01" style="background-color: #5FB878;">LayIM 3.0 - layui 旗舰之作</a>
        </div>
      </div>

      <div class="fly-panel" style="padding: 20px 0; text-align: center;">
        <img src="${ctxStatic}/res/images/weixin.jpg" style="max-width: 100%;" alt="layui">
        <p style="position: relative; color: #666;">微信扫码关注 layui 公众号</p>
      </div>

    </div>
  </div>
</div>
</body>
</html>