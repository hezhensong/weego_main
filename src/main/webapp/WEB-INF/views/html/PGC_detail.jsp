<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
	<meta charset="UTF-8">
	<title>PGC活动</title>
	<link rel="stylesheet" type="text/css" href="../css/PGC_detail.css">
</head>
<body>
	<header>
		<h1>${title_1}</h1>
		<div class="head">
			<img src="${pic_author}">
		</div>
	</header>
	<div class="author">
		<h1>${author}</h1>
		<p>${author_drb}</p>
		<div class="line"></div>
	</div>
	<div class="drb"><img src="../resource/img/pgc/yin1.png"><br/>&nbsp;${article_1}<br/><img class="float-r" src="../resource/img/pgc/yin2.png"></div>

	<div class="page_1">
		<h2><div class="small"></div>选择马代没理由</h2>
		<div class="drb">${article_2}</div>
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
		<img src="${pic_1}">
		<h3>(图片来源：${resource_1})</h3>
	</div>
	<div class="page_1">
		<h2><div class="small"></div>${title_2}</h2>
		<div class="drb">${article_3}</div>
	</div>
	<div class="pic_2">
		<img src="${pic_2}">
		<h3>(图片来源：${resource_2})</h3>
	</div>
	<div class="drb">${article_4}</div>
	<footer></footer>

</body>
</html>