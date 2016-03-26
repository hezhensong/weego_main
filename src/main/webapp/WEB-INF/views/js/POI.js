var _detail_time = $("#time").html();
var _detail_phone = $("#phone").html();
var _detail_price = $("#price").html();
var _detail_ticket = $("#ticket").html();
var _tag = $(".type").html();
var _comment_from = $("#comment_from")
var _comments_rating =$(".comments_rating").html();
alert(_comments_rating);



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

if(_comments_rating == "2.5分"){
	$(".rating_pic").attr("src","../resource/img/poi/review2_5.png")；
}
