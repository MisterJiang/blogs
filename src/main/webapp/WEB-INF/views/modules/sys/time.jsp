<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>时间轴</title>
  <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
  <script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
  <script type="text/javascript">
    var table;
    var layer;
    var $;
    var curr = ${pageList.pageNum};
    layui.use(['table', 'jquery', 'layer', 'layedit'], function(){
      var table = layui.table;
              $ = layui.jquery;
        layer = layui.layer;
      var layedit = layui.layedit, ue = "";

      $("#btnAdd").on("click", function () {
        var index = layer.open({
            type: 1,
            title: "新增",
            area: ['500px', '300px'],
            btnAlign: 'c',
            moveType: 1, //拖拽模式，0或者1
            skin: "layui-layer-molv",
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                console.log(ue);
              var content = layedit.getContent(ue);
              if (content.length == 0) {
                layer.msg("至少得有一个字吧");
                return;
              }
              var load = layer.msg("正在添加...", {
                icon: 16
                , shade: 0.4
              });
              setTimeout(function () {
                $.ajax({
                  url: "${ctx}/sys/saveTimeLine",
                  type: "post",
                  data: {"content": content},
                  success: function (res) {
                    layer.close(load);
                    if (res) {
                      layer.closeAll();
                      layer.msg("添加成功");
                      location.reload();
                    } else {
                      layer.msg("操作失败")
                    }
                  },
                })
              }, 500);
            },
            content: "<textarea id=\"content\" style=\"display: ;\"></textarea>",
            success: function (layero) {
              ue = layedit.build('content', {
                height: 150,
                tool: [
                  'strong' //加粗
                  , 'italic' //斜体
                  , 'underline' //下划线
                  , 'del'
                  , 'left'
                  , 'center'
                  , 'right'
                  , '|'
                  , 'face',
                ]//删除线]
              }); //建立编辑器
            }
        });
      });
    });



    layui.use(['laypage', 'layer'], function() {
        var laypage = layui.laypage
                , layer = layui.layer;

        laypage.render({
            elem: 'page'
            ,count: ${pageList.total}
            ,curr:${pageList.pageNum}
            ,theme: '#FF5722'
            ,limit: 5
            ,skip: true
           /* ,curr: location.hash.replace('#!page=', '') //获取hash值为fenye的当前页
            ,hash: 'page' //自定义hash值*/
            ,jump: function(obj, first){
                if(!first){
                    window.location.href= "${ctx}/sys/time?page=" + obj.curr;
                }
            }
        });
    });

    function del(id){
        layer.confirm('真的删除行么', function(index){
            $.ajax({
                url: '${ctx}/sys/timeLine/delete',
                type: 'post',
                dataType: 'json',
                data: {"id": id},
                cache: false,
                beforeSend:function(jqXHR,settings){
                    var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
                },
                success: function (data) {
                    layer.closeAll();
                    if(data.code == 1) {
                       // layer.msg(data.msg);
                        window.location.href= "${ctx}/sys/time?page=" + curr;
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
        });
    }

  </script>
</head>
<body>
<div class="layui-btn-group">
  <a class="layui-btn layui-btn-danger" id="btnAdd"><i class="layui-icon">&#xe654;</i>新增时光轴</a>
</div>
<div class="layui-btn-group">
  <a class="layui-btn layui-btn-normal" href="${ctx}/time${urlSuffix}" target="_blank"><i class="fa fa-eye fa-fw"></i>时光轴预览</a>
</div>
<table class="layui-table">
    <colgroup>
        <col width="180">
        <col width="180">
        <col>
        <col width="70">
    </colgroup>
    <thead>
    <tr>
        <th>增加时间</th>
        <th>添加者</th>
        <th>时间轴内容</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pageList.list}" var="result">
        <tr>
            <td><fmt:formatDate value="${result.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>${result.createUser}</td>
            <td>${result.content}</td>
            <td><button class="layui-btn layui-btn-danger layui-btn-sm" onclick="del('${result.id}');">删除</button></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="page"></div>
</body>
</html>
