

/**
 * 页面渲染结束后自动执行的类
 */
$(document).ready(function() {
    $.kafkaClusterCfgUtils.init();
});


/**
 * 所有其他类型的方法
 * @type {{init: $.kafkaClusterCfgUtils.init, queryList: $.kafkaClusterCfgUtils.queryList}}
 */
$.kafkaClusterCfgUtils={
		init:function(){
            $.kafkaClusterCfgUtils.queryList();
            $.kafkaClusterCfgUtils.initValid();
		},
		queryList:function(){

            $('#kafkaClusterCfgTable').bootstrapTable('refreshOptions',{
                url: getContextPath() + "/kafkaClusterCfg/selectByExample",
                queryParams:function(params){
                    return{
                        offset: params.offset,
                        pageSize: params.limit
                        //对象查询相关可以从此处传递值,具体实现需自行编码
                        //例如: id:id

                    }
                },
                responseHandler: function (response) {
                    return response;
                }
            });
        },
        initValid:function(){

            $('#addKafkaClusterCfgForm').validate({
                rules:{

                            id : "required",

                            clusterName : "required",

                            monitorUrl : "required",



                            name : "required",

                },
                messages:{
                        id : "请输入!",
                        clusterName : "请输入!",
                        monitorUrl : "请输入!",
                        name : "请输入!",

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



            $('#updateKafkaClusterCfgForm').validate({
            rules:{

                    clusterName : "required",

                    monitorUrl : "required",



                    name : "required",

            },
            messages:{
                    clusterName : "请输入!",
                    monitorUrl : "请输入!",
                    name : "请输入!",

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
 * @type {{deleteById: $.kafkaClusterCfgUtils.button.deleteById, updateById: $.kafkaClusterCfgUtils.button.updateById, deleteBatch: $.kafkaClusterCfgUtils.button.deleteBatch}}
 */
$.kafkaClusterCfgUtils.button={
    add:function(){

        $('#addKafkaClusterCfgForm div').removeClass('has-error');

        $('#addKafkaClusterCfgForm').validate().resetForm();

        $('#addKafkaClusterCfgForm')[0].reset();

        $('#addKafkaClusterCfgDialog').modal('show');
    },
    deleteByPrimaryKey:function(
            id
    ){

        $("#idForDel").val(id);

        $('#deleteKafkaClusterCfgDialog').modal('show');

    },
    updateById:function(
            id
    ){
        $.ajax({
            url: getContextPath() + "/kafkaClusterCfg/selectByPrimaryKey",
            data: {
                        id:id
                    },
            success: function (response) {
                //if (response.code != '000') {
                    //如果返回Json中存在标识码等,可以通过下面的方法显示错误信息,
                //    $.utils.alertError(response.message);
              //  } else {
                    var data= $.utils.objToJson(response);

                    $('#updateKafkaClusterCfgForm div').removeClass('has-error');

                    $('#updateKafkaClusterCfgForm').validate().resetForm();

                    $('#updateKafkaClusterCfgForm')[0].reset();

                    $('#updateKafkaClusterCfgForm').fill(JSON.parse(data));

                    $('#updateKafkaClusterCfgDialog').modal('show');
                //}
            }
        });
    },
    operationBatch:function () {
        //获取选中的所有数据
        var list = $('#kafkaClusterCfgTable').bootstrapTable('getAllSelections');

        if(list == null || list.length == 0){
            $.utils.alertWarning("请选择至少一个数据!");
            return;
        }

        $("#batchKafkaClusterCfgDialog").modal("show");

    },
    selectByPrimaryKey:function(
            id
    ){
        $.ajax({
            url: getContextPath() + "/kafkaClusterCfg/selectByPrimaryKey",
            data: {
                        id:id
                    },
            success: function (response) {
                    var data= $.utils.objToJson(response);

                    $('#showKafkaClusterCfgForm')[0].reset();

                    $('#showKafkaClusterCfgForm').fill(JSON.parse(data));

                    $('#showKafkaClusterCfgDialog').modal('show');

            }
        });
    }

};

/**
 * 所有直接通过操作与后台进行交互的方法
 * @type {{addKafkaClusterCfg: $.kafkaClusterCfgUtils.submit.addKafkaClusterCfg, updateABC: $.kafkaClusterCfgUtils.submit.updateKafkaClusterCfg, deleteById: $.kafkaClusterCfgUtils.submit.deleteById, search: $.kafkaClusterCfgUtils.submit.search, batch: $.kafkaClusterCfgUtils.submit.batch}}
 */
$.kafkaClusterCfgUtils.submit={
    addKafkaClusterCfg:function(){
        if($('#addKafkaClusterCfgForm').valid()){
            $.utils.submit('/kafkaClusterCfg/insert', '#addKafkaClusterCfgForm', '#addKafkaClusterCfgDialog', '新增出错~', null, '#kafkaClusterCfgTable', null, '新增成功~');
        }
    },
    updateKafkaClusterCfg:function(){
        if($('#updateKafkaClusterCfgForm').valid()){
            $.utils.submit('/kafkaClusterCfg/updateByPrimaryKeySelective', '#updateKafkaClusterCfgForm', '#updateKafkaClusterCfgDialog', '修改出错~', null, '#kafkaClusterCfgTable', null, '修改成功~');
        }
    },
    deleteByPrimaryKey:function() {
        $.utils.submit('/kafkaClusterCfg/deleteByPrimaryKey', '#deleteKafkaClusterCfgForm', '#deleteKafkaClusterCfgDialog', '修改出错~', null, '#kafkaClusterCfgTable', null, '修改成功~');
    },
    search:function(){

        $("#kafkaClusterCfgTable").bootstrapTable('removeAll');

        //重新传递查询条件,也可以修改URL
        $("#kafkaClusterCfgTable").bootstrapTable('refreshOptions',{
            url:getContextPath() + "/kafkaClusterCfg/selectByPrimaryKey",
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

    },
    batch:function () {
        //获取全部选择的数据
        var list = $('#kafkaClusterCfgTable').bootstrapTable('getAllSelections');
        //进行Ajax交互处理数据
    }

};

/**
 * 所有额外处理格式化的方法
 * @type {{operation: $.kafkaClusterCfgUtils.formatter.operation}}
 */
$.kafkaClusterCfgUtils.formatter={
    operation:function (value, row) {
        var html =  "<div class='btn-toolbar' role='toolbar'>" +
                    "<button type='button' class='btn btn-info' onclick='$.kafkaClusterCfgUtils.button.selectByPrimaryKey("
                            + row.id
                    +
                    ")'>查看</button>"+
                    "<button type='button' class='btn btn-primary' onclick='$.kafkaClusterCfgUtils.button.updateById("
                            +   row.id
                    +
                    ")'>修改</button>"+
                    "<button type='button' class='btn btn-danger' onclick='$.kafkaClusterCfgUtils.button.deleteByPrimaryKey("
                            +   row.id
                    +
                    ")'>删除</button>"+
                    "</div>";
        return  html;
    }
};


