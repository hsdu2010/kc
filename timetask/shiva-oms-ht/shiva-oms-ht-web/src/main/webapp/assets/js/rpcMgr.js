/**
 * 所有其他类型的方法
 * @type {{init: $.rpcMgr.init, queryList: $.rpcMgr.queryList}}
 */
var fileName;
var jarInfo = {}; //k:类名 : 类方法
$.rpcMgr={
		init:function(){
            $.rpcMgr.queryList();
            $.rpcMgr.initValid();
            $.rpcMgr.button.uploadFile();
		},
		queryList:function(){
			var appName = $("#searchForm input[name=appName]").val();
            $('#rpcMgrTable').bootstrapTable({
                url: getContextPath() + "/rpcMgr/searchByAppName",
                queryParams:function(params){
                    return{
                        offset: params.offset,
                        pageSize: params.limit,
                        "appName":'%'+appName+'%'
                    }
                },
                responseHandler: function (response) {
                    return response;
                },
                uniqueId: "id"
            });
        },
        initValid:function(){
            $('#updateUserRpcInfoForm').validate({
                rules:{
                	applicationName : "required",
                	zkUrl : "required",
                    interfaceName : 'required'
                },
                messages:{
                	applicationName : "",
                        zkUrl : "",
                        interfaceName : ""

                },
                errorClass: "text-danger",
                errorElement: "span",
                highlight:function(element, errorClass, validClass) {
                	$(element).parent().addClass('has-error');
                },
                unhighlight: function(element, errorClass, validClass) {
                	$(element).parent().removeClass('has-error');
                }

            });
            $('#addUserRpcInfoForm').validate({
            rules:{
            	applicationName : "required",
            	zkUrl : "required"
            },
            messages:{
            	applicationName : "",
            	zkUrl : ""
            },
            errorClass: "text-danger",
            errorElement: "span",
            highlight:function(element, errorClass, validClass) {
            $(element).parent().addClass('has-error');
            },
            unhighlight: function(element, errorClass, validClass) {
            $(element).parent().removeClass('has-error');
            }

            });
        },
        methodParamTemplate: function(type){
        	return  '<div class="input-group col-md-12 col-xs-12 col-lg-12 col-sm-12" style="margin-bottom:10px;">'+
            			'<div class="input-group-addon" style="width:100px;">'+type+':</div>'+
            			'<input type="text" class="form-control argValue">'+
            		'</div>';
        },
        queryJarInfo: function(jarName){
            $.ajax({
                url: getContextPath() + "/rpcMgr/queryInterfaceInfo",
                data: {
                	jarName : jarName
                },
                success: function (response) {
                	console.info(response);
                	if(response.success){
                		var optionsHtml = "";
                		var rows = response.obj;
                		jarInfo = rows;
                		for(var i = 0; i < rows.length; i++){
                			optionsHtml += "<option value='" + i + "'>" + rows[i].className + "</option>";
            			}
                		if(rows.length > 0){
                			$("#interfaceName").empty().append(optionsHtml);
                			$.rpcMgr.button.changeInterName();
                		}
                	}
                }
            });
        }
};

/**
 * 所有非后台交互的按钮产生的动作方法
 * @type {{deleteById: $.rpcMgr.button.deleteById, updateById: $.rpcMgr.button.updateById, deleteBatch: $.rpcMgr.button.deleteBatch}}
 */
