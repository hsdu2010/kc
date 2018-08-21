$.barCodeCreate = {
	generateCode: function(prefix){
		var pre = prefix ? prefix : "";
        pre = pre.length>=12 ? pre.substring(0,11) : pre;
        if(pre.length==0){
            for(let i=0;i<3;i++){
                pre += Math.floor(Math.random()*10)+"";
            }
        }

        var randLen = 11-pre.length;
        for(let i=0;i<randLen;i++){
            pre += Math.floor(Math.random()*10)+"";
        }
        var sum = 0;
        for (let i = 3,j = 15; i < pre.length; i++) {
            var tmp = pre.substr(i,1) * j;
            sum += Math.floor(tmp / 10) + tmp % 10;
            j -= 2;
        }
        var cb = (9 * sum) % 10;
        var waybillno = pre+""+cb;
        return waybillno;
	},
	generatePackageNo: function(){
		var packageNo = '';
        for(let i = 0 ; i < 3; i++){
            packageNo += Math.floor(Math.random()*10);
        }
        return $.barCodeCreate.generateCode(packageNo);
	},
	generate: function(){
		$.statistic.recordOperation('createBarCode');
		let type = $("select[name=type]").val();
		let code = '';
		if(type == 1){
			code = $.barCodeCreate.generateCode('944');
		}else if(type == 2){
			code =  $.barCodeCreate.generateCode('945');
		}else{
			code = $.barCodeCreate.generatePackageNo();
		}
		$("input[name=code]").val(code);
		JsBarcode("#barCode", code, {
			format: "CODE128",
			width: 2,
			height: 60
		})
	},
	generateBarCodeByNo: function(){
		$.statistic.recordOperation('createBarCode');
		let waybillNo = $("input[name=code]").val();
    	if(waybillNo){
    		JsBarcode("#barCode", waybillNo,{
    			format: "CODE128",
    			width: 2,
    			height: 60
    		});
    	}
	}
}