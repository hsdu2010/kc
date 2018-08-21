


var methods = [];
var xmls = [];

/**
 * 所有其他类型的方法
 * @type {{init: $.webserviceManagerUtils.init, queryList: $.webserviceManagerUtils.queryList}}
 */
$.webserviceManagerUtils={
		init:function(){
            $.webserviceManagerUtils.queryList();
            $.webserviceManagerUtils.initValid();
			 $("#methodList").change(function(){
				console.info("dd");
			    var index = $("#methodList option:checked").attr("data-index");
	 			$("#requestText").val(xmls[index]);
			 });
		},
		queryList:function(){
			var name = $("#searchForm input[name=name]").val();
            $('#webserviceManagerTable').bootstrapTable({
                url: getContextPath() + "/webserviceManager/searchByName",
                queryParams:function(params){
                    return{
                        offset: params.offset,
                        pageSize: params.limit,
                        "name":'%'+name+'%'
                        //对象查询相关可以从此处传递值,具体实现需自行编码
                        //例如: id:id
                    }
                },
                responseHandler: function (response) {
                    return response;
                },
                uniqueId:"id"
            });
        },
        initValid:function(){
            $('#addWebserviceManagerForm').validate({
                rules:{
                			name : "required",
                            url : "required",
                            method : "required",

                },
                messages:{
                	name : "",
                        url : "",
                        method : "",
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
 * @type {{deleteById: $.webserviceManagerUtils.button.deleteById, updateById: $.webserviceManagerUtils.button.updateById, deleteBatch: $.webserviceManagerUtils.button.deleteBatch}}
 */
$.webserviceManagerUtils.button={
    add:function(){
        $('#addWebserviceManagerForm div').removeClass('has-error');
        $('#addWebserviceManagerForm').validate().resetForm();
        $('#addWebserviceManagerForm')[0].reset();
        $("#methodList").empty();
        $('#saveBtn').unbind().click(function(){
        	$.webserviceManagerUtils.submit.addWebserviceManager();
        });
    },
    selectByPrimaryKey:function(
            id
    ){
	      var data = $('#webserviceManagerTable').bootstrapTable('getRowByUniqueId', id);
	      $('#addWebserviceManagerForm')[0].reset();
	      $('#addWebserviceManagerForm').fill(data);
	      $('#addWebserviceManagerForm div').removeClass('has-error');
	      $('#addWebserviceManagerForm').validate().resetForm();
	      var optionsHtml = " <option value='" + data.method + "' >" + data.method + "</option>";
	      $("#methodList").empty().append(optionsHtml);
	      $('#addWebserviceManagerDialog').modal('show');
	      $('#saveBtn').unbind().click(function(){
	      	$.webserviceManagerUtils.submit.updateWebserviceManager();
	      });
    }

};

/**
 * 所有直接通过操作与后台进行交互的方法
 * @type {{addWebserviceManager: $.webserviceManagerUtils.submit.addWebserviceManager, updateABC: $.webserviceManagerUtils.submit.updateWebserviceManager, deleteById: $.webserviceManagerUtils.submit.deleteById, search: $.webserviceManagerUtils.submit.search, batch: $.webserviceManagerUtils.submit.batch}}
 */
$.webserviceManagerUtils.submit={
    addWebserviceManager:function(){
        if($('#addWebserviceManagerForm').valid()){
            $.utils.submit('/webserviceManager/insert', '#addWebserviceManagerForm', '#addWebserviceManagerDialog', '新增出错~', null, '#webserviceManagerTable', null, '新增成功~');
        }
    },
    updateWebserviceManager:function(){
        if($('#addWebserviceManagerForm').valid()){
            $.utils.submit('/webserviceManager/updateByPrimaryKeySelective', '#addWebserviceManagerForm', '#addWebserviceManagerDialog', '修改出错~', null, '#webserviceManagerTable', null, '修改成功~');
        }
    },
    deleteByPrimaryKey:function(id) {
    	var data = {
    		id : id
    	};
        $.utils.submit('/webserviceManager/deleteByPrimaryKey', null, null, '删除出错~', data, '#webserviceManagerTable', null, '删除成功~');
    },
    search : function(){
    	var name = $("#searchForm input[name=name]").val();
    	$("#webserviceManagerTable").bootstrapTable('removeAll');
        //重新传递查询条件,也可以修改URL
        $("#webserviceManagerTable").bootstrapTable('refreshOptions',{
            url:getContextPath() + "/webserviceManager/searchByName",
            queryParams:function(params){
                return {
                	offset: params.offset,
                    pageSize: params.limit,
                    "name":'%'+name+'%'
                }
            },
            responseHandler: function (response) {
                return response;
            }
        });
    },
    queryMethodList : function(){
    	var wsUrl = $("#wsUrl").val();
    	if(wsUrl != ''){
            $.ajax({
                url: "http://10.202.43.6:8083/getSoapMsg",
                data: {
                	wsdl:wsUrl,
                },
                type:"GET",
                success: function (response) {
                	var datas = JSON.parse(response);
                	methods = [];
                	xmls = [];
                	$.each(datas, function(methodName, xml){
                		methods.push(methodName);
                		xmls.push(xml);
                	});
                	var optionsHtml = "";
            		for(var i = 0; i < methods.length; i++){
            			optionsHtml += "<option value='" + methods[i] + "' data-index='"+i+"'>" + methods[i] + "</option>";
        			}
            		$("#methodList").empty().append(optionsHtml);
            		if(xmls.length >0){
            			$("#requestText").val(xmls[0]);
            		}
                }
            });
    	}
    },
    sender : function(){
    	$.statistic.recordOperation('webservice');
    	if($('#addWebserviceManagerForm').valid()){
    		 $.ajax({
    	            url: getContextPath() + "/interInvoke/wsRequest",
    	            data: {
    	            	wsUrl : $("#wsUrl").val().replace("?wsdl", ""),
    	            	requestBody:$("#requestText").val()
    	            },
    	            type:"post",
    	            success: function (response) {
    	            	if(response.success){
    	            		var xml = $.utils.formatXml(response.obj);
    	            		$("#response").val(xml);
    	            	}
    	            }
    	        });
    	}
    }
};

/**
 * 所有额外处理格式化的方法
 * @type {{operation: $.webserviceManagerUtils.formatter.operation}}
 */
$.webserviceManagerUtils.formatter={
    operation:function (value, row) {
        var html =  "<div class='btn-toolbar' role='toolbar'>" +
                    "<button type='button' class='btn-link' onclick=$.webserviceManagerUtils.button.selectByPrimaryKey('"
                            + row.id
                    +
                    "')>选择</button>|"+
                    "<button type='button' class='btn-link' onclick=$.webserviceManagerUtils.submit.deleteByPrimaryKey('"
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
    $.webserviceManagerUtils.init();
});
