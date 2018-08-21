$.statistic={
	recordOperation : function(moduleName){
		$.ajax({
			url : getContextPath() + '/statistic/recordOperation',
			type : 'POST',
			data : {
				moduleName : moduleName
			},
			success : function(resp){
				//记录成功
			}
		});
	}
}