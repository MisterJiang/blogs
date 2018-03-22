<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>角色管理</title>
  <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
  <script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
  <script src="${ctxStatic}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
  <script type="text/javascript">
    var table;
    layui.use('table', function(){
      table = layui.table;
      //监听工具条
      table.on('tool(roleList)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
          layer.msg('ID：'+ data.id + ' 的查看操作');
        } else if(obj.event === 'del'){
          layer.confirm('真的删除行么', function(index){
            $.ajax({
              url: '${ctx}/sys/role/delete',
              type: 'post',
              dataType: 'json',
              data: {"id": data.id},
              cache: false,
              beforeSend:function(jqXHR,settings){
                var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
              },
              success: function (data) {
                layer.closeAll();
                if(data.code == 1) {
                  layer.msg(data.msg);
                  obj.del();
                }else{
                  layer.msg(data.msg);
                }
              }
            });
          });
        } else if(obj.event === 'edit'){
          layer.alert('编辑行：<br>'+ JSON.stringify(data))
        }
      });
    });
  </script>
</head>
<body>
<table class="layui-table" lay-data="{url: '${ctx}/sys/roleList', id:'id'
,page:{layout: ['prev', 'page', 'next','skip','count'] , limit: 20}, cellMinWidth: 80, height: 'full-20'
,even:true
}" lay-filter="roleList">
  <thead>
  <tr>
    <th lay-data="{field:'type', fixed: 'left', align: 'center'}">角色编码</th>
    <th lay-data="{field:'roleName', fixed: 'left', align: 'center'}">角色名称</th>
    <th lay-data="{fixed: 'right', width:178, align:'center', toolbar: '#bar'}">操作</th>
  </tr>
  </thead>
</table>
<script type="text/html" id="bar">
  <%--<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>--%>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
</html>
