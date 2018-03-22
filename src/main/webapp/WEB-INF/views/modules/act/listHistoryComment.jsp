<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>查看历史批注</title>
  <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
  <script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
  <script type="text/javascript">
    var processInstanceId = '${processInstanceId}';
    layui.use('table', function() {
      table = layui.table;
    });
  </script>
</head>
<body>
<table class="layui-table" lay-data="{url: '${ctx}/act/task/listHistoryComment?processInstanceId=${processInstanceId}', id:'listHistoryComment', even:true
}" lay-filter="listHistoryComment">
  <thead>
  <tr>
    <th lay-data="{field:'time', fixed: 'left', align: 'center'}">批注时间</th>
    <th lay-data="{field:'userId', fixed: 'left', align: 'center'}">批注人</th>
    <th lay-data="{field:'message', fixed: 'left', align: 'center'}">批注信息</th>
  </tr>
  </thead>
</table>
</body>
</html>
