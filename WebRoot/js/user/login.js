/*
 *校验登录表单 
 */
$(function() {
	/*
	 * 给登录按钮添加提交事件完成表单校验
	 */
	$("#submit").submit(function() {
		//重置错误信息
		$("#msg").text("");
		var bool = true; //标记是否可以提交表单
		//获取所有输入框并对其进行相应校验
		$(".input").each(function() {
			//获取其name值
			var inputName = $(this).attr("name");
			if (!invokeValidateFunction(inputName)) { //若调用方法返回值为false(校验不通过)
				bool = false; //标记为false
			}
		});
		return bool;
	});

	/*
	 * 文本框获取焦点时
	 */
	$(".input").focus(function() {
		//隐藏当前文本框对应的lable错误内容
		var inputName = $(this).attr("name");
		$("#" + inputName + "Error").css("display", "none");
	});

	/*
	 * 文本框失去焦点时
	 */
	$(".input").blur(function() {
		/*
		 * 根据当前文本框的name值调用对应方法
		 */
		var inputName = $(this).attr("name");
		invokeValidateFunction(inputName);
	});
});

/*
 * 根据文本框的name值调用对应方法
 */
function invokeValidateFunction(inputName) {
	//将输入的参数名称的第一个字符转换为大写
	inputName = inputName.substring(0, 1).toUpperCase() + inputName.substring(1);
	//获得要执行的方法名称
	var funName = "validate" + inputName;
	//执行当前方法名称的方法(带返回值)
	return eval(funName + "()");
}

/*
 * 校验用户名
 */
function validateLoginname() {
	var bool = true; //标记是否校验成功
	//隐藏错误信息
	$("#loginnameError").css("display", "none");
	//获取文本框中的值
	var inputVal = $("#loginname").val();
	//非空校验
	if (!inputVal) {
		$("#loginnameError").css("display", "");
		$("#loginnameError").text("用户名不能为空！");
		bool = false;
	} else if (inputVal.length < 3 || inputVal.length > 10) {
		$("#loginnameError").css("display", "");
		$("#loginnameError").text("用户名长度为3-10");
		bool = false;
	}
	return bool;
}


/*
 * 校验密码
 */
function validateLoginpass() {
	var bool = true; //标记是否校验成功
	//隐藏错误信息
	$("#loginpassError").css("display", "none");
	//获取文本框中的值
	var inputVal = $("#loginpass").val();
	//非空校验
	if (!inputVal) {
		$("#loginpassError").css("display", "");
		$("#loginpassError").text("密码不能为空！");
		bool = false;
	} else if (inputVal.length < 6 || inputVal.length > 12) {
		$("#loginpassError").css("display", "");
		$("#loginpassError").text("密码长度为3-12");
		bool = false;
	}
	return bool;
}