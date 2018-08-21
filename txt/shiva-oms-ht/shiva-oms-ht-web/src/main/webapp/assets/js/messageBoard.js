$("#faceSwitch").click(function(){
	if($(this).hasClass("on")){
		$("#fatherImg").hide();
		$(this).removeClass("on");
	}else{
		$("#fatherImg").show();
		$(this).addClass("on");
		return false;
	}
});

$("#fatherImg").click(function(){
	return false;
});
$("body").click(function(){
	$("#fatherImg").hide();
	$("#faceSwitch").removeClass("on");
});

$("#fatherImg .lis").click(function(){
	var img = $(this).find("img").clone(true);
	$("#commentTextArea").append(img);
});

$.messageBoard={};
$.messageBoard.formatterFace = function(){
	return "<img src='../assets/img/timg.jpg' class='img-circle' alt='User Image' width='50'>";
}

$.messageBoard.formatterComment = function(message, row){
	return "<div class='commentWrrap'><div style='margin-bottom:5px;'>"+row.createEmp+"</div><div style='margin-bottom:20px;'>"+row.message+"</div><div>"+row.createtime+"</div></div>";
}

$.messageBoard.submit = function(){
	var commentHtml = $("#commentTextArea").html();
	if(commentHtml.trim() == ''){
		$.utils.alertWarning("请输入留言内容！");
		return ;
	}
	var request = {
		"message" : commentHtml
	};
    $.ajax({
        url: getContextPath()+ "/messageBoard/comment",
        data: request,
        type:"post",
        success: function (response) {
        	if(response){
        		$("#commentTextArea").html("");
        		$("#commentTable").bootstrapTable('refresh');
        	}else{
        		$.utils.alertError("提交留言失败");
        	}
        }
    });
}

$('#commentTable').bootstrapTable({
    url: getContextPath() + "/messageBoard/selectByExample",
    showHeader: false,
    queryParams:function(params){
        return {
            offset: params.offset,
            pageSize: params.limit
        }
    },
    responseHandler: function (response) {
    	$("#commentNum").text(response.totalRecord);
        return response;
    },
    formatNoMatches: function(){
        return "暂无留言数据";
    }
});

