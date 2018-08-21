/**
 * 所有其他类型的方法
 * @type {{init: $.redisCfgUtils.init, queryList: $.redisCfgUtils.queryList}}
 */
$.redisCfgUtils={
		init:function(){
            $.redisCfgUtils.queryList();
            $.redisCfgUtils.initValid();
            $.redisCfgUtils.initEvent();
		},
		initEvent : function(){
			$("#queryGroups a").each(function(){
				if($(this).attr("data-toggle") == "collapse"){
					var iconObj = $(this).children("i");
					$(this).click(function(){
						//原先属于折叠状态
						if(iconObj.hasClass("fa-angle-left")){
							$.redisCfgUtils.closeAllOpenPanel();
							iconObj.removeClass("fa-angle-left").addClass("fa-angle-down");
							$(this).parent("div").next().show(200);
						}else{//原先展开状态
							iconObj.removeClass("fa-angle-down").addClass("fa-angle-left");
							$(this).parent("div").next().hide(200);
						}
					})
				}
			})
			$(".operateType").change(function(){
				var selectedVal = $(this).val();
				if(selectedVal == '1'){
					$(".queryValuesWrap").show();
				}else{
					$(".queryValuesWrap").hide();
				}
			});
			$(".responseWrap").on("dblclick", "span", function(){
        		$("#responseContent").val("").val($.utils.jsonFormatSrc($(this).text()));
        		$('#displayResponseDialog').modal('show');
        	});
		},
		//关闭所有展开的面板
		closeAllOpenPanel : function(){
			$("#queryGroups a").each(function(){
				$(this).children("i").removeClass("fa-angle-down").addClass("fa-angle-left");
				$(this).parent("div").next().hide(200);
			})
		},
		queryList:function(){
			let moduleName = $("#searchForm input[name=moduleName]").val();
            $('#redisCfgTable').bootstrapTable({
                url: getContextPath() + "/redisCfg/searchByModuleName",
                queryParams:function(params){
                    return {
                        offset: params.offset,
                        pageSize: params.limit,
                        "moduleName":'%'+moduleName+'%'
                    }
                },
                responseHandler: function (response) {
                    return response;
                }
            });
        },
        validForm : function(formId){
        	var masterName = $(formId).find("input[name=masterName]").val();
        	var useSentinel = $(formId).find("select[name=useSentinel]").val();
        	if(useSentinel == 2 || (useSentinel == 1 && masterName)){
        		return true;
        	}else{
        		$.utils.alertWarning("使用哨兵时，主节点名不能为空");
        		return false;
        	}
        },
        initValid:function(){

            $('#addRedisCfgForm').validate({
                rules:{


                            address : "required",
                            
                            moduleName : "required",

                            password : "required",
                            
                            timeout : "required"



                },
                messages:{
                        moduleName : "请输入模块名!",
                        address : "请输入连接地址!",
                        password : "请输入密码!",
                        timeout : "请输入超时时间！"

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



            $('#updateRedisCfgForm').validate({
            rules:{


                    address : "required",



                    usesentinel : "required",

                    password : "required",





            },
            messages:{
                    address : "请输入连接地址!",
                    usesentinel : "请输入是否使用哨兵!",
                    password : "请输入密码!",

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




        }
};

/**
 * 所有非后台交互的按钮产生的动作方法
 * @type {{deleteById: $.redisCfgUtils.button.deleteById, updateById: $.redisCfgUtils.button.updateById, deleteBatch: $.redisCfgUtils.button.deleteBatch}}
 */
$.redisCfgUtils.button={
    add:function(){

        $('#addRedisCfgForm div').removeClass('has-error');

        $('#addRedisCfgForm').validate().resetForm();

        $('#addRedisCfgForm')[0].reset();
        
        $('#addRedisCfgForm textarea[name=operateResult]').text("");

        $('#addRedisCfgDialog').modal('show');
    },
    deleteByPrimaryKey:function(
            id
    ){

        $("#idForDel").val(id);

        $('#deleteRedisCfgDialog').modal('show');

    },
    updateById:function(
            id
    ){
        $.ajax({
            url: getContextPath() + "/redisCfg/selectByPrimaryKey",
            data: {
                        id:id
                    },
            success: function (response) {
                var data= $.utils.objToJson(response);
                $('#updateRedisCfgForm div').removeClass('has-error');
                $('#updateRedisCfgForm').validate().resetForm();
                $('.responseWrap').empty();
                $('#updateRedisCfgForm')[0].reset();
                $('#updateRedisCfgForm').fill(JSON.parse(data));
                $('#updateRedisCfgForm textarea[name=operateResult]').text("");
                $('#updateRedisCfgDialog').modal('show');
            }
        });
    },
    operationBatch:function () {
        //获取选中的所有数据
        var list = $('#redisCfgTable').bootstrapTable('getAllSelections');

        if(list == null || list.length == 0){
            $.utils.alertWarning("请选择至少一个数据!");
            return;
        }

        $("#batchRedisCfgDialog").modal("show");

    },
    selectByPrimaryKey:function(
            id
    ){
        $.ajax({
            url: getContextPath() + "/redisCfg/selectByPrimaryKey",
            data: {
                        id:id
                    },
            success: function (response) {
                    var data= $.utils.objToJson(response);

                    $('#showRedisCfgForm')[0].reset();

                    $('#showRedisCfgForm').fill(JSON.parse(data));

                    $('#showRedisCfgDialog').modal('show');

            }
        });
    }

};

/**
 * 所有直接通过操作与后台进行交互的方法
 * @type {{addRedisCfg: $.redisCfgUtils.submit.addRedisCfg, updateABC: $.redisCfgUtils.submit.updateRedisCfg, deleteById: $.redisCfgUtils.submit.deleteById, search: $.redisCfgUtils.submit.search, batch: $.redisCfgUtils.submit.batch}}
 */
$.redisCfgUtils.submit={
    addRedisCfg:function(){
    	$.statistic.recordOperation('redis');
        if($('#addRedisCfgForm').valid() && $.redisCfgUtils.validForm("#addRedisCfgForm")){
            $.utils.submit('/redisCfg/insert', '#addRedisCfgForm', '#addRedisCfgDialog', '新增出错~', null, '#redisCfgTable', null, '新增成功~');
        }
    },
    updateRedisCfg:function(){
    	$.statistic.recordOperation('redis');
        if($('#updateRedisCfgForm').valid()){
            $.utils.submit('/redisCfg/updateByPrimaryKeySelective', '#updateRedisCfgForm', '#updateRedisCfgDialog', '修改出错~', null, '#redisCfgTable', null, '修改成功~');
        }
    },
    deleteByPrimaryKey:function() {
    	$.statistic.recordOperation('redis');
        $.utils.submit('/redisCfg/deleteByPrimaryKey', '#deleteRedisCfgForm', '#deleteRedisCfgDialog', '删除出错~', null, '#redisCfgTable', null, '删除成功~');
    },
    search:function(){
    	$.statistic.recordOperation('redis');
        $("#redisCfgTable").bootstrapTable('removeAll');
        let moduleName = $("#searchForm input[name=moduleName]").val();
        //重新传递查询条件,也可以修改URL
        $("#redisCfgTable").bootstrapTable('refreshOptions',{
            url:getContextPath() + "/redisCfg/searchByModuleName",
            queryParams:function(params){
            	return {
                    offset: params.offset,
                    pageSize: params.limit,
                    "moduleName":'%'+moduleName+'%'
                };
            },
            responseHandler: function (response) {
                return response;
            }
        });

    },
    batch:function () {
        //获取全部选择的数据
        var list = $('#redisCfgTable').bootstrapTable('getAllSelections');
        //进行Ajax交互处理数据
    },
    queryRedis : function(formId){
    	$.statistic.recordOperation('redis');
    	var keys = $(formId).find("input[name=queryKeys]").val().trim();
    	var operateType = $(formId).find("select[name=operateType]").val();
    	if(!keys){
    		$.utils.alertWarning("键值不能为空!");
    		return;
    	}
    	if(keys.indexOf("，") >= 0){
    		$.utils.alertWarning("键值分隔符必须是英文逗号','!");
    		return;
    	}
    	if($(formId).valid() && $.redisCfgUtils.validForm(formId)){
    		if(operateType == 1){ //查询redis操作
    			$.ajax({
            		url : getContextPath() + "/redis/queryRedis",
            		data : $(formId).serialize(),
            		type : "post",
            		beforeSend:function(){
    	            	$.utils.loading(".responseWrap").show();
    	            },
    	            complete : function(){
    	            	$.utils.loading(".responseWrap").hide();
    	            },
            		success : function(data){
            			if(data.success){
            				var obj = data.obj;
            				var responseBody = "";
            				for(var index in obj){
            					responseBody += "<span style='white-space:nowrap;'>{\"key\":\"" + index + "\", \"value\": " + obj[index] + "}</span><br/>";
            				}
            				$(".responseWrap").html(responseBody);
            			}else{
            				$(".responseWrap").text("查询失败");
            			}
            		},
            		error : function(){
            			$.utils.alertError("服务器异常!");
            		}
            	});
    		}else if(operateType == 2){ //删除redis操作
    			$.ajax({
            		url : getContextPath() + "/redis/delRedis",
            		data : $(formId).serialize(),
            		type : "post",
            		success : function(data){
            			var resultArea = $(formId).find("textarea[name=operateResult]");
            			if(data.success){
            				$(resultArea).text("删除成功");
            			}else{
            				$(resultArea).val(data.errorMessage);
            			}
            		},
            		error : function(){
            			$.utils.alertError("服务器异常!");
            		}
            	});
    		}
    	}
    }

};

/**
 * 所有额外处理格式化的方法
 * @type {{operation: $.redisCfgUtils.formatter.operation}}
 */
$.redisCfgUtils.formatter={
    operation:function (value, row) {
        var html =  "<div class='btn-toolbar' role='toolbar'>" +
                    "<button type='button' class='btn-link' onclick='$.redisCfgUtils.button.updateById(\""
                            +   row.id
                    +
                    "\")'>选择</button> | "+
                    "<button type='button' class='btn-link' onclick='$.redisCfgUtils.button.deleteByPrimaryKey(\""
                            +   row.id
                    +
                    "\")'>删除</button>"+
                    "</div>";
        return  html;
    }
};
/**
 * 页面渲染结束后自动执行的类
 */
$(document).ready(function() {
    $.redisCfgUtils.init();
});