$.rpcMgr.button={
    add:function(){
    	$("#showFileName").text("");
    	$('#addUserRpcInfoForm').validate().resetForm();
    	$('#addUserRpcInfoForm div').removeClass('has-error');
    	$("#addUserRpcInfoForm")[0].reset(); //表单数据重置
    	$("#addUserRpcInfoDialog").modal('show');
    },
    deleteByPrimaryKey:function(
            id
    ){
        $("#idForDel").val(id);
        $('#deleteUserRpcInfoDialog').modal('show');
    },
    updateById:function(
            id
    ){
        $.ajax({
            url: getContextPath() + "/rpcMgr/selectByPrimaryKey",
            data: {
                        id:id
                    },
            success: function (response) {

                    var data= $.utils.objToJson(response);

                    $('#updateUserRpcInfoForm div').removeClass('has-error');

                    $('#updateUserRpcInfoForm').validate().resetForm();

                    $('#updateUserRpcInfoForm')[0].reset();

                    $('#updateUserRpcInfoForm').fill(JSON.parse(data));

                    $('#updateUserRpcInfoDialog').modal('show');
                //}
            }
        });
    },
    operationBatch:function () {
        //获取选中的所有数据
        var list = $('#rpcMgrTable').bootstrapTable('getAllSelections');

        if(list == null || list.length == 0){
            $.utils.alertWarning("请选择至少一个数据!");
            return;
        }

        $("#batchUserRpcInfoDialog").modal("show");

    },
	uploadFile:function(){
		$(".a-upload").on("change","input[type='file']",function(){
		    var filePath=$(this).val();
		    if(filePath.indexOf(".jar")!=-1){
		        $(".fileerrorTip").html("").hide();
		        fileName=$.utils.jarpath(filePath);
		        $("#uploadjar-error").remove()
		        $(".showFileName").html("文件名:"+ fileName);
		        $("#isUploaded").val("1");
		    }else{
		        $(".showFileName").html("");
		        $("#isUploaded").val("0");
		        $(".fileerrorTip").html("请上传后缀名为.jar的文件").show();
		        return false 
		    }
		    
		   
		});
		 $("#methodList").change(function(){
		    var selectText = $("#methodList option:checked").text();
 			var params = selectText.match(/\w+/g);
 			params = params.slice(1);
 			var divs = "";
 			$.each(params, function(k, v){
 				divs += $.rpcMgr.methodParamTemplate(v);
 			});
 			$("#methodParams").empty().append(divs);
		    });
	},
	selectByPrimaryKey : function(id){
		var data = $('#rpcMgrTable').bootstrapTable('getRowByUniqueId', id);
        $('#updateUserRpcInfoForm div').removeClass('has-error');
        $('#updateUserRpcInfoForm').validate().resetForm();
        $('#updateUserRpcInfoForm')[0].reset();
        $('#updateUserRpcInfoForm').fill(data);
        //请求jar信息
        $.rpcMgr.queryJarInfo(data.jarName);
		$("#updateUserRpcInfoDialog").modal('show');
	},
	changeInterName: function(){
		var classIndex = $("#interfaceName").val();
		var obj = jarInfo[classIndex];
		if(obj){
			var optionsHtml = "";
    		var rows = obj.methods;
    		for(var i = 0; i < rows.length; i++){
    			optionsHtml += "<option value='" + i + "'>" + rows[i] + "</option>";
			}
    		if(rows.length > 0){
    			var method0 = rows[0];
    			var params = method0.match(/\w+/g);
    			params = params.slice(1);
    			var divs = "";
    			$.each(params, function(k, v){
    				divs += $.rpcMgr.methodParamTemplate(v);
    			});
    			$("#methodParams").empty().append(divs);
    		}
    		$("#methodList").empty().append(optionsHtml);
		}
	}
};

/**
 * 所有直接通过操作与后台进行交互的方法
 * @type {{addUserRpcInfo: $.rpcMgr.submit.addUserRpcInfo, updateABC: $.rpcMgr.submit.updateUserRpcInfo, deleteById: $.rpcMgr.submit.deleteById, search: $.rpcMgr.submit.search, batch: $.rpcMgr.submit.batch}}
 */
