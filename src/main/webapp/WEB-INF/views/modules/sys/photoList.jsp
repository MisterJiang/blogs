<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>个人相册展示</title>
  <style type="text/css">
    body{overflow-y:scroll;font-family:"HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, sans-serif;background:#f4f4f4;padding:10px;margin:0;}
    h1{font-size:31px;line-height:32px;font-weight:normal;margin-bottom:25px;}
    a,a:hover{border:none;text-decoration:none;}
    img,a img{border:none;}
    pre{overflow-x:scroll;background:#ffffff;border:1px solid #cecece;padding:10px;}
    .clear{clear:both;}
    .zoomed > .container{-webkit-filter:blur(3px);filter:blur(3px);}
    .container{width:100%;margin:5px;}
    .gallery{list-style-type:none;float:left;background:#ffffff;padding:20px 20px 10px 20px;margin:0;-webkit-box-shadow:0 1px 3px rgba(0,0,0,0.25);-moz-box-shadow:0 1px 3px rgba(0,0,0,0.25);box-shadow:0 1px 3px rgba(0,0,0,0.25);-webkit-border-radius:2px;-moz-border-radius:2px;border-radius:2px;}
    .gallery li{float:left;padding:0 10px 10px 10px;}
    .gallery li:nth-child(8n){padding-right:0;}
    .gallery li a,.gallery li img{float:left;}
  </style>
  <!--图片弹出层样式 必要样式-->
  <link rel="stylesheet"  href="${ctxStatic}/sys/photo/zoom.css" media="all"/>
  <link rel="stylesheet" href="${ctxStatic}/res/layui/css/layui.css">
  <script src="${ctxStatic}/res/layui/layui.js" type="text/javascript"></script>
  <script type="text/javascript">
    layui.use('flow', function(){
      var $ = layui.jquery; //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
      var flow = layui.flow;
      flow.lazyimg({
        elem: '.gallery img'
      });

      flow.load({
        elem: '#LAY_demo1' //指定列表容器
        ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
          var lis = [];
          //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
          $.ajax({
            url: '${ctx}/photo/listPage',
            type: 'post',
            dataType: 'json',
            data: {"page":page},
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            cache: false,
            /*beforeSend:function(jqXHR,settings){
              var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
            },*/
            success: function (data) {
              if(data.code == 0){
                layui.each(data.data, function(index, item){
                  console.log(item);
                  lis.push('<li><a href="http://www.17sucai.com/preview/47509/2013-10-15/gurde-ZOOM-6ab6033/img/gallery/DSC_0008-660x441.jpg"><img width="100px" height="100px" src="http://www.17sucai.com/preview/47509/2013-10-15/gurde-ZOOM-6ab6033/img/gallery/DSC_0008-660x441.jpg" /></a></li>');

                  //  lis.push('<li>'+ item.title +'</li>');
                });
              }
              //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
              //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
              next(lis.join(''), page < data.pages);
            }
          });
        }
      });
    });
  </script>
</head>
<body>
<div class="container">
  <ul class="gallery">
    <c:forEach items="${pageList.list}" var="result">
      <li>
        <%--<a href="/photofiles/${result.userName}/${result.imageName}">
          <img width="100px" height="100px" lay-src="/photofiles/${result.userName}/${result.imageName}" />
        </a>--%>
          <a href="${qiNiuPhotosUrl}/${result.keyHash}">
            <img width="100px" height="100px" lay-src="${qiNiuPhotosUrl}/${result.keyHash}" />
          </a>
      </li>
    </c:forEach>
  </ul>
  <div class="clear"></div>
</div>
<script src="${ctxStatic}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/sys/photo/zoom.min.js" type="text/javascript"></script>
</body>
</html>
