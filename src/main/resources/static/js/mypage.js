var successMyinfoupdate = function() {
	alert("수정 완료");
	location.href="/myPage.html"
}

var failMyinfoupdate = function() {
	alert("수정 실패");
}


var successGet = function(data) {

	$('#userNumber').val(data.userNumber);
	$('#userName').val(data.userName);
	$('#email').val(data.email);


}

var failGet = function() {
	alert('유저정보 불러오기 실패');
	location.href='/';

}


$(document).ready(function(){ 

	requestJsonData('/user', 'GET' , {}, successGet, failGet);




	$('#mypgform').submit(function () {

		if($('#password').val() != $('#password_confirm').val()){
			alert('비밀번호가 일치하지 않습니다.');
			return false;
			
		}
		if($('#password').val() == "" || $('#password').val() == null) { //패스워드 입력안했을경우
			requestJsonDataPUT('/user',
					{
						userNumber : $('#userNumber').val().trim(),
						userName:$('#userName').val().trim(),
						email:$('#email').val().trim(),
					},successMyinfoupdate, failMyinfoupdate)

		}
		else {
		requestJsonDataPUT('/user',
				{
					userNumber : $('#userNumber').val().trim(),
					userName:$('#userName').val().trim(),
					email:$('#email').val().trim(),
					password:$('#password').val().trim(),

				},successMyinfoupdate, failMyinfoupdate)
		}
				return false;
	});



})