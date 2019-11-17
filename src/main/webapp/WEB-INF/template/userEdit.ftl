<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>fire-app</title>
    <script src="/static/js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="/static/js/layer/layer.js"></script>

    <script type="text/javascript">

        function edit1(id, email, tepPhone) {

            var id = $("#userId").val();
            var telPhone = $("#telPhone").val();
            var email = $("#email").val();
            var passWord = $("#passWord").val();
            var url = "userUpdate.do";
            $.ajax({
                url: url,
                type: "POST",
                data: {
                    id: id,
                    telPhone: telPhone,
                    email: email,
                    passWord: passWord
                },
                beforeSend: function () {
                    loadingIndex = layer.load(2, {time: 10 * 1000});
                },
                success: function (result) {
                    layer.close(loadingIndex);
                    if (result.success) {
                        alert("修改成功");
                        window.location.href = "userListPage.do";
                    } else {
                        layer.msg("用户不存在，请重新操作", {time: 1000, icon: 5, shift: 6});
                    }
                }

            });
        }

        function checkForm() {
            var id = $("#userId").val();
            var telPhone = $("#telPhone").val();
            var email = $("#email").val();
            var passWord = $("#passWord").val();
            if (!id) {
                alert("id不能为空!");
                return false;
            }

            if (!telPhone) {
                alert("telPhone不能为空!");
                return false;
            }
            return true;
        }

        function edit1(id, email, tepPhone) {

            var id = $("#userId").val();
            var telPhone = $("#telPhone").val();
            var email = $("#email").val();
            var passWord = $("#passWord").val();
            var url = "userUpdate.do";
            //参数校验
            //提交表单
            $("#updateUser_from").submit();
        }
    </script>
</head>
<body>


<div class="login_banner">


    <div>
        <div>
            <form id="updateUser_from" method="post" action="userUpdate.do" omsubmit='return checkForm()'>
                <input type="hidden" name="id" id="userId" value="${(user.id)!}"/>
                <label>用户名称：</label>
                <input type="text" name="userName" id="username" value="${(user.userName)!}"/>
                <br/>
                <br/>
                <label>电话：</label>
                <input type="text" name="telPhone" id="telPhone" value="${(user.telPhone)!}"/>
                <br/>
                <label>邮箱：</label>
                <input type="text" name="email" id="email" value="${(user.email)!}"/>
                <label>单号：</label>
                <input type="text" name="passWord" id="passWord"/>
                <br/>
            <#--<input type="button" value="修改" id="get_btn" onclick="edit()"/>-->
                <input type="submit" value="修改"/>
            </form>

        </div>
    </div>
</div>

</body>
</html>