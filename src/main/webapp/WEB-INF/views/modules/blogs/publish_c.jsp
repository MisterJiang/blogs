<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
        <title>文章发布</title>
        <meta name="decorator" content="default"/>
    <!-- Bootstrap -->
   <link rel="stylesheet" href="${ctxStatic}/js/CFEditor/bootstrap/css/bootstrap-2014-10-23-e2373d4a.min.css">
    <link href="${ctxStatic}/js/CFEditor/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- bootstrap -->
    <script src="${ctxStatic}/js/CFEditor/bootstrap/js/bootstrap-2014-10-23-140084e9.min.js"></script>

    <script src="${ctxStatic}/js/CFEditor/js/global.js"></script>

    <script src="${ctxStatic}/js/CFEditor/wysihtml5/main.js"></script>
    <script src="${ctxStatic}/js/CFEditor/wysihtml5/classfoo-editor.js"></script>

        <style>
            .transit {
                -webkit-transition: all 0.4s ease-in-out;
                -o-transition: all 0.4s ease-in-out;
                transition: all 0.4s ease-in-out
            }

            .cfeditor {
                padding: 10px;
                overflow: auto;
                width: 100%;
                height: 350px;
                border: 1px solid #cccccc;
                border-radius: 4px;
                outline: 0
            }

            div.cf-toolbar a.wysihtml5-command-active {
                background-image: none;
                -webkit-box-shadow: inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05);
                -moz-box-shadow: inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05);
                box-shadow: inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05);
                background-color: #E6E6E6;
                background-color: #D9D9D9;
                outline: 0
            }

            .change-label .label {
                font-size: 100%
            }

            .cf-toolbar .tb-bg ul li a,.cf-toolbar .tb-alert ul li a {
                padding: 0px 0px
            }

            .cf-toolbar .tb-bg ul {
                padding: 0px
            }

            .cf-toolbar .tb-bg ul li span {
                display: block;
                padding: 8px 10px;
                width: 100%
            }

            .cf-toolbar .tb-alert ul li p {
                margin: 6px
            }

            .cf-toolbar .showit {
                width: 66px
            }

            .cf-toolbar .result-wrap.showit {
                width: 39px
            }

            .cf-toolbar .hideit {
                width: 0px
            }

            .cf-toolbar .extra-part {
                overflow-x: hidden;
                display: block
            }

            .cf-toolbar .toggle-extra-part {
                padding: 6px;
                background-color: transparent
            }

            .cf-toolbar .extra-part.open {
                overflow-x: visible
            }
            .bg-primary,.bg-success,.bg-info,.bg-warning,.bg-danger {
                padding: 8px 10px
            }
        </style>
    </head>

    <body>
    <div class="fly-panel"></div>
    <div class="layui-container fly-panel fly-marginTop">

        <div class="fly-panel" pad20 style="padding-top: 5px;">




        </div>




    </div>
</body>
</html>