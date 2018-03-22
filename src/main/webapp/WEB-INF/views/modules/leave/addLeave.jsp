<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>添加请假</title>
  <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
  <script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
  <script src="${ctxStatic}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
  <script type="text/javascript">
    layui.use(['form'], function() {
      var form = layui.form
              ,layer = layui.layer
      //监听提交
      form.on('submit(addLeave)', function(data){
        var leaveDate = $("#leaveDate").val();
        var leaveDays = $("#leaveDays").val();
        var leaveReason = $("#leaveReason").val();
       // console.log("leaveDate="+ leaveDate, "leaveDays="+leaveDays, "leaveReason="+ leaveReason);
        $.ajax({
          url:'${ctx}/leave/addLeave',
          type: 'post',
          dataType: 'json',
          data:{leaveDays:leaveDays, leaveReason:leaveReason, leaveDate:leaveDate},
          cache: false,
          success: function (data) {
            layer.msg("添加请假成功！");
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index); //再执行关闭
          }
        });
      });
    });

    layui.use('laydate', function() {
      var laydate = layui.laydate;
      //时间选择器
      laydate.render({
        elem: '#leaveDate'
        ,type: 'datetime'
      });
    });
  </script>
</head>
<body>
<br/>
<form class="layui-form"  id="searchForm">
   <div class="layui-form-item">
     <label class="layui-form-label">请假日期</label>
     <div class="layui-input-inline">
       <input type="text" required lay-verify="required" class="layui-input" id="leaveDate">
     </div>
   </div>
  <div class="layui-form-item">
    <label class="layui-form-label">请假天数</label>
    <div class="layui-input-inline">
      <input type="number" required lay-verify="required" class="layui-input" id="leaveDays">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">请假原因</label>
    <div class="layui-input-inline">
      <input type="text" required lay-verify="required" class="layui-input" id="leaveReason">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label"></label>
    <div class="layui-input-inline">
      <button lay-submit="" class="layui-btn" lay-filter="addLeave">添加请假</button>
    </div>
  </div>
</form>
</body>
</html>
