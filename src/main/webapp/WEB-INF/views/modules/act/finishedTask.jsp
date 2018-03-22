<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>已办任务</title>
  <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
  <script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
  <script type="text/javascript">
    layui.use('table', function() {
      table = layui.table;
      //监听工具条
      table.on('tool(finishedTask)', function(obj){
        var data = obj.data;
        if(obj.event === 'showCurrentView'){
          layer.open({
            type: 2,
            title:  '查看当前流程图',
            maxmin: true, //开启最大化最小化按钮
            shade: 0.8,
            area: ['80%', '95%'],
            content: '${ctx}/act/task/showCurrentView?taskId=' + data['id']
          });
        }else if(obj.event === 'viewHis'){
          layer.open({
            type: 2,
            title: '历史批注',
            shadeClose: true,
            maxmin: true, //开启最大化最小化按钮
            shade: 0.8,
            area: ['85%', '95%'],
            content: '${ctx}/act/task/listHistoryComment?processInstanceId='+data['processInstanceId']
          });
        }
      });
    });
  </script>
</head>
<body>
<table class="layui-table" lay-data="{url: '${ctx}/act/task/finishedTask', id:'finishedTask',
 page:{layout: ['prev', 'page', 'next','skip','count'] , limit: 20}, even:true}" lay-filter="finishedTask">
  <thead>
  <tr>
    <th lay-data="{field:'id', width:150, align: 'center'}">任务ID</th>
    <th lay-data="{field:'name', align: 'center'}">任务名称</th>
    <th lay-data="{field:'createTime', align: 'center'}">创建时间</th>
    <th lay-data="{field:'endTime', align: 'center'}">结束时间</th>
    <th lay-data="{width:200, align: 'center', toolbar: '#bar'}">操作</th>
  </tr>
  </thead>
</table>
<script type="text/html" id="bar">
<%--  {{#  if(d.endTime == ''){ }}--%>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="showCurrentView">查看流程图</a>
<%--  {{#  } }}--%>
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="viewHis">查看历史批注</a>
</script>
</body>
</html>
