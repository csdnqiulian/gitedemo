<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试成才管理</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${ctxStatic}/plug/ace/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${ctxStatic}/plug/ace/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${ctxStatic}/plug/ace/assets/css/ui.jqgrid.css" />
		<link rel="stylesheet" href="${ctxStatic}/plug/ace/assets/css/ace.min.css" />
		<!-- 自定义 styles -->
		<link rel="stylesheet" href="${ctxStatic}/plug/custom/css/main.css" />
		<!-- basic scripts -->
		<!--[if IE]>
		<script src="${ctxStatic}/plug/ace/assets/js/jquery-1.10.2.min.js"></script>
		<![endif]-->
		<!--[if !IE]> -->
		<script src="${ctxStatic}/plug/ace/assets/js/jquery-2.0.3.min.js"></script>
		<!-- <![endif]-->
		<script src="${ctxStatic}/plug/ace/assets/js/bootstrap.min.js"></script>
		<script src="${ctxStatic}/plug/ace/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="${ctxStatic}/plug/ace/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
		<script src="${ctxStatic}/plug/custom/js/common.js"></script>
</head>
<body>
	<div class="main-container" id="main-container">
		<div class="widget-box">
		<div class="widget-header">
			<h4>数据字典管理</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main">
				<input type="hidden" name="page" id="page" value="">
				<input type="hidden" name="isadd" id="isadd"  value="">
				<input type="hidden" name="type_id" id="type_id"  value="">
				<input type="text" id="type_name" name="type_name" class="nav-search-input" placeholder="类型名称" /> 
				<button type="button" class="btn btn-purple btn-sm" onclick="search()">
					搜索 <i class="icon-search icon-on-right bigger-110"></i>
				</button>
				<button type="button" class="btn btn-sm" onclick="doreset()">
					重置<i class="icon-undo icon-on-right bigger-110"></i>
				</button>
				<button type="button" class="btn btn-primary btn-sm" onclick="addtype()">
					新增<i class="icon-plus-sign icon-on-right bigger-110"></i>
				</button>
				<button type="button" class="btn btn-danger btn-sm" onclick="deletetypes()">
					删除<i class="icon-trash icon-on-right bigger-110"></i>
				</button>
			</div>
		</div>
		<div class="row" style="margin-left: 10px">
			<div class="jqGrid_wrapper">
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function search(page){
			if(page == null){
				page = 1;
			}
			$("#grid-table").jqGrid("setGridParam", {
				url: '${ctx}/jqgrid/list.do?classNameId=testfreemarkerList&ispaging=true',
				search : true,
				datatype:'json',
				postData:{'jsonParam':toJosnParam()},
		        page : page
			}).trigger("reloadGrid"); 
		}
		function toJosnParam(){
			var jsonParam = "{'type_name':''}";
			return jsonParam;
		}
	</script>
	
    <script type="text/javascript">
	jQuery(function($) {
		var jqgridheight = "";
		//如果parent.top.jqgridheight不存在，那么就使用默认值300
		try{
			jqgridheight = parent.top.jqgridheight-330;
		}catch(e){
			jqgridheight = 300;
		}
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";
		jQuery(grid_selector).jqGrid({
			datatype: "local",
			mtype: "POST",
			height: jqgridheight,
			colModel: [
						{label:"名字",name: "name",index: "name",width:3},
						{label:"备注", name: "remark",index: "remark",width:4},
						{label:"操作",name: "",index: "",width:3,sortable:false,width:3}
			           ],
			viewrecords : true,
			rowNum:10,
			rowList:[10,20,30],
			sortname : 'id',
			sortorder : 'asc',
			pager : pager_selector,
			altRows: true,
			//toppager: true,
			prmNames : {
				page:"page",
				rows:"length", 
				sort: "orderName",
				order: "orderType", 
				search:"search"
			},
			multiselect: true,
			//multikey: "ctrlKey",
	        multiboxonly: true,
			loadComplete : function() {//必要执行的图标替换
				var replacement = 
				{
					'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
					'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
					'ui-icon-seek-next' : 'icon-angle-right bigger-140',
					'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
				};
				$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
					var icon = $(this);
					var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
					
					if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
				});
			},
			autowidth: true
		});
		
		// 设置jqgrid的宽度  
        $(window).bind('resize', function () {  
            var width = $('.jqGrid_wrapper').width();  
            jQuery(grid_selector).setGridWidth(width);  
        });  
        
        function oper_timeFormatter(cellvalue, options, rowObject){
  		  return (new Date(parseFloat(cellvalue))).format("yyyy-MM-dd hh:mm:ss");
  	   }
        
       function YHFormatter(cellvalue, options, rowObject){
    	  return "<button type=\"button\" class=\"btn btn-primary btn-xs\" onclick=\"jk('"+rowObject.sales_id+"')\">监控<i class=\"icon-eye-open icon-on-right bigger-110\"></i></button>";
       }
        
	});
	
	function jk(sales_id){
		var url = "${pageContext.request.contextPath}/xf/business/sale/salesmonitordetail.do?sales_id="+sales_id+"&one_building_id=";
		var feature = "fullscreen=yes,channelmode=yes, titlebar=no, toolbar=no, scrollbars=no," +
        "resizable=yes, status=no, copyhistory=no, location=no, menubar=no,width="+(window.screen.availWidth) +
        "height="+(window.screen.availHeight);
		window.open(url,"jiankong",feature);
	}
	
	$(function(){
		
		var page = "";
		if(page == ""){
			page = "1";
		}	
		$("#type_name").val("${p_type_name}"); 
		search(page);
	}); 
	
</script>
</body>
</html>