<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
	<meta charset="UTF-8">
	<title>新城市活动</title>
	<script type="text/javascript" src="../resource/jquery-1.3.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../css/newactivity.css">
	<style type="text/css">
		.one {
			background: url(${bg});
			height: 1060px;
		}
	</style>
</head>
<body>
	<div class="one"></div>

	<div class="two">
		<p class="title">【&nbsp;${title}&nbsp;】</p>
		<div class="detail" id="detail_time">
			<img src="../resource/img/activity/details_time@3x.png">
			<div class="text">
    			<p>时间：</p>
    			<p id="actime">${time}</p>
			</div>
		</div>
		<div class="detail" id="detail_ip">
			<img src="../resource/img/activity/details_ip@3x.png">
			<div class="text">
    			<p>地点：</p>
    			<a id="ip">${ip}</a>
			</div>
		</div>
		<div class="detail" id="detail_web">
			<img src="../resource/img/activity/details_web@3x.png">
			<div class="text">
    			<p>官网：</p>
    			<a id="web" href="blank">${web}</a>
			</div>
		</div>
		<div class="detail" id="detail_ticket">
			<img src="../resource/img/activity/details_ticket@3x.png">
			<div class="text">
    			<p>订票：</p>
    			<a id="ticket" href="blank">${ticket}</a>
			</div>
		</div>
	</div>

	<div class="three">
		<div class="aty">活动详情</div>
		<div class="drb">${details}</div>
	</div>

	<div class="four">
		<h5 id="text_title">${text_title}</h5>
		<div class="drb inner" id="describe">${describe}</div>
		<img id="pic" src="${pic}">
		<div class="small" id="pic_describe">${pic_describe}</div>
	</div>
	<footer></footer>
</body>
<script type="text/javascript" src="../js/newactivity.js"></script>
</html>