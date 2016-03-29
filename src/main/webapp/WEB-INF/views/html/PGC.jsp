<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta charset="UTF-8">
<title>PGC活动</title>
<link rel="stylesheet" type="text/css" href="../css/PGC.css">
<script type="text/javascript" src="../resource/jquery-1.3.2.min.js"></script>
<style type="text/css">
header {
	background-image: url(${cover_img});
	background-size: cover;
	height: 41%;
	width: 100%;
}

.page .poi {
	height: 420px;
	background-image: url(${poi_bg});
	background-size: cover;
	margin: 30px 0;
}
</style>
</head>
<body>
	<header>
		<h1>${title}</h1>
	</header>

	<c:choose>
		<c:when test="${person =='' || person == 'null'}">
			<div style="display: none" class="author">
				<div class="head">
					<img id="author_pic" src="${person.headImage}">
				</div>
				<h1 id="author_name">${person.userName}</h1>
				<p>${person.jobDesc}</p>
				<div class="line"></div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="author">
				<div class="head">
					<img id="author_pic" src="${person.headImage}">
				</div>
				<h1 id="author_name">${person.userName}</h1>
				<p>${person.jobDesc}</p>
				<div class="line"></div>
			</div>
		</c:otherwise>
	</c:choose>

	<div class="from">
		作者：<span id="author">${original.author}</span>,来源：<span id="from">${original.source}</span>
	</div>

	<div class="drb">
		<img class="float-l" src="../resource/img/pgc/yin1.png"><br />
		<span style="font-size: 20px;">&nbsp;&nbsp;&nbsp;</span>${original.desc}</br>
		<img class="float-r" src="../resource/img/pgc/yin2.png">
	</div>
	<c:forEach items="${poilist}" var="PgcPoi">
		<div class="page">

			<c:choose>
				<c:when
					test="${PgcPoi.paragraph.title =='' || PgcPoi.paragraph.title == 'null'}">
					<div style="display: none" class="page_title">
						<div class="small"></div>
						<h2 id="text_title">${PgcPoi.paragraph.title}</h2>
					</div>
				</c:when>
				<c:otherwise>
					<div class="page_title">
						<div class="small"></div>
						<h2 id="text_title">${PgcPoi.paragraph.title}</h2>
					</div>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when
					test="${PgcPoi.paragraph.desc =='' || PgcPoi.paragraph.desc == 'null'}">
					<div style="display: none" id="text" class="drb">${PgcPoi.paragraph.desc}</div>
				</c:when>
				<c:otherwise>
					<div id="text" class="drb">${PgcPoi.paragraph.desc}</div>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when
					test="${PgcPoi.poi.title =='' || PgcPoi.poi.title == 'null'}">
					<div style="display: none" class="poi" id="poi">
						<img class="overlay" id="poi_pic"
							src="../resource/img/pgc/overlay.png">
						<div class="type" id="poi_tag">${PgcPoi.poi.tag}</div>
						<div class="center">
							<div class="line"></div>
							<img class="icon" alt="1" src="">
							<div class="line"></div>
							<h2 id="poi_title">${PgcPoi.poi.title}</h2>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="poi" id="poi">
						<a
							href="http://192.168.8.104:8080/share/v1/poi?poiId=${PgcPoi.poi.id}&type=${PgcPoi.poi.type}">
							<img class="overlay" id="poi_pic"
							src="../resource/img/pgc/overlay.png">
						</a>
						<div class="type" id="poi_tag">${PgcPoi.poi.tag}</div>
					<!--  	<c:when
							test="${PgcPoi.poi.tag =='' || PgcPoi.poi.tag == ' '}">
							<div style="display: none" class="type" id="poi_tag">${PgcPoi.poi.tag}</div>
						</c:when>
						<c:otherwise>
							<div class="type" id="poi_tag">${PgcPoi.poi.tag}</div>
						</c:otherwise>-->
						<div class="center">
							<div class="line"></div>
							<img class="icon" alt="${PgcPoi.poi.type}" src="">
							<div class="line"></div>
							<h2 id="poi_title">${PgcPoi.poi.title}</h2>
						</div>
					</div>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when
					test="${PgcPoi.image.url =='' || PgcPoi.image.url == 'null'}">
					<div style="display: none" class="page_pic">
						<img id="pic" src="${PgcPoi.image.url}">
						<h3 class="resource">
							(图片来源：<span id="pic_resource">PgcPoi.image.source</span>)
						</h3>
					</div>
				</c:when>
				<c:otherwise>
					<div class="page_pic">
						<img id="pic" src="${PgcPoi.image.url}">
						<h3 class="resource">
							(图片来源：<span id="pic_resource">PgcPoi.image.source</span>)
						</h3>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</c:forEach>

	<footer>
		<h5>
			由weego重新排版&nbsp;<a>阅读原文</a>
		</h5>
		<div class="logo">
			<img src="${original.image}">
		</div>
		<p class="accounts">${original.source}</p>
		<div class="accounts_breif">${original.desc}</div>
	</footer>
</body>
<script type="text/javascript" src="../js/PGC.js"></script>


</html>