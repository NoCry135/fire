<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>fire-app</title>
    <script src="/static/js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="/static/js/layer/layer.js"></script>

    <script type="text/javascript">
        function get() {
            var userName = $("#username").val();
            var telPhone = $("#telPhone").val();
            var email = $("#email").val();
            // var url = "userList.do";
            var url = "userListPage.do";
            $.ajax({
                url: url,
                type: "POST",
                data: {
                    userName: userName,
                    telPhone: telPhone,
                    email: email
                },
                beforeSend: function () {
                    loadingIndex = layer.load(2, {time: 10 * 1000});
                },
                success: function (result) {
                    layer.close(loadingIndex);
                    $("#userList").html(result);
                    window.onload;
                }

            });

        }

        function spu_img_click(count) {
            //为图片绑定点击事件
            $("#spu_img_file_" + count).click();

        }

        function spu_img_replace(count) {
            // 获得图片对象的缓存
            var b_img = $("#spu_img_file_" + count)[0].files[0];
            // 用图片对象替换按钮
            $("#spu_img_" + count).attr("src", window.URL.createObjectURL(b_img));

            var length = $("#spu_img_box input").length;
            if (length == 5 || length >= 5) {
                $("#spu_img_count_span").html("<span style = 'color:red;'>图片数量达到上限</span>");
            } else if (length == count) {
                spu_add_img(count + 1);
                $("#spu_img_count").html(5 - count);
            }
        }

        function spu_add_img(count) {
            $("#spu_img_box").append('<img id = "spu_img_' + count + '" src="/static/image/upload_hover.png" style = "width:150px; high:150px; float:left; cursor:pointer;" onclick = "spu_img_click(' + count + ')"/>');
            $("#spu_img_box").append('<input style= "display:none;" id = "spu_img_file_' + count + '" type="file" name="files" onchange ="spu_img_replace(' + count + ')" />');
        }

        function spu_submit_btn() {
            var shp_mch_input = $("#shp_mch_input").val();
            var shp_msh_input = $("#shp_msh_input").val();
            if (shp_mch_input == "") {
                alert("商品名称不能为空");
                layer.msg("商品名称不能为空", {time: 1000, icon: 5, shift: 6});
                return;
            }
            if (shp_msh_input == "") {
                alert("商品描述不能为空");
                layer.msg("商品描述不能为空", {time: 1000, icon: 5, shift: 6});
                return;
            }
            $("form").submit();

        }
    </script>
</head>
<body>


<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎</span>
    </div>

    <div id="content">
        <div class="login_form">
            <h1>2222</h1>
        ${(user.userName)!"username"}
        </div>
        <div>
            <div class="form">
                <form method="post">
                    <label>用户名称：</label>
                    <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                           name="userName" id="username"/>
                    <br/>
                    <br/>
                    <label>电话：</label>
                    <input class="itxt" type="text" placeholder="请输入电话" autocomplete="off" tabindex="1"
                           name="telPhone" id="telPhone"/>
                    <br/>
                    <label>邮箱：</label>
                    <input class="itxt" type="text" placeholder="请输入邮箱" autocomplete="off" tabindex="1"
                           name="email" id="email"/>
                    <br/>
                    <input type="button" value="查询" id="get_btn" onclick="get()"/>
                    <input type="button" value="新增" id="add_btn" onclick="add()"/>
                </form>
            </div>

            <div id="userList">

            </div>
        </div>
    </div>
</div>

<div>
    商品发布管理首页<br/>
    <form action="save_spu.do" method="post" enctype="multipart/form-data">

        商品编号<input id="shp_mch_input" type="text" name="goodsNo"/><br>
        商品描述<br><textarea id="shp_msh_input" name="shp_msh" rows="10" cols="20"></textarea><br>
        商品图片<br><span id="spu_img_count_span">你还可以上传<span id="spu_img_count" style="color:red">5</span>张图片</span>
        <button type="button" onclick="spu_submit_btn()">发布商品信息</button>
        <br>
        <div id="spu_img_box">
            <img id="spu_img_1" src="/static/image/upload_hover.png"
                 style="width:150px; high:150px; float:left; cursor:pointer;" onclick="spu_img_click(1)"/>
            <input style="display:none;" id="spu_img_file_1" type="file" name="files" onchange="spu_img_replace(1)"/>
            <br/>

        </div>
    </form>
</div>

<br/>

<hr/>


<div id="bottom">
			<span>
				snal.Copyright &copy;2018
			</span>
</div>
</body>
</html>