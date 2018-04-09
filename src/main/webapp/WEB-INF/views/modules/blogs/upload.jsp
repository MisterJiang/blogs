<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>七牛上传图片</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<form class="form-horizontal" name="upform" action="/upload/uploadToqiNiu" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label>上传文件:</label>
        <div class="col-sm-10">
            <input type="file" name="filename"/><br/>
            　<input type="submit" value="提交" /><br/>
        </div>
    </div>
</form>
</body>
</html>
