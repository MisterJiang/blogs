<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${article.title}</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        var articleId = '${article.id}';
        $(function () {
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
                success: function (data) {
                    console.log(data.obj.list);
                    var app = new Vue({
                        el:"#jieda",
                        template:'#jieda_template',
                        data: {result: data.obj.list}
                    });

                }
            });
        }
    </script>
    <script type="text/template" id="jieda_template">
            <ul class="jieda" id="jieda">
                <li data-id="111" class="jieda-daan" v-for="item in result">
                    <div class="detail-about detail-about-reply">
                        <a class="fly-avatar" href="#">
                            <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="">
                        </a>
                        <div class="fly-detail-user">
                            <a href="#" class="fly-link">
                                <cite>贤心</cite>
                                <i class="layui-badge fly-badge-vip">楼主</i>
                            </a>
                        </div>
                        <div class="detail-hits">
                            <span>{{item.createTime}}</span>
                        </div>
                    </div>
                    <div class="detail-body jieda-body photos">
                        <p>{{item.replyMsg}}</p>
                    </div>
                    <div class="jieda-reply">
                            <span type="reply">
                                <i class="iconfont icon-svgmoban53"></i>回复
                            </span>
                    </div>
                    <ul style="padding-left: 50px;">
                        <li data-id="111" class="jieda-daan">
                            <div class="detail-about detail-about-reply">
                                <a class="fly-avatar" href="#">
                                    <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="">
                                </a>
                                <div class="fly-detail-user">
                                    <a href="#" class="fly-link">
                                        <cite>superadmin</cite>
                                        <i class="layui-badge fly-badge-vip">楼主</i>
                                    </a>
                                </div>
                                <div class="detail-hits">
                                    <span>2017-11-30</span>
                                </div>
                            </div>
                            <div class="detail-body jieda-body photos">
                                <p>香菇那个蓝瘦，这是一条被采纳的回帖</p>
                            </div>
                            <div class="jieda-reply">
                                    <span type="reply">
                                <i class="iconfont icon-svgmoban53"></i>回复
                            </span>
                            </div>
                        </li>

                        <li data-id="111" class="jieda-daan">
                            <div class="detail-about detail-about-reply">
                                <a class="fly-avatar" href="#">
                                    <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="">
                                </a>
                                <div class="fly-detail-user">
                                    <a href="#" class="fly-link">
                                        <cite>admin</cite>
                                        <i class="layui-badge fly-badge-vip">楼主</i>
                                    </a>
                                </div>
                                <div class="detail-hits">
                                    <span>2017-11-30</span>
                                </div>
                            </div>
                            <div class="detail-body jieda-body photos">
                                <p>香菇那个蓝瘦，这是一条被采纳的回帖</p>
                            </div>
                            <div class="jieda-reply">
                                    <span type="reply">
                                        <i class="iconfont icon-svgmoban53"></i>回复
                                    </span>
                            </div>
                        </li>
                    </ul>
                </li>
                <!-- 无数据时 -->
                <!-- <li class="fly-none">抢沙发</li> -->
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
                <div class="layui-form layui-form-pane">
                    <form action="${ctx}" method="post">
                        <div class="layui-form-item layui-form-text">
                            <a name="comment"></a>
                            <div class="layui-input-block">
                                <textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <input type="hidden" name="jid" value="123">
                            <button class="layui-btn" lay-filter="*" lay-submit>提交回复</button>
                        </div>
                    </form>
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
