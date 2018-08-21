

/**
 * 所有其他类型的方法
 * @type {{init: $.dbConnCfgUtils.init, queryList: $.dbConnCfgUtils.queryList}}
 */
$.dbConnCfgUtils={
		init:function(){
            $.dbConnCfgUtils.queryList();
            $.dbConnCfgUtils.initValid();
		},
		queryList:function(){
			var connName = $("#searchForm input[name=connName]").val();
            $('#dbConnCfgTable').bootstrapTable({
                url: getContextPath() + "/dbConnCfg/searchByConnName",
                queryParams:function(params){
                    return{
                        offset: params.offset,
                        pageSize: params.limit,
                        "connName": '%'+connName+'%'
                        //对象查询相关可以从此处传递值,具体实现需自行编码
                        //例如: id:id

                    }
                },
                responseHandler: function (response) {
                    return response;
                }
            });
        },
        connTest : function(formId, element){
        	var host = $(formId).find("input[name=host]").val();
        	var port = $(formId).find("input[name=port]").val();
        	var dbName = $(formId).find("input[name=dbName]").val();
        	var username = $(formId).find("input[name=username]").val();
        	var password = $(formId).find("input[name=password]").val();
        	if(host && port && dbName && username && password){
        		$.ajax({
        			url : getContextPath() + "/jdbc/dbConnTest",
        			data : {
        				"host" : host,
        				"port" : port,
        				"dbName" : dbName,
        				"username" : username,
        				"password" : password
        			},
        			dataType : "json",
        			type : "post",
        			success : function(data){
        				console.log(data);
        				if(data){
        					$(element).next().html("连接成功");
        				}else{
        					$(element).next().html("连接失败");
        				}
        			},
        			error : function(){
        				$(element).next().html("测试失败");
        			}
        		})
        	}else{
        		$(element).next().html("连接参数不能为空");
        	}
        },
        initValid:function(){

            $('#addDbConnCfgForm').validate({
                rules:{

                            //dbId : "required",

                            connName : "required",

                            host : "required",

                            port : "required",

                            dbName : "required",

                            username : "required",

                            password : "required",





                },
                messages:{
                        //dbId : "请输入id!",
                        connName : "请输入数据库连接名!",
                        host : "请输入主机名!",
                        port : "请输入端口号!",
                        dbName : "请输入数据库名称!",
                        username : "请输入用户名!",
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



            $('#updateDbConnCfgForm').validate({
            rules:{

                    connName : "required",

                    host : "required",

                    port : "required",

                    dbName : "required",

                    username : "required",

                    password : "required",





            },
            messages:{
                    connName : "请输入数据库连接名!",
                    host : "请输入主机名!",
                    port : "请输入端口号!",
                    dbName : "请输入数据库名称!",
                    username : "请输入用户名!",
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
 * @type {{deleteById: $.dbConnCfgUtils.button.deleteById, updateById: $.dbConnCfgUtils.button.updateById, deleteBatch: $.dbConnCfgUtils.button.deleteBatch}}
 */
$.dbConnCfgUtils.button={
    add:function(){

        $('#addDbConnCfgForm div').removeClass('has-error');

        $('#addDbConnCfgForm').validate().resetForm();

        $('#addDbConnCfgForm')[0].reset();
        
        $('#addDbConnCfgForm input[name=createEmp]').val($.utils.username);

        $('#addDbConnCfgForm input[name=modifyEmp]').val($.utils.username);
        
        $('#addConnTest').html('');

        $('#addDbConnCfgDialog').modal('show');
    },
    deleteByPrimaryKey:function(
            dbId
    ){

        $("#dbIdForDel").val(dbId);

        $('#deleteDbConnCfgDialog').modal('show');

    },
    updateById:function(
            dbId
    ){
        $.ajax({
            url: getContextPath() + "/dbConnCfg/selectByPrimaryKey",
            data: {
                        dbId:dbId
                    },
            success: function (response) {
                //if (response.code != '000') {
                    //如果返回Json中存在标识码等,可以通过下面的方法显示错误信息,
                //    $.utils.alertError(response.message);
              //  } else {
                    var data= $.utils.objToJson(response);

                    $('#updateDbConnCfgForm div').removeClass('has-error');

                    $('#updateDbConnCfgForm').validate().resetForm();

                    $('#updateDbConnCfgForm')[0].reset();

                    $('#updateDbConnCfgForm').fill(JSON.parse(data));
                    
                    $('#updateDbConnCfgForm input[name=modifyEmp]').val($.utils.username);
                    
                    $('#updateConnTest').html('');

                    $('#updateDbConnCfgDialog').modal('show');
                //}
            }
        });
    },
    operationBatch:function () {
        //获取选中的所有数据
        var list = $('#dbConnCfgTable').bootstrapTable('getAllSelections');

        if(list == null || list.length == 0){
            $.utils.alertWarning("请选择至少一个数据!");
            return;
        }

        $("#batchDbConnCfgDialog").modal("show");

    },
    selectByPrimaryKey:function(
            dbId
    ){
        $.ajax({
            url: getContextPath() + "/dbConnCfg/selectByPrimaryKey",
            data: {
                        dbId:dbId
                    },
            success: function (response) {
                    var data= $.utils.objToJson(response);

                    $('#showDbConnCfgForm')[0].reset();

                    $('#showDbConnCfgForm').fill(JSON.parse(data));

                    $('#showDbConnCfgDialog').modal('show');

            }
        });
    }

};

/**
 * 所有直接通过操作与后台进行交互的方法
 * @type {{addDbConnCfg: $.dbConnCfgUtils.submit.addDbConnCfg, updateABC: $.dbConnCfgUtils.submit.updateDbConnCfg, deleteById: $.dbConnCfgUtils.submit.deleteById, search: $.dbConnCfgUtils.submit.search, batch: $.dbConnCfgUtils.submit.batch}}
 */
$.dbConnCfgUtils.submit={
    addDbConnCfg:function(){
        if($('#addDbConnCfgForm').valid()){
        	$.statistic.recordOperation('dbConnCfg');
            $.utils.submit('/dbConnCfg/insert', '#addDbConnCfgForm', '#addDbConnCfgDialog', '新增出错~', null, '#dbConnCfgTable', null, '新增成功~');
        }
    },
    updateDbConnCfg:function(){
        if($('#updateDbConnCfgForm').valid()){
        	$.statistic.recordOperation('dbConnCfg');
            $.utils.submit('/dbConnCfg/updateByPrimaryKeySelective', '#updateDbConnCfgForm', '#updateDbConnCfgDialog', '修改出错~', null, '#dbConnCfgTable', null, '修改成功~');
        }
    },
    deleteByPrimaryKey:function() {
    	$.statistic.recordOperation('dbConnCfg');
        $.utils.submit('/dbConnCfg/deleteByPrimaryKey', '#deleteDbConnCfgForm', '#deleteDbConnCfgDialog', '删除出错~', null, '#dbConnCfgTable', null, '删除成功~');
    },
    search:function(){
    	$.statistic.recordOperation('dbConnCfg');
        $("#dbConnCfgTable").bootstrapTable('removeAll');
        let connName = $("#searchForm input[name=connName]").val();
        //重新传递查询条件,也可以修改URL
        $("#dbConnCfgTable").bootstrapTable('refreshOptions',{
            url:getContextPath() + "/dbConnCfg/searchByConnName",
            queryParams:function(params){
                return {
                	offset: params.offset,
                    pageSize: params.limit,
                    "connName":'%'+connName+'%'
                }

            },
            responseHandler: function (response) {
            	return response;
            }
        });

    },
    batch:function () {
        //获取全部选择的数据
        var list = $('#dbConnCfgTable').bootstrapTable('getAllSelections');
        //进行Ajax交互处理数据
    }

};

/**
 * 所有额外处理格式化的方法
 * @type {{operation: $.dbConnCfgUtils.formatter.operation}}
 */
$.dbConnCfgUtils.formatter={
    operation:function (value, row) {
        var html =  "<div class='btn-toolbar' role='toolbar'>" +
                    "<button type='button' class='btn-link' onclick='$.dbConnCfgUtils.button.selectByPrimaryKey(\""
                            + row.dbId
                    +
                    "\")'>查看</button>|"+
                    "<button type='button' class='btn-link' onclick='$.dbConnCfgUtils.button.updateById(\""
                            +   row.dbId
                    +
                    "\")'>修改</button>|"+
                    "<button type='button' class='btn-link' onclick='$.dbConnCfgUtils.button.deleteByPrimaryKey(\""
                            +   row.dbId
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
    $.dbConnCfgUtils.init();
});


