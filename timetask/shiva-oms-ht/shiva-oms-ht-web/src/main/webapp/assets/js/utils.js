function getContextPath() {
	var pathName = document.location.pathname;

	var curWwwPath = window.document.location.href;
	var pos = curWwwPath.indexOf(pathName);

	var localhostPath = curWwwPath.substring(0, pos);

	var length = pathName.split("/");

	var result = '';
	if (length.length > 3) {
		if (length[1] != 'index') {
			result = '/' + length[1];
		}
	}
	return (localhostPath + result);
}

function getBasePath() {
	var pathName = document.location.pathname;

	var length = pathName.split("/");

	var result = '';
	if (length.length > 3) {
		if (length[1] != 'index') {
			result = '/' + length[1];
		}
	}
	return result;
}
//格式化代码函数,已经用原生方式写好了不需要改动,直接引用就好
String.prototype.removeLineEnd = function () {
    return this.replace(/(<.+?\s+?)(?:\n\s*?(.+?=".*?"))/g, '$1 $2')
};
function getPrefix(prefixIndex) {
    var span = '    ';
    var output = [];
    for (var i = 0; i < prefixIndex; ++i) {
        output.push(span);
    }

    return output.join('');
}
$.utils = {
	formatXml:function(text){
        //去掉多余的空格
        text = '\n' + text.replace(/(<\w+)(\s.*?>)/g, function ($0, name, props) {
                    return name + ' ' + props.replace(/\s+(\w+=)/g, " $1");
                }).replace(/>\s*?</g, ">\n<");

        //把注释编码
        text = text.replace(/\n/g, '\r').replace(/<!--(.+?)-->/g, function ($0, text) {
            var ret = '<!--' + escape(text) + '-->';
            //alert(ret);
            return ret;
        }).replace(/\r/g, '\n');

        //调整格式
        var rgx = /\n(<(([^\?]).+?)(?:\s|\s*?>|\s*?(\/)>)(?:.*?(?:(?:(\/)>)|(?:<(\/)\2>)))?)/mg;
        var nodeStack = [];
        var output = text.replace(rgx, function ($0, all, name, isBegin, isCloseFull1, isCloseFull2, isFull1, isFull2) {
            var isClosed = (isCloseFull1 == '/') || (isCloseFull2 == '/' ) || (isFull1 == '/') || (isFull2 == '/');
            //alert([all,isClosed].join('='));
            var prefix = '';
            if (isBegin == '!') {
                prefix = getPrefix(nodeStack.length);
            }
            else {
                if (isBegin != '/') {
                    prefix = getPrefix(nodeStack.length);
                    if (!isClosed) {
                        nodeStack.push(name);
                    }
                }
                else {
                    nodeStack.pop();
                    prefix = getPrefix(nodeStack.length);
                }

            }
            var ret = '\n' + prefix + all;
            return ret;
        });

        var prefixSpace = -1;
        var outputText = output.substring(1);
        //alert(outputText);

        //把注释还原并解码，调格式
        outputText = outputText.replace(/\n/g, '\r').replace(/(\s*)<!--(.+?)-->/g, function ($0, prefix, text) {
            //alert(['[',prefix,']=',prefix.length].join(''));
            if (prefix.charAt(0) == '\r')
                prefix = prefix.substring(1);
            text = unescape(text).replace(/\r/g, '\n');
            var ret = '\n' + prefix + '<!--' + text.replace(/^\s*/mg, prefix) + '-->';
            //alert(ret);
            return ret;
        });
        return outputText.replace(/\s+$/g, '').replace(/\r/g, '\r\n');
	},
	/**
	 * 时间格式化 formatStr 为“yyyy-MM-dd” dateTime 为日期类型
	 */
	dateFormat : function(formatStr, dateTime) {

		var str = formatStr;
		var Week = [ '日', '一', '二', '三', '四', '五', '六' ];

		str = str.replace(/yyyy|YYYY/, dateTime.getFullYear());
		str = str
				.replace(/yy|YY/, (dateTime.getYear() % 100) > 9 ? (dateTime
						.getYear() % 100).toString() : '0'
						+ (dateTime.getYear() % 100));

		str = str.replace(/MM/,
				dateTime.getMonth() > 8 ? dateTime.getMonth() + 1 : '0'
						+ (dateTime.getMonth() + 1));
		str = str.replace(/M/g, dateTime.getMonth() + 1);

		str = str.replace(/w|W/g, Week[dateTime.getDay()]);

		str = str.replace(/dd|DD/, dateTime.getDate() > 9 ? dateTime.getDate()
				.toString() : '0' + dateTime.getDate());
		str = str.replace(/d|D/g, dateTime.getDate());

		str = str.replace(/hh|HH/, dateTime.getHours() > 9 ? dateTime
				.getHours().toString() : '0' + dateTime.getHours());
		str = str.replace(/h|H/g, dateTime.getHours());
		str = str.replace(/mm/, dateTime.getMinutes() > 9 ? dateTime
				.getMinutes().toString() : '0' + dateTime.getMinutes());
		str = str.replace(/m/g, dateTime.getMinutes());

		str = str.replace(/ss|SS/, dateTime.getSeconds() > 9 ? dateTime
				.getSeconds().toString() : '0' + dateTime.getSeconds());
		str = str.replace(/s|S/g, dateTime.getSeconds());
		return str;
	},
	jarpath:function(value){
		var strs= new Array();
		strs = value.split(/[/\\]/);
		return strs[strs.length-1];
	},
	isJSON : function(str){
		if (typeof str == 'string') {
	        try {
	            var obj=JSON.parse(str);
	            if(str.indexOf('{')>-1){
	                return true;
	            }else{
	                return false;
	            }

	        } catch(e) {
	            return false;
	        }
	    }
	    return false;
	},
	submit : function(controllerURL, formId, dialogId, errMsg, data,
			refreshTable, dialogShow, sucMsg) {
		var reqData = null;
		var that = this;
		if (formId) {
			reqData = $(formId).serialize();
		} else {
			reqData = data;
		}
		
		$.ajax({
			type : "post",
			url : getContextPath() + controllerURL,
			dataType : 'json',
			data : reqData,
			async : false,
			success : function(response) {
				if (response == true) {
					if (refreshTable) {
						$(refreshTable).bootstrapTable('refresh');
					}
					if (!dialogShow) {
						$(dialogId).modal('hide');
					}
					if (sucMsg) {
						that.alertSuccess(sucMsg);
					}
					
				} else {
					$.utils.alertWarning(errMsg);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.utils.alertError('AJAX出错');
			}
		});
	},
	submit2 : function(controllerURL, formId, dialogId, errMsg, data,
			refreshTable, dialogShow, sucMsg) {
		var reqData = null;
		var that = this;
		if (data) {
			reqData = data;
		} else {
			reqData = $(formId).serialize();
		}
		
		$.ajax({
			type : "post",
			url : getContextPath() + controllerURL,
			dataType : 'json',
			data : reqData,
			async : false,
			success : function(response) {
				if (response.success == true) {
					if (refreshTable) {
						$(refreshTable).bootstrapTable('refresh');
					}
					if (!dialogShow) {
						$(dialogId).modal('hide');
					}
					if (sucMsg) {
						that.alertSuccess(sucMsg);
					}
					$(formId)[0].reset();
				} else {
					$.utils.alertWarning(errMsg);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.utils.alertError('AJAX出错');
			}
		});
	},
	loading:function(targetEl){
		var loadingWrap = "<div id='load-content'><span class='loading'></div></span>";
		return {
			show : function(){
				if($("#load-content").length ==  0){
					$(targetEl).empty().append(loadingWrap); 
				}
			},
			hide : function(){
				$(targetEl).remove("#load-content");
			}
		};
	},
	alertError : function(msg, options) {
		BootstrapDialog.alert({
			type : 'type-danger',
			title : '错误',
			buttonLabel : '确定',
			message : msg,
			draggable : true
		});
	},
	alertSuccess : function(msg, options) {
		BootstrapDialog.alert({
			type : 'type-success',
			title : '正确',
			buttonLabel : '确定',
			message : msg,
			draggable : true
		})
	},
	alertWarning : function(msg, options) {
		BootstrapDialog.alert({
			type : 'type-warning',
			title : '警告',
			buttonLabel : '确定',
			message : msg,
			draggable : true
		});
	},
	alertInfo : function(msg, options) {
		BootstrapDialog.alert({
			type : 'type-info',
			title : '信息',
			buttonLabel : '确定',
			message : msg,
			draggable : true
		});
	},
	objToJson : function(obj) {
		return JSON.stringify(obj);

	},
	updatePsd : function() {
		if ($('#updatePasswordForm').valid()) {
			$.utils.submit('/user/updatePsdByPrimaryKeySelective',
					'#updatePasswordForm', '#updatePassWordDialog', '修改出错~',
					null, false, null, '修改成功~');
		}
	},
	updatePsdByUsername : function() {

		$('#updatePasswordForm div').removeClass('has-error');

		$('#updatePasswordForm').validate().resetForm();

		$('#updatePasswordForm')[0].reset();

		$('#updatePassWordDialog').modal('show');
	},
	initValid : function(id) {
		$('#idForUpdatePsd').val(id);

		$('#updatePasswordForm').validate({
			rules : {
				orignPsd : {
					required : true,
					rangelength : [ 6, 10 ]
				},
				newPsd : {
					required : true,
					rangelength : [ 6, 10 ]
				},
				newPsdConfirm : {
					equalTo : '#newPassword'
				}
			},
			messages : {
				orignPsd : {
					required : "原密码不能为空",
					rangelength : "原密码6-10位"
				},
				newPsd : {
					required : "新密码不能为空",
					rangelength : "新密码6-10位"
				},
				newPsdConfirm : {
					equalTo : "两次密码输入不一致"
				}
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
	logout : function() {
		sessionStorage.clear();
		window.location.href = getBasePath()+"/logout";
	},
	init : function() {
		var id = $.utils.loadUserName();
		$.utils.initValid(id);
	},
	username:'',
	loadUserName : function() {
		var id = 0;
		var cur = this;
		$.ajax({
			url : getContextPath() + "/system/userInfo",
			dataType : 'json',
			type : 'get',
			async : false,
			success : function(response) {
				if (response.success) {
					if(!response.obj){
						response.obj = {
								"sysUsername" : "happy_test",
								"sysName" : "happy_test",
								"id" : 8
						};
					}
					if (response.obj.sysUsername == null
							|| response.obj.sysUsername == "") {
						window.location.href = '../pages/417.html';
					} else if (response.obj.delStatus) {
						window.location.href = '../pages/404.html';
					} else {
						$("#userName").text(response.obj.sysName);
						$("#helloUserName").text(
								"hello," + response.obj.sysName);
						id = response.obj.id;
						cur.username = response.obj.sysUsername
						$.utils.loadSideBar(id);
					}
				} else {

				}
			},

		});

		return id;
	},
	loadSideBar : function(id) {
		var url = getContextPath() + "/system/loadSideMenus";
		var firstMenu = null;
		var firstChildMenu = null;
		$
				.ajax({
					url : url,
					data : {
						id : id
					},
					dataType : "json",
					async : false, // 子菜单需要同步加载
					cache : true, // 可以使用缓存
					success : function(response) {
						var html = '';
						var resArr = response.obj;
						for (var i = 0; i < resArr.length; ++i) {// 一级children
							if (firstMenu == null) {
								firstMenu = resArr[i].resourceUrl;
							}
							html += '<li class="treeview">';
							html += '<a href="#">';
							html += '<i class="fa fa-tasks"></i>';// 图标可以改变
							html += '<span>' + resArr[i].resourceName
									+ '</span>'
							html += '<i class="fa fa-angle-left pull-right"></i>';
							html += '</a>';
							html += '<ul class="treeview-menu">';
							var childMenu = resArr[i].childMenu;
							for ( var k in childMenu) {// 二级children
								var tempStr = childMenu[k] + ".html";
								var fragmentStr = childMenu[k].substring(childMenu[k].lastIndexOf("/") + 1, childMenu[k].length);
								html += '<li><a data-url="'
										+ tempStr
										+ '" href="#'+ fragmentStr +'"><i class="fa fa-angle-double-right"></i>'
										+ k + '</a></li> '
							}
							html += '</ul>';
							html += '</li>';
						}
						html += '<li><a id="suggessLink" href="#" data-url="/pages/messageBoard.html"><i class="fa fa-tasks"></i><span>用户建议</span></a></li>'
						$('#sidebar-menu').html(html);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						if (XMLHttpRequest.status == '401') {

						}
					}
				});
		return firstChildMenu;
	},
	jsonFormatFill: function(id){
		var formatText = this.jsonFormatSrc($("#"+id).val());
		if(formatText){
			$("#"+id).val(formatText);
		}
	},
	jsonFormat: function(txt){
		return this.jsonFormatSrc(txt, false);
	},
	jsonFormatSrc : function (txt,compress/*是否为压缩模式*/){/* 格式化JSON源码(对象转换为JSON文本) */ 
		try{
			var jsonStr = JSON.stringify(eval("("+txt+")") , null, 4);
			return jsonStr;
		}catch(e){
//			alert("json格式不正确");
		}
		return txt;
	},
	removeHeader:function(opt){
		$(opt).parent().parent().remove(); 
	},
	getHeader:function(tablename){
		var headers = {};
		$('#'+tablename).find("tr").each(function(){
			var indexTr = $(this).index();
			if(indexTr >0){
				var headKey = $(this).find("input[name='headKey']").val().trim();
				var headValue = $(this).find("input[name='headValue']").val().trim();
				if(headKey !="" && headValue != ""){
					headers[headKey] = headValue;
				}
			}else{
				return;
			}
		});
		return JSON.stringify(headers);
	},
	addHeader: function(opt){
		var html = '';
		html += 	'<tr style="margin-bottom:5px;">';
		html += 		'<td style="width:40%;text-align: center;">';
		html += 			'<input type="text" class="header-params form-control" style=" border-radius: 4px !important;" name="headKey" required="">'
		html += 		'</td>';
		html += 		'<td style="width:5%;text-align: center;">:</td>';
		html += 		'<td style="width:40%;text-align: center;">';
		html += 		'<input type="text" class="header-params form-control" style=" border-radius: 4px !important;" name="headValue" required="">';
		
		html += 		'</td>';
		html += 		'<td style="width:10%;text-align:center;"><a class="detail-icon" href="javascript:" onclick="$.utils.removeHeader(this)"><i class="glyphicon glyphicon-minus icon-minus"></i></a></td>';
		html += 	'</tr>';
		$("#headerTable").append(html);
	},
	analysisHeader:function(httpHeader,tablename){
		try{
			httpHeader = JSON.parse(httpHeader);
			$("#"+tablename).html('');
			var html = '<tbbdy><tr>\
				<td style="width:40%;text-align: center;">键</td>\
				<td style="width:5%;text-align: center;"></td>\
				<td style="width:40%;text-align: center;">值</td>\
				<td style="width:10%;text-align:center;">\
				<a class="detail-icon" href="javascript:" onclick="$.utils.addHeader(this,\''+tablename+'\',false)"><i class="glyphicon glyphicon-plus icon-plus"></i></a></td>\
			</tr>\
			<tr>\
				<td style="width:40%;text-align: center;">\
					<input type="text" class="header-params form-control" style=" border-radius: 4px !important;" name="headKey" required="true" value="Content-Type">\
				</td>\
				<td style="width:5%;text-align: center;">:</td>\
				<td style="width:40%;text-align: center;">\
					<input type="text" class="header-params form-control" style=" border-radius: 4px !important;" name="headValue" required="true" value="application/json">\
				</td>\
			</tr>';
		      for(var key in httpHeader){
		    	  if(key != "Content-Type"){
					html += 	'<tr style="margin-bottom:5px;">\
								<td style="width:40%;text-align: center;">\
								<input type="text" class="header-params form-control" style=" border-radius: 4px !important;" name="headKey" required="true" value=\"'+key+'\"> \
								</td>\
								<td style="width:5%;text-align: center;">:</td>\
								<td style="width:40%;text-align: center;">\
								<input type="text" class="header-params form-control" style=" border-radius: 4px !important;" name="headValue" required="true" value=\''+httpHeader[key]+'\'>\
								</td>\
								<td style="width:10%;text-align:center;"><a class="detail-icon" href="javascript:" onclick="$.utils.removeHeader(this)"><i class="glyphicon glyphicon-minus icon-minus"></i></a></td>\
								</tr>';
		    	  }
		      }
			html += "</tbody>";
		    $("#"+tablename).html(html);
		}catch(error){
			
		}

	},
	/**
	 * 加载主页面
	 * 
	 * @param path
	 *            页面路径
	 * @param historyFlag
	 *            是否加入到历史记录中,true 为是,false为否
	 * @param sessionFlag
	 *            是否需要清除session,true 为是, false 为否
	 * @param json
	 *            需要存入到session中的json数据
	 */
	loadModule : function(path) {
		var url = getContextPath() + path;
		$.ajax({
			url : url,
			async : false, // 需要异步加载
			dataType : "html",
			cache : false,
			success : function(data) {
				$('#content').html('');
				$('#content').html(data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if (XMLHttpRequest.status == '401') {
					window.location.href = getContextPath();
				}
			}
		});
	},
	/**
	 * 对带双引号的字符串进行转义
	 * 如： "text" 转义为 \"text\"
	 * @param  {[type]} val 需要转义的字符串
	 * @return {[type]}     转义后的字符串
	 */
	replace:function(val){
        return val.replace(/\"/g,"\\\"").trim().replace(/\s/g,"");
    },
	queryTopic:function(cluster, topic){
		var topicInfo;
		 $.ajax({
	         url: getContextPath() + "/kafkaReadManager/queryTopic",
	         async:false,
	         data: {"clusterName":cluster, "topic":topic},
	         success: function (response) {
	         	if(response.success){
	         		var obj = JSON.parse(response.obj);
	         		if(obj.total > 0){
	         			topicInfo = obj.rows[0];
	         			console.info(topicInfo);
	         		}
	         	}
	         }
	     });
		 return topicInfo;
	}
};

/** 将表单序列化为一个json对象，只支持单个值的控件 */
$.fn.serializeFormToJson = function() {
	var serializeObj = {};
	$(this.serializeArray()).each(function() {
		serializeObj[this.name] = this.value;
	});
	return serializeObj;
}
