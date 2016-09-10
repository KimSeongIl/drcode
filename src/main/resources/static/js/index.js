var signUp=function(data){
	alert('회원가입 성공');
	location.href = "/";
}

var signUpFail=function(){
	alert('회원가입 실패');
}

function passwdCheck() {
	
	if ($('#passwordsignup').val() != $('#passwordsignup_confirm').val()) {
		alert('비밀번호가 일치하지 않습니다');
		$('#passwordsignup').focus();
		return false;
	}
}

var successFindPassword=function(){
	alert('임시비밀번호 이메일 발송');
	location.href="/";
}

var failFindPassword=function(){
	alert('비밀번호 찾기 실패');
}

var successDepartmentsGet = function(data) {
	var list='';
	
	$.each(data, function(key, value) {
	list += '<option value="'+value.id+'">'+ value.departmentName +'</option>';
	});
	
	$('#departmentsignup').append(list);
}

var failDepartmentsGet = function() {
	alert('불러오기 실패');
}


$(document).ready(function(){
	
	requestJsonData('/getDepartments', 'GET', {},successDepartmentsGet, failDepartmentsGet);

	
	
	$('#signUpForm').submit(function (){
		
		requestJsonData('/user','POST',
				{
					userNumber : $('#usernumsignup').val().trim(),
					userName:$('#usernamesignup').val().trim(),
					email:$('#emailsignup').val().trim(),
					password:$('#passwordsignup').val().trim(),
					departmentId:$('#departmentsignup').val(),
					auth:'student'
				},signUp, signUpFail);

		return false;
	})
	
	
	$('#findPasswordForm').submit(function(){
		
		requestJsonDataPUT('/user/password',
				{
					userNumber:$('#findPswdusername').val().trim(),
					email:$('#findPswdemail').val().trim()
				},successFindPassword,failFindPassword);
		
		return false;
	
	})
	
})






