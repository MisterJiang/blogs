<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>流程执行过程</title>
  <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
  <script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
  <script type="text/javascript">
    var processInstanceId = '${processInstanceId}';
    var taskId = '${taskId}';
    layui.use('table', function() {
      table = layui.table;
    });
  </script>
</head>
<body>
<table class="layui-table" lay-data="{url: '${ctx}/act/task/listAction?processInstanceId=${processInstanceId}&taskId=${taskId}',
 id:'listAction', even:true}" lay-filter="listAction">
  <thead>
  <tr>
    <th lay-data="{field:'activityId', align: 'center'}">任务节点ID</th>
    <th lay-data="{field:'activityName', align: 'center'}">任务节点名称</th>
    <th lay-data="{field:'startTime', align: 'center'}">开始时间</th>
    <th lay-data="{field:'endTime', align: 'center'}">结束时间</th>
    <th lay-data="{field:'durationInMillis', align: 'center'}">花费时间(毫秒)</th>
  </tr>
  </thead>
</table>
</body>
</html>
