/**
 * 所有其他类型的方法
 * @type {{init: $.httpMgr.init, queryList: $.httpMgr.queryList}}
 */
$.httpMgr={
		init:function(){
            $.httpMgr.queryList();
            $.httpMgr.initValid();
            $.httpMgr.initHeaders();
		},
		queryList:function(){
			let remark = $("#searchForm input[name=remark]").val();
            $('#httpMgrTable').bootstrapTable({
                url: getContextPath() + "/httpMgr/searchByRemark",
                queryParams:function(params){
                    return{
                        offset: params.offset,
                        pageSize: params.limit,
                        "remark":'%'+remark+'%'
                    }
                },
                responseHandler: function (response) {
                    return response;
                },
                uniqueId:"id"
            });
        },
        initValid:function(){
            $('#addUserHttpInfoForm').validate({
                rules:{
                	requestUrl : "required",
                	remark : "required",
                	requestMethod:"required"
                },
                messages:{
                	requestUrl : "",
                	remark : "",
                	requestMethod:""
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
        initHeaders : function(){
        	
        	var html = '';
        	html += '<tbody style="display: block;overflow-y: scroll;height:200px;">';
        	html += 	'<tr style="margin-bottom:5px;">';
        	html += 		'<td style="width:40%;text-align: center;">键</td>';
        	html += 		'<td style="width:5%;text-align: center;"></td>';
        	html += 		'<td style="width:40%;text-align: center;">值</td>';
        	html += 		'<td style="width:10%;text-align:center;"><a class="detail-icon" href="javascript:" onclick="$.utils.addHeader(this)"><i class="glyphicon glyphicon-plus icon-plus"></i></a></td>';
        	html += 	'</tr>';
        	
        	html += 	'<tr>';
        	html += 		'<td style="width:40%;text-align: center;">';
        	html += 			'<input type="text" class="header-params form-control" style=" border-radius: 4px !important;" name="headKey" required="" value="Content-Type">'
        	html += 		'</td>';
        	html += 		'<td style="width:5%;text-align: center;">:</td>';
        	html += 		'<td style="width:40%;text-align: center;">';
        	html += 		'<input type="text" class="header-params form-control" style=" border-radius: 4px !important;" name="headValue" required="" value="application/json">';
        	
        	html += 		'</td>';
        	html += 	'</tr>';
        	html += '</tbody>';
        	$("#headerTable").html(html);
        }
};


/**
 * 所有非后台交互的按钮产生的动作方法
 * @type {{deleteById: $.httpMgr.button.deleteById, updateById: $.httpMgr.button.updateById, deleteBatch: $.httpMgr.button.deleteBatch}}
 */
$.httpMgr.button={
    selectByPrimaryKey:function(
            id
    ){
    	var data = $('#httpMgrTable').bootstrapTable('getRowByUniqueId', id);
    	$.utils.analysisHeader(data.requestHeader,"headerTable");
    	$('#addUserHttpInfoForm').validate().resetForm();
    	$('#addUserHttpInfoForm div').removeClass('has-error');
    	$('#addUserHttpInfoForm')[0].reset();
    	$('#addUserHttpInfoForm').fill(data);
    	$("#httpRequestType").val((data.requestMethod ? "1" : "0"));
		$("#saveBtn").unbind().click(function(){
			$.httpMgr.submit.updateUserHttpInfo();
		});
		$('#addUserHttpInfoDialog').modal('show');
    },
    add:function(){
    	$('#addUserHttpInfoForm').validate().resetForm();
    	$('#addUserHttpInfoForm div').removeClass('has-error');
    	$("#addUserHttpInfoForm")[0].reset(); //表单数据重置
    	$.httpMgr.initHeaders(); //初始化请求头
		$("#saveBtn").unbind().click($.httpMgr.submit.addUserHttpInfo);
		$("#addUserHttpInfoDialog").modal('show');
    }
};

/**
 * 所有直接通过操作与后台进行交互的方法
 * @type {{addUserHttpInfo: $.httpMgr.submit.addUserHttpInfo, updateABC: $.httpMgr.submit.updateUserHttpInfo, deleteById: $.httpMgr.submit.deleteById, search: $.httpMgr.submit.search, batch: $.httpMgr.submit.batch}}
 */
$.httpMgr.submit={
    addUserHttpInfo:function(){
    	if($("#addUserHttpInfoForm").valid()){
        	var requestHeader = $.utils.getHeader("headerTable");
        	if(requestHeader == ""){
        		$.utils.alertWarning("请求头不能为空!");
        		return ;
        	}
        	$("#requestHeader").val(requestHeader);
        	$.utils.submit2('/httpMgr/insert', "#addUserHttpInfoForm", '#addUserHttpInfoDialog', '新增出错~', null, '#httpMgrTable', null, '新增成功~');
    	}
    },
    updateUserHttpInfo:function(){
    	if($("#addUserHttpInfoForm").valid()){
        	var requestHeader = $.utils.getHeader("headerTable");
        	if(requestHeader == ""){
        		$.utils.alertWarning("请求头不能为空!");
        		return ;
        	}
        	$("#requestHeader").val(requestHeader);
        	 $.utils.submit2('/httpMgr/updateByPrimaryKeySelective', '#addUserHttpInfoForm', '#addUserHttpInfoDialog', '修改出错~', null, '#httpMgrTable', null, '修改成功~');
    	}
    },
    deleteByPrimaryKey:function(id) {
        $.utils.submit2('/httpMgr/deleteByPrimaryKey', '#deleteUserHttpInfoForm', '#deleteUserHttpInfoDialog', '删除出错~', {"id":id}, '#httpMgrTable', null, '删除成功~');
    },
    search:function(){
        $("#httpMgrTable").bootstrapTable('removeAll');
        var remark = $("#searchForm input[name=remark]").val();
        //重新传递查询条件,也可以修改URL
        $("#httpMgrTable").bootstrapTable('refreshOptions',{
            url:getContextPath() + "/httpMgr/searchByRemark",
            queryParams:function(params){
            	return{
                    offset: params.offset,
                    pageSize: params.limit,
                    "remark": '%'+remark+'%'
                }
            },
            responseHandler: function (response) {
            	return response;
            }
        });

    },
    httpSend : function(){
    	$.statistic.recordOperation('http');
    	if($("#addUserHttpInfoForm").valid()){
    		var requestHeader = $.utils.getHeader("headerTable");
    		var requestTemplate = $("#requestTemplate").val();
        	if(requestHeader == ""){
        		$.utils.alertWarning("请求头不能为空!");
        		return ;
        	}
        	if(requestTemplate == ""){
        		$.utils.alertWarning("请求参数不能为空!");
        		return ;
        	}
        	$("#requestHeader").val(requestHeader);
    		var data = {
        			requestUrl : $("#requestUrl").val(),
    				requestHeader : requestHeader,
    				request : requestTemplate,
    				requestType : $("#httpRequestType").val()
    		}
    		
    		$.ajax({
    			type : "post",
    			url : getContextPath() + "/interInvoke/httpRequest",
    			dataType : "json",
    			data : data,
    			async : false,
    			success:function(response){
    				if (!response.success) {
    					$.utils.alertError("http调用失败");
    				} else {
    					var data = response.obj;
    					if($.utils.isJSON(data)){
    						data = $.utils.jsonFormat(data);
    					}
    					$("#response").val(data);
    				}
    			},
    			error : function(XMLHttpRequest, textStatus, errorThrown) {
    				$.utils.alertError('AJAX出错');
    			}
    		});
    	}
    }
};

/**
 * 所有额外处理格式化的方法
 * @type {{operation: $.httpMgr.formatter.operation}}
 */
$.httpMgr.formatter={
    operation:function (value, row) {
        var html =  "<div class='btn-toolbar' role='toolbar'>" +
                    "<button type='button' class='btn-link' onclick=$.httpMgr.button.selectByPrimaryKey('"
                            + row.id
                    +
                    "')>选择</button>|"+
                    "<button type='button' class='btn-link' onclick=$.httpMgr.submit.deleteByPrimaryKey('"
                            +   row.id
                    +
                    "')>删除</button>"+
                    "</div>";
        return  html;
    }
};
/**
 * 页面渲染结束后自动执行的类
 */
$(document).ready(function() {
    $.httpMgr.init();
});

