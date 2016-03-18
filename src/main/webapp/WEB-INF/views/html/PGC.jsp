<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>PGC活动详情页面</title>
    <link rel="stylesheet" type="text/css" href="../css/PGC.css">
    <style >
        .one {
            background-image: url(${head});
            background-size: cover;
            height: 640px;
            width: 100%;
        }
        .pic_1{
            height: 420px;
            background-image: url(${pic_3});
            background-size: cover;
            margin: 2% 0;
            opacity: 0.7;
        }
    </style>
</head>

<body>
<div class="one"></div>

<div class="two">
    作者：<span id="author">${author}</span>, 来源：<span id="from">${resource_1}</span>
</div>

<div class="detail"><img src="../resource/img/pgc/yin1.png"><br/>&nbsp;${article}<br/>
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
    <img src="${pic_4}">
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
    <img src="${pic_5}">
    <h3>(图片来源：${resource_3}</h3>
</div>
<div class="detail">${title_4}</div>

<h5>由weego重新排版&nbsp;<a>阅读原文</a></h5>
<div class="logo"><img src="${logo}"></div>
<p class="weego">weego公众账号</p>

<footer>weego基于旅行者的兴趣和意图依托强大的数据库的智能计算，帮助用户在简单旅行准备的同时，成为一个旅行中的实时向导。</footer>

</body>
</html>
