

/**
 * 所有其他类型的方法
 * @type {{init: $.uploadjarManagerUtils.init, queryList: $.uploadjarManagerUtils.queryList}}
 */
var fileName;
$.uploadjarManagerUtils={
		init:function(){
            $.uploadjarManagerUtils.queryList();
            $.uploadjarManagerUtils.initValid();
            $.uploadjarManagerUtils.initEvent();
		},
		initEvent : function(){
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
		},
		queryList:function(){
            $('#uploadjarManagerTable').bootstrapTable({
                url: getContextPath() + "/uploadjarManager/selectByExample",
                queryParams:function(params){
                    return{
                        offset: params.offset,
                        pageSize: params.limit
                    }
                },
                responseHandler: function (response) {
                    return response;
                },
                uniqueId: "id"
            });
        },
        initValid:function(){

            $('#adduploadjarManagerForm').validate({
                rules:{

                            id : "required",

                            name : "required"

                },
                messages:{
                        id : "",
                        name : ""

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
 * @type {{deleteById: $.uploadjarManagerUtils.button.deleteById, updateById: $.uploadjarManagerUtils.button.updateById, deleteBatch: $.uploadjarManagerUtils.button.deleteBatch}}
 */
$.uploadjarManagerUtils.button={
    add:function(){

        $('#adduploadjarManagerForm div').removeClass('has-error');

        $('#adduploadjarManagerForm').validate().resetForm();

        $('#adduploadjarManagerForm')[0].reset();

    },
    updateById:function(
            id
    ){
        $.ajax({
            url: getContextPath() + "/uploadjarManager/selectByPrimaryKey",
            data: {
                        id:id
                    },
            success: function (response) {
                    var data= $.utils.objToJson(response);

                    $('#updateUploadjarManagerForm div').removeClass('has-error');

                    $('#updateUploadjarManagerForm').validate().resetForm();

                    $('#updateUploadjarManagerForm')[0].reset();

                    $('#updateUploadjarManagerForm').fill(JSON.parse(data));

                    $('#updateUploadjarManagerDialog').modal('show');
                //}
            }
        });
    },
    operationBatch:function () {
        //获取选中的所有数据
        var list = $('#uploadjarManagerTable').bootstrapTable('getAllSelections');

        if(list == null || list.length == 0){
            $.utils.alertWarning("请选择至少一个数据!");
            return;
        }

        $("#batchUploadjarManagerDialog").modal("show");

    },
    selectByPrimaryKey:function(
            id
    ){
        $.ajax({
            url: getContextPath() + "/uploadjarManager/selectByPrimaryKey",
            data: {
                        id:id
                    },
            success: function (response) {
                    var data= $.utils.objToJson(response);

                    $('#showUploadjarManagerForm')[0].reset();

                    $('#showUploadjarManagerForm').fill(JSON.parse(data));

                    $('#showUploadjarManagerDialog').modal('show');

            }
        });
    }

};

/**
 * 所有直接通过操作与后台进行交互的方法
 * @type {{addUploadjarManager: $.uploadjarManagerUtils.submit.addUploadjarManager, updateABC: $.uploadjarManagerUtils.submit.updateUploadjarManager, deleteById: $.uploadjarManagerUtils.submit.deleteById, search: $.uploadjarManagerUtils.submit.search, batch: $.uploadjarManagerUtils.submit.batch}}
 */
$.uploadjarManagerUtils.submit={
    addUploadjarManager:function(){
    	$.statistic.recordOperation('uploadJar');
        if($('#adduploadjarManagerForm').valid()){
        	var isUploaded = $("#isUploaded").val();
			if(isUploaded == "0"){
				$(".fileerrorTip").html("请上传后缀名为.jar的文件").show();
				return ;
			}
			$(".fileerrorTip").html("").hide();
			$.ajaxFileUpload({
				url : getContextPath() + '/uploadjarManager/insert',
	            data : {"name":$("#name").val(),"jarName":fileName},
	            type : 'POST',
	            secureuri : false, // 一般设置为false
	            fileElementId : 'uploadjar',
	            dataType : 'json',
	            success : function(response) {
	            	if (response == true) {
	            		$("#uploadjarManagerTable").bootstrapTable('refresh');
	            		$("#adduploadjarManagerDialog").modal('hide');
	            	}else{
	            		$.utils.alertWarning("添加信息出错")
	            	}						
	            },
	            error : function() {
	            	$.utils.alertWarning("添加信息出错");
	            }
	         });
        }
    },
    deleteByPrimaryKey:function(id) {
    	var data = $('#uploadjarManagerTable').bootstrapTable('getRowByUniqueId', id);
        $.utils.submit('/uploadjarManager/deleteByPrimaryKey', null, '#deleteUploadjarManagerDialog', '删除出错~', {"id":id,"jarName":data.jarName}, '#uploadjarManagerTable', null, '删除成功~');
    },
    search:function(){

        $("#uploadjarManagerTable").bootstrapTable('removeAll');

        //重新传递查询条件,也可以修改URL
        $("#uploadjarManagerTable").bootstrapTable('refreshOptions',{
            url:getContextPath() + "/uploadjarManager/selectByPrimaryKey",
            queryParams:function(params){
                return $("#searchForm").serialize();

            },
            responseHandler: function (response) {

                return {
                    rows:new Array(response),
                    totalRecord:1
                }
            }
        });

    }

};

/**
 * 所有额外处理格式化的方法
 * @type {{operation: $.uploadjarManagerUtils.formatter.operation}}
 */
$.uploadjarManagerUtils.formatter={
    operation:function (value, row) {
        var html =  "<div class='btn-toolbar' role='toolbar'>" +
                    "<button type='button' class='btn-link' onclick=$.uploadjarManagerUtils.submit.deleteByPrimaryKey('"
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
    $.uploadjarManagerUtils.init();
});
