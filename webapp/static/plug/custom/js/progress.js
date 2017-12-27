/**
 * 简单进度条的js
 */

var timer_count = 0;
var interval = "";
//显示精度条 remindtitle进度条上面的提示语句，如果没有传的话会使用默认的提示
function showprogress(remindtitle){ 
	var o1 = document.getElementById("pop1");   
	var o2 = document.getElementById("pop2");
	if(remindtitle != "" && remindtitle != null && typeof(remindtitle) != "undefined") {
		$("#remindtitle").html(remindtitle);
	}
	o1.style.width = document.documentElement.scrollWidth;   
	o1.style.height = document.documentElement.scrollHeight;  
	o1.style.display = "block";   
	o2.style.display = "block";
	interval = window.setInterval("rollprogress()",100);
}

//隐藏进度条并归零相关进度条属性
function hideprogress(){
	clearInterval(interval);
	$(".progress").attr("data-percent","100%");
	$(".progress-bar").width("100%");
	setTimeout(function(){
		var o1 = document.getElementById("pop1");   
		var o2 = document.getElementById("pop2");  
		o1.style.width = document.documentElement.scrollWidth;   
		o1.style.height = document.documentElement.scrollHeight;  
		o1.style.display = "none";   
		o2.style.display = "none"; 
		timer_count = 0;
		$(".progress").attr("data-percent","0%");
		$(".progress-bar").width("0%");
		$("#remindtitle").html("正在加载中，请等候。。。");
	}, 700);
}

//进度条累加
function rollprogress(){
	timer_count = timer_count + 1;
	var newProgress = 0.5 + 0.007*timer_count;
	if(newProgress<=0.93){
		var progress = Math.round(newProgress*100)+"%";
		$(".progress").attr("data-percent",progress);
		$(".progress-bar").width(progress);
	}
}
