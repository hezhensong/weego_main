var _detail_time = $("#time").html();
var _detail_phone = $("#phone").html();
var _detail_price = $("#price").html();
var _detail_ticket = $("#ticket").html();
var _tag = $(".type").html();
var _comment_from = $("#comment_from").html();
var _comments_rating =$(".comments_rating").html();
var _breif_desc = $("#breif_desc").html();
var _little_tip = $("#little_tip").html();
var _reviews =$(".reviews").html();

if(_detail_time == ""){
	$("#detail_time").hide();
}

if(_detail_phone == ""){
	$("#detail_phone").hide();
}

if(_detail_price == ""){
	$("#detail_price").hide();
}

if(_detail_ticket == ""){
	$("#detail_ticket").hide();
}

if(_tag == ""){
	$(".type").hide();
}

if(_comment_from == "来自google"){
	$("#comment_pic").attr("src","../resource/img/poi/google.png");
}else{
	$("#comment_pic").attr("src","../resource/img/poi/tripadvisor.png");
}

if(_reviews == "2.5分"){
	$(".reviews_pic").attr("src","../resource/img/poi/reviews2_5.png");
}else if(_reviews == "3分"){
	$(".reviews_pic").attr("src","../resource/img/poi/reviews3.png");
}else if(_reviews == "3.5分"){
	$(".reviews_pic").attr("src","../resource/img/poi/reviews3_5.png");
}else if(_reviews == "4分"){
	$(".reviews_pic").attr("src","../resource/img/poi/reviews4.png");
}else if(_reviews == "4.5分"){
	$(".reviews_pic").attr("src","../resource/img/poi/reviews4_5.png");
}else{
	$(".reviews_pic").attr("src","../resource/img/poi/reviews5.png");
}


if(_comments_rating == "2.5分"){
	$(".rating_pic").attr("src","../resource/img/poi/reviews2_5.png");
}else if(_comments_rating == "3分"){
	$(".rating_pic").attr("src","../resource/img/poi/reviews3.png");
}else if(_comments_rating == "3.5分"){
	$(".rating_pic").attr("src","../resource/img/poi/reviews3_5.png");
}else if(_comments_rating == "4分"){
	$(".rating_pic").attr("src","../resource/img/poi/reviews4.png");
}else if(_comments_rating == "4.5分"){
	$(".rating_pic").attr("src","../resource/img/poi/reviews4_5.png");
}else{
	$(".rating_pic").attr("src","../resource/img/poi/reviews5.png");
}


if(_breif_desc == ""){
	$("#breif").hide();
}

if(_little_tip == ""){
	$("#tip").hide();
}
