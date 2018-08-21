




/**
 * 所有其他类型的方法
 * @type {{init: $.kafkaReadManagerUtils.init, queryList: $.kafkaReadManagerUtils.queryList}}
 */
var env={
};
var env2 = {
};

$.kafkaReadManagerUtils={
		isQuerySelf: false,
		init:function(){
			$.kafkaReadManagerUtils.queryEnv();
            $.kafkaReadManagerUtils.queryList();
            $.kafkaReadManagerUtils.initValid();
            $.kafkaReadManagerUtils.render();
            $.kafkaReadManagerUtils.initEvent();
		},
		render:function(){
			var options = "";
			$.each(env, function(name){
				options += "<option value='"+name+"'>"+name+"</option>";
			});
			$("#envType").empty().append(options);
		},
		queryEnv:function(){
	        $.ajax({
	            url: getContextPath() + "/kafkaClusterCfg/selectByExample",
	            async:false,
	            data: {
	            	offset:0,
	            	pageSize : 100
	                    },
	            success: function (response) {
	            	$.each(response.rows, function(k, item){
	            		env[item.name+'-'+item.clusterName] = item;
	            		env2[item.id] = item;
	            	});
	            }
	        });
		},
		queryList:function(){
			let name = $("#searchForm input[name=name]").val();
            $('#kafkaReadManagerTable').bootstrapTable({
                url: getContextPath() + "/kafkaReadManager/searchByName",
                queryParams:function(params){
                    return{
                        offset: params.offset,
                        pageSize: params.limit,
                        "name":'%'+name+'%',
                        isQuerySelf:$.kafkaReadManagerUtils.isQuerySelf
                    }
                },
                responseHandler: function (response) {
                    return response;
                },
                uniqueId:"id"
            });
        },
        initValid:function(){
            $('#addKafkaReadManagerForm').validate({
                rules:{

                            name : "required",

                            topic : "required",

                            token : "required",

                },
                messages:{
                	name : "",
                	topic : "",
                	token : "",

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
        initEvent : function(){
        	$("#response").on("dblclick", "span", function(){
        		var formatText = $.utils.jsonFormatSrc($(this).text());
        		if(formatText){
            		$("#responseContent").val(formatText);
            		$('#displayResponseDialog').modal('show');
        		}
        	});
        }
};

/**
 * 所有非后台交互的按钮产生的动作方法
 * @type {{deleteById: $.kafkaReadManagerUtils.button.deleteById, updateById: $.kafkaReadManagerUtils.button.updateById, deleteBatch: $.kafkaReadManagerUtils.button.deleteBatch}}
 */
$.kafkaReadManagerUtils.button={
    add:function(){
        $('#addKafkaReadManagerForm div').removeClass('has-error');

        $('#addKafkaReadManagerForm').validate().resetForm();

        $('#addKafkaReadManagerForm')[0].reset();
        $("#response").empty();
        $("#numWrap").hide();
        $('#addKafkaReadManagerDialog').modal('show');
        $('#saveBtn').unbind().click($.kafkaReadManagerUtils.submit.addKafkaReadManager);
    },
    deleteByPrimaryKey:function(
            id
    ){
        $("#id").val(id);
        $('#deleteKafkaReadManagerDialog').modal('show');

    },
    selectByPrimaryKey:function(
            id
    ){
    	var data = $('#kafkaReadManagerTable').bootstrapTable('getRowByUniqueId', id);
        $('#addKafkaReadManagerForm')[0].reset();
        $('#addKafkaReadManagerForm').fill(data);
        $('#addKafkaReadManagerForm div').removeClass('has-error');
        $('#addKafkaReadManagerForm').validate().resetForm();
        var cluster = env2[data.clusterId];
        if(cluster){
        	$("#envType").val(cluster.name+"-"+cluster.clusterName);
        }
        $("#response").empty();
        $("#numWrap").hide();
        $('#addKafkaReadManagerDialog').modal('show');
        $("#saveBtn").unbind().click(function(){
        	$.kafkaReadManagerUtils.submit.updateKafkaReadManager();
        });
    },
    queryTopic : function(){
    	var topic = $("#topic").val();
    	if(topic.trim() != ""){
    		var cluster = env[$("#envType").val()];
        	var topicInfo = $.utils.queryTopic(cluster.name +"-"+cluster.clusterName, topic);
        	if(topicInfo){
        		$("#token").val(topicInfo.token);
        	}
    	}
    }
};

/**
 * 所有直接通过操作与后台进行交互的方法
 * @type {{addKafkaReadManager: $.kafkaReadManagerUtils.submit.addKafkaReadManager, updateABC: $.kafkaReadManagerUtils.submit.updateKafkaReadManager, deleteById: $.kafkaReadManagerUtils.submit.deleteById, search: $.kafkaReadManagerUtils.submit.search, batch: $.kafkaReadManagerUtils.submit.batch}}
 */
$.kafkaReadManagerUtils.submit={
    addKafkaReadManager:function(){
        if($('#addKafkaReadManagerForm').valid()){
        	$("#clusterId").val(env[$("#envType").val()].id); //设置集群环境
            $.utils.submit('/kafkaReadManager/insert', '#addKafkaReadManagerForm', '#addKafkaReadManagerDialog', '新增出错~', null, '#kafkaReadManagerTable', null, '新增成功~');
        }
    },
    updateKafkaReadManager:function(){
        if($('#addKafkaReadManagerForm').valid()){
        	$("#clusterId").val(env[$("#envType").val()].id); //设置集群环境
            $.utils.submit('/kafkaReadManager/updateByPrimaryKeySelective', '#addKafkaReadManagerForm', '#addKafkaReadManagerDialog', '修改出错~', null, '#kafkaReadManagerTable', null, '修改成功~');
        }
    },
    deleteByPrimaryKey:function(id) {
    	$("#id").val(id);
        $.utils.submit('/kafkaReadManager/deleteByPrimaryKey', '#addKafkaReadManagerForm', '#addKafkaReadManagerDialog', '删除出错~', null, '#kafkaReadManagerTable', null, '删除成功~');
    },
    search:function(){
        $("#kafkaReadManagerTable").bootstrapTable('removeAll');
        let name = $("#searchForm input[name=name]").val();
        //重新传递查询条件,也可以修改URL
        $("#kafkaReadManagerTable").bootstrapTable('refreshOptions',{
            url:getContextPath() + "/kafkaReadManager/searchByName",
            queryParams:function(params){
                return {
                    offset: params.offset,
                    pageSize: params.limit,
                    "name" : '%'+name+'%',
                    isQuerySelf:$.kafkaReadManagerUtils.isQuerySelf
                };

            },
            responseHandler: function (response) {
                return response;
            }
        });

    },
    fetchKafka:function(){
    	$.statistic.recordOperation('readKafka');
    	 if($('#addKafkaReadManagerForm').valid()){
    		var maxSize = $("#maxSize").val();
    		if(!maxSize){
    			$.utils.alertWarning("请输入读取条数!");
    			return;
    		} 
    		var cluster = env[$("#envType").val()];
    		var formData = {
    			clusterName: cluster.clusterName,
    			monitorUrl : cluster.monitorUrl,
    			topicName : $("#topic").val(),
    			maxSize : $("#maxSize").val(),
    			filterStr : $("#filterCondition").val(),
    			token : $("#topic").val()+":"+$("#token").val(),
    			deserialize : $("#deserialize").val()
    		}
	        $.ajax({
	            url: getContextPath() + "/kafkaReadManager/fetchKafka",
	            async:true,
	            data: formData,
	            beforeSend:function(){
	            	$.utils.loading("#response").show();
	            },
	            complete : function(){
	            	$.utils.loading("#response").hide();
	            },
	            success: function (response) {
	            	if(response.success){
	            		var article = "";
	            		$.each(response.obj, function(row, content){
	            			if(content){
		            			article += "<span style='white-space:nowrap;'>"+content.replace(/</g, "&lt;").replace(/>/g, "&gt;")+"</span><br/>";
	            			}
	            		});
	            		$("#num").text(response.obj.length);
	            		$("#numWrap").show();
	            		$("#response").html(article);
	            	}else{
	            		$.utils.loading("#response").hide();
	            		$.utils.alertWarning("读取kafka失败!");
	            	}
	            }
	        });
    	 };
    }
};

/**
 * 所有额外处理格式化的方法
 * @type {{operation: $.kafkaReadManagerUtils.formatter.operation}}
 */
$.kafkaReadManagerUtils.formatter={
    operation:function (value, row) {
        var html =  "<div class='btn-toolbar' role='toolbar'>" +
                    "<button type='button' class='btn-link' onclick=$.kafkaReadManagerUtils.button.selectByPrimaryKey('"
                            + row.id
                    +
                    "')>选择</button>|"+
                    "<button type='button' class='btn-link' onclick=$.kafkaReadManagerUtils.submit.deleteByPrimaryKey('"
                            +   row.id
                    +
                    "')>删除</button>"+
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
    $.kafkaReadManagerUtils.init();
});
