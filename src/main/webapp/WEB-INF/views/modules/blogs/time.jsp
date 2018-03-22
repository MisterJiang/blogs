<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>时光轴</title>
  <meta name="decorator" content="default"/>
  <style>
    .layui-timeline{padding: 40px 40px 20px 40px}
  </style>
</head>
<body>
<div class="fly-panel"></div>
<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="content detail">
      <div class="fly-panel">
          <ul class="layui-timeline">
            <c:forEach items="${timeLineList}" var="timeLine">
              <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                  <h3 class="layui-timeline-title"><fmt:formatDate value="${timeLine.createTime}" pattern="yyyy年MM月dd日"/></h3>
                  <p>
                    ${timeLine.content}
                  </p>
                </div>
              </li>
            </c:forEach>
          </ul>
        </div>
      </div>
    </div>
</div>
</div>
</body>
</html>