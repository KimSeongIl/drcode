var thisPage=1;



var successDelete = function() {
	alert('삭제 완료');
	location.href="/admin_main.html";
}

var failDelete = function() {
	alert('삭제 실패');
}

var userDelete = function(id) {
	if(confirm("정말 삭제하시겠습니까?")){
	requestJsonDataDELETE('/users' , {id : id}, successDelete, failDelete);
	}
	else{
		return false;
	}
}

var successUsersGet = function(data) {
	var table = $('#table');
	table.html("");
	var content = "<table class='table table-bordered'>";
	content += "<tr>";

		
		content += "<th> 학번   </th>";
		content += "<th> 이름   </th>";
		content += "<th> 이메일  </th>";
		content += "<th> 분류  </th>";
		content += "<th>   </th>";

		content += "</tr>";
		
			$.each(data.userList, function(key , value){
				content += "<tr>";
				
				content += ("<td>" + value.userNumber + "</td>");
				content += ("<td>" + value.userName + "</td>");
				content += ("<td>" + value.email + "</td>");
				content += ("<td>" + value.auth + "</td>");
				content += ("<td> <input type='button' class='btn btn-danger' value='삭제' onclick='userDelete("+value.id+")'></td>");
				content += "</tr>";
			})

		
		content += "</table>";
		
		
		var pageCount = data.rowCount;
		
		content += pagination(pageCount, thisPage, "userPage"); 

		
		table.html(content);
		
		$('.userPage').click(function(){ // 페이지 클릭시
			
			thisPage=$(this).attr('page'); // 누른 페이지 번호
			
			
			requestJsonData('/users', 'GET' , {auth: $('.nav-pills').find('.active').attr('data') , page : thisPage}, successUsersGet, failUsersGet);
		})
};



var failUsersGet = function() {
	alert('불러오기 실패');
}

var successDepartmentsInsertGet = function(data) {
	var list='';
	
	
	$.each(data, function(key, value) {
		
	list += '<option value="'+value.id+'">'+ value.departmentName +'</option>';
	});
	
	$('#insertdepartment').append(list);
}


var failDepartmentsInsertGet = function() {
	alert('불러오기 실패');
}


$(document).ready(function(){
	
	requestJsonData('/getDepartments', 'GET', {},successDepartmentsInsertGet, failDepartmentsInsertGet);
	requestJsonData('/users', 'GET' , {auth: 'student', page : thisPage}, successUsersGet, failUsersGet);
	
	
	$('.nav-pills li').click(function() {
		thisPage=1;
		$('.nav-pills').find('.active').removeClass('active');
		$(this).addClass('active');
		 
		requestJsonData('/users', 'GET' , {auth: $(this).attr('data'), page : thisPage}, successUsersGet, failUsersGet);
	})
	
	
	$('#insertUser').click(function() {
		
		requestJsonData('/user','POST',
				{
					userNumber : $('#insertuserNumber').val().trim(),
					userName:$('#insertuserName').val().trim(),
					email:$('#insertemail').val().trim(),
					password:$('#insertpassword').val().trim(),
					auth:$('#insertauth').val().trim(),
					departmentId:$('#insertdepartment').val().trim()
				},signUp, signUpFail);
	})
	
	
	
});


