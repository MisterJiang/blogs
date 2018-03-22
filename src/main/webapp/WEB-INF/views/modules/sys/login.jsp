<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>用户登录</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
      $(document).ready(function() {
        layui.use(['form'], function() {
          var form = layui.form
                  , layer = layui.layer
          //监听提交
          form.on('submit(loginCheck)', function(data){
            var userName = $("#userName").val();
            var password = $("#password").val();
            var rememberMe = $("#rememberMe").val();
            $.ajax({
              url: '${ctx}/login/login',
              type: 'post',
              dataType: 'json',
              data: {"userName":userName, "password":password},
              contentType: "application/x-www-form-urlencoded; charset=utf-8",
              cache: false,
              beforeSend:function(jqXHR,settings){
                var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
              },
              success: function (data) {
                layer.closeAll();
                if(data.code == 0){
                  layer.msg(data.msg);
                }else if(data.code == 2){
                  layer.msg(data.msg);
                }else{
                  layer.msg(data.msg + ", 正在跳转页面...")
                  var redirectUrl = data.obj;
                  setTimeout(function(){
                    if(redirectUrl != undefined){
                      window.location.href= ${ctx}redirectUrl;  //跳转到相应的url
                    }else{
                      window.location.href= '/';
                    }
                  }, 1000);
                }
              }
            });

          });
        });
      });
    </script>
</head>
<body>
<div class="layui-container fly-marginTop">
  <div class="fly-panel fly-panel-user" pad20>
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title">
        <li class="layui-this">登入</li>
        <li><a href="${ctx}/login/reg">注册</a></li>
      </ul>
      <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <div class="layui-form layui-form-pane">
            <form onsubmit="return false;">
              <div class="layui-form-item">
                <label for="userName" class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                  <input type="text" id="userName" name="userName" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label for="password" class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="password" name="password" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
              </div>
              <%--<div class="layui-form-item">
                <label for="password" class="layui-form-label">记住密码</label>
                <input type="checkbox" id="rememberMe" name="rememberMe">
              </div>--%>
              <div class="layui-form-item">
                <c:choose>
                  <c:when test="${sessionScope.sessionInfo.userName == null}">
                    <button id="login" class="layui-btn" lay-filter="loginCheck" lay-submit="">立即登录</button>
                  </c:when>
                  <c:otherwise>
                    <span id="isNotLogin" style="color: red;">用户名【${sessionScope.sessionInfo.userName}】已登录</span>
                  </c:otherwise>
                </c:choose>
                <%--<span style="padding-left:20px;">
                  <a href="#">忘记密码？</a>
                </span>--%>
              </div>
             <%-- ${sessionScope.sessionInfo.userName}--%>
              <%--<div class="layui-form-item fly-form-app">
                <span>或者使用社交账号登入</span>
                <a href="" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-qq" title="QQ登入"></a>
                <a href="" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-weibo" title="微博登入"></a>
              </div>--%>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
