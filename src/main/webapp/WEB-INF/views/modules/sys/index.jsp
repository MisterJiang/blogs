<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>blogs-后台管理</title>
  <script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
  <script src="${ctxStatic}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
  <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/sys/css/hp-layui.css" />
  <script src="${ctxStatic}/sys/hpModules/hpTheme.js" type="text/javascript"></script>
  <link rel="shortcut icon" href="favicon.ico" />
</head>
<body class="layui-layout-body hp-white-theme">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">
      blogs 后台
    </div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
   <%-- <ul class="layui-nav layui-layout-left">
      <li class=""hp-tab-add layui-nav-item">
        <a href="">阿里云</a>
      </li>
      <li class="layui-nav-item">
        <a href="">CRM系统</a>
      </li>
      <li class="layui-nav-item">
        <a href="">EHR系统</a>
      </li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd>
            <a href="">邮件管理</a>
          </dd>
          <dd>
            <a href="">消息管理</a>
          </dd>
          <dd>
            <a href="">授权管理</a>
          </dd>
        </dl>
      </li>
    </ul>--%>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a class="name" href="javascript:;">
          <i class="layui-icon"></i>主题
          <span class="layui-nav-more"></span>
        </a>
        <dl class="layui-nav-child layui-anim layui-anim-upbit">
          <dd>
            <a skin="hp-black-theme" class="hp-theme-skin-switch"  href="javascript:;">低调黑</a>
          </dd>
          <dd >
            <a skin="hp-blue-theme" class="hp-theme-skin-switch" href="javascript:;">炫酷蓝</a>
          </dd>
          <dd>
            <a skin="hp-green-theme" class="hp-theme-skin-switch"  href="javascript:;">原谅绿</a>
          </dd>
        </dl>
      </li>
      <li class="layui-nav-item">
        <a class="name" href="javascript:;">
          【${sessionScope.sessionInfo.name}】 欢迎您！
          <span class="layui-nav-more"></span>
        </a>
        <dl class="layui-nav-child">
          <dd>
            <a href="${ctx}/" target="_blank">首页</a>
          </dd>
          <dd>
            <a class="hp-tab-add" hp-href="${ctx}/sys/userinfo" href="javascript:;">
              基本资料
            </a>
          </dd>
          <dd>
            <a class="hp-tab-add" hp-href="${ctx}/admin/updatePwd" href="javascript:;">
              安全设置
            </a>
          </dd>
          <dd>
            <a href="${ctx}/login/layout">退出</a>
          </dd>
        </dl>
      </li>
    </ul>
  </div>
  <div class="layui-side hp-left-menu">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <%--<ul class="layui-nav hp-nav-none">
        <li class="layui-nav-item">
          <a href="javascript:;"  class="hp-user-name">
            <span class="hp-kd">【${sessionScope.sessionInfo.name}】</span>
          </a>
          <dl class="layui-nav-child">
            <dd>
              <a href="">基本资料</a>
            </dd>
            <dd>
              <a href="">安全设置</a>
            </dd>
          </dl>
        </li>
      </ul>--%>
      <ul class="layui-nav layui-nav-tree">
        <shiro:hasRole name="superadmin">
          <li class="layui-nav-item">
            <a class="" href="javascript:;">用户中心</a>
            <dl class="layui-nav-child">
              <dd>
                <a class="hp-tab-add" hp-href="${ctx}/sys/user" href="javascript:;">用户管理</a>
              </dd>
              <dd>
                <a class="hp-tab-add" hp-href="${ctx}/sys/role" href="javascript:;">角色管理</a>
              </dd>
              <dd>
                <a class="hp-tab-add" hp-href="${ctx}/sys/loginLog" href="javascript:;">登陆日志</a>
              </dd>
              <dd>
                <a class="hp-tab-add" hp-href="${ctx}/sys/time" href="javascript:;">时间轴</a>
              </dd>
            </dl>
          </li>
        </shiro:hasRole>
        <li class="layui-nav-item">
          <a class="" href="javascript:;">配置管理</a>
          <dl class="layui-nav-child">
            <dd>
              <a class="hp-tab-add" hp-href="${ctx}/druid/index.html" href="javascript:;">数据监控</a>
            </dd>
          </dl>
        </li>
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">文章管理</a>
          <dl class="layui-nav-child">
            <dd>
              <a class="hp-tab-add" hp-href="${ctx}/sys/article" href="javascript:;">文章列表</a>
            </dd>
          </dl>
        </li>

        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">相册管理</a>
          <dl class="layui-nav-child">
            <dd>
              <a class="hp-tab-add" hp-href="${ctx}/photo" href="javascript:;">照片上传</a>
            </dd>
            <dd>
              <a class="hp-tab-add" hp-href="${ctx}/photo/list" href="javascript:;">相册展示</a>
            </dd>
          </dl>
        </li>

        <li class="layui-nav-item">
          <a class="" href="javascript:;">请假管理</a>
          <dl class="layui-nav-child">
            <dd>
              <a class="hp-tab-add" hp-href="${ctx}/leave" href="javascript:;">请假列表</a>
            </dd>
          </dl>
        </li>

        <li class="layui-nav-item">
          <a class="" href="javascript:;">任务管理</a>
          <dl class="layui-nav-child">
            <dd>
              <a class="hp-tab-add" hp-href="${ctx}/act/task/agencyTask" href="javascript:;">代办任务管理</a>
            </dd>
            <dd>
              <a class="hp-tab-add" hp-href="${ctx}/act/task/finishedTask" href="javascript:;">已办任务管理</a>
            </dd>
          </dl>
        </li>
        <shiro:hasRole name="superadmin">
          <li class="layui-nav-item">
            <a class="" href="javascript:;">流程管理</a>
            <dl class="layui-nav-child">
              <dd>
                <a class="hp-tab-add" hp-href="${ctx}/act/deploy" href="javascript:;">流程部署管理</a>
              </dd>
              <dd>
                <a class="hp-tab-add" hp-href="${ctx}/act/processDefinition/processDefinition" href="javascript:;">流程定义管理</a>
              </dd>
            </dl>
          </li>
        </shiro:hasRole>
      </ul>
    </div>
  </div>
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div class="layui-tab hp-tab " style="" lay-filter="hp-tab-filter" lay-allowclose="true">
      <ul class="layui-tab-title" style="">
        <li class="layui-this" lay-id="0">首页</li>
      </ul>
      <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
          55555555555
        </div>
      </div>

    </div>
  </div>

  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © blogs-version-1.0
  </div>
</div>
<script>
  // 配置
  layui.config({
    base: '${ctxStatic}/sys/hpModules/' // 扩展模块目录
  }).extend({ // 模块别名
    hpTab: 'hpTab/hpTab',
    hpRightMenu: 'hpRightMenu/hpRightMenu',
    hpFormAll: 'hpFormAll/hpFormAll'
  });
  //JavaScript代码区域
  layui.use(['element', 'carousel','hpTheme', 'hpTab', 'hpLayedit', 'hpRightMenu'], function() {
    var element = layui.element;
    var carousel = layui.carousel; //轮播
    var hpTab = layui.hpTab;
    var hpRightMenu = layui.hpRightMenu;
    var hpTheme=layui.hpTheme;
    $ = layui.jquery;
    // 初始化主题
    hpTheme.init();
    //初始化轮播
    carousel.render({
      elem: '#test1',
      width: '100%', //设置容器宽度
      interval: 1500,
      height: '500px',
      arrow: 'none', //不显示箭头
      anim: 'fade', //切换动画方式
    });

    // 初始化 动态tab
    hpTab.init();
    // 右键tab菜单
    hpRightMenu.init();

  });
</script>
</body>

</html>