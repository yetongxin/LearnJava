<html>
<head>
    <meta charset="UTF-8">
    <title>卖家管理系统</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="/sell/css/style.css">
</head>

<body>
    <div id="wrapper" class="toggled">
        <#include "../common/nav.ftl">
        <div id="page-content-wrapper">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-6 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>订单id</th>
                                <th>订单总金额</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${order.orderId}</td>
                                <td>${order.orderAmount}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-6 column">
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th>商品id</th>
                                <th>商品名称</th>
                                <th>商品价格</th>
                                <th>商品数量</th>
                                <th>总价</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list order.getOrderDetailList() as detail>
                            <tr>
                                <td>${detail.productId}</td>
                                <td>${detail.productName}</td>
                                <td>${detail.productPrice}</td>
                                <td>${detail.productQuantity}</td>
                                <td>${detail.productPrice*detail.productQuantity}</td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>