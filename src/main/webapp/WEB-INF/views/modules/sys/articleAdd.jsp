<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>文章添加</title>
    <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">

    <script src="${ctxStatic}/res/layui/layui.all.js" type="text/javascript"></script>

    <script src="${ctxStatic}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8" src="${ctxStatic}/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctxStatic}/js/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${ctxStatic}/js/ueditor/lang/zh-cn/zh-cn.js"></script>




    <script type="text/javascript">

    </script>
</head>
<body>
    <div>
        <label>标题：</label>
        <div>
            <input type="text" id="L_title" name="title" placeholder="文章标题" class="layui-input">
        </div>
       <%-- <label>文章类型</label>
        <div>
            <select name="city" lay-verify="required">
                <option value=""></option>
                <option value="0">北京</option>
                <option value="1">上海</option>
                <option value="2">广州</option>
                <option value="3">深圳</option>
                <option value="4">杭州</option>
            </select>
        </div>--%>
        <div id="editor" type="text/plain" style="width:99.5%;height:500px; margin-top: 20px;"></div>
        <button class="layui-btn" onclick="publicArticle();">立即发布</button>
    </div>


    <script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');


    function publicArticle() {

        var title = $("#L_title").val();
        if(title == ''){
            layer.msg("标题不能为空！");
            return false;
        }


        if(ue.hasContents()){
            var content = ue.getContent();
            var title = $("#L_title").val();
            var textView  = ue.getContentTxt(); //纯文本
            textView = textView.substring(0, 250);
            $.ajax({
                url: '${ctx}/article/publish/save',
                type: 'post',
                dataType: 'json',
                data: {"content":content, "title":title, "textView":textView},
                cache: false,
                success: function (data) {
                    if(data.code == 0){
                        layer.msg(data.msg);
                    }else if(data.code == 2){
                        layer.msg(data.msg);
                    }else{
                        layer.msg("发布成功！");
                        setTimeout(function(){
                            window.location.href= '${ctx}/article/detail/'+ data.obj + '${urlSuffix}';
                        }, 1000);
                    }
                }
            });
        }else {
            layer.msg("内容不能为空！");
            return false;
        }

    }




    function isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData () {
        alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
    }

    function clearLocalData () {
        UE.getEditor('editor').execCommand( "clearlocaldata" );
        alert("已清空草稿箱")
    }
        </script>

</body>
</html>
