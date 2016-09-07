/*print assignment list*/
var page = 1;

var openWindow = function(id) {
	cw=850;
	ch=700;
	sw=screen.availWidth;
	sh=screen.availHeight;
	px=(sw-cw)/2;
	py=(sh-ch)/2;
	
	
	window.open('/student_code.html?id='+id ,"", 'left='+px+',top='+py+',width='+cw+',height='+ch+',toolbar=no,menubar=no,status=no,scrollbars=no,resizable=no');

}
var successAssignmentsGet = function(data) {
	
	var list = '';

	list += '<table class="table list_table">';
	list += '<tr>';
	list += '<th>번호</th>';
	list += '<th>제목</th>';
	list += '<th>제출기한</th>';
	list += '<th>연장기한</th>';
	list += '<th>점수</th>';
	list += '<th>코드 확인</th>';
	list += '</tr>';

	$.each(data.assignmentList , function(index, value) {
		
		
		
		list += '<tr class="assignmentContent">';
		list += '<th>' + (index+1) + '</th>';
		//list += '<th><a href="/professor_assignment.html?id='+value.id+'">' + value.assignmentName + '</a></th>';
		list += '<th><a href="/student_assignment.html?id='+value.assignmentId+'">' + value.assignmentName + '</a></th>';
		list += '<th>' + value.limitTimeStr + '</th>';
		list += '<th>' + value.extensionTimeStr + '</th>';
		if(value.score==undefined){
			list += '<th> </th>';
		}
		else{
			list += '<th>' + value.score + '</th>';
		}
		
		list += '<th><a <a style="cursor:pointer" onclick="openWindow('+value.assignmentId+')"> <i class="fa fa-file-code-o" style="margin-left:20px;" aria-hidden="true"></i> </a></th>'; 
		//list += '<th>' + value.assignmentId + '</th>';
		 
		list += '</tr>';
	});
	list += '</table>';

	var pageCount = data.pageCount;
	
	list += pagination(pageCount, page, "assignmentListPage");

	$('#assignmentList').html(list);

	$('.assignmentListPage').click(function() {
		
		page = $(this).attr('page');
		requestJsonData('/studentAssignmentList', 'GET', { page:page},successAssignmentsGet, failAssignmentsGet);
		
	});

	
	
}

var failAssignmentsGet = function(data) {
	alert('과제목록 불러오기 실패');
}


var successSubjectsGet = function(data) {
	
	var tab = '';
	if(data.subjectList.length > 0) {
		tab += '<li class="active" data="' + data.subjectList[0].id+'"><a>' + data.subjectList[0].subjectName + '</a></li>';
		
	}
	for(var i=1; i < data.subjectList.length; i++) {
		tab += '<li data="' + data.subjectList[i].id +'"><a>' + data.subjectList[i].subjectName + '</a></li>';		
	}
	$('#subject_tab').html(tab);
	
	if(data.subjectList.length > 0){
		requestJsonData('/studentAssignmentList', 'GET', { subject_id: $('#subject_tab').find('.active').attr('data'),  page:page},successAssignmentsGet, failAssignmentsGet);
	}
	
	$('#subject_tab li').click(function() {
		
		$('#subject_tab').find('.active').removeClass('active');
		$(this).addClass('active');
		
		requestJsonData('/studentAssignmentList', 'GET', { subject_id: $(this).attr('data'), page:page},successAssignmentsGet, failAssignmentsGet);
		
	})

}

var failSubjectsGet = function(data) {
	alert('과목목록 불러오기 실패');
}

$(document).ready(function() {

			requestJsonData('/studentSubjectList', 'GET', {}, successSubjectsGet, failSubjectsGet);

			
		});







