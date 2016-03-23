var _author_pic = $("#author_pic").attr("src");
var _author = $("#author").html();
var _text_title = $("#text_title").html();
var _text = $("#text").html();
var _poi_title = $("#poi_title").html();
var _poi_tag = $("#poi_tag").html();
var _pic = $("#pic").attr("src");
var _pic_resource = $("#pic_resource").html();
var _public = $(".public").html();
//alert(_public);



if(_author_pic == ""){
	$(".author").hide();
}

if(_author == ""){
	$(".from").hide();
}

if(_text_title == ""){
	$(".page_title").hide();
}

if(_text == ""){
	$("#text").hide();
}

if(_poi_title == ""){
	$("#poi").hide();
}

if(_poi_tag == ""){
	$("#poi_tag").hide();
}

if(_pic == ""){
	$(".page_pic").hide();
}

if(_pic_resource == ""){
	$(".resource").hide();
}

if(_public == ""){
	$("footer").hide();
}
