var myScroll;
	function loaded() {
		myScroll = new iScroll('wrapper', { scrollbarClass: 'myScrollbar' });
	}

	document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);

	document.addEventListener('DOMContentLoaded', loaded, false);


/*$.ajax({
    url: 'http://192.168.8.102:8080/api/v3/city/test1',
    dataType: 'JSONP',
    type: 'GET',
    success: function (data) {
        console.log(data);
        alert("aaaa");
    }
});*/



/*$(function(){
	get_cover_img();
})


function get_cover_img(){
	list_url='http://192.168.8.102:8080/api/v3/city/test1';
	$.get(list_url,{},function(rsp){
		lister(rsp);
	},'jsonp');
}

function lister(rsp){
	console.log(rsp);
}    
    */