<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>照片上传</title>
  <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
  <script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
  <script src="${ctxStatic}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
  <script type="text/javascript">
    layui.use('upload', function() {
      var $ = layui.jquery
              , upload = layui.upload;
      //多文件列表示例
      var demoListView = $('#demoList')
              ,uploadListIns = upload.render({
                elem: '#testList'
                ,url: '${ctx}/photo/upload'
                ,accept: 'images'
                ,multiple: true
                ,auto: false
                ,bindAction: '#testListAction'
                ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                  layer.load(); //上传loading
                }
                ,choose: function(obj){
                  var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                  //读取本地文件
                  obj.preview(function(index, file, result){
                    var tr = $(['<tr id="upload-'+ index +'">'
                      ,'<td>'+ file.name +'</td>'
                      ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                      ,'<td>等待上传</td>'
                      ,'<td>'
                      ,'<button class="layui-btn layui-btn-xs layui-btn-mini demo-reload layui-hide">重传</button>'
                      ,'<button class="layui-btn layui-btn-xs layui-btn-mini layui-btn-danger demo-delete">删除</button>'
                      ,'</td>'
                      ,'</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function(){
                      obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function(){
                      delete files[index]; //删除对应的文件
                      tr.remove();
                      uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    demoListView.append(tr);
                  });
                }
                ,done: function(res, index, upload){
                  layer.closeAll();
                  if(res.code == 0){ //上传成功
                    var tr = demoListView.find('tr#upload-'+ index)
                            ,tds = tr.children();
                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(3).html(''); //清空操作
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                  }
                  this.error(index, upload);
                }
                ,error: function(index, upload){
                  layer.closeAll();
                  var tr = demoListView.find('tr#upload-'+ index)
                          ,tds = tr.children();
                  tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                  tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
                }
              });
    });
  </script>
</head>
<body>
<div class="layui-upload">
  <button type="button" class="layui-btn layui-btn-normal" id="testList">选择照片</button>
  <div class="layui-upload-list">
    <table class="layui-table">
      <thead>
      <tr>
        <th>文件名</th>
        <th>大小</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody id="demoList"></tbody>
    </table>
  </div>
  <button type="button" class="layui-btn" id="testListAction">开始上传</button>
</div>
</body>
</html>