var strSplitObj = function(str){
	var param = str.split("&");
	var request = {};
	$.each(param, function(k, item){
		var items = item.split("=");
		request[items[0]] = decodeURIComponent(items[1]);
	})
	return request;
}
$.rpcMgr.submit={
    addUserRpcInfo:function(){
		if($("#addUserRpcInfoForm").valid()){
			var isUploaded = $("#isUploaded").val();
			if(isUploaded == "0"){
				$(".fileerrorTip").html("请上传后缀名为.jar的文件").show();
				return ;
			}
			$(".fileerrorTip").html("").hide();
			var request = strSplitObj($("#addUserRpcInfoForm").serialize());
			console.info(request);
			request["jarName"] = fileName;
			$.ajaxFileUpload({
				url : getContextPath() + '/rpcMgr/insert',
	            data : request,
	            type : 'POST',
	            secureuri : false, // 一般设置为false
	            fileElementId : 'uploadjar',
	            dataType : 'json',
	            success : function(response) {
	            	if (response.success == true) {
	            		$("#rpcMgrTable").bootstrapTable('refresh');
	            		$("#addUserRpcInfoDialog").modal('hide');
	            	}else{
	            		$.utils.alertWarning(response.errorMessage)
	            	}						
	            },
	            error : function() {
	            	$.utils.alertWarning("添加信息出错");
	            }
	         });
		}
    },
    updateUserRpcInfo:function(){
        if($('#updateUserRpcInfoForm').valid()){
            $.utils.submit('/rpcMgr/updateByPrimaryKeySelective', '#updateUserRpcInfoForm', '#updateUserRpcInfoDialog', '修改出错~', null, '#rpcMgrTable', null, '修改成功~');
        }
    },
    deleteByPrimaryKey:function(id) {
        $.utils.submit2('/rpcMgr/deleteByPrimaryKey', null, null, '删除出错~', {id:id}, '#rpcMgrTable', null, '删除成功~');
    },
    search:function(){
    	$("#rpcMgrTable").bootstrapTable('removeAll');
    	let appName = $("#searchForm input[name=appName]").val();
    	$("#rpcMgrTable").bootstrapTable('refreshOptions',{
    		url:getContextPath() + "/rpcMgr/searchByAppName",
    		queryParams:function(params){
            	return{
                    offset: params.offset,
                    pageSize: params.limit,
                    "appName": '%'+appName+'%'
                }
            },
            responseHandler: function (response) {
            	return response;
            }
    	});
    },
    sender : function(){
    	$.statistic.recordOperation('rpc');
    	if ($('#updateUserRpcInfoForm').valid()) {
    		var parms = [];
    		$.each($(".argValue"), function(k, v){    			
    			parms.push($(this).val());
    		});
    		var request = strSplitObj($("#updateUserRpcInfoForm").serialize());
    		request["interfaceName"] = $("#interfaceName option:checked").text();
    		request["methodParam"] = parms.join("@@");
    		request["zkUrl"] = decodeURIComponent(request["zkUrl"], true)
            $.ajax({
                url: getContextPath() + "/rpcMgr/invokerRpc",
                data: request,
	            type : 'POST',
                success: function (response) {
                	console.info(response);
                	if(response.success){
                		var data = response.obj;
    					if($.utils.isJSON(data)){
    						data = $.utils.jsonFormat(data);
    					}
    					$("#response").val(data);
                	}else{
                		$.utils.alertWarning("接口调用失败");
                	}
                }
            });
    	}
    }
};

/**
 * 所有额外处理格式化的方法
 * @type {{operation: $.rpcMgr.formatter.operation}}
 */
$.rpcMgr.formatter={
    operation:function (value, row) {
        var html =  "<div class='btn-toolbar' role='toolbar'>" +
			        "<button type='button' class='btn-link' onclick=$.rpcMgr.button.selectByPrimaryKey('"
			        +   row.id
			        +
			        "')>选择</button>|"+	
                    "<button type='button' class='btn-link' onclick=$.rpcMgr.submit.deleteByPrimaryKey('"
                            +   row.id
                    +
                    "')>删除  </button>"+
                    "</div>";
        return  html;
    }
};

/**
 * 页面渲染结束后自动执行的类
 */
$(document).ready(function() {
   $.rpcMgr.init();
});

