$.quickQuery = {
	init : function(){
		$.quickQuery.loadQueryCfg(0, 10);
	},
	bindPanelClick : function(){
		$("#queryGroups a").each(function(){
			if($(this).attr("data-toggle") == "collapse"){
				var iconObj = $(this).children("i");
				$(this).click(function(){
					//原先属于折叠状态
					if(iconObj.hasClass("fa-angle-left")){
						$.quickQuery.closeAllOpenPanel();
						iconObj.removeClass("fa-angle-left").addClass("fa-angle-down");
						$(this).parent("div").next().show(200);
					}else{//原先展开状态
						iconObj.removeClass("fa-angle-down").addClass("fa-angle-left");
						$(this).parent("div").next().hide(200);
					}
				})
			}
		})
	},
	//关闭所有展开的面板
	closeAllOpenPanel : function(){
		$("#queryGroups a").each(function(){
			$(this).children("i").removeClass("fa-angle-down").addClass("fa-angle-left");
			$(this).parent("div").next().hide(200);
		})
	},
	//分页加载查询配置
	loadQueryCfg : function(offset, pageSize){
		$("#paginationDiv").hide();
		$("#queryGroups").empty();
		var sqlName = $("#searchForm input[name=sqlName]").val();
		$.ajax({
			url : getContextPath() + "/sqlCfg/searchBySqlName",
			dataType : "json",
			type : "POST",
			data : {
				"pageSize" : pageSize,
				"offset" : offset,
				"sqlName" : '%'+sqlName+'%'
			},
			async : false,
			success : function(data){
				if(!data.rows || data.rows.length == 0){
					$("#queryGroups").append('<h4 class="text-center">暂无可用接口</h4>');
					return;
				}
				for(var i = 0; i < data.rows.length; i++){
					$.quickQuery.buildQueryPanel(data.rows[i]);
				}
				$("#totalRecords").html(data.totalRecord);
				$.quickQuery.showPaginationUI(offset, pageSize, data.totalRecord);
			},
			error : function(){
				$("#queryGroups").append("<h4 class=\"text-center\">拉取查询接口失败，请稍后重试</h4>");
			}
		});
		$.quickQuery.bindPanelClick();
	},
	buildQueryPanel : function(row){
		var obj = $('<div style="margin-top:8px" class="panel panel-default"></div>');
		obj.append('<div class="panel-heading"></div>');
		obj.children("div").append('<a data-toggle="collapse" href="javascript:void(0)">'
				+ '<span class="pull-left">'+ row.sqlName + '</span><i class="fa fa-angle-left pull-right"></i>'
				+ '<div style="clear:both"></div></a>');
		obj.append('<div class="panel-body" style="display:none"></div>');
		obj.children(".panel-body").append('<form role="form" class="form-inline"><input type="text" class="form-control" name="params">'
				+ ' <button type="button" class="btn btn-primary" onclick="$.quickQuery.quickQueryFunc(\'' + row.sqlId +'\', \'' + row.description + '\',this)">查询</button></form><hr>'
				+ '<div class="result-div" style="overflow-x:auto"></div>');
		obj.find("input[name='params']").attr("placeholder", row.paramDesc ? row.paramDesc : "请输入查询参数");
		$("#queryGroups").append(obj);
	},
	showPaginationUI : function(offset, pageSize, totalRecord){
		$("ul.dropdown-menu").find("li").each(function(){
			$(this).removeClass("active");
			if($(this).children("a").html() == pageSize){
				$(this).addClass("active");
			}
		});
		$("#pageSize").html(pageSize);
		$("#startNum").html(offset + 1);
		$("#endNum").html((offset + pageSize) > totalRecord ? totalRecord : (offset + pageSize));
		if(pageSize >= totalRecord){
			$("#paginationBarDiv").hide();
		}else{
			$("#paginationBarDiv").show();
		}
		$("li.page-number").remove();
		$.quickQuery.buildPaginationBar(offset, pageSize, totalRecord);
		$("#paginationDiv").show();
	},
	search : function(){
		
	},
	buildPaginationBar : function(offset, pageSize, totalRecord){
		var pageNum = (offset / pageSize) + 1;
		var totalPage = parseInt((totalRecord - 1) / pageSize) + 1;
		var startPage, endPage;
		//分页栏最多显示5个页码
		if(totalPage < 5){
			startPage = 1;
			endPage = totalPage;
		}else{
			var temp = parseInt((pageNum - 1) / 5);
			startPage = temp * 5 + 1;
			endPage = (temp + 1) * 5;
			endPage = endPage < totalPage ? endPage : totalPage;
		}
		for(var i = endPage; i>= startPage; i--){
			var item = $('<li class="page-number"></li>');
			if(i == pageNum){
				item.addClass("active");
			}
			var pageOffset = (i-1) * pageSize;
			item.append('<a href="javascript:void(0)" onclick="$.quickQuery.loadQueryCfg('+pageOffset + ',' + pageSize +')">' + i +'</a>');
			$("li.page-pre").after(item);
		}
	},
	turnPage : function(type){
		var pageSize = $("#pageSize").html() - 0;
		var totalRecord = $("#totalRecords").html() - 0;
		var offset;
		if(type == "first"){
			offset = 0;
		}else if(type == "pre"){
			offset = $("#startNum").html() - pageSize - 1;
		}else if(type == "next"){
			offset = $("#endNum").html() - 0;
		}else{
			offset = totalRecord - totalRecord % pageSize;
		}
		if(offset < 0 || offset >= totalRecord){
			return;
		}
		$.quickQuery.loadQueryCfg(offset, pageSize);
	},
	quickQueryFunc : function(sqlId, description , element){
		var param = $(element).prev().val();
		if(!param){
			$.utils.alertWarning("请输入查询参数");
			return;
		}
		$.statistic.recordOperation('quickQuery');
		$(element).parent().siblings("div.result-div").empty();
		$.ajax({
			url : getContextPath() + "/jdbc/quickQuery",
			type : "post",
			data : {
				"param" : param,
				"sqlId" : sqlId,
			},
			dataType : "json",
			success : function(data){
				$.quickQuery.fillTableByQuickResult(data, description, element);
			},
			error : function(){
				$.utils.alertError("ajax查询出错");
			}
		});
	},
	fillTableByQuickResult : function(data, description, element){
		if(data && data.length != 0){
			var targetDiv = $(element).parent().siblings("div.result-div");
			for(var index = 0; index < data.length; index++){
				targetDiv.append('<p><strong>' + data[index].tableName + '</strong></p>');
				targetDiv.append('<table class="text-center table table-condensed table-bordered table-hover"><thead class="default"><tr style="white-space:nowrap"></tr></thead><tbody></tbody></table>');
				var tableHead = $(targetDiv).find("table:last").find("thead tr");
				var tableBody = $(targetDiv).find("table:last").find("tbody");
				var headers = data[index].tableHeaders;
				for(var i = 0; i < headers.length; i++){
					tableHead.append('<th>' + headers[i] + '</th>');
				}
				var contents = data[index].records;
				if(contents.length == 0){
					tableBody.append('<tr class="text-center"><td colspan=' + headers.length + '><h4 class="text-center text-danger">查询结果为空</h4></td></tr>');
				}
				for(var row = 0; row < contents.length; row++){
					var record = '<tr tr style="white-space:nowrap">';
					for(var column = 0; column < contents[row].length; column++){
						record = record + "<td>" + contents[row][column] + "</td>";
					}
					record += "</tr>";
					tableBody.append(record);
				}
			}
			var columns = targetDiv.find("table:last").find("th").length;
			targetDiv.find("table:last").append('<tfoot><tr><td colspan="' + columns + '"><div class="pull-left">' + description + '</div></td></tr></tfoot>');
		}else{
			$(element).parent().siblings("div.result-div").append('<h4 class="text-center text-danger">查询结果为空</h4>');
		}
	},
};
$(document).ready(function(){
	$.quickQuery.init();
})