/**
 * 所有其他类型的方法
 * @type {{init: $.kafkaWriteManagerUtils.init, queryList: $.kafkaWriteManagerUtils.queryList}}
 */
$.kafkaWriteManagerUtils={
		init:function(){
            $.kafkaWriteManagerUtils.queryList();
            $.kafkaWriteManagerUtils.initValid();
            $.kafkaWriteManagerUtils.loadKafkaWriteEnv();
		},
		queryList:function(){
			let name = $("#searchForm input[name=name]").val();
            $('#kafkaWriteManagerTable').bootstrapTable({
                url: getContextPath() + "/kafkaWriteManager/searchByName",
                queryParams:function(params){
                    return{
                        offset: params.offset,
                        pageSize: params.limit,
                        "name" : '%'+name+'%'
                    }
                },
                responseHandler: function (response) {
                    return response;
                }
            });
        },
        loadKafkaWriteEnv : function(){
        	$.ajax({
        		url : getContextPath() + "/kafkaClusterCfg/selectByExample",
        		type : "GET",
        		dataType : "json",
        		success : function(data){
        			for(var i = 0; i < data.totalRecord; i++){
        				$("#addKafkaWriteManagerForm select[name=clusterId]").append('<option value="'+data.rows[i].id+'">'+ data.rows[i].name + "-" +data.rows[i].clusterName+'</option>');
        				$("#updateKafkaWriteManagerForm select[name=clusterId]").append('<option value="'+data.rows[i].id+'">' + data.rows[i].name + "-" +data.rows[i].clusterName+'</option>');
        			}
        		},
        		error : function(){
        			$.utils.alertError("拉取kafka配置出错");
        		}
        	});
        },
        sendKafkaMsg : function(formId){
        	$.statistic.recordOperation('writeKafka');
        	var clusterId = $(formId).find("select[name=clusterId]").val();
        	var writeType = $(formId).find("select[name=writeType]").val();
        	var topic = $(formId).find("input[name=topic]").val();
        	var token = $(formId).find("input[name=token]").val();
        	var message = $(formId).find("textarea[name=message]").val();
        	if(clusterId && writeType && topic && token && message){
        		$.ajax({
            		url : getContextPath() + "/kafka/write",
            		type : "post",
            		dataType : "json",
            		data : {
            			"clusterId" : clusterId,
            			"writeType" : writeType,
            			"topic" : topic,
            			"token" : token,
            			"message" : message
            		},
            		success : function(data){
            			if(data){
            				$.utils.alertSuccess("发送成功");
            			}else{
            				$.utils.alertWarning("发送失败");
            			}
            		},
            		error : function(){
            			$.utils.alertError("服务器异常");
            		}
            	});
        	}else{
        		$.utils.alertWarning("请完整填写所需参数");
        	}
        },
        initValid:function(){

            $('#addKafkaWriteManagerForm').validate({
                rules:{


                            clusterId : "required",

                            name : "required",


                            topic : "required",

                            token : "required",

                            writeType : "required",

                },
                messages:{
                        clusterId : "",
                        name : "",
                        topic : "",
                        token : "",
                        writeType : "",
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



            $('#updateKafkaWriteManagerForm').validate({
            rules:{

                    clusterId : "required",

                    name : "required",


                    topic : "required",

                    token : "required",

                    writeType : "required",

                    message : "required",




            },
            messages:{
                    clusterId : "",
                    name : "",
                    topic : "",
                    token : "",
                    writeType : "",
                    message : "",

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
 * @type {{deleteById: $.kafkaWriteManagerUtils.button.deleteById, updateById: $.kafkaWriteManagerUtils.button.updateById, deleteBatch: $.kafkaWriteManagerUtils.button.deleteBatch}}
 */
$.kafkaWriteManagerUtils.button={
    add:function(){

        $('#addKafkaWriteManagerForm div').removeClass('has-error');

        $('#addKafkaWriteManagerForm').validate().resetForm();

        $('#addKafkaWriteManagerForm')[0].reset();

        $('#addKafkaWriteManagerDialog').modal('show');
    },
    deleteByPrimaryKey:function(
            id
    ){

        $("#idForDel").val(id);

        $('#deleteKafkaWriteManagerDialog').modal('show');

    },
    updateById:function(
            id
    ){
        $.ajax({
            url: getContextPath() + "/kafkaWriteManager/selectByPrimaryKey",
            data: {
                        id:id
                    },
            success: function (response) {
                    var data= $.utils.objToJson(response);

                    $('#updateKafkaWriteManagerForm div').removeClass('has-error');

                    $('#updateKafkaWriteManagerForm').validate().resetForm();

                    $('#updateKafkaWriteManagerForm')[0].reset();

                    $('#updateKafkaWriteManagerForm').fill(JSON.parse(data));
                    
                    $("#updateKafkaWriteManagerForm select[name=clusterId]").val(response.clusterId);
                    
                    $("#updateKafkaWriteManagerForm select[name=writeType]").val(response.writeType);

                    $('#updateKafkaWriteManagerDialog').modal('show');
                //}
            }
        });
    },
    operationBatch:function () {
        //获取选中的所有数据
        var list = $('#kafkaWriteManagerTable').bootstrapTable('getAllSelections');

        if(list == null || list.length == 0){
            $.utils.alertWarning("请选择至少一个数据!");
            return;
        }

        $("#batchKafkaWriteManagerDialog").modal("show");

    },
    selectByPrimaryKey:function(
            id
    ){
        $.ajax({
            url: getContextPath() + "/kafkaWriteManager/selectByPrimaryKey",
            data: {
                        id:id
                    },
            success: function (response) {
                    var data= $.utils.objToJson(response);

                    $('#showKafkaWriteManagerForm')[0].reset();

                    $('#showKafkaWriteManagerForm').fill(JSON.parse(data));

                    $('#showKafkaWriteManagerDialog').modal('show');

            }
        });
    },
    queryTopic : function(type){
    	var formId;
    	var envSel;
    	var tokenEl;
    	if(type == 1){
    		formId = "#updateKafkaWriteManagerForm";
    		envSel = "#updateEnvSel :selected";
    		tokenEl = "#updateToken";
    	}else{
    		formId = "#addKafkaWriteManagerForm";
    		envSel = "#newEnvSel :selected";
    		tokenEl = "#newToken";
    	}
    	var formData = $(formId).serializeFormToJson();
    	var topic = formData.topic;
    	if(topic.trim() != ""){
    		var clusterName = $(envSel).text();
        	var topicInfo = $.utils.queryTopic(clusterName, topic);
        	if(topicInfo){
        		$(tokenEl).val(topicInfo.token);
        	}
    	}
    }

};

/**
 * 所有直接通过操作与后台进行交互的方法
 * @type {{addKafkaWriteManager: $.kafkaWriteManagerUtils.submit.addKafkaWriteManager, updateABC: $.kafkaWriteManagerUtils.submit.updateKafkaWriteManager, deleteById: $.kafkaWriteManagerUtils.submit.deleteById, search: $.kafkaWriteManagerUtils.submit.search, batch: $.kafkaWriteManagerUtils.submit.batch}}
 */
$.kafkaWriteManagerUtils.submit={
    addKafkaWriteManager:function(){
        if($('#addKafkaWriteManagerForm').valid()){
            $.utils.submit('/kafkaWriteManager/insert', '#addKafkaWriteManagerForm', '#addKafkaWriteManagerDialog', '新增出错~', null, '#kafkaWriteManagerTable', null, '新增成功~');
        }
    },
    updateKafkaWriteManager:function(){
        if($('#updateKafkaWriteManagerForm').valid()){
            $.utils.submit('/kafkaWriteManager/updateByPrimaryKeySelective', '#updateKafkaWriteManagerForm', '#updateKafkaWriteManagerDialog', '修改出错~', null, '#kafkaWriteManagerTable', null, '修改成功~');
        }
    },
    deleteByPrimaryKey:function() {
        $.utils.submit('/kafkaWriteManager/deleteByPrimaryKey', '#deleteKafkaWriteManagerForm', '#deleteKafkaWriteManagerDialog', '删除出错~', null, '#kafkaWriteManagerTable', null, '删除成功~');
    },
    search:function(){
        $("#kafkaWriteManagerTable").bootstrapTable('removeAll');
        let name = $("#searchForm input[name=name]").val();
        //重新传递查询条件,也可以修改URL
        $("#kafkaWriteManagerTable").bootstrapTable('refreshOptions',{
            url:getContextPath() + "/kafkaWriteManager/searchByName",
            queryParams:function(params){
                return {
                    offset: params.offset,
                    pageSize: params.limit,
                    "name" : '%'+name+'%'
                };
            },
            responseHandler: function (response) {
                return response;
            }
        });

    }

};

/**
 * 所有额外处理格式化的方法
 * @type {{operation: $.kafkaWriteManagerUtils.formatter.operation}}
 */
$.kafkaWriteManagerUtils.formatter={
    operation:function (value, row) {
        var html =  "<div class='btn-toolbar' role='toolbar'>" +
                    "<button type='button' class='btn-link' onclick='$.kafkaWriteManagerUtils.button.updateById(\""
                            +   row.id
                    +
                    "\")'>选择</button>|"+
                    "<button type='button' class='btn-link' onclick='$.kafkaWriteManagerUtils.button.deleteByPrimaryKey(\""
                            +   row.id
                    +
                    "\")'>删除</button>"+
                    "</div>";
        return  html;
    },
    env : function(value, row){
    	var cluster = env2[value];
    	if(cluster){
    		return "<span>"+cluster.name+"-"+cluster.clusterName+"</span>";
    	}
    }
};

/**
 * 页面渲染结束后自动执行的类
 */
$(document).ready(function() {
    $.kafkaWriteManagerUtils.init();
});


