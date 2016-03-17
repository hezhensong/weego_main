<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>PGC活动详情页面</title>
    <style type="text/css">
        body {
            background-image: url(../resource/img/pgc/bg.png);

            background-size: cover;
            color: #fff;
        }

        body, ul, li p div h3 {
            padding: 0;
            margin: 0;
            border: 0;
        }

        .one {
            background-image: url(../resource/img/pgc/1.jpg);
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

        .three {
            background-image: url(../resource/img/pgc/2.jpg);
            background-size: cover;
            margin: 2% 0;
            height: 420px;
        }

        .four {
            background-image: url(../resource/img/pgc/3.jpg);
            background-size: cover;
            height: 420px;
        }

        .picsorse {
            margin: 2% 0 6% 0;
            font-size: 24px;
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
<div class="one">背景1</div>

<div class="two">
    作者：<span id="author">${author}</span>, 来源：<span id="from">weego公众账号</span>
</div>

<div class="detail"><img src="../resource/img/pgc/yin1.png"></br>&nbsp;马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合。</br>
    <img class="float-r" src="../resource/img/pgc/yin2.png">
</div>

<h3 class="title">
    <div class="small"></div>
    为啥马尔代夫捏
</h3>

<div class="detail">
    马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合。
</div>

<div class="three"></div>

<div class="four"></div>

<h4 class="picsorse">(图片来源:aaaaaa)</h4>

<h3 class="title">
    <div class="small"></div>
    憋错过走过路过别错过
</h3>

<div class="detail">
    马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合。
</div>

<div class="four"></div>
<h4 class="picsorse">(图片来源:bbbbb)</h4>
<div class="detail">代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合。</div>

<h5>由weego重新排版&nbsp;<a>阅读原文</a></h5>
<div class="logo"><img src="../resource/img/pgc/4.jpg"></div>
<p class="weego">weego公众账号</p>

<footer>weego基于旅行者的兴趣和意图依托强大的数据库的智能计算，帮助用户在简单旅行准备的同时，成为一个旅行中的实时向导。</footer>

</body>
</html>