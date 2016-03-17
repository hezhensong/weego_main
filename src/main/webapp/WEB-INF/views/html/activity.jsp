<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>城市活动详情</title>
    <script type="application/javascript" src="../resource/iscroll.js"></script>
    <script type="text/javascript" src="../resource/jquery-1.3.2.min.js">
    </script>
    <script type="text/javascript" src="../js/main.js">
    </script>

    <style type="text/css">
        body, ul, li p {
            padding: 0;
            margin: 0;
            border: 0;
        }

        body {
            font-size: 12px;
            -webkit-user-select: none; /*无法选择文本*/
            -webkit-text-size-adjust: none;
            font-family: helvetica;
            background-color: #282828;
        }

        #wrapper {
            z-index: 1;
            top: 0px;
            bottom: 0px;
            left: 0;
            position: absolute;
            width: 100%;
            overflow: auto;
        }

        #scroller {
            position: relative;
            width: 100%;
        }

        ul {
            position: relative;
            width: 100%;
        }

        li {
            font-size: 20px;
            color: #fff;
        }

        .one {
            height: 1060px;
        }

        .two {
            margin: 5% 30px;
        }

        .two .title {
            font-size: 60px;
            font-weight: bold;
            color: #fff;
            text-align: center;
            margin-bottom: 4%;
        }

        .two .detail {
            height: 150px;
            font: 36px helvetica-light;
        }

        .two .detail img {
            float: left;
            margin-top: 56px;
        }

        .two .detail .text {
            margin-left: 80px;
            border-bottom: 1px #8a8a8a solid;
            height: 150px;
            line-height: 150px;
        }

        .two .detail .text p {
            display: inline-block;
        }

        .two .detail .text a {
            color: #ff4f4e
        }

        .three {
            margin: 4% 30px 3% 30px;
            text-align: center;
        }

        .three .aty {
            background: url(../resource/img/activity/details_activity_line@3x.png) no-repeat center;
            font-size: 60px;
            margin-bottom: 4%;
        }

        .drb {
            font-size: 42px;
            line-height: 60px;
            text-align: left;
        }

        h5 {
            margin: 4% 0 2% 30px;
            font-size: 60px
        }

        .four .inner {
            margin: 0 30px;
        }

        .four img {
            margin: 2% 20px 20px 2%;
            height: 360px;
            width: 96%;
        }

        .four .small {
            color: #ffffff;
            opacity: 0.5;
            text-align: center;
            font-size: 32px;
        }

        .five {
            margin: 30px 30px 60px 30px;
        }

        .five .prize {
            margin: 50px 0 34px 0;
        }

        .logo {
            margin: 3% auto;
            height: 100px;
        }

    </style>
</head>

<body>
<div id="wrapper">

    <div id="scroller">
        <ul id="thelist">
            <li class="one">背景图1</li>
            <li class="two">
                <p class="title">【&nbsp; 俄罗斯国家顶级芭蕾舞团来华演出&nbsp;】</p>
                <div class="detail">
                    <img src="../resource/img/activity/details_time@3x.png">
                    <div class="text">
                        <p>时间：</p>
                        <p id="time">1999年3月</p>
                    </div>
                </div>

                <div class="detail">
                    <img src="../resource/img/activity/details_ip@3x.png">
                    <div class="text">
                        <p>地点：</p>
                        <a id="ip">北京市</a>
                    </div>
                </div>

                <div class="detail">
                    <img src="../resource/img/activity/details_web@3x.png">
                    <div class="text">
                        <p>官网：</p>
                        <a id="web" href="blank">www.baidu.com</a>
                    </div>
                </div>

                <div class="detail">
                    <img src="../resource/img/activity/details_ticket@3x.png">
                    <div class="text">
                        <p>订票：</p>
                        <a id="ticket" href="blank">www.baidu.com</a>

                    </div>
                </div>
            </li>

            <li class="three">
                <div class="aty">活动详情</div>
                <div class="drb">
                    剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科
                </div>
            </li>

            <li class="four">
                <h5>团长简介</h5>
                <div class="drb inner">
                    剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科
                </div>
                <img src="">
                <div class="small">(剧院团长：阿宝刻录机呼呼)</div>
            </li>

            <li class="five">
                <div class="drb">
                    剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科
                </div>
                <h5>获奖情况</h5>
                <div class="drb">
                    剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科剧团全称俄罗斯莫斯科
                </div>
                <div class="logo">dddd</div>
            </li>
        </ul>
    </div>
</div>

</body>
</html>