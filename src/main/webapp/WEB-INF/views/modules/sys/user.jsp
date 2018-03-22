<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>用户管理</title>
    <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
    <script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
    <script type="text/javascript">
        var table;
        layui.use('table', function(){
            table = layui.table;
        });
    </script>
</head>
<body>
<table class="layui-table" lay-data="{url: '${ctx}/sys/userList', id:'userName'
,page:{layout: ['prev', 'page', 'next','skip','count'] , limit: 20}, cellMinWidth: 80, height: 'full-20'
,even:true
}" lay-filter="userList">
    <thead>
    <tr>
        <th lay-data="{field:'userName', fixed: 'left', align: 'center'}">用户名</th>
        <th lay-data="{field:'email',  fixed: 'left', align: 'center'}">邮箱</th>
        <th lay-data="{field:'createTime', sort: true,  fixed: 'left', align: 'center'}">注册时间</th>
        <th lay-data="{field:'roleTypeName', fixed: 'left', align: 'center'}">角色</th>
    </tr>
    </thead>
</table>
</body>
</html>
