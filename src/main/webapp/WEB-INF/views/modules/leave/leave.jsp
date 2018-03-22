<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>请假申请</title>
  <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
  <script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
  <script type="text/javascript">
    var table;
    var $;
    layui.use('table', function(){
      $ = layui.jquery,
        table = layui.table;
      //监听工具条
      table.on('tool(leaveList)', function(obj) {
        var data = obj.data;
        if (obj.event === 'del') {

        } else if(obj.event === 'detail'){
          layer.msg('ID：'+ data.id + ' 的查看操作');

        }else if(obj.event === 'edit'){

        }else if(obj.event === 'startApply'){
          layer.confirm('确认提交吗', function(index){
            //流程启动
            $.ajax({
              url: '${ctx}/leave/startApply',
              type: 'post',
              dataType: 'json',
              data: {leaveId: data.id},
              cache: false,
              success: function (data) {
                layer.closeAll();
                console.log(data.code);
                if(data.code == 1) {
                  layer.msg(data.msg);
                  table.reload('id', {
                    url: '${ctx}/leave'
                    ,where: {} //设定异步数据接口的额外参数
                    //,height: 300
                  });
                }else{
                  layer.msg(data.msg);
                }
              }
            });
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
        }else if(obj.event === 'listAction'){
          layer.open({
            type: 2,
            title: '流程执行过程',
            shadeClose: true,
            maxmin: true, //开启最大化最小化按钮
            shade: 0.8,
            area: ['85%', '95%'],
            content: '${ctx}/act/task/listAction?processInstanceId='+data['processInstanceId']
          });
        }

      });
    });

    function openLeaveInput(){
      layer.open({
        type: 2,
        title: '添加请假',
        shadeClose: true,
        maxmin: true, //开启最大化最小化按钮
        shade: 0.8,
        area: ['600px', '95%'],
        content: '${ctx}/leave/addLeave'
      });
    }
  </script>
</head>
<body>
<div class="layui-form-item">
  <div class="layui-input-inline">
    <button type="button" onclick="openLeaveInput();" class="layui-btn" id="upload">添加请假</button>
  </div>
</div>
<table class="layui-table" lay-data="{url: '${ctx}/leave', id:'id'
,page:{layout: ['prev', 'page', 'next','skip','count'] , limit: 7}, cellMinWidth: 80, even:true
}" lay-filter="leaveList">
  <thead>
  <tr>
    <th lay-data="{field:'leaveDate', fixed: 'left', align: 'center'}">请假日期</th>
    <th lay-data="{field:'leaveDays', fixed: 'left', align: 'center'}">请假天数</th>
    <th lay-data="{field:'leaveReason', fixed: 'left', align: 'center'}">请假原因</th>
    <th lay-data="{fixed: 'left', align: 'center', toolbar: '#state'}">状态</th>
    <%--<th lay-data="{field:'processInstanceId', fixed: 'left', align: 'center'}">流程实例id</th>--%>
    <th lay-data="{fixed: 'right', width:200, align:'center', toolbar: '#bar'}">操作</th>
  </tr>
  </thead>
</table>
<script type="text/html" id="bar">
  <%--<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
  {{#  if(d.state == '未提交'){ }}
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="startApply">提交申请</a>
  {{#  } else { }}
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="listAction">审批进度</a>
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="viewHis">查看历史批注</a>
  {{#  } }}
</script>
<script type="text/html" id="state">
  {{# if(d.state == '未提交'){ }}
    <span style="color:red;">{{d.state}}</span>
  {{# } }}

  {{# if(d.state == '审批拒绝'){ }}
  <span style="color:blue;">{{d.state}}</span>
  {{# } }}

  {{# if(d.state == '审核中'){ }}
    <span style="color:green;">{{d.state}}</span>
  {{# } }}

  {{# if(d.state == '审批通过'){ }}
  <span style="color:purple;">{{d.state}}</span>
  {{# } }}
</script>
</body>
</html>
