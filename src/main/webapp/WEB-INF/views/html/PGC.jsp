<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>PGC活动详情页面</title>
    <style type="text/css">
        body {
            background-color: #2e3a43;
            color: #fff;
        }

        body, ul, li p div h3 {
            padding: 0;
            margin: 0;
            border: 0;
        }

        .extra{
        margin-top: -204px;
        }

        .one {
            background-image: url(../resource/img/pgc/${head});
            background-size: cover;
            height: 640px;
            width: 100%;
        }

        .two {
            margin: 6% 3%;
            text-align: center;
            font: 30px HelveticaNeue-Light;
        }

        .detail {
            margin: 0 3%;
            font-size: 30px;
            line-height: 50px;
        }

        .detail img {
            margin-right: 55px;
        }

        .special {
            font: 50px bold;
        }

        .float-r {
            float: right;
        }

        .float-l {
            float: left;
        }

        .title {
            margin: 4% 0 2% 0;
            font: 40px HelveticaNeue-Bold;
            text-align: center;
        }

        .title .small {
            height: 38px;
            width: 8px;
            background-color: #ff4f4e;
            display: inline-block;
        }

        .pic_1{
            height: 420px;
            background-image: url(../resource/img/pgc/${pic_3});
            background-size: cover;
            margin: 2% 0;
            opacity: 0.7;
        }

        .pic_2 .center{
            margin:0 auto;
            height: 172px;
            width: 422px;
            border-bottom: 1px solid #fff;
            position: relative;
            top: -110px;
        }
        .pic_2 .center .line{
            border-bottom: 1px solid #fff;
            width: 150px;
            display: inline-block;
        }
        .pic_2 .center .icon{
            position: relative;
            top:15px;
            display: inline-block;
            margin: 0 32px;
            height: 30px;
            width: 42px;
            background-image:url(../resource/img/pgc_detail/icon.png);
            background-size: cover; 
        }
        .pic_2 .center h2{
            font-size: 46px;
            font-weight: bold;
            text-align: center;
            color: #fff;
            z-index: 200;
        }
        .pic_2{
            margin-bottom: 5%;
        }
        .pic_2 .type{
            padding-top: 22px;
            font-size: 26px;
            text-align: center;
            position: relative;
            top: -377px;
            background-image:url(../resource/img/pgc_detail/lable.png); 
            background-size: cover; 
            height: 60px;
            width: 300px;
        }
        .pic_2 img{
            width: 100%;
            height: 420px;
        }
        .pic_2 h3{
            margin:2% 0 6% 0;
            font-size:24px ;
            text-align: center;
            opacity: 0.5;
        }

        .logo {
            margin: 3% auto;
            height: 100px;
        }

        .logo img {
            position: relative;
            margin: 0 auto;
            width: 100px;
            height: 100px;
            left: 42%;
        }

        h5 {
            margin: 5% 3%;
            font-size: 30px;
            opacity: 0.5
        }

        h5 a {
            color: #ff4f4e;
        }

        .weego {
            font-size: 36px;
            font-weight: bold;
            text-align: center;
        }
        footer {
            margin: 0 3% 6% 3%;
            text-align: center;
            font-size: 30px;
            line-height: 60px;

        }
    </style>
</head>

<body>
<div class="one"></div>

<div class="two">
    作者：<span id="author">${author}</span>, 来源：<span id="from">${resource_1}</span>
</div>

<div class="detail"><img src="../resource/img/pgc/yin1.png"></br>&nbsp;${article}</br>
    <img class="float-r" src="../resource/img/pgc/yin2.png">
</div>

<h3 class="title">
    <div class="small"></div>
    ${title_1}
</h3>

<div class="detail">
    ${article_1}
</div>

<div class="pic_1">
</div>

<div class="pic_2 extra">
    <div class="center">
        <div class="line"></div>
        <div class="icon"></div>
        <div class="line"></div>
            <h2>马尔代夫天堂岛</h2>
    </div>
    <div class="type">度假海岛</div>
    <img src="../resource/img/pgc/${pic_4}">
    <h3>(图片来源：${resource_2})</h3>
</div>

<h3 class="title">
    <div class="small"></div>
    ${title_2}
</h3>

<div class="detail">
    ${article_2}
</div>

<div class="pic_2">
    <img src="../resource/img/pgc/${pic_5}">
    <h3>(图片来源：${resource_3)</h3>
</div>
<div class="detail">${title_1}</div>

<h5>由weego重新排版&nbsp;<a>阅读原文</a></h5>
<div class="logo"><img src="../resource/img/pgc/${pic_4}"></div>
<p class="weego">weego公众账号</p>

<footer>weego基于旅行者的兴趣和意图依托强大的数据库的智能计算，帮助用户在简单旅行准备的同时，成为一个旅行中的实时向导。</footer>

</body>
</html>
