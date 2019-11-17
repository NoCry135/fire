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

    <div >
        上传excel
        <form action="/excelController/importGoodsDetail.do" method="post" enctype="multipart/form-data">
            <input type="file" name="excelfiles" "/>
            <input type="submit"/>

        </form>
    </div>

</div>


<div id="bottom">
			<span>
				snal.Copyright &copy;2018
			</span>
</div>
</body>
</html>