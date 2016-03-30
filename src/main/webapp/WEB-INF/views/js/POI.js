var _tag = $(".type").html();
var _comment_from = $("#comment_from").html();
var _comments_rating =$(".comments_rating").html();
var _reviews =$(".reviews").html();

$(document).ready(function(){
	$("#single").addClass("first")
})

if(_tag == ""){
	$(".type").hide();
}

if(_comment_from == "来自于google"){
	$("#comment_pic").attr("src","../resource/img/poi/google.png");
}else{
	$("#comment_pic").attr("src","../resource/img/poi/tripadvisor.png");
}

if(_reviews == "2.5分"){
	$(".reviews_pic").attr("src","../resource/img/poi/reviews2_5.png");
}else if(_reviews == "3.0分"){
	$(".reviews_pic").attr("src","../resource/img/poi/reviews3.png");
}else if(_reviews == "3.5分"){
	$(".reviews_pic").attr("src","../resource/img/poi/reviews3_5.png");
}else if(_reviews == "4.0分"){
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

