<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
	<meta charset="UTF-8">
	<title>探索城市</title>
	<link rel="stylesheet" type="text/css" href="../css/POI.css">
</head>
<body>
	<header></header>
	<div class="title">
		<div class="center">
			<div class="line"></div>
			<div class="icon"></div>
			<div class="line"></div>
			<h2>${title}<br/>${english}</h2>
		</div>
		<div class="score">
			<img src="../resource/img/poi/reviews.png">
			<div class="reviews">${reviews}</div>
		</div>
	</div>
	<div class="tips">
		<h2>${describe}</h2>
		<div class="special">
			<div class="single first">${type1}</div>
			<div class="single">${type2}</div>
			<div class="single">${type3}</div>
		</div>
	</div>
	<div class="tips">
		<div class="breif">
			<img src="../resource/img/poi/tips.png">
			<h3>简介</h3>
		</div>
		<p class="margin">${breif}</p>
	</div>
	<div class="tips">
		<div class="breif">
			<img src="../resource/img/poi/tips.png">
			<h3>小贴士</h3>
		</div>
		<div class="margin">
			<p>推荐：${recommend}</p>
			<p>注意事项:</p>
			<p>1.${tip1}</p>
			<p>2.${tip2}</p>
			<p>3.${tip3}</p>
		</div>
	</div>
	<div class="tips">
		<div class="breif">
			<img src="../resource/img/poi/menu.png">
			<h3>菜品推荐</h3>
		</div>
		<div class="menu first_menu">
			<img class="overlay" src="../resource/img/poi/overlay.png">
			<div class="type">${type4}</div>
			<div class="menu_name">${menu_name1}</div>
		</div>
		<p>${menu1}</p>
	</div>
	<div class="tips">
		<div class="menu second_menu">
			<img class="overlay" src="../resource/img/poi/overlay.png">
			<div class="type">${type5}</div>
			<div class="menu_name">${menu_name2}</div>
		</div>
		<p>${menu2}</p>
	</div>
	<div class="tips last_tip">
		<div class="breif">
			<img src="../resource/img/poi/comment.png">
			<h3>评论</h3>
		</div>
		<div class="comment">
			<div class="left">
				<img src="../resource/img/poi/google.png">
				<p>来自google</p>
			</div>
			<div class="right">
				<p>4分</p>
				<img src="../resource/img/poi/reviews.png">
			</div>
		</div>
		<p class="main_comment">${main_comment}</p>
		<div class="good_comment"><img src="../resource/img/pgc/yin1.png"><br/>&nbsp;${good_comment}<br/><img class="float_r" src="../resource/img/pgc/yin2.png"></div>
		<div class="map">map</div>
		<div class="message">
			<div class="details">
				<img src="../resource/img/poi/phone.png">
				<p>价格：</p>
				<h3>${price}</h3>
			</div>
			<div class="details">
				<img src="../resource/img/poi/phone.png">
				<p>电话：</p>
				<h3>${phone}</h3>
			</div>
			<div class="details">
				<img src="../resource/img/poi/web.png">
				<p>网址：</p>
				<h3>${web}</h3>
			</div>
			<div class="details">
				<img src="../resource/img/poi/time.png">
				<p>时间：</p>
				<h3>${time}</h3>
			</div>
			<div class="details" id="last_tip">
				<img src="../resource/img/poi/time.png">
				<p>设施</p>
				<div class="all">
					<div class="little">
						<img class="little" src="../resource/img/poi/1.png">
						<div class="text">提供wifi</div>
					</div>
					<div class="little">
						<img class="little" src="../resource/img/poi/2.png">
						<div class="text">提供wifi</div>
					</div>
					<div class="little">
						<img class="little" src="../resource/img/poi/3.png">
						<div class="text">提供wifi</div>
					</div>
					<div class="little">
						<img class="little" src="../resource/img/poi/4.png">
						<div class="text">提供wifi</div>
					</div>
					<div class="little">
						<img class="little" src="../resource/img/poi/5.png">
						<div class="text">提供wifi</div>
					</div>
					<div class="little">
						<img class="little" src="../resource/img/poi/wifi.png">
						<div class="text">提供wifi</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
	</footer>
</body>
</html>