<%--
  Created by IntelliJ IDEA.
  User: 张紫捷
  Date: 2019/4/12
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!doctype html>
<html lang="en">
<head>
    <base href="<%=request.getContextPath()%>/">
    <meta charset="UTF-8">
    <title>途牛旅行-跟团游</title>
    <link rel="stylesheet" href="./resources/css/reset.css"/>
    <link rel="stylesheet" href="./resources/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="./resources/css/style.css"/>

    <link rel="stylesheet" href="./resources/css/table.css"/>
    <link rel="stylesheet" href="./resources/css/ordercenter.css"/>


    <script src="./resources/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<!--滚动屏-->
<div class="scroll-head"></div>
<!--顶部-->
<div class="top-wrapper">
    <div class="top-info">
        <div class="top-left">
            <c:if test="${user==null}">
                <div data-toggle="arrowdown" id="arrow1" class="user-name">
                    <a href="user/toLogin">登录</a>
                </div>
                <div data-toggle="arrowdown" id="arrow2" class="msg-info">
                    <i class="fa fa-gray"></i>
                    <a href="#">注册</a>
                </div>
            </c:if>
            <c:if test="${user!=null}">
                <div data-toggle="arrowdown" id="arrow1" class="user-name">
                    <p>登录成功，欢迎：${user.uid} &nbsp; &nbsp;</p>
                </div>
                <div data-toggle="arrowdown" id="arrow2" class="msg-info">
                    <a href="user/loginout">注销</a>
                </div>
            </c:if>
        </div>

        <!--top-right-->
        <div class="top-right">
            <div data-toggle="arrowdown" id="arrow3" class="user-name">
                <a href="#">我的账户</a>

            </div>
            <div data-toggle="arrowdown" id="arrow4" class="user-name">
                <i class="fa fa-shopping-cart fa-orange"></i>
                <a href="user/myOrder?uid=${user.uid}">我的订单</a>

            </div>
        </div>
    </div>


</div>

<!--top-main-->
<div class="top-main">
    <img src="./resources/image/logo.jpg" alt="logo"/>
    <div class="search-wrapper">
        <div class="search-box">
            <ul data-toggle="arrowdown" id="arrow8" class="search-toggle" >
                <li class="drop-down"><a href="#">所有产品</a><span class="down-icon"></span>
                    <ul class="search-toggle-box">
                        <li><a href="#">跟团游</a></li>
                        <li><a href="#">景点</a></li>
                        <li><a href="#">攻略</a></li>
                        <li><a href="#">酒店</a></li>
                    </ul>
                </li>
            </ul>
            <input class="search-in" type="text" placeholder="请输入关键字">
            <input type="button" class="search-but" value="搜索">

        </div>
    </div>

</div>

<div class="right-con" style="height: 100px;">
    <div class="nav" style="border-bottom: 2px solid rgba(0,128,0,0.5);width: 730px;">

        <a href="#">首页</a>
        <a href="user/groupAll">组团游</a>
        <a href="user/tours">跟团游</a>
        <a href="user/essay">攻略</a>
        <a href="user/food">美食</a>
        <a href="user/hotel">酒店</a>
        <a href="user/scene">景点</a>
        <a href="user/message">留言板</a>


    </div>


</div>
<br><br><br><br><br><br><br><br>

<!-- 订单中心 -->
<div class="grzxbj">
    <div class="selfinfo center">
        <div class="lfnav fl">
            <div class="ddzx">订单中心</div>
            <div class="subddzx">
                <ul>
                    <li><a href="" style="color:#ff6700;font-weight:bold;">我的订单</a></li>
                </ul>
            </div>

        </div>
        <c:if test="${routeName}!= null">
        <div class="rtcont fr">
            <div class="ddzxbt">交易订单</div>
            <div class="ddxq">
                <div class="ddspt fl"><img src="" alt=""></div>
                <div class="ddbh fl"><${routeName}></div>
                <div class="ztxx fr">
                    <ul>
                        <li>预定成功</li>
                        <li>总价：${routePrice}</li>
                        <li>预定人：${routeOrderName}</li>
                        <li><a href="user/routesDetails?routeName=${routeName}&routeId=${routeId}">查看详情></a></li>
                        <div class="clear"></div>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>

        </div>
        </c:if>
        <div class="rtcont fr">
            <div class="ddzxbt">预定酒店订单</div>
            <div class="ddxq">
                <div class="ddspt fl"><img src="../resources/image/bbbb.jpg" alt=""></div>
                <div class="ddbh fl"><${hotelName}>预定了${orderPeople}人${orderDay}天</div>
                <div class="ztxx fr">
                    <ul>
                        <li>预定成功</li>
                        <li>总价：${hotelPrice}</li>
                        <li>预定人：${orderName}</li>
                        <li><a href="user/toHotelDetails?hotelId=${hotelId}">查看详情></a></li>
                        <div class="clear"></div>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>

        </div>
        <div class="clear"></div>
    </div>
</div>






<!--footer-->
<div class="footer">
    <div class="footer-right">
        <div class="footer-nav" style="border-bottom: 1px solid rgba(0,125,0,0.5);">

        </div>
        <div class="about-tao">
            <a href="#">关于途牛</a>
            <a href="#">联系我们</a>
            <a href="#">法律声明</a>
            <span class="gary-text">&copy; 2019tuniu.com 版权所有</span>
        </div>
    </div>
</div>

<!--回到顶部和底部-->

<div class="backtoTop" id="backToTop1">
    <div id="backToTop-up" class="up-back"><i class="fa fa-angle-up"></i></div>
    <div id="backToTop-down" class="down-back"><i class="fa fa-angle-down"></i></div>
</div>


<script src="./resources/js/jquery_1.9.js"></script>
<script src="./resources/js/main.js"></script>
<script src="./resources/js/img-show.js"></script>




</body>
</html>