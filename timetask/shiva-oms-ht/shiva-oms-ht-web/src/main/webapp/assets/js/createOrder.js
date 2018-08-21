/**
 * 全量报文默认内容
 * @type {String}
 */
var defaultAllJson = "{\"sysSource\":\"UCMP\",\"operateType\":\"1\",\"clientCode\":\"8774044182\",\"pickupType\":\"1\",\"clientOrderNo\":\"\",\"sysOrderNo\":\"TEST987789699757\",\"orderType\":\"9\",\"orderTime\":\"2017-06-22 19:32:04\",\"pickupAppointTime\":\"2017-09-19 08:50:04\",\"senderCompany\":\"Apple APAC\",\"senderTel\":\"13692236062\",\"senderName\":\"毛珍\",\"senderCityCode\":\"510\",\"senderProvince\":\"深圳市\",\"senderCityName\":\"深圳市\",\"senderArea\":\"深圳市\",\"senderAddr\":\" 上海市浦东新区陆家嘴东路\",\"isGenBillNo\":\"0\",\"isNeedScreen\":\"0\",\"isUnderCall\":\"1\",\"isNeedArtifiScreen\":\"0\",\"monthlyCard\":\"0103443483\",\"addtionInfoList\":[{\"attrName\":\"attr001\",\"attrVal\":\"000009726\"},{\"attrName\":\"attr002\",\"attrVal\":\"8774044181\"},{\"attrName\":\"attr003\",\"attrVal\":\"W502831721\"},{\"attrName\":\"attr004\",\"attrVal\":\"LP01\"},{\"attrName\":\"attr005\",\"attrVal\":\"PEK\"},{\"attrName\":\"attr006\",\"attrVal\":\"PEK\"},{\"attrName\":\"attr007\",\"attrVal\":\"20160602\"},{\"attrName\":\"attr008\",\"attrVal\":\"20160602\"},{\"attrName\":\"attr009\",\"attrVal\":\"CN\"},{\"attrName\":\"attr010\",\"attrVal\":\"北京\"},{\"attrName\":\"attr011\",\"attrVal\":\"北京\"},{\"attrName\":\"attr012\",\"attrVal\":\"海淀区\"},{\"attrName\":\"attr013\",\"attrVal\":\"CN\"},{\"attrName\":\"attr014\",\"attrVal\":\"北京\"},{\"attrName\":\"attr015\",\"attrVal\":\"北京\"},{\"attrName\":\"attr016\",\"attrVal\":\"顺义区\"},{\"attrName\":\"attr017\",\"attrVal\":\"010AEE\"},{\"attrName\":\"attr018\",\"attrVal\":\"KG\"},{\"attrName\":\"attr019\",\"attrVal\":\"CR\"},{\"attrName\":\"attr020\",\"attrVal\":\"1\"},{\"attrName\":\"attr021\",\"attrVal\":\"8774044181>:000010>:ML6J2CH/A>\"},{\"attrName\":\"attr022\",\"attrVal\":\"101312\"},{\"attrName\":\"attr023\",\"attrVal\":\"100096\"},{\"attrName\":\"attr024\",\"attrVal\":\"S1\"},{\"attrName\":\"attr025\",\"attrVal\":\"HUB\"}],\"receiverInfoList\":[{\"receiverTel\":\"13436600616\",\"receiverCompany\":\"颜 辉\",\"receiverSerial\":\"6\",\"receiverMobile\":\"13436600616\",\"receiverContact\":\"颜 辉\",\"receiverProvince\":\"深圳市\",\"receiverArea\":\"深圳市\",\"receiverAddr\":\"广东省深圳市龙岗区龙城街道龙岗大道金融大厦\",\"parcelTotalWeight\":0.68,\"parcelWeightUnit\":\"KG\",\"parcelTotalVolume\":1.92E-4,\"parcelVolumeUnit\":\"CR\",\"isSignBack\":\"0\",\"isSelfPick\":\"0\",\"parcelNum\":\"\",\"receiverCityCode\":\"755\",\"waybillNo\":\"442247954353\",\"paymentType\":\"3\",\"totalLength\":0.0,\"totalWidth\":0.0,\"totalHeight\":0.0,\"addserviceinfoList\":[{\"serviceCode\":\"IN01\",\"value\":\"6543.00\",\"value2\":\"CODPOS\"}]}]}";

