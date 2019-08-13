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
                        <th>订单id</th>
                        <th>姓名</th>
                        <th>手机号</th>
                        <th>地址</th>
                        <th>金额</th>
                        <th>订单状态</th>
                        <th>支付状态</th>
                        <th>创建时间</th>
                        <th colspan="2">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderDTOPage.content as order>
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.buyerName}</td>
                        <td>${order.buyerPhone}</td>
                        <td>${order.buyerAddress}</td>
                        <td>${order.orderAmount}</td>
                        <td>${order.getOrderStatusEnums(order.orderStatus).getMsg()}</td>
                        <td>${order.getPayStatusEnums(order.payStatus).getMsg()}</td>
                        <td>${order.createTime}</td>
                        <td><a href="/sell/seller/order/detail?orderId=${order.orderId}">详情</a></td>
                        <td><a></a>
                            <#if order.getOrderStatusEnums(order.orderStatus).getMsg()=="新订单" >
                                <a href="/sell/seller/order/cancel?orderId=${order.orderId}">取消</a>
                            </#if>
                        </td>
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

                    <#list 1..orderDTOPage.getTotalPages() as pagenum>
                        <#if (pagenum==page)>
                            <li class="disabled"><a href="#">${pagenum}</a></li>
                        <#else>
                            <li><a href="list?page=${pagenum}&size=${size}">${pagenum}</a></li>
                        </#if>
                    </#list>
                    <#if (page==orderDTOPage.getTotalPages())>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="list?page=${page+1}&size=${size}">下一页</a></li>
                    </#if>
                </ul>
            </div>
        </div>
            </div>
        </div>
        <#--弹窗-->
        <div class="modal fade" id="modal-container" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myModalLabel">
                            提醒
                        </h4>
                    </div>
                    <div class="modal-body">
                        您有新的订单...
                    </div>
                    <div class="modal-footer">
                        <button onclick="javascript:document.getElementById('music').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button onclick="javascript:location.reload()" type="button" class="btn btn-primary">确认</button>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div width="20px" height="20px">
    <audio id="music" loop="loop" controls="controls" src="/sell/music/song.mp3"></audio>
    </div>
    <script src="https://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/bootstrap.min.js"></script>
    <script>
       var webSocket = null;
       if('WebSocket' in window){
           webSocket = new WebSocket('ws://sellsell.natapp1.cc/sell/webSocket');
       }else {
           console.log("该浏览器不支持websocket");
       }

       webSocket.onopen = function (event) {
           console.log('建立连接');
       }
       webSocket.onclose = function (event) {
           console.log('连接关闭');
       }
       webSocket.onmessage = function (event) {
           console.log(event.data);
           $("#modal-container").modal('show');
            document.getElementById("music").play();
       }
       webSocket.onbeforeunload = function () {
           webSocket.close();
       }
       webSocket.onerror = function () {
           console.log("websocket通信出现错误");
       }
    </script>
</body>
</html>

