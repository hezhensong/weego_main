<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta charset="UTF-8">
<meta name="apple-touch-fullscreen" content="YES" />
<title>新城市活动</title>
<script type="text/javascript" src="../resource/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/activity.css">
<style type="text/css">
.one {
	background: url(${bg});
	background-size: cover;
	height: 37%;
}
</style>
</head>
<body>
	<div class="one">
		<div class="tag">${tag}</div>
	</div>
	<div class="two">
		<p class="title">【&nbsp;${title}&nbsp;】</p>
		<div class="detail" id="detail_time">
			<img src="../resource/img/activity/details_time@3x.png">
			<p>时间：</p>
			<p class="control" id="actime">${time}</p>
		</div>
		<div class="detail" id="detail_ip">
			<img src="../resource/img/activity/details_ip@3x.png">
			<p>地点：</p>
			<p class="control" id="ip">${ip}</p>
		</div>
		<div class="detail" id="detail_web">
			<img src="../resource/img/activity/details_web@3x.png">
			<p>官网：</p>
			<a class="control" id="web" href="${web}">${web}</a>
		</div>
		<div class="detail" id="detail_ticket">
			<img src="../resource/img/activity/details_ticket@3x.png">
			<p>订票：</p>
			<a class="control" id="ticket" href="${ticket}">${ticket}</a>
		</div>
	</div>

	<div class="three">
		<div class="aty">活动详情</div>
		<div class="drb">${details}</div>
	</div>
	<c:forEach items="${paragraphs}" var="ActivityParagraphs">
		<div class="four">
			<c:choose>
				<c:when
					test="{ActivityParagraphs.imageTitle =='' || ActivityParagraphs.imageTitle == 'null'}">
					<div style="display: none" id="text_title">${ActivityParagraphs.imageTitle}</div>
				</c:when>
				<c:otherwise>
					<div id="text_title">${ActivityParagraphs.imageTitle}</div>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when
					test="{ActivityParagraphs.detailUp =='' || ActivityParagraphs.detailUp == 'null'}">
					<div style="display: none" id="drb inner" class="describe">${ActivityParagraphs.detailUp}${ActivityParagraphs.detailDown}</div>
				</c:when>
				<c:otherwise>
					<div id="drb inner" class="describe">${ActivityParagraphs.detailUp}${ActivityParagraphs.detailDown}</div>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when
					test="{ActivityParagraphs.imageUrl == '' || ActivityParagraphs.imageUrl == null || ActivityParagraphs.imageUrl == 'null'}">
					<img style="display: none" id="pic"
						src="${ActivityParagraphs.imageUrl}">
				</c:when>
				<c:otherwise>
					<img id="pic" src="${ActivityParagraphs.imageUrl}">
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when
					test="{ActivityParagraphs.imageBrief =='' || ActivityParagraphs.imageBrief == 'null'}">
					<div style="display: none" class="small" id="pic_describe">${ActivityParagraphs.imageBrief}</div>
				</c:when>
				<c:otherwise>
					<div class="small" id="pic_describe">${ActivityParagraphs.imageBrief}</div>
				</c:otherwise>
			</c:choose>

		</div>
	</c:forEach>
	<footer></footer>
</body>
<script type="text/javascript" src="../js/activity.js"></script>
</html>