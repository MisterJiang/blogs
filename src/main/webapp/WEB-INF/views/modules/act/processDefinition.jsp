<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>流程定义管理</title>
  <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
  <script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
  <script src="${ctxStatic}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
  <script type="text/javascript">
    var table;
    layui.use('table', function(){
      table = layui.table;
    //监听工具条
      table.on('tool(processDefinition)', function(obj) {
        var data = obj.data;
        if(obj.event === 'showView'){
          layer.open({
            type: 2,
            title:  data.name + '流程图',
            maxmin: true, //开启最大化最小化按钮
            shade: 0.8,
            area: ['85%', '95%'],
            content: '${ctx}/act/processDefinition/showView?deploymentId=' + data['deploymentId'] + '&diagramResourceName='+ data['diagramResourceName']
          });
        }
      });

    });
  </script>
</head>
<body>
<table class="layui-table" lay-data="{url: '${ctx}/act/processDefinition/processDefinition', id:'id'
,page:{layout: ['prev', 'page', 'next','skip','count'] , limit: 20}}" lay-filter="processDefinition">
  <thead>
  <tr>
    <th lay-data="{field:'id', align:'center'}">编号</th>
    <th lay-data="{field:'name', align:'center'}">流程名称</th>
    <th lay-data="{field:'key', align:'center'}">流程定义的key</th>
    <th lay-data="{field:'version', width:60, align:'center'}">版本</th>
    <th lay-data="{field:'resourceName', align:'center'}">文件名称</th>
    <th lay-data="{field:'diagramResourceName', align:'center'}">图片名称</th>
    <th lay-data="{field:'deploymentId', align:'center'}">流程部署Id</th>
    <th lay-data="{fixed: 'right', width:178, align:'center', toolbar: '#bar'}">操作</th>
  </tr>
  </thead>
</table>
<script type="text/html" id="bar">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="showView">查看流程图</a>
</script>
</body>
</html>
