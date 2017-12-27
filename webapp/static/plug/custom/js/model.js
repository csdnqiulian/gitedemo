var iframedata = "";//用于存放iframe的数据
			
//隐藏modal
function modalin(){
	$('#modal-form1').modal('hide');
}

/**
	显示modal
	iframurl：modal中iframe的请求地址
	iframewidth：modal中iframe的宽度
	iframeheight：modal中iframe的高度
	modalcontentwidth：modal中modal-content的宽度
	showclosebutton:是否显示关闭按钮 默认显示
	showtitle：需要显示的表头信息
**/
function modalout(iframurl,iframewidth,iframeheight,modalcontentwidth,showclosebutton,showtitle) {
  $(".modal-content").width(modalcontentwidth);
  $("#iframepage1").width(iframewidth);
  $("#iframepage1").height(iframeheight);
  $('#iframepage1').attr("src",iframurl);
  $('#modal-form1').modal({
	    show:true,
	    backdrop:'static',
	    keyboard:false
  });
  $('#modal-dialog1').css("display","table");
  if(!showclosebutton) {
	  $("#modalclosebutton").css("display","none");
  }
  if(typeof(showtitle) != "undefined" && showtitle != "") {
	  $("#modaltitle").html(showtitle);
  }
}

//用于显示渐渐消失的提示信息  tipmessage：提示信息
function showtip(tipmessage){
	var tip = "保存成功";
	if(typeof(tipmessage) != "undefined" && tipmessage != "") {
		tip = tipmessage;
	}
	$.gritter.add({
		text: tip,
		sticky: false,  
	    time: 1500,  
	    speed:500,  
	    class_name: 'gritter-info gritter-center-autotop'
	});
}