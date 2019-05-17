/*
 * 页面用户注册信息处理
 */
$(function() {
	//遍历所有错误信息标签,调用一个方法来确定是否显示label标签
	$(".labelClass").each(function() {
		showErrors($(this));
	});

	//所有输入框获取焦点事件
	$(".inputClass").focus(function() {
		/*
		当输入框获取焦点时隐藏错误信息
		*/
		//获取当前输入框下的label的id
		var labelId = "#" + $(this).attr("id") + "Error";
		//清空label中的内容
		$(labelId).text("");
		//判断label是否显示
		showErrors($(labelId));
	});

	//所有输入框失去焦点事件
	$(".inputClass").blur(function() {
		//获取当前输入框的id
		var id = $(this).attr("id");
		//根据其id调用对应的校验方法(根据命名规则补全其校验方法名称)
		var funName = "validate" + id.substring(0, 1).toUpperCase() + id.substring(1) + "()";
		//调用指定名称方法
		eval(funName);
	});
});

//判断label中的错误内容是否显示
function showErrors(ele) {
	//获取当前label中的内容
	var text = ele.text();
	if (!text) { //如果没有内容,则隐藏当前label
		ele.css("display", "none");
	} else { //否则显示
		ele.css("display", "");
	}
}


/**
校验表单合法性
*/
//校验用户名
function validateUsername() {
	var id = "username"; //用户名输入框id值
	//获取用户名文本框中的值
	var usernameVal = $("#" + id).val();
	//根据输入框id获取当前对应label
	var label = $("#" + id + "Error");

	if (!usernameVal) { //非空校验
		/*
		给当前文本框下对应的label赋值
		*/
		label.text("用户名不能为空");
		//判断是否显示lable
		showErrors(label);
		return false;
	}

	/*
	长度校验
	*/
	if (usernameVal.length < 3 || usernameVal.length > 10) {
		/*
		给当前文本框下对应的label赋值
		*/
		label.text("用户名长度必须在3-10位之间！");
		//判断是否显示lable
		showErrors(label);
		return false;
	}


	/*
	ajax异步请求判断用户是否已被注册
	*/
	$.ajax({
		url : "UserServlet", //要请求的servlet
		data : {
			method : "ajaxValidateUsername", //请求servlet的方法
			username : usernameVal //参数
		},
		type : "POST", //请求方式
		dataType : "json", //数据类型
		async : false, //是否异步
		cache : false, //是否缓存
		success : function(result) { //请求成功返回参数result
			if (!result) { //若返回false则说明该用户存在
				var label = $("#" + id + "Error");
				label.text("该用户已被注册！");
				//判断是否显示lable
				showErrors(label);
				return false;
			}
		}
	});

	return true;
}

//校验密码
function validatePassword() {
	var id = "password"; //密码输入框id值
	//获取密码文本框中的值
	var passwordVal = $("#" + id).val();
	//根据输入框id获取当前对应label
	var label = $("#" + id + "Error");

	if (!passwordVal) { //非空校验
		/*
		给当前文本框下对应的label赋值
		*/
		label.text("密码不能为空");
		//判断是否显示lable
		showErrors(label);
		return false;
	}

	/*
	长度校验
	*/
	if (passwordVal.length < 6 || passwordVal.length > 12) {
		/*
		给当前文本框下对应的label赋值
		*/
		label.text("密码长度必须在6-12位之间！");
		//判断是否显示lable
		showErrors(label);
		return false;
	}

}


//校验两次输入密码是否一致
function validateRepass() {
	var id = "repass"; //确认密码输入框id值
	//获取确认密码文本框中的值
	var repassVal = $("#" + id).val();
	//根据输入框id获取当前对应label
	var label = $("#" + id + "Error");

	if (!repassVal) { //非空校验
		/*
		给当前文本框下对应的label赋值
		*/
		label.text("确认密码不能为空");
		//判断是否显示lable
		showErrors(label);
		return false;
	}

	/*
	 * 校验两次密码是否一致
	 */
	var password = $("#password").val(); //获取输入密码
	if (repassVal != password) {
		/*
		给当前文本框下对应的label赋值
		*/
		label.text("两次输入密码不一致!");
		//判断是否显示lable
		showErrors(label);
		return false;
	}
}