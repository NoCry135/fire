<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>fire-app</title>
    <script src="/static/js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="/static/js/layer/layer.js"></script>

    <script type="text/javascript">

    </script>
</head>
<body>


<div class="login_banner">


    <div>
        <div>
            <table border="1" cellpadding="5" cellspacing="0">
                <tr>
                    <th>id</th>
                    <th>userName</th>
                    <th>email</th>
                    <th>telPhone</th>
                    <th>createUser</th>
                    <th>updateUser</th>
                    <th>createTime</th>
                    <th>updateTime</th>
                    <th>EDIT</th>
                    <th>DELETE</th>
                </tr>

                <#list users as user>
                <#--<#list users?sort_by("id") as user>-->
                <tr>
                    <td>${(user.id)!}</td>
                    <td>${(user.userName)!}</td>
                    <td>${(user.email)!}</td>
                    <td>${(user.telPhone)!}</td>
                    <td>${(user.createUser)!}</td>
                    <td>${(user.updateUser)!}</td>
                    <td>${(user.createTime?string('yyyy-MM-dd hh:mm:ss'))!}</td>
                    <td>${(user.updateTime?string('yyyy-MM-dd hh:mm:ss'))!}</td>
                    <td><a href="userEditPage.do?id=${(user.id)!}&email=${(user.email)!}&telPhone=${(user.telPhone)!}">Edit</a>
                    </td>
                <#--<td><a href="javascript()"-->
                <#--onclick="edit(${(user.id)!},${(user.email)!},${(user.telPhone)!})">Edit</a></td>-->
                    <td><a class="delBtn" href="delete.do?id=${(user.id)!}">Delete</a>
                    </td>
                </tr>

                </#list>


            </table>
        </div>
    </div>
</div>

</body>
</html>