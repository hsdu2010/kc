$.packageStatus={
		statusOpCode : {
			"10" : [{"code":"50","desc":"50 一票一件"},{"code":"43","desc":"43 收件入仓"}],
			"20" : [{"code":"30","desc":"30 装车"},{"code":"606","desc":"606 清关完成"}],
			"40" : [{"code":"44","desc":"44 派件出仓"},{"code":"204","desc":"204 快件交接"}],
			"50" : [{"code":"80","desc":"80 正常派件"},{"code":"648","desc":"648 新旧运单关联"}],
			"60" : [{"code":"33","desc":"33 滞留入仓"},{"code":"77","desc":"77 中转滞留"}],
			"80" : [{"code":"60","desc":"60 海关安检"},{"code":"604","desc":"604 CFS清关"}],
			"90" : [{"code":"33+8","desc":"33+8 快件已破损"},{"code":"33+8","desc":"33+8 快件已破损"}],
			"100" : [{"code":"","desc":"无需操作巴枪"},{"code":"","desc":"无需操作巴枪"}],
			"110" : [{"code":"33+79","desc":"33+79 滞留件作废操作"},{"code":"33+79","desc":"33+79 滞留件作废操作"}],
			"120" : [{"code":"","desc":"无需操作巴枪"},{"code":"","desc":"无需操作巴枪"}]
		},
		init : function(){
			$("input:radio[name=isSpecifyBar]").change(function(){
				if($(this).val() == "true"){//指定触发巴枪操作
					$("#barOperate").show();
				}else{
					$("#barOperate").hide();
				}
			});
			$("select[name=packageStatus]").change(function(){
				var type = $("select[name=packageStatus]").val();
				$("#opCode1").val($.packageStatus.statusOpCode[type][0]["code"]);
				$("#opCode2").val($.packageStatus.statusOpCode[type][1]["code"]);
				$("#barOpCode1").html($.packageStatus.statusOpCode[type][0]["desc"]);
				$("#barOpCode2").html($.packageStatus.statusOpCode[type][1]["desc"]);
			});
		},
		setOpCode : function(type){
			$("#opCode1").val($.packageStatus.statusOpCode[type][0]["code"]);
			$("#opCode2").val($.packageStatus.statusOpCode[type][1]["code"]);
			$("#barOpCode1").html($.packageStatus.statusOpCode[type][0]["desc"]);
			$("#barOpCode2").html($.packageStatus.statusOpCode[type][1]["desc"]);
		},
		generateCode : function(prefix){
			var pre = prefix ? prefix : "";
        	pre = pre.length>=12 ? pre.substring(0,11) : pre;
        	if(pre.length==0){
        		for(var i=0;i<3;i++){
        			pre += Math.floor(Math.random()*10)+"";
        		}
        	}
        	var randLen = 11-pre.length;
        	for(var i=0;i<randLen;i++){
        		pre += Math.floor(Math.random()*10)+"";
        	}
        	var sum = 0;
        	for (var i = 3,j = 15; i < pre.length; i++) {
        		var tmp = pre.substr(i,1) * j;
        		sum += Math.floor(tmp / 10) + tmp % 10;
        		j -= 2;
        	}
        	var cb = (9 * sum) % 10;
        	var waybillno = pre+""+cb;
        	return waybillno;
		},
		generatePakNo : function(){
			var pakNo = '';
			for(var i = 0; i < 3; i++){
				pakNo = pakNo + '' + parseInt(Math.random()*9 + 1);
			}
			$("input[name=packageNo]").val($.packageStatus.generateCode(pakNo));
		},
		checkParam : function(packageNo, zoneCode, operateEmpCode){
			if(!packageNo){
				$.utils.alertWarning("包裹号不能为空");
				return false;
			}
			if(!zoneCode){
				$.utils.alertWarning("操作网点不能为空");
				return false;
			}
			if(!operateEmpCode){
				$.utils.alertWarning("操作人工号不能为空");
				return false;
			}
			return true;
		},
		generateOrderNo : function(){
			var orderNo = '';
			for(var i = 0; i < 26; i++){
				orderNo += Math.floor(Math.random()*10);
			}
			return orderNo;
		},
		sendPackageStatus : function(){
			$.statistic.recordOperation('packageStatus');
			var packageNo = $("input[name=packageNo]").val();
			var zoneCode = $("input[name=zoneCode]").val();
			var packageStatus = $("select[name=packageStatus]").val();
			var opCode = $("input[name=opCode]").val();
			var orderNo = $.packageStatus.generateOrderNo();
			var operateEmpCode = $("input[name=opCode]").val();
			if(!$.packageStatus.checkParam(packageNo, zoneCode, operateEmpCode)){
				return false;
			}
			$.ajax({
				url : getContextPath() + "/pkgStatus/sendPkgStatus",
				type : "POST",
				data : {
					"packageNo" : packageNo,
					"zoneCode" : zoneCode,
					"packageStatus" : packageStatus,
					"opCode" : opCode,
					"orderNo" : orderNo,
					"operateEmpCode" : operateEmpCode
				},
				success : function(data){
					if(data.success){
						$.utils.alertSuccess("发送成功");
					}else{
						$.utils.alertError("发送失败");
					}
				},
				error : function(){
					$.utils.alertError("Ajax请求异常")
				}
			})
		},
}
$(document).ready(function(){
	$.packageStatus.init();
});