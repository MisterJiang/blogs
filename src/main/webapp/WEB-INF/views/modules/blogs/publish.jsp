<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>文章发布</title>
  <meta name="decorator" content="default"/>
  <script>
    $(document).ready(function() {
      var layedit;
      var index;
      layui.use('layedit', function(){
        layedit = layui.layedit
                ,$ = layui.jquery;
        layedit.set({
          uploadImage: {
            url: '${ctx}/article/uploadImage' //接口url
            ,type: 'post' //默认post
          }
        });
        index = layedit.build('L_content', {//建立编辑器
          tool: ['strong', 'italic', 'underline', 'del', 'unlink', 'link', 'left', 'center', 'right', '|', 'face', 'image', 'code']
        });

        //编辑器外部操作
        var active = {
          content: function(){
            var title = $("#L_title").val();  //标题
            var content = layedit.getContent(index);
            var str = "<pre class=" + "layui-code" +" lay-lang";
            content = content.replace(/<pre lay-lang/g, str);
            var strTitle = 'alt=' + '"' + title +'"';
          //  console.log(strTitle);
            content = content.replace(/alt="undefined"/g, strTitle);
          //  console.log(content);
            var textView = layedit.getText(index);
            layer.open({
              type: 1
              ,title: '预览'
              ,shade: false
              ,area: ['100%', '100%']
              ,scrollbar: false
              ,content: '<div class="detail-body" style="margin:20px;">'+  content +'</div>'
            });
          }
          ,text: function(){
            alert(layedit.getText(index)); //获取编辑器纯文本内容
          }
          ,selection: function(){
            alert(layedit.getSelection(index));
          }
        };

        $('.site-demo-layedit').on('click', function(){
          var type = $(this).data('type');
          active[type] ? active[type].call(this) : '';
        });
      });

      layui.use(['form'], function () {
        var form = layui.form
                , layer = layui.layer;
        //监听提交
        form.on('submit(articleSave)', function (data) {
          var content = layedit.getContent(index);
          var title = data.field.title;  //标题
          var str = "<pre class=" + "layui-code" +" lay-lang";
          content = content.replace(/<pre lay-lang/g, str);
          var strTitle = 'alt=' + '"' + title +'"';
      //    console.log(strTitle);
          content = content.replace(/alt="undefined"/g, strTitle);
       //   console.log(content);
          var textView  = layedit.getText(index); //纯文本
          textView = textView.substring(0, 250);
          if(content == ''){
            layer.msg("内容不能为空！");
          }else{
            $.ajax({
              url: '${ctx}/article/publish/save',
              type: 'post',
              dataType: 'json',
              data: {"content":content, "title":title, "textView":textView},
              cache: false,
              success: function (data) {
                console.log(data.obj);
                //layer.closeAll();
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
          }
          return false;
        });
      });
    });
  </script>
</head>
<body>
<div class="fly-panel"></div>
<div class="layui-container fly-panel fly-marginTop">
  <div class="fly-panel" pad20 style="padding-top: 5px;">
   <%-- <div class="fly-none">没有权限</div>--%>
    <div class="layui-form layui-form-pane">
      <div class="layui-tab layui-tab-brief" lay-filter="user">
        <ul class="layui-tab-title">
          <li class="layui-this">发表新帖<!-- 编辑帖子 --></li>
        </ul>
        <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
          <div class="layui-tab-item layui-show">
            <form id="searchForm" onsubmit="return false;">
              <div class="layui-row layui-col-space15 layui-form-item">
                <div class="layui-col-md9">
                  <label for="L_title" class="layui-form-label">标题</label>
                  <div class="layui-input-block">
                    <input type="text" id="L_title" name="title" lay-verify="required" placeholder="标题" autocomplete="off" class="layui-input">
                    <!-- <input type="hidden" name="id" value="{{d.edit.id}}"> -->
                  </div>
                </div>
              </div>
              <div class="layui-form-item layui-form-text">
                <div class="layui-input-block">
                <textarea id="L_content" name="content"></textarea>
                </div>
              </div>
              <div class="layui-form-item">
                <span class="layui-btn site-demo-layedit" data-type="content">预览</span>
                <button class="layui-btn" lay-filter="articleSave" lay-submit="">立即发布</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>