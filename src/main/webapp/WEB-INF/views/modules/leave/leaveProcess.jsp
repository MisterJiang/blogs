<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>请假审批</title>
  <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
  <script src="${ctxStatic}/res/layui/layui.all.js" type="text/javascript"></script>
  <script src="${ctxStatic}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
  <%--<script src="${ctxStatic}/res/layer/layer.js" type="text/javascript"></script>--%>
  <script type="text/javascript">
      function process(flag){ //状态 1 通过、 2 拒绝
        var comment = $("#comment").val();
        var taskId = $("#taskId").val();
        if(comment == ''){
          layer.msg("审批备注不能为空！");
          return;
        }else{
          $.ajax({
            url: '${ctx}/leave/leaveProcess',
            type: 'post',
            dataType: 'json',
            data: {taskId:taskId, comment:comment, flag:flag},
            cache: false,
            success: function (data) {
              layer.msg(data.msg);
              setTimeout(function(){
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            //    window.location.href= '${ctx}/act/task/agencyTask';  //跳转刷新页面
              }, 1000);
            }
          });
        }
      }
  </script>
</head>
<body>
<br/>
<form class="layui-form" onsubmit="return false;" id="searchForm">
  <input type="hidden" value="${leave.id}" class="layui-input" id="id">
  <input type="hidden" value="${taskId}" class="layui-input" id="taskId">
  <input type="hidden" value="${leave.processInstanceId}" class="layui-input" id="processInstanceId">
  <div class="layui-form-item">
    <label class="layui-form-label">请假日期</label>
    <div class="layui-input-inline">
      <input type="text" value="${leaveDate}" disabled class="layui-input" id="leaveDate">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">请假天数</label>
    <div class="layui-input-inline">
      <input type="number" value="${leave.leaveDays}" class="layui-input" id="leaveDays">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">请假原因</label>
    <div class="layui-input-inline">
      <input type="text" value="${leave.leaveReason}" disabled class="layui-input" id="leaveReason">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">审批备注*</label>
    <div class="layui-input-inline">
      <textarea class="layui-textarea" id="comment" style="width: 250px;"></textarea>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label"></label>
    <div class="layui-input-inline">
      <button class="layui-btn ayui-btn-warm" onclick="process('yes');">通过</button>
      &nbsp;&nbsp;
      <button class="layui-btn layui-btn-danger" onclick="process('no');">拒绝</button>
    </div>
  </div>
</form>
</body>
</html>
