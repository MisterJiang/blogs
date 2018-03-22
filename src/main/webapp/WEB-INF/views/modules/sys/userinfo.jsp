<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>用户信息</title>
    <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
    <script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
    <script src="${ctxStatic}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
</head>
<body>
  <div style="padding: 30px 50px 20px 50px">
  <div>
    <label class="layui-inline" style="text-align: right;">头像:</label>
    <label style="padding-left: 50px;">
      ${user.userName}
    </label>
    <hr/>
  </div>

    <div>
      <label class="layui-inline" style="text-align: right;">用户名:</label>
      <label style="padding-left: 50px;">
        ${user.userName}
      </label>
      <hr/>
    </div>
    <div>
      <label class="layui-inline" style="text-align: right;">头像:</label>
      <label style="padding-left: 50px;">
        ${user.userName}
      </label>
      <hr/>
    </div>



</div>





</body>
</html>
