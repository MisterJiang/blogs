<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <script src="${ctxStatic}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        var i = 1;
        function  addTb() {
            i++;
            var options = $("#select1 option:selected");
            var val = options.val();
            var text = options.text();

            var options2 = $("#select2 option:selected");

            var val2 = options2.val();
            var text2 = options2.text();
            var html = "<tr id='tr_"+ i +"'>\n" +
                "            <td>\n" + i +
                "            </td>\n" +
                "            <td>\n" + text2 +
                "            </td>\n"+
                "         <td>   <input type='button' onclick='del(" + i + ");' value='删除'>" +
                "      </td>  </tr>";

            $("#tb1").append(html);

        }

        function del(index) {
            var tr = document.getElementById('tr_'+index);
            console.log(tr);


            tr.parentNode.removeChild(tr);
        }


        function getValue() {
            var $trs  = $("#tb1").find("tr");
            console.log($trs);
        }

    </script>
    <title>Title</title>
</head>
<body>
    <input type="button" onclick="getValue();" value="按钮">
    <table id="tb" width="500" height="200" border="5">
        <th>表头1</th>
        <th>表头2</th>
        <th>操作</th>
        <tr>
            <td>
                <select id="select1" style="width: 100px;">
                    <option value="a">a</option>
                    <option value="b">b</option>
                    <option value="c">c</option>
                </select>
            </td>
            <td>
                <select id="select2" style="width: 100px;">
                    <option value="123">123</option>
                    <option value="456">456</option>
                    <option value="789">789</option>
                </select>
            </td>
            <td>
                <input type="button" onclick="addTb();" value="添加"></td>

            </td>
        </tr>

    </table>

    <table id="tb1" width="500" height="200" border="5">
        <th>添加的值</th>
        <th>添加的值</th>
        <th>操作</th>

        <tr id="tr_1">
            <td>a</td>
            <td>123</td>
            <td>
                <input type="button" onclick="del(1);" value="删除">

            </td>
        </tr>
    </table>
</body>
</html>
