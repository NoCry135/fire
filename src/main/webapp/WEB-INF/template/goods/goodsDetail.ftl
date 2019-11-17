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

    <div id="l_content">
        <span class="login_word">欢迎</span>
    </div>

    <div id="content">
        <div class="login_form">
            <h1>2222</h1>
        ${(user.userName)!"username"}
        </div>
        <div>
            商品:<h3>   ${(item.goodsNo)!"goodsNo"}   ${(item.title)!"title"} </h3>
            <#list itemImages as iamge>
              名称：${iamge.imageName}

            <img src="/${iamge.url}" style="width:150px; high:150px; float:left; cursor:pointer;"/>

            </#list>

        </div>
    </div>
</div>


<div id="bottom">
			<span>
				snal.Copyright &copy;2018
			</span>
</div>
</body>
</html>