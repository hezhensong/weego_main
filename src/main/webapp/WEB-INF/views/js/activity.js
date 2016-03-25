var _actime = $("#actime").html();
var _acip = $("#ip").html();
var _acweb = $("#web").html();
var _acticket = $("#ticket").html();
var _text_title = $(".text_title").html();
var _describe = $(".describe").html();
var _pic_describe = $(".pic_describe").html();
var _pic = $(".pic").attr("src");
var _tag = $(".tag").html();



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
	 	$(".text_title").hide;

	 if(_describe == "" || _describe == "null"){
		$(".describe").hide();
	}

	if(_pic_describe == "" || _pic_describe == "null"){
		$(".pic_describe").hide();
	}

<<<<<<< HEAD
	if(_pic == "" || _pic == "null"){
		$(".pic").hide();
=======
<<<<<<< HEAD
	if(_pic == "" || _pic == "null"){
=======
	if(_pic == "" || _pic == "null" || _pic == "undefined"){
>>>>>>> 7377ef9c63467f8d9e785db8fdfb2f636c81f256
		$("#pic").hide();
>>>>>>> 10ad2068e42a83eada4fc1c1e86e5341695add96
	}

	if(_tag == "" || _tag == "null" ){
		$(".tag").hide();
	}