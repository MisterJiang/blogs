<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${article.title}</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        var articleId = '${article.id}';
        $(function () {
          //  $('pre').each(function(i, e) {hljs.highlightBlock(e)});  //代码高亮
            innit();
        });

        //初始化查询评论列表
        function innit() {
            $.ajax({
                url:'${ctx}/comment/list',
                type: 'post',
                dataType: 'json',
                data: {"articleId":articleId},
                cache: false,
                beforeSend:function(jqXHR,settings){
                    $("#jieda").html("<li class='fly-none'>数据加载中...</li>");
                },
                success: function (data) {
                //    console.log(data.obj.list);
                    if(data['obj']['list'].length > 0){
                        new Vue({
                            el:"#jieda",
                            template:'#jieda_template',
                            data: {result: data.obj.list},
                            methods:{
                                replyTo:function(item, type){
                                    $("#reply").remove();
                                    $("." + item.id).append(
                                        "<div id='reply'><br/><textarea id='reply_text' class='layui-textarea' placeholder='说一说' style='height: 100px;'></textarea>" +
                                        "<br/><div class='layui-form-item' id='replySave'>" +
                                        "<button class='layui-btn' onclick='reply("+JSON.stringify(item)+","+ JSON.stringify(type) +");'>提交回复</button>"+
                                        "</div>" +
                                        "</div>"
                                    );
                                    $("html, body").animate(
                                        {scrollTop: $("#reply_text").offset().top},
                                        {duration: 500, easing: "swing"});
                                    return false;
                                },
                            }
                        });
                    }else {
                        $("#jieda").html("<li class='fly-none'>抢沙发</li>");
                    }
                }
            });
        }

        function reply(item,type) {

            if (${sessionInfo != null}){
                var replyMsg = $("#reply_text").val();
                if (replyMsg == ''){
                    layer.msg('评论不能为空！', function(){});
                    return;
                }
                console.log(JSON.stringify(item));
                var commentId;
                var toUserName;
                var id;
                if(type == 'comment'){  //回复评论
                    commentId = item['id'];
                    toUserName = item['userName'];
                }else if(type == 'reply'){ //回复评论人
                    commentId = item['commentId'];
                    toUserName = item['fromUserName'];
                    id = item['id'];

                }
                replySave(commentId, toUserName, replyMsg, type, id);
            }else {
                layer.msg('未登录', function(){});
            }
        }

        function  replySave(commentId, toUserName, replyMsg, type, id) {
            $.ajax({
                url:'${ctx}/comment/replySave',
                type: 'post',
                dataType: 'json',
                data: {"commentId":commentId, "toUserName":toUserName, "replyMsg":replyMsg},
                cache: false,
                beforeSend:function(jqXHR,settings){
                   // layer.msg('加载中', {icon: 16, shade: 0.01});
                },
                success: function (data) {
                    //console.log(data['obj']);
                    var html = "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "";

                    //console.log(data['obj']);replyAppend
                    if(type == 'reply'){
                        $("#reply_text").before("<li class='jieda-daan'>11111111111111</li>" +
                            "</ul>");
                    }else {
                        $("#replyAppend").append("<ul style='padding-left: 50px;'>" +
                            "<li class='jieda-daan'>11111111111111</li>" +
                            "</ul>");
                    }

                }

            });
        }


        function pinlun(id) {  //发表评论
            $("#reply").remove();
            if (${sessionInfo != null}){
                var content = $("#L_content").val();
                if(content!=''){
                    pinlunSave(id, content);
                }else {
                    layer.msg('评论不能为空！', function(){});
                }
            }else {
                layer.msg('未登录', function(){});
            }
        }

        //保存
        function pinlunSave(id, content) {
            $.ajax({
                url:'${ctx}/comment/save',
                type: 'post',
                dataType: 'json',
                data: {"id":id, "content":content},
                cache: false,
                beforeSend:function(jqXHR,settings){
                    layer.msg('加载中', {icon: 16, shade: 0.01});
                },
                success: function (data) {
                    layer.closeAll();
                    if (data.code == 1){
                        $(".fly-none").hide();
                        var comment = data['obj'];
                        $("#L_content").val('');
                        $("#jieda").append(
                            "<li id="+ comment['id'] +" class='jieda-daan'>" +
                            "<div class='detail-about detail-about-reply'>" +
                            "<a class='fly-avatar' href='#'>" +
                            "<img src='https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg' alt=''>" +
                            "</a>" +
                            "<div class='fly-detail-user'>" +
                            "<a href='#' class='fly-link'>" +
                            "<cite>"+comment['userName']+"</cite>"+
                            " <i class='layui-badge fly-badge-vip'>楼主</i>" +
                            "</a>" +
                            "</div>" +
                            "<div class='detail-hits'>" +
                            "<span>"+new Date(comment['createTime']).toLocaleString()+"</span>" +
                            "</div>" +
                            "</div>" +
                            "<div class='detail-body jieda-body photos'>" +
                            "<p>"+comment['replyMsg']+"</p>" +
                            "</div>" +
                            "</li>");
                     //   innit();
                    }else {
                        layer.msg(data.msg, function(){});
                    }
                }
            })
        }
    </script>
    <script type="text/template" id="jieda_template">
            <ul class="jieda" id="jieda">
                <li v-bind:id="comment.id" class="jieda-daan" v-for="comment in result">
                    <div class="detail-about detail-about-reply">
                        <a class="fly-avatar" href="#">
                            <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="">
                        </a>
                        <div class="fly-detail-user">
                            <a href="#" class="fly-link">
                                <cite>{{comment.userName}}</cite>
                                <i class="layui-badge fly-badge-vip">楼主</i>
                            </a>
                        </div>
                        <div class="detail-hits">
                            <span>{{new Date(comment.createTime).toLocaleString()}}</span>
                        </div>
                    </div>
                    <div class="detail-body jieda-body photos">
                        <p>{{comment.replyMsg}}</p>
                    </div>
                    <div class="jieda-reply">
                        <span type="reply" v-on:click="replyTo(comment,'comment')">
                            <i class="iconfont icon-svgmoban53"></i>回复
                        </span>
                    </div>

                    <div id="replyAppend">
                        <ul v-for="reply in comment.replyList" style="padding-left: 50px;">
                            <li v-bind:id="reply.id" class="jieda-daan">
                                <div class="detail-about detail-about-reply">
                                    <a class="fly-avatar" href="#">
                                        <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="">
                                    </a>
                                    <div class="fly-detail-user">
                                        @<a href="#" class="fly-link"><cite>{{reply.fromUserName}}</cite></a>回复&nbsp;
                                        @<a href="#" class="fly-link"><cite>{{reply.toUserName}}</cite></a>
                                    </div>
                                    <div class="detail-hits">
                                        <span>{{new Date(reply.createTime).toLocaleString()}}</span>
                                    </div>
                                </div>
                                <div class="detail-body jieda-body photos">
                                    <p>{{reply.replyMsg}}</p>
                                </div>
                                <div class="jieda-reply" v-on:click="replyTo(reply,'reply')">
                                    <span type="reply"><i class="iconfont icon-svgmoban53"></i>回复</span>
                                </div>
                            </li>
                            <div v-bind:class="reply.id"></div>
                        </ul>
                    </div>
                    <div v-bind:class="comment.id"></div>
                </li>
            </ul>
    </script>
</head>
<body>
<div class="fly-panel"></div>
<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md9 content detail">
            <div class="fly-panel detail-box">
                <h1>${article.title}</h1>
                <div class="fly-detail-info">
                    <div class="fly-admin-box" data-id="123">
                        <span class="layui-btn layui-btn-xs jie-admin" type="del">删除</span>
                        <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="1">置顶</span>
                    </div>
           <span class="fly-list-nums">
            <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i> 66</a>
            <i class="iconfont" title="人气">&#xe60b;</i> ${article.view}
          </span>
                </div>
                <div class="detail-about">
                    <a class="fly-avatar" href="../user/home.html">
                        <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="贤心">
                    </a>
                    <div class="fly-detail-user">
                        <a href="../user/home.html" class="fly-link">
                            <cite>贤心</cite>
                            <i class="iconfont icon-renzheng" title="认证信息：{{ rows.user.approve }}"></i>
                            <i class="layui-badge fly-badge-vip">VIP3</i>
                        </a>
                        <span>${article.view}</span>
                    </div>
                    <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                        <span style="padding-right: 10px; color: #FF7200">悬赏：60飞吻</span>
                        <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="add.html">编辑此贴</a></span>
                    </div>
                </div>
                <div class="detail-body photos">
                    ${article.content}
                </div>
            </div>
            <div class="fly-panel detail-box" id="flyReply">
                <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                    <legend>评论</legend>
                </fieldset>
                <ul class="jieda" id="jieda"></ul>
                <hr class="layui-bg-blue">
                <div class="layui-form layui-form-pane">
                    <div class="layui-form-item layui-form-text">
                        <a name="comment"></a>
                        <div class="layui-input-block">
                            <textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea <%--fly-editor--%>" style="height: 150px;"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item" id="pinlun">
                        <button class="layui-btn" onclick="pinlun('${article.id}')">提交回复</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md3">
            <dl class="fly-panel fly-list-one">
                <dt class="fly-panel-title">本周热议</dt>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>

                <!-- 无数据时 -->
                <!--
                <div class="fly-none">没有相关数据</div>
                -->
            </dl>

            <div class="fly-panel">
                <div class="fly-panel-title">
                    这里可作为广告区域
                </div>
                <div class="fly-panel-main">
                    <a href="http://layim.layui.com/?from=fly" target="_blank" class="fly-zanzhu" time-limit="2017.09.25-2099.01.01" style="background-color: #5FB878;">LayIM 3.0 - layui 旗舰之作</a>
                </div>
            </div>

            <div class="fly-panel" style="padding: 20px 0; text-align: center;">
                <img src="${ctxStatic}/res/images/weixin.jpg" style="max-width: 100%;" alt="layui">
                <p style="position: relative; color: #666;">微信扫码关注 layui 公众号</p>
            </div>

        </div>
    </div>
</div>
</body>
</html>