$.orderCreate={
		init:function(){ 
            $.orderCreate.initValid();
            $.orderCreate.queryList(); 
            $.orderCreate.formatter.setAppointTime('appointTime'); 
            $("#allJson").val(defaultAllJson);
		},
        initValid:function(){ 
            $('#simpleCreateForm').validate({
                rules:{
                    sysOrderNo : "required",
                    paymentTypeCode : "required",
                    senderAddr : "required",
                    senderCityCode : "required"
                },
                messages:{
                        sysOrderNo : "请输入订单号!",
                        paymentTypeCode : "请输入支付方式!",
                        senderAddr : "请输入寄方地址!",
                        senderCityCode : "请输入寄方城市代码!"

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

            $('#allJsonCreateForm').validate({
                rules:{
                    allJson : "required"
                },
                messages:{
                        allJson : "请输入报文内容!"

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
        queryList:function(){ 
            $("#templateSelectTable").bootstrapTable('destroy'); 
            $('#templateSelectTable').bootstrapTable({
                url: getContextPath() + "/jsonTemplate/findAllTemplate",
                queryParams:function(params){
                    return{
                        offset: params.offset,
                        pageSize: params.limit,
                        templateType:$("#templateType").val()
                        //对象查询相关可以从此处传递值,具体实现需自行编码
                        //例如: id:id
                    }
                },
                responseHandler: function (response) {
                    return response;
                }
            });
        }
};

/**
 * 所有非后台交互的按钮产生的动作方法
 * @type {{deleteById: $.orderCreate.button.deleteById, updateById: $.orderCreate.button.updateById, deleteBatch: $.orderCreate.button.deleteBatch}}
 */
$.orderCreate.button={
    simpleCreate:function(){
    },
    allJsonCreate:function(){   
    },
    templateSelect:function(){//模板管理控制
        //重新查询
        $.orderCreate.queryList();
        $('#templateSelectDialog').modal('show'); 
    },
    templateAdd:function(){//新增模板
        //清空内容
        $("#templateName").val("");
        $("#templateContent").val("");
        $('#templateAddDialog').modal('show');
    },
    templateShow:function(id){//查看模板
        $.ajax({
            url: getContextPath() + "/jsonTemplate/findTemplateById",
            data: {id:id},
            success: function (response) {
                    var data= $.utils.objToJson(response); 
                    $('#showTemplateForm').fill(JSON.parse(data));
                    $('#templateShowDialog').modal('show');

            }
        });
    },
    templateUpdate:function(id){//更新模板
        $.ajax({
            url: getContextPath() + "/jsonTemplate/findTemplateById",
            data: {id:id},
            success: function (response) {
                    var data= $.utils.objToJson(response); 
                    $('#updateTemplateForm').fill(JSON.parse(data));
                    $('#templateUpdateDialog').modal('show');
            }
        });
    },
    templateDelete:function(id) {//删除模板
        $("#idForDel").val(id);
        $('#deleteTemplateDialog').modal('show');
    },
    templateChoose:function(val){//选择模板
        $('#templateSelectDialog').modal('hide');
        $("#allJson").val(val);
    },
    close:function(val){
       $("#"+val).modal('hide');
    }
};

/**
 * 所有直接通过操作与后台进行交互的方法
 * @type {{}}
 */
$.orderCreate.submit={
    simpleCreate:function(){
    	$.statistic.recordOperation('createOrder');
        if ($('#simpleCreateForm').valid()) {
            $.ajax({
            url:getContextPath() + "/orderCreate/simpleCreate",
            type:"post",
            data:$('#simpleCreateForm').serialize(),// 你的formid
            success:function(data){  
                var jsonData = JSON.parse(data);
                var result = jsonData[0].result;
                var msg = jsonData[0].msg;
                if(result){
                    $.utils.alertSuccess(msg);
                }else{
                    $.utils.alertError(msg);
                }
            },
            error:function(e){
                $.utils.alertError("系统异常，请稍后再试！");
            }
        }); 
        } 
    },
    allJsonCreate:function(){
    	$.statistic.recordOperation('createOrder');
        if ($('#allJsonCreateForm').valid()) {
            $.ajax({
            url:getContextPath() + "/orderCreate/allJsonCreate",
            type:"post",
            data:$('#allJsonCreateForm').serialize(),// 你的formid
            success:function(data){ 
               var jsonData = JSON.parse(data);
                var result = jsonData[0].result;
                var msg = jsonData[0].msg;
                if(result){
                    $.utils.alertSuccess(msg);
                }else{
                    $.utils.alertError(msg);
                }
            },
            error:function(e){ 
                $.utils.alertError("系统异常，请稍后再试！");
            }
        });
        } 
    },
    addTemplate:function(){
        if($('#addTemplateForm').valid()){
            $.utils.submit('/jsonTemplate/insertTemplate', '#addTemplateForm', '#templateAddDialog', '新增出错~', null, '#templateSelectTable', null, '新增成功~');
        }
    },
    updateTemplate:function(){
        if($('#updateTemplateForm').valid()){
            $.utils.submit('/jsonTemplate/updateTemplate', '#updateTemplateForm', '#templateUpdateDialog', '修改出错~', null, '#templateSelectTable', null, '修改成功~');
        }
    },
    deleteTemplate:function() {
        $.utils.submit('/jsonTemplate/deleteTemplate', '#deleteSqlCfgForm', '#deleteTemplateDialog', '删除出错~', null, '#templateSelectTable', null, '删除成功~');
    }
};


$.orderCreate.formatter={
    operation:function (value, row) {
        var html =  "<div class='btn-toolbar' role='toolbar'>" +
                    "<button type='button' class='btn-link' onclick='$.orderCreate.button.templateShow(\""
                            + row.id
                    +
                    "\")'>查看</button>|"+
                    "<button type='button' class='btn-link' onclick='$.orderCreate.button.templateUpdate(\""
                            +   row.id
                    +
                    "\")'>修改</button>|"+
                    "<button type='button' class='btn-link' onclick='$.orderCreate.button.templateDelete(\""
                            +   row.id
                    +
                    "\")'>删除</button>|"+
                    "<button type='button' class='btn-link' onclick='$.orderCreate.button.templateChoose(\""
                            +   $.utils.replace(row.templateContent)
                    +
                    "\")'>选择</button>"
                    "</div>";
        return  html;
    },
    setAppointTime:function(id){
        var mydate = new Date();
        var year = mydate.getFullYear();//获取年份
        var month = $.orderCreate.formatter.fillZero(mydate.getMonth()+1);//获取月份
        var date = $.orderCreate.formatter.fillZero(mydate.getDate()+1);//获取日期
        var hour = $.orderCreate.formatter.fillZero(mydate.getHours() + 3);//获取当前小时并在此小时内加3小时
        var minute = $.orderCreate.formatter.fillZero(mydate.getMinutes());//获取分钟
        var second = $.orderCreate.formatter.fillZero(mydate.getSeconds());//获取秒数
        $("#"+id).val(year+"-"+month+"-"+date + " " + hour+":"+minute+":"+second);
    },
    fillZero:function(val){
        if (parseInt(val) < 10 ){
            return "0" + val;
        }else{
            return val;
        }
    }
};

/**
 * 页面渲染结束后自动执行的类
 */
$(document).ready(function() {
   $.orderCreate.init();
});


