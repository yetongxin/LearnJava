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
                <div class="col-md-12 column">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </tr>
                        </thead>
                        <tbody>
                    <#list productInfoPage.content as product>
                    <tr>
                        <td>${product.productId}</td>
                        <td>${product.productName}</td>
                        <td><a href="${product.productIcon}"></a></td>
                        <td>${product.productPrice}</td>
                        <td>${product.productStock}</td>
                        <td>${product.productDescription}</td>
                        <td>${product.categoryType}</td>
                        <td>${product.createTime}</td>
                        <td>${product.updateTime}</td>
                        <td>
                            <#if product.getProductStatusEnums(product.productStatus).getMsg()=="下架" >
                                <a href="/sell/seller/product/upload?productId=${product.productId}">上架</a>
                                <#else>
                                <a href="/sell/seller/product/download?productId=${product.productId}">下架</a>
                            </#if>
                        </td>
                        <td><a href="/sell/seller/product/add?productId=${product.productId}">修改</a></td>
                    </tr>
                    </#list>
                        </tbody>
                    </table>
                    <ul class="pagination pull-right">
                    <#if (page==1)>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="list?page=${page-1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..productInfoPage.getTotalPages() as pagenum>
                        <#if (pagenum==page)>
                            <li class="disabled"><a href="#">${pagenum}</a></li>
                        <#else>
                            <li><a href="list?page=${pagenum}&size=${size}">${pagenum}</a></li>
                        </#if>
                    </#list>
                    <#if (page==productInfoPage.getTotalPages())>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="list?page=${page+1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

