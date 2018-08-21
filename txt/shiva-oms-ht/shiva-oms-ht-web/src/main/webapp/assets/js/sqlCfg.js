

/**
 * 所有其他类型的方法
 * @type {{init: $.sqlCfgUtils.init, queryList: $.sqlCfgUtils.queryList}}
 */
$.sqlCfgUtils={
		init:function(){
            $.sqlCfgUtils.queryList();
            $.sqlCfgUtils.initValid();
            $.sqlCfgUtils.loadDbInfo();
		},
		queryList:function(){
			var sqlName = $("#searchForm input[name=sqlName]").val();
            $('#sqlCfgTable').bootstrapTable({
                url: getContextPath() + "/sqlCfg/searchBySqlName",
                queryParams:function(params){
                    return{
                        offset: params.offset,
                        pageSize: params.limit,
                        "sqlName": '%'+sqlName+'%'
                        //对象查询相关可以从此处传递值,具体实现需自行编码
                        //例如: id:id

                    }
                },
                responseHandler: function (response) {
                    return response;
                }
            });
        },
        loadDbInfo:function(){
        	var optionTemplate;
        	$.ajax({
        		type:'POST',
        		url:getContextPath() + "/dbConnCfg/selectByExample",
        		dataType:'json',
        		async:false,
        		success:function(data){
        			for(var i = 0; i < data.rows.length; i++){
        				optionTemplate = "<option value='" + data.rows[i].dbId + "'>" + data.rows[i].connName + "</option>";
        				$("#addSqlCfgForm select[name=dbId]").append(optionTemplate);
        				$("#updateSqlCfgForm select[name=dbId]").append(optionTemplate);
        			}
        		}
        		
        	});
        },
        initValid:function(){

            $('#addSqlCfgForm').validate({
                rules:{


                            dbId : "required",

                            sqlStatement : "required",

                            sqlName : "required",







                },
                messages:{
                        dbId : "请输入数据库id!",
                        sqlStatement : "请输入sql语句!",
                        sqlName : "请输入查询名!",

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



            $('#updateSqlCfgForm').validate({
            rules:{

                    dbId : "required",

                    sqlStatement : "required",

                    sqlName : "required",







            },
            messages:{
                    dbId : "请输入数据库id!",
                    sqlStatement : "请输入sql语句!",
                    sqlName : "请输入查询名!",

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
 * @type {{deleteById: $.sqlCfgUtils.button.deleteById, updateById: $.sqlCfgUtils.button.updateById, deleteBatch: $.sqlCfgUtils.button.deleteBatch}}
 */
$.sqlCfgUtils.button={
    add:function(){

        $('#addSqlCfgForm div').removeClass('has-error');

        $('#addSqlCfgForm').validate().resetForm();

        $('#addSqlCfgForm')[0].reset();
        
        $('#addSqlCfgForm input[name=createEmp]').val($.utils.username);
        $('#addSqlCfgForm input[name=modifyEmp]').val($.utils.username);

        $('#addSqlCfgDialog').modal('show');
    },
    deleteByPrimaryKey:function(
            sqlId
    ){

        $("#sqlIdForDel").val(sqlId);

        $('#deleteSqlCfgDialog').modal('show');

    },
    updateById:function(
            sqlId
    ){
        $.ajax({
            url: getContextPath() + "/sqlCfg/selectByPrimaryKey",
            data: {
                        sqlId:sqlId
                    },
            success: function (response) {
                //if (response.code != '000') {
                    //如果返回Json中存在标识码等,可以通过下面的方法显示错误信息,
                //    $.utils.alertError(response.message);
              //  } else {
                    var data= $.utils.objToJson(response);

                    $('#updateSqlCfgForm div').removeClass('has-error');

                    $('#updateSqlCfgForm').validate().resetForm();

                    $('#updateSqlCfgForm')[0].reset();

                    $('#updateSqlCfgForm').fill(JSON.parse(data));
                    
                    $('#updateSqlCfgForm input[name=modifyEmp]').val($.utils.username);

                    $('#updateSqlCfgDialog').modal('show');
                //}
            }
        });
    },
    operationBatch:function () {
        //获取选中的所有数据
        var list = $('#sqlCfgTable').bootstrapTable('getAllSelections');

        if(list == null || list.length == 0){
            $.utils.alertWarning("请选择至少一个数据!");
            return;
        }

        $("#batchSqlCfgDialog").modal("show");

    },
    selectByPrimaryKey:function(
            sqlId
    ){
        $.ajax({
            url: getContextPath() + "/sqlCfg/selectByPrimaryKey",
            data: {
                        sqlId:sqlId
                    },
            success: function (response) {
                    var data= $.utils.objToJson(response);

                    $('#showSqlCfgForm')[0].reset();

                    $('#showSqlCfgForm').fill(JSON.parse(data));

                    $('#showSqlCfgDialog').modal('show');

            }
        });
    }

};

/**
 * 所有直接通过操作与后台进行交互的方法
 * @type {{addSqlCfg: $.sqlCfgUtils.submit.addSqlCfg, updateABC: $.sqlCfgUtils.submit.updateSqlCfg, deleteById: $.sqlCfgUtils.submit.deleteById, search: $.sqlCfgUtils.submit.search, batch: $.sqlCfgUtils.submit.batch}}
 */
$.sqlCfgUtils.submit={
    addSqlCfg:function(){
    	$.statistic.recordOperation('sqlCfg');
        if($('#addSqlCfgForm').valid()){
            $.utils.submit('/sqlCfg/insert', '#addSqlCfgForm', '#addSqlCfgDialog', '新增出错~', null, '#sqlCfgTable', null, '新增成功~');
        }
    },
    updateSqlCfg:function(){
    	$.statistic.recordOperation('sqlCfg');
        if($('#updateSqlCfgForm').valid()){
            $.utils.submit('/sqlCfg/updateByPrimaryKeySelective', '#updateSqlCfgForm', '#updateSqlCfgDialog', '修改出错~', null, '#sqlCfgTable', null, '修改成功~');
        }
    },
    deleteByPrimaryKey:function() {
    	$.statistic.recordOperation('sqlCfg');
        $.utils.submit('/sqlCfg/deleteByPrimaryKey', '#deleteSqlCfgForm', '#deleteSqlCfgDialog', '删除出错~', null, '#sqlCfgTable', null, '删除成功~');
    },
    search:function(){
    	$.statistic.recordOperation('sqlCfg');
    	var sqlName = $("#searchForm input[name=sqlName]").val();
        $("#sqlCfgTable").bootstrapTable('removeAll');

        //重新传递查询条件,也可以修改URL
        $("#sqlCfgTable").bootstrapTable('refreshOptions',{
            url:getContextPath() + "/sqlCfg/searchBySqlName",
            queryParams:function(params){
                return {
                	offset: params.offset,
                    pageSize: params.limit,
                    "sqlName":'%'+sqlName+'%'
                }
            },
            responseHandler: function (response) {
                return response;
            }
        });

    },
    batch:function () {
        //获取全部选择的数据
        var list = $('#sqlCfgTable').bootstrapTable('getAllSelections');
        //进行Ajax交互处理数据
    }

};

/**
 * 所有额外处理格式化的方法
 * @type {{operation: $.sqlCfgUtils.formatter.operation}}
 */
$.sqlCfgUtils.formatter={
    operation:function (value, row) {
        var html =  "<div class='btn-toolbar' role='toolbar'>" +
                    "<button type='button' class='btn-link' onclick='$.sqlCfgUtils.button.selectByPrimaryKey(\""
                            + row.sqlId
                    +
                    "\")'>查看</button>|"+
                    "<button type='button' class='btn-link' onclick='$.sqlCfgUtils.button.updateById(\""
                            +   row.sqlId
                    +
                    "\")'>修改</button>|"+
                    "<button type='button' class='btn-link' onclick='$.sqlCfgUtils.button.deleteByPrimaryKey(\""
                            +   row.sqlId
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
    $.sqlCfgUtils.init();
});

