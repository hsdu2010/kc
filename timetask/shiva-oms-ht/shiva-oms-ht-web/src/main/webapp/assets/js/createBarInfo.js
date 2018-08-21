var defaultAllJson = "{\"autoloading\": \"1\",\"barOprCode\": \"000121\",\"barRecordId\": \"AAABX3wZ88x2q91K8ulFaZovknzSIwAY\",\"barScanDt\": \"2017-11-02 17:39:56\",\"barScanTm\": \"2017-11-02 17:39:56\",\"barScanTmStd\": \"2017-11-02 17:39:56\",\"barUploadTm\": \"2017-11-02 17:39:56\",\"barUploadTypeCode\": 0,\"contnrCode\": \"755A010A9999\",\"feeAmt\": 5.5,\"opCode\": \"50\",\"originalFlag\": false,\"phoneZone\": \"755\",\"source\": 0,\"subbillPieceQty\": 0,\"waybillNo\": \"666777888999\",\"weightQty\": 5.6,\"zoneCode\": \"755A\"}";

$.barCreate = {
	init : function() {
		$.barCreate.initValid();
		$.barCreate.queryList();
		$("#allJson").val(defaultAllJson);
		$.barCreate.initExtendAttachList();
	},
	initValid : function() {
		$('#simpleCreateForm').validate({
			rules : {
				waybillNo : "required",
				sourceDeptCode : "required",
				opCode : "required",
				barScanTm : "required",
			},
			messages : {
				waybillNo : "请输入运单号!",
				sourceDeptCode : "请输入寄件网点!",
				opCode : "请输入巴枪码!",
				barScanTm : "请输入巴枪扫描时间",
			},
			errorClass : "text-danger",
			errorElement : "span",
			highlight : function(element, errorClass, validClass) {
				$(element).parent().addClass('has-error');
			},
			unhighlight : function(element, errorClass, validClass) {
				$(element).parent().removeClass('has-error');
			}

		});

		$('#allJsonCreateForm').validate({
			rules : {
				allJson : "required",
			},
			messages : {
				allJson : "请输入报文!",
			},
			errorClass : "text-danger",
			errorElement : "span",
			highlight : function(element, errorClass, validClass) {
				$(element).parent().addClass('has-error');
			},
			unhighlight : function(element, errorClass, validClass) {
				$(element).parent().removeClass('has-error');
			}

		});
	},
	queryList : function() {
		$("#barTemplateTable").bootstrapTable('destroy');
		$('#barTemplateTable').bootstrapTable({
			url : getContextPath() + "/jsonTemplate/findAllTemplate",
			queryParams : function(params) {
				return {
					offset : params.offset,
					pageSize : params.limit,
					templateType : "fvpBar"
				}
			},
			responseHandler : function(response) {
				return response;
			},
			uniqueId : "id"
		});
	},
	generateCode : function(prefix) {
		var pre = prefix ? prefix : "";
		pre = pre.length >= 12 ? pre.substring(0, 11) : pre;
		if (pre.length == 0) {
			for (let i = 0; i < 3; i++) {
				pre += Math.floor(Math.random() * 10) + "";
			}
		}

		var randLen = 11 - pre.length;
		for (let i = 0; i < randLen; i++) {
			pre += Math.floor(Math.random() * 10) + "";
		}
		var sum = 0;
		for (let i = 3, j = 15; i < pre.length; i++) {
			var tmp = pre.substr(i, 1) * j;
			sum += Math.floor(tmp / 10) + tmp % 10;
			j -= 2;
		}
		var cb = (9 * sum) % 10;
		var waybillno = pre + "" + cb;
		return waybillno;
	},
	generateWaybillNo : function() {
		let waybillNo = '';
		for (let i = 0; i < 3; i++) {
			waybillNo = waybillNo + '' + parseInt(Math.random() * 9 + 1);
		}
		waybillNo = $.barCreate.generateCode(waybillNo);
		$("input[name=waybillNo]").val(waybillNo);
		JsBarcode("#barCode", waybillNo, {
			format : "CODE128",
			width : 2,
			height : 60
		});
	},
	generateBarCodeByNo : function() {
		let waybillNo = $("input[name=waybillNo]").val();
		if (waybillNo) {
			JsBarcode("#barCode", waybillNo, {
				format : "CODE128",
				width : 2,
				height : 60
			});
		}
	},
	generateContnrCode : function() {
		$("input[name=contnrCode]").val($.barCreate.generateCode('333'));
	},
	initExtendAttachList : function(){
		for(let i = 1; i <= 50; i++){
			$("#extendAttachCell select").append('<option value="extendAttach'+i+'">扩展字段'+i+'</option>');
		}
		
	}
};

$.barCreate.button = {
	templateExtendAttachGroup : {

	},
	simpleCreate : function() {
	},
	allJsonCreate : function() {
	},
	templateSelect : function() {// 模板管理控制
		$.barCreate.queryList();
		$('#templateSelectDialog').modal('show');
	},
	templateAdd : function() {// 新增模板
		$('#templateAddDialog').modal('show');
	},
	templateShow : function(id) {// 查看模板
		$.ajax({
			url : getContextPath() + "/jsonTemplate/findTemplateById",
			data : {
				id : id
			},
			success : function(response) {
				var data = $.utils.objToJson(response);

				$('#showTemplateForm').fill(JSON.parse(data));

				$('#templateShowDialog').modal('show');

			}
		});
	},
	quickFillBarOper : function(opCode) {
		$("input[name=opCode]").val(opCode);
	},
	cloneExtendAttachGroup : function() {
		if ($(".extend-attach-group").length < 8) {
			$("#extendAttachCell").append(
					$.barCreate.button.templateExtendAttachGroup[0].outerHTML);
		}
	},
	removeExtendAttachGroup : function(obj) {
		if ($(".extend-attach-group").length > 1) {
			$(obj).parent().remove();
		}
	},
	templateUpdate : function(id) {// 更新模板
		$.ajax({
			url : getContextPath() + "/jsonTemplate/findTemplateById",
			data : {
				id : id
			},
			success : function(response) {
				var data = $.utils.objToJson(response);

				$('#updateTemplateForm').fill(JSON.parse(data));

				$('#templateUpdateDialog').modal('show');

			}
		});
	},
	templateUpdateDelete : function(id) {// 删除模板
		$("#idForDel").val(id);
		$('#deleteTemplateDialog').modal('show');
	},
	templateChoose : function(id) {// 选择模板
		$('#templateSelectDialog').modal('hide');
		var data = $('#barTemplateTable')
				.bootstrapTable('getRowByUniqueId', id);
		$("#allJson").val(data.templateContent);
	},
	close : function(val) {
		$("#" + val).modal('hide');
	}
};

$.barCreate.submit = {
	simpleCreate : function() {
		$.statistic.recordOperation('createBarInfo');
		if ($('#simpleCreateForm').valid() && checkExtendParam()) {
			$.ajax({
				url : getContextPath() + "/barCreate/simpleCreate",
				type : "post",
				data : $('#simpleCreateForm').serialize(),// 你的formid
				success : function(data) {
					if (data.success) {
						$.utils.alertSuccess(data.errorMesssage);
					} else {
						$.utils.alertError(data.errorMessage);
					}
				},
				error : function(e) {
					$.utils.alertError("系统异常，请稍后再试！");
				}
			});
		} else {
			return false;
		}
	},
	simpleCreateBar : function() {
		$.statistic.recordOperation('createBarInfo');
		if ($("#simpleCreateForm").valid()) {
			var param = {};
			var flag = false;
			param["waybillNo"] = $("input[name=waybillNo]").val();
			param["opCode"] = $("input[name=opCode]").val();
			param["zoneCode"] = $("input[name=sourceDeptCode]").val();
			param["contnrCode"] = $("input[name=contnrCode]").val();
			param["opAttachInfo"] = $("input[name=opAttachInfo]").val();
			param["stayWhyCode"] = $("input[name=stayWhyCode]").val();
			param["phoneZone"] = $("input[name=phoneZone]").val();
			param["courierCode"] = $("input[name=courierCode]").val();
			param["barScanTm"] = $("input[name=barScanTm]").val();
			param["barOprCode"] = $("input[name=barOprCode]").val();
			param["objTypeCode"] = $("input[name=objTypeCode]").val();
			param["barSn"] = $("input[name=barSn]").val();
			$(".extend-attach-group select").each(function() {
				if (!param[$(this).val()]) {
					if ($(this).val()) {
						var extendAttachValue = $(this).next().val();
						if (extendAttachValue) {// 扩展字段值不为空
							param[$(this).val()] = extendAttachValue;
						} else {
							flag = true;
							$.utils.alertWarning("扩展字段值不能为空");
							return false;
						}
					}
				} else {
					flag = true;
					$.utils.alertWarning("不能出现重复的扩展字段");
					return false;
				}
			});
			if (flag) {
				return;
			}
			$.ajax({
				url : getContextPath() + "/barCreate/simpleCreate",
				type : "post",
				data : param,
				success : function(data) {
					if (data.success) {
						$.utils.alertSuccess(data.errorMessage);
					} else {
						$.utils.alertError(data.errorMessage);
					}
				},
				error : function(e) {
					$.utils.alertError("系统异常，请稍后再试！");
				}
			});
			$("#barDatetimepicker").val(
					$.utils.dateFormat("yyyy-MM-dd hh:mm:ss", new Date()))
					.datetimepicker({
						format : 'yyyy-mm-dd hh:ii:ss',
						autoclose : true,
						language : "zh-CN"
					});
		}
	},
	allJsonCreate : function() {
		if ($('#allJsonCreateForm').valid()) {
			$.ajax({
				url : getContextPath() + "/barCreate/allJsonCreate",
				type : "post",
				data : $('#allJsonCreateForm').serialize(),
				success : function(data) {
					if (data.indexOf("生成成功") >= 0) {
						$.utils.alertSuccess(data);
					} else {
						$.utils.alertError(data);
					}
				},
				error : function(e) {
					$.utils.alertError("系统异常，请稍后再试！");
				}
			});
		}
	},
	addTemplate : function() {
		if ($('#addTemplateForm').valid()) {
			$.utils.submit('/jsonTemplate/insertTemplate', '#addTemplateForm',
					'#templateAddDialog', '新增出错~', null, '#barTemplateTable',
					null, '新增成功~');
		}
	},
	updateTemplate : function() {
		if ($('#updateTemplateForm').valid()) {
			$.utils.submit('/jsonTemplate/updateTemplate',
					'#updateTemplateForm', '#templateUpdateDialog', '修改出错~',
					null, '#barTemplateTable', null, '修改成功~');
		}
	},
	deleteByPrimaryKey : function() {
		$.utils.submit('/jsonTemplate/deleteTemplate', '#deleteTemplateForm',
				'#deleteTemplateDialog', '删除出错~', null, '#barTemplateTable',
				null, '删除成功~');
	}
};

var extend1Param;
var extend2Param;
var extend3Param;
function setExtend1Param() {
	extend1Param = $("#extendSelect1").val();
	if (checkExtendParam()) {
		$('input[name="extend1"]').attr('name', extend1Param);
	}
}
function setExtend2Param() {
	extend2Param = $("#extendSelect2").val();
	if (checkExtendParam()) {
		$('input[name="extend2"]').attr('name', extend2Param);
	}
}
function setExtend3Param() {
	extend3Param = $("#extendSelect3").val();
	if (checkExtendParam()) {
		$('input[name="extend3"]').attr('name', extend3Param);
	}
}

$.barCreate.formatter = {
	operation : function(value, row) {
		var html = "<div class='btn-toolbar' role='toolbar'>"
				+ "<button type='button' class='btn-link' onclick='$.barCreate.button.templateShow(\""
				+ row.id
				+ "\")'>查看</button>|"
				+ "<button type='button' class='btn-link' onclick='$.barCreate.button.templateUpdate(\""
				+ row.id
				+ "\")'>修改</button>|"
				+ "<button type='button' class='btn-link' onclick='$.barCreate.button.templateUpdateDelete(\""
				+ row.id
				+ "\")'>删除</button>|"
				+ "<button type='button' class='btn-link' onclick='$.barCreate.button.templateChoose(\""
				+ row.id + "\")'>选择</button>"
		"</div>";
		return html;
	}
};

/**
 * 页面渲染结束后自动执行的类
 */
$(document)
		.ready(
				function() {
					$.barCreate.init();
					$.barCreate.button.templateExtendAttachGroup = $(".extend-attach-group");
					$("#barDatetimepicker").val(
							$.utils.dateFormat("yyyy-MM-dd hh:mm:ss",
									new Date())).datetimepicker({
						format : 'yyyy-mm-dd hh:ii:ss',
						autoclose : true,
						language : "zh-CN"
					})
				});
