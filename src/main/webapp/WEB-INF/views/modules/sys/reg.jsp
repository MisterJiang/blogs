<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>用户注册</title>
  <meta name="decorator" content="default"/>
  <script type="text/javascript">
    $(document).ready(function() {
      layui.use(['form'], function() {
        var form = layui.form
                , layer = layui.layer
        //监听提交
        form.on('submit(regSave)', function(data){
      //    var json_data = $('#searchForm').serializeJSON();
          var userName = $("#userName").val();
          var password = $("#password").val();
          var email = $("#email").val();
          var repass = $("#repass").val();
          var name = $("#name").val();
          if(password != repass){
            layer.msg("输入两次密码不一致！");
            return false;
          }else{
            $.ajax({
              url: '${ctx}/login/regSave',
              type: 'post',
              dataType: 'json',
              contentType: "application/x-www-form-urlencoded; charset=utf-8",
              data: {"userName":userName, "email":email, "password":password, "name":name},
              cache: false,
              success: function (data) {
                layer.closeAll();
                if(data.code == 0){
                  layer.msg(data.msg);
                }else if(data.code == 2){
                  layer.msg(data.msg);
                }else{
                  layer.msg(data.msg);
                  setTimeout(function(){
                    layer.closeAll();
                    layer.msg(data.msg + ", 正在跳转页面...")
                    window.location.href= '${ctx}/login';  //跳转到登录页
                  }, 1000);
                }
              }
            });
          }
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
        <li><a href="${ctx}/login">登入</a></li>
        <li class="layui-this">注册</li>
      </ul>
      <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <div class="layui-form layui-form-pane">
            <form id="searchForm" onsubmit="return false;">
              <div class="layui-form-item">
                <label for="userName" class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                  <input type="text" id="userName" name="userName" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">将会成为您唯一的登入名</div>
              </div>
              <div class="layui-form-item">
                <label for="userName" class="layui-form-label">昵称</label>
                <div class="layui-input-inline">
                  <input type="text" id="name" name="name" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">昵称：“旧时光”</div>
              </div>
              <div class="layui-form-item">
                <label for="email" class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                  <input type="text" id="email" name="email" required lay-verify="email" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">438837583@qq.com</div>
              </div>
              <div class="layui-form-item">
                <label for="password" class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="password" name="password" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">6到16个字符</div>
              </div>
              <div class="layui-form-item">
                <label for="repass" class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="repass" name="repass" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <c:choose>
                  <c:when test="${sessionScope.sessionInfo.userName == null}">
                    <button class="layui-btn" lay-filter="regSave" lay-submit="">立即注册</button>
                  </c:when>
                  <c:otherwise>
                    <span id="isNotLogin" style="color: red;">用户名【${sessionScope.sessionInfo.userName}】已登录</span>
                  </c:otherwise>
                </c:choose>
              </div>
              <%--<div class="layui-form-item fly-form-app">
                <span>或者直接使用社交账号快捷注册</span>
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