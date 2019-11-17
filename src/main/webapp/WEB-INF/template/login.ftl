<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>fire-app</title>
    <script src="/static/js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="/static/js/layer/layer.js"></script>
    <script src="/static/js/bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        function login() {
            //获取用户输入的用户名和密码
            var userName = $("#username").val();
            var password = $("#password").val();
            var routeKey = $("#routeKey").val();
            if (userName == "") {
                layer.msg("登录账号不能为空，请输入", {time: 1000, icon: 5, shift: 6}, function () {
                    userName.focus();
                });
                return;
            }
            if (password == "") {
                layer.msg("登录密码不能为空，请输入", {time: 1000, icon: 5, shift: 6}, function () {
                    password.focus();
                });
                return;
            }
            var url = "indexController.do?method=loginfire";

            var loadingIndex = -1;
            var config = {
                url: url,
                type: "POST",
                data: {
                    userName: userName,
                    passWord: password,
                    routeKey: routeKey
                },
                beforeSend: function () {
                    loadingIndex = layer.load(2, {time: 10 * 1000});
                },
                //dataType:"json",
                success: function (result) {
                    layer.close(loadingIndex);
                    if (result.success) {
                        window.location.href = "indexController.do?method=index";
                    } else {
                        if (result.msg) {
                            layer.msg(result.msg, {time: 1000, icon: 5, shift: 6});
                        } else {
                            layer.msg("用户不存在，请重新操作", {time: 1000, icon: 5, shift: 6});
                        }
                    }
                }
            };
            $.ajax(config);
        }
    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>会员</h1>
                    <a href="template/user/regist.ftl">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg"></span>
                </div>
                <div class="form">
                    <form action="indexController.do?method=loginSystem" method="post">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="userName" id="username"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="passWord" id="password"/>
                        <br/>
                        <br/>
                        <select name="routeKey" class="form-control" id="routeKey">
                            <option value="1">会员</option>
                            <option value="2">用户</option>
                        </select>
                        <input type="submit" value="form登录" id="sub_btn"/>
                        <input type="button" value="js登录" id="sub_btn" onclick="login()"/>
                    </form>
                </div>

            </div>
        </div>
    </div>

    <#--<href src = ""></href>-->
    <a href = "indexController/goodsExcel.do" >测试上传excel</a>

</div>
<div id="bottom">
			<span>
				snal.Copyright &copy;2018
			</span>
</div>
</body>
</html>