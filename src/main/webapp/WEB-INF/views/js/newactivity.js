var _actime = $("#actime").html();
var _acip = $("#ip").html();
var _acweb = $("#web").html();
var _acticket = $("#ticket").html();
var _text_title = $("text_title").html();
var _describe = $("#describe").html();
var _pic_describe = $("#pic_describe").html();
var _pic = $("#pic").attr("src");


	if(_actime == "" || _actime == "null"){
		$("#detail_time").hide();
	}

	if(_acip == "" || _acip == "null"){
		$("#detail_ip").hide();
	}

	if(_acweb == "" || _acweb == "null"){
		$("#detail_web").hide();
	}

	if(_acticket == "" || _acticket == "null"){
		$("#detail_ticket").hide();
	}

	 if(_text_title =="" || _text_title =="null")
	 	$("#text_title").hide;

	 if(_describe == "" || _describe == "null"){
		$("#describe").hide();
	}

	if(_pic_describe == "" || _pic_describe == "null"){
		$("#pic_describe").hide();
	}

	if(_pic == ""){
		$("#pic").hide();
	}