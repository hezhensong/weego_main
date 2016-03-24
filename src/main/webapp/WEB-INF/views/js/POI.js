var _detail_time = $("#time").html();
var _detail_phone = $("#phone").html();
var _detail_price = $("#price").html();
var _detail_ticket = $("#ticket").html();
var _tag = $(".type").html();




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
