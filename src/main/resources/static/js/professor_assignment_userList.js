/*print assignment Userlist*/
var page = 1;

var successAssignmentUserListGet=function(data){
	
	
	var list = '';
	list += '<h4 class="assignment">번호 :  '+ data.assignmentDto.id;+'<h4>'
	list += '<h4 class="assignment">과제명 :'+ data.assignmentDto.assignmentName+'<h4>'
	list += '<table class="table table-bordered">';
	list += '<tr>';	
	list += '<th>학번</th>'
	list += '<th>이름</th>';
	list += '<th>성적</th>';
	list += '</tr>';

	$.each(data.assignmentUserDtoList , function(index, value) {
		list += '<tr class="assignmentContent">';
		//list += '<td>' + (index+1) + '</td>';
	
		list += '<td>' + value.userNumber + '</td>';
		list += '<td>' + value.userName + '</td>';
		list += '<td>'+ value.score +'</td>';
		list += '</tr>';
	});
	list += '</table>';

	var pageCount = data.pageCount;
	
	list += pagination(pageCount, page, "assignmentUserListPage");

	$('#assignmentList').html(list);

	$('.assignmentUserListPage').click(function() {
		
		page = $(this).attr('page');
		requestJsonData('/assignmentUserList/'+id, 'GET', {page:page},successAssignmentUserListGet, failAssignmentUserListGet);
		
	});
	
}

var failAssignmentUserListGet=function(){
	alert('get fail..');
}


$(document).ready(function(){

	requestJsonData('/assignmentUserList/'+id,'GET',{page:page},successAssignmentUserListGet,failAssignmentUserListGet);
	
});