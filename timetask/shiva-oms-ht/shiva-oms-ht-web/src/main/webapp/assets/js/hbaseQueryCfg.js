$.hbaseQueryCfgUtils={
	init : function(){
		$.hbaseQueryCfgUtils.queryList();
		$.hbaseQueryCfgUtils.initValid();
		$.hbaseQueryCfgUtils.queryTypeChange();
		$.hbaseQueryCfgUtils.initEvent();
	},
	queryList : function(){
		let moduleName = $('#queryModuleName').val();
		moduleName = moduleName == '' ? '' : ('%' + moduleName + '%');
		$('#hbaseQueryCfgTable').bootstrapTable({
			url : getContextPath() + "/hbaseQuery/selectCfgs",
			queryParams : function(params){
				return {
					offset : params.offset,
					pageSize : params.limit,
					'moduleName' : moduleName
				}
			},
			responseHandler : function(response){
				return response;
			},
			uniqueId:"id"
		});
	},
	initValid : function(){
		$("#addHbaseQueryCfgForm").validate({
			rules:{
				moduleName : "required",
				tableName : "required",
				queryType : "required"
			},
			messages : {
				moduleName : "",
				tableName : "",
				queryType : ""
			},
			errorClass : "text-danger",
			errorElement : "span",
			 highlight : function(element, errorClass, validClass) {
				$(element).parent().addClass('has-error');
			},
			unhighlight : function(element, errorClass, validClass) {
				$(element).parent().removeClass('has-error');
			}
		});
		$("#updateHbaseQueryCfgForm").validate({
			rules:{
				moduleName : "required",
				tableName : "required",
				queryType : "required"
			},
			messages : {
				moduleName : "",
				tableName : "",
				queryType : ""
			},
			errorClass : "text-danger",
			errorElement : "span",
			 highlight : function(element, errorClass, validClass) {
				$(element).parent().addClass('has-error');
			},
			unhighlight : function(element, errorClass, validClass) {
				$(element).parent().removeClass('has-error');
			}
		});
	},
	queryTypeChange : function(){
		$("#addHbaseQueryCfgForm select[name=queryType]").change(function(){
			if($(this).val() == '1'){
				$("#addColumnFamilyDiv").addClass("hidden");
				$("#addColumnNameDiv").addClass("hidden");
			}else{
				$("#addColumnFamilyDiv").removeClass("hidden");
				$("#addColumnNameDiv").removeClass("hidden");
			}
		});
		$("#updateHbaseQueryCfgForm select[name=queryType]").change(function(){
			if($(this).val() == '1'){
				$("#updateColumnFamilyDiv").addClass("hidden");
				$("#updateColumnNameDiv").addClass("hidden");
			}else{
				$("#updateColumnFamilyDiv").removeClass("hidden");
				$("#updateColumnNameDiv").removeClass("hidden");
			}
		});
		$("#addFuzzySearchBox").change(function(){
			if($(this).prop('checked')){
				$("#addHbaseQueryCfgForm input[name=isFuzzySearch]").val(1);
			}else{
				$("#addHbaseQueryCfgForm input[name=isFuzzySearch]").val(0);
			}
		});
		$("#updateFuzzySearchBox").change(function(){
			if($(this).prop('checked')){
				$("#updateHbaseQueryCfgForm input[name=isFuzzySearch]").val(1);
			}else{
				$("#updateHbaseQueryCfgForm input[name=isFuzzySearch]").val(0);
			}
		});
	},
	initEvent : function(){
    	$("#updateHbaseQueryCfgForm #queryResult").on("dblclick", "span", function(){
    		var formatText = $.utils.jsonFormatSrc($(this).text());
    		if(formatText){
        		$("#responseContent").val(formatText);
        		$('#displayResponseDialog').modal('show');
    		}
    	});
    	$("#addHbaseQueryCfgForm #queryResult").on("dblclick", "span", function(){
    		var formatText = $.utils.jsonFormatSrc($(this).text());
    		if(formatText){
        		$("#responseContent").val(formatText);
        		$('#displayResponseDialog').modal('show');
    		}
    	});
    }
};
$.hbaseQueryCfgUtils.button={
	add : function(){
		$("#addHbaseQueryCfgForm div").removeClass('has-error');
		$("#addHbaseQueryCfgForm").validate().resetForm();
		$("#addHbaseQueryCfgForm")[0].reset();
		$("#addHbaseQueryCfgForm #queryResult").html('');
		$("#addHbaseQueryCfgDialog").modal('show');
	},
	updateById : function(id){
		$.ajax({
			url : getContextPath() + '/hbaseQuery/selectById',
			data : {
				id : id
			},
			type : 'get',
			dataType : 'json',
			success : function(resp){
				if(resp.success){
					let obj = resp.obj;
					$("#updateHbaseQueryCfgForm div").removeClass('has-error');
					$("#updateHbaseQueryCfgForm").validate().resetForm();
					$("#updateHbaseQueryCfgForm")[0].reset();
					$("#updateHbaseQueryCfgForm #queryResult").html('');
					$("#updateHbaseQueryCfgForm").fill(obj);
					$("#updateHbaseQueryCfgForm input[name=isFuzzySearch]").val(0);
					if(obj != null){
						$("#updateHbaseQueryCfgForm select[name=queryType]").val(obj.queryType);
						if(obj.queryType == '2'){
							$("#updateColumnFamilyDiv").removeClass("hidden");
							$("#updateColumnNameDiv").removeClass("hidden");
						}else{
							$("#updateColumnFamilyDiv").addClass("hidden");
							$("#updateColumnNameDiv").addClass("hidden");
						}
					}
					$("#updateHbaseQueryCfgDialog").modal('show');
				}else{
					$.utils.alertError("获取配置信息失败");
				}
			},
			error : function(){
				$.utils.alertError("网络异常，请稍后重试");
			}
		})
	},
	deleteByPrimaryKey : function(id){
		$("#idForDel").val(id);
        $('#deleteHbaseQueryCfgDialog').modal('show');
	}
};
$.hbaseQueryCfgUtils.submit={
	addHbaseQueryCfg : function(){
		if($("#addHbaseQueryCfgForm").valid()){
			$.utils.submit('/hbaseQuery/saveCfg', '#addHbaseQueryCfgForm', '#addHbaseQueryCfgDialog', '新增出错~', null, '#hbaseQueryCfgTable', null, '新增成功');
		}
	},
	updateById : function(){
		if($("#updateHbaseQueryCfgForm").valid()){
			$.utils.submit('/hbaseQuery/updateCfg', '#updateHbaseQueryCfgForm', '#updateHbaseQueryCfgDialog', '修改出错~', null, '#hbaseQueryCfgTable', null, '修改成功');
		}
	},
	deleteByPrimaryKey : function(){
		$.utils.submit('/hbaseQuery/deleteCfg', '#deleteHbaseQueryCfgForm', '#deleteHbaseQueryCfgDialog', '删除出错~', null, '#hbaseQueryCfgTable', null, '删除成功');
	},
	search : function(){
		let moduleName = $('#queryModuleName').val();
		moduleName = (moduleName == '' ? '' : ('%' + moduleName + '%'));
		$('#hbaseQueryCfgTable').bootstrapTable('refreshOptions',{
			url : getContextPath() + "/hbaseQuery/selectCfgs",
			queryParams : function(params){
				return {
					offset : params.offset,
					pageSize : params.limit,
					'moduleName' : moduleName
				}
			},
			responseHandler : function(response){
				return response;
			},
			uniqueId:"id"
		});
	},
	queryHbase : function(formId){
		$.statistic.recordOperation('queryHbase');
		if($(formId).valid()){
			var tableName = $(formId).find("input[name=tableName]").val();
			var queryType = $(formId).find("select[name=queryType]").val();
			var queryParam = $(formId).find("input[name=queryParam]").val();
			var limitNum = $(formId).find("input[name=limitNum]").val();
			var filterFamily = $(formId).find("input[name=filterFamily]").val();
			var filterColumn = $(formId).find("input[name=filterColumn]").val();
			var isFuzzySearch = $(formId).find("input[name=isFuzzySearch]").val();
			if(queryType == "2"){//列查询
				if(!(filterFamily && filterColumn && queryParam)){
					$.utils.alertWarning("根据列查询请输入列族、列名和筛选条件");
					return;
				}
			}
			var component = $(formId).find("#query-result");
			$.ajax({
				type : "POST",
				url : getContextPath() + "/hbaseQuery/queryHbase",
				dataType : "json",
				data : {
					"tableName" : tableName,
					"queryType" : queryType,
					"queryParam" : queryParam,
					"limitNum" : limitNum,
					"filterFamily" : filterFamily,
					"filterColumn" : filterColumn,
					"isFuzzySearch" : isFuzzySearch
				},
				beforeSend:function(){
	            	$.utils.loading(component).show();
	            },
	            complete : function(){
	            	$.utils.loading(component).hide();
	            },
				success : function(resp){
					$.utils.loading(component).hide();
					if(resp.success){
						var articleContent = "";
						for(var record in resp.obj){
							var content = JSON.stringify(resp.obj[record]);
							if(content){
								articleContent += "<span style='white-space:nowrap;'>" + content.replace(/</g, "&lt;").replace(/>/g, "&gt;") + "</span><br/>";
							}
							$(formId).find(".query-result").html(articleContent);
						}
						$(formId).find(".result-num").html(resp.obj.length);
						$(formId).find(".result-info").removeClass("hide");
					}else{
						$.utils.alertWarning("查询失败，请检查查询参数或稍后重试");
					}
				},
				error : function(){
					$.utils.loading(component).hide();
					$.utils.alertError("网络异常，请稍后重试");
				}
			});
		}
	}
}
$.hbaseQueryCfgUtils.formatter={
	operation : function(value, row){
		var html =  "<div class='btn-toolbar' role='toolbar'>" +
        			"<button type='button' class='btn-link' onclick='$.hbaseQueryCfgUtils.button.updateById(\""
                	+  row.id +
                	"\")'>选择</button>|"+
                	"<button type='button' class='btn-link' onclick='$.hbaseQueryCfgUtils.button.deleteByPrimaryKey(\""
                	+  row.id +
                	"\")'>删除</button>"+
                	"</div>";
		return html;
	}
};
$(document).ready(function() {
	$.hbaseQueryCfgUtils.init();
});