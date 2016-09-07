/*print assignment list*/
var page = 1;
var checkDeleteArr = {'AssignmentDto':[]};


var successAssignmentsGet = function(data) {
	
	console.log(data);
	var list = '';

	list += '<table class="table list_table">';
	list += '<tr>';
	list += '<th>번호</th>'
	list += '<th>과제명</th>';
	list += '<th>제출기한</th>';
	list += '<th>연장기한</th>';
	list += '<th>채점</th>';
	list+=	'<th>선택</th>';
	list += '</tr>';

	$.each(data.assignmentDtoList , function(index, value) {

		list += '<tr class="assignmentContent" value="' + value.id + '" >';
		list += '<th>' + (index+1) + '</th>';
		list += '<th><a href="/professor_assignment.html?id='+value.id+'">' + value.assignmentName + '</a></th>';
		list += '<th>' + value.limitTimeStr.substr(0,16) + '</th>';
		list += '<th>' + value.extensionTimeStr.substr(0,16) + '</th>';
		list += '<th> <a href="/professor_assignment_userList.html?id='+value.id+'"><i class="fa fa-search" aria-hidden="true"></i> </a></th>';
		list+= 	'<th><label class="checkbox-inline"><input name="checkDelete" type="checkbox" value="'+value.id+'"></label></th>';
		list += '</tr>';
	});
	
	list += '</table>';
	
	var pageCount = data.rowCount;
	console.log(data.count);
	list += pagination(pageCount, page, "subjectPage");

	$('#assignmentList').html(list);

	$('.subjectPage').click(function() {
		
		page = $(this).attr('page');
		requestJsonData('/assignments', 'GET', { subjectId:subjectId, page:page},successAssignmentsGet, failAssignmentsGet);
		
	});

}

var failAssignmentsGet = function(data) {
	alert('fail');
}




/*delete Assignment*/
var successDeleteAssignment = function(){
	alert('success delete assignment');
	checkDeleteArr.length=0;
	location.href="/professor_assignment_list.html?subjectId="+subjectId;
}

var failDeleteAssignment = function(){
	alert('fail delete assignment');
}


/*ajax function*/
var getAssignment = function(subjectId,page){
	console.log('get assignment');
	requestJsonData('/assignments', 'GET', { subjectId:subjectId, page:page},successAssignmentsGet, failAssignmentsGet);
}

var deleteAssignment = function(checkDeleteArr){
	requestJsonDataDELETE('/assignment',{ checkDeleteArr : checkDeleteArr },successDeleteAssignment,failDeleteAssignment);
}


/*다중삭제목록 제이슨삽입*/
var getCheckedValue = function(){
	
	$('input[name=checkDelete]:checked').each(function(){
		checkDeleteArr.AssignmentDto.push({id:$(this).val()});
	});	
	
}


$(document).ready(function() {

	getAssignment(subjectId,page);


	$('#addAssignment').click(function(){
		location.href="/professor_assignment_add.html?subjectId="+subjectId;
	});
	
	$('.deleteAssignment').click(function(){
		getCheckedValue();
		deleteAssignment(checkDeleteArr);
	});
	

});
