<html>
<meta charset="UTF-8">
<title>错误页面</title>
<link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="alert alert-dismissable alert-danger">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <h4>
                        错误!
                    </h4> <strong>订单出现问题:${msg}</strong><br />
                    三秒后将自动返回<a href="${url}" class="alert-link">点击这里立即返回</a>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    setTimeout(function () {
        location.href = "${url}";
    },3000);
</script>
</html>