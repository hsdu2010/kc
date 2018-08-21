$.orderState = {
	init : function() {
		$.orderState.initValid();
	},
	initValid : function() {
		$('#orderStateForm').validate({
			rules : {
				stateCode : "required",
				sysOrderNo : "required",
				omsOrderNo : "required"
			},
			messages : {
				stateCode : "请选择要生成的状态!",
				sysOrderNo : "请输入来源系统订单号!",
				omsOrderNo : "请输入OMS订单号!"
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
	}
};

$.orderState.submit = {

	create : function() {
		$.statistic.recordOperation('orderState');
		if ($('#orderStateForm').valid() && checkParam()) {
			$.ajax({
				url : getContextPath() + "/orderState/createState",
				type : "post",
				data : $('#orderStateForm').serialize(),// 你的formid
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
		} else {
			return false;
		}
	}
};

function checkParam() {
	if ("40003" == $("#stateCode").val()) {
		if (null == $('input[name="waybillNo"]').val()
				|| '' == $('input[name="waybillNo"]').val()) {
			$.utils.alertWarning("请输入运单号！")
			return false;
		}
	}
	return true;
}

/**
 * 页面渲染结束后自动执行的类
 */
$(document).ready(function() {
	$.orderState.init();
});
