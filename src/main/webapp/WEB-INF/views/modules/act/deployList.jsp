<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>流程部署管理</title>
  <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
  <script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
  <script src="${ctxStatic}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
  <script type="text/javascript">
    var stauts = true;
    var table;
    var $;
    var upload;
    var name;
    var layer;
    layui.use('table', function(){
      table = layui.table;
      //监听工具条
      table.on('tool(deployList)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
          layer.confirm('真的删除行么', function(index){
          //  layer.msg('ID：'+ data.id + ' 的查看操作');
            $.ajax({
              url: '${ctx}/act/deleteDeploy',
              type: 'post',
              dataType: 'json',
              data: {"id": data.id},
              cache: false,
              beforeSend:function(jqXHR,settings){
                var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
              },
              success: function (data) {
                layer.closeAll();
                if(data.code == 1) {
                  layer.msg(data.msg);
                  obj.del();
                }else{
                  layer.msg(data.msg);
                }
              }
            });
          });
        }
      });
    });
    layui.use('upload', function() {
      $ = layui.jquery
              ,upload = layui.upload
              ,layer = layui.layer;
      //选完文件后不自动上传
      upload.render({
        elem: '#addDeploy'
        ,url: '${ctx}/act/addDeploy'
        ,accept: 'file' //普通文件
        ,exts: 'zip' //只允许上传压缩文件
        ,auto: false
        ,data:{name: name}
        ,bindAction: '#upload'
        /*,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
         layer.load(); //上传loading
         }*/
        ,done: function(res){
          layer.msg(res.msg);
          if(res.code == 0){  //成功
            stauts = false;
            setTimeout(function(){
              table.reload('id', {
                url: '${ctx}/act/deployList'
                ,where: {} //设定异步数据接口的额外参数
                //,height: 300
              });
              //window.location.href= '${ctx}/act/deploy';  //跳转到相应的url
            }, 1500);
          }else{
            setTimeout(function(){
              window.location.href= '${ctx}/act/deploy';  //跳转到相应的url
            }, 1000);
          }
        }
      });
    });
    /*function getName(){
      if(!stauts){
        layer.msg("请不要重复提交！");
      }
      name = $("#name").val();
      console.log(name);
      document.getElementById("upload").click();
    }*/
  </script>
</head>
<body>
<div class="layui-upload">
  <form class="layui-form" onsubmit="return false;">
   <%-- <div class="layui-form-item">
      <label class="layui-form-label">流程名称</label>
      <div class="layui-input-inline">
        <input type="text" id="name" name="name" required lay-verify="required" placeholder="流程名称" class="layui-input">
      </div>
    </div>--%>
    <div class="layui-form-item">
      <label class="layui-form-label">流程文件</label>
      <div class="layui-input-inline">
        <button type="button" class="layui-btn layui-btn-primary" id="addDeploy"><i class="layui-icon"></i>只允许压缩文件zip格式</button>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label"></label>
      <div class="layui-input-inline">
        <button type="button" lay-submit class="layui-btn" id="upload">上传部署</button>
      </div>
    </div>
  </form>
</div>
<table class="layui-table" lay-data="{url: '${ctx}/act/deployList', id:'id'
,page:{layout: ['prev', 'page', 'next','skip','count'] , limit: 20}, height: 'full-30'
,even:true
}" lay-filter="deployList">
  <thead>
  <tr>
    <th lay-data="{field:'id', width:178, fixed: 'left', align: 'center'}">编号</th>
    <th lay-data="{field:'name', fixed: 'left', align: 'center'}">流程名称</th>
    <th lay-data="{field:'deploymentTime', fixed: 'left', align: 'center'}">部署时间</th>
    <th lay-data="{width:100, align: 'center', toolbar: '#bar'}">操作</th>
  </tr>
  </thead>
</table>
<script type="text/html" id="bar">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
</html>
