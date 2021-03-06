<%@ page contentType="text/html;charset=UTF-8" %>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="author" content="blogs"/>
<meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=10" />
<link rel="stylesheet" href="${ctxStatic}/res/css/global.css">
<link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
<script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/jquery/jquery.serialize-object.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/vue/vue.min.js" type="text/javascript"></script>

<!--代码高亮-->
<%--<link rel="stylesheet" href="${ctxStatic}/js/highlight/styles/default.css">
<link rel="stylesheet" href="${ctxStatic}/js/highlight/styles/solarized-light.css">
--%>
<%--<link rel="stylesheet" href="${ctxStatic}/js/highlight/styles/vs.css">--%>
<link rel="stylesheet" href="${ctxStatic}/js/highlight/styles/railscasts.css">
<script src="${ctxStatic}/js/highlight/highlight.pack.js" type="text/javascript"></script>


<script src="${ctxStatic}/js/ajaxFileUpload/ajaxfileupload.js" type="text/javascript"></script>

<%--
<script src="${ctxStatic}/js/wangEditor/wangEditor.min.js" type="text/javascript"></script>--%>



<script>
    layui.config({
        version: "3.0.0"
        ,base: '${ctxStatic}/res/mods/' //这里实际使用时，建议改成绝对路径
    }).extend({
        fly: 'index'
    }).use('fly');
</script>