<html>
<head>
    <meta charset="UTF-8">
    <title>添加商品</title>
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
                    <form role="form" method="post" action="/sell/seller/product/addsubmit">

                        <div class="form-group">
                            <label for="productName">商品名称</label>
                            <input type="text" class="form-control" id="productName" name="productName" value="${(product.productName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="productPrice">商品价格</label>
                            <input type="text" class="form-control" id="productPrice" name="productPrice" value="${(product.productPrice)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="productStock">商品库存</label>
                            <input type="text" class="form-control" id="productStock" name="productStock" value="${(product.productStock)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="productDescription">商品描述</label>
                            <input type="text" class="form-control" id="productDescription" name="productDescription" value="${(product.productDescription)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="productIcon">商品图片链接</label>
                            <input type="text" class="form-control" id="productIcon" name="productIcon" value="${(product.productIcon)!""}"/>
                        </div>
                        <div class="form-group">
                            <label for="categoryType">商品类别</label>
                            <select class="form-control" id="categoryType" name="categoryType">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                        <#if (product)?? && (product.categoryType==category.categoryType)>
                                            selected
                                        </#if>
                                    >${category.categoryName}</option>
                                </#list>
                            </select>
                        </div>
                        <input type="hidden" value="${(product.productId)!""}"><!--添加一个隐藏域传递id -->
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>