<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>查看当前流程图</title>
  <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
  <script src="${ctxStatic}/res/layui/layui.all.js" type="text/javascript"></script>
  <script src="${ctxStatic}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
  $(function(){
    var isNull = ${isNull};
    if(isNull){
      layer.msg('任务办理完成无法查看流程图', function(){
        //关闭后的操作
      });
    }
  });
</script>
<body>
<c:if test="${isNull == false}">
  <img src="${ctx}/act/processDefinition/showView?deploymentId=${deploymentId}&diagramResourceName=${diagramResourceName}">
  <div style="position: absolute;border: 1px solid red;top:${y-1}px;left:${x-1}px;width:${width+1}px;height:${height+1}px"></div>
</c:if>
</body>
</html>