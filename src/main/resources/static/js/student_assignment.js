var openWindow = function(id) {
	cw=850;
	ch=700;
	sw=screen.availWidth;
	sh=screen.availHeight;
	px=(sw-cw)/2;
	py=(sh-ch)/2;
	
	
	window.open('/student_code.html?id='+id ,"", 'left='+px+',top='+py+',width='+cw+',height='+ch+',toolbar=no,menubar=no,status=no,scrollbars=no,resizable=no');

}


var successAssignmentGet=function(data){

	
	var list='';
	list+='<table class="table student_assignment_table">';
	list+='<tr>';
	list+=	'<th>강좌명</th>';
	list+=	'<th>'+data.subjectName +'</th>';
	list+='</tr>';
	list+='<tr>'
	list+=	'<th>번호</th>';
	list+=	'<th>'+data.id+'</th>';
	list+='</tr>';
	list+='<tr>';
	list+=	'<th>제목</th>';
	list+=	'<th>'+data.assignmentName+'</th>';
	list+='</tr>';
	list+='<tr>';
	list+=	'<th class="assignment_t1">내용</th>';
	list+=	'<th class="assignment_t2">'+data.content+'</th>';
	list+='</tr>';
	list+='<tr>';
	list+=	'<th>제출기한</th>';
	list+=	'<th>' + data.limitTimeStr + '</th>';
	list+='</tr>';
	list+='<tr>';
	list+=  '<th>연장기한</th>';
	list+=  '<th>' + data.extensionTimeStr + '</th>';
	list+='</tr>';
	list+=  '<th>코드 확인</th>';
	list+=  '<th><a style="cursor:pointer" onclick="openWindow('+data.id+')"> <i class="fa fa-file-code-o" aria-hidden="true"></i> </a></th>';
	list+='</tr>';
	list+='</table>';
	
	
	$('#assignmentContent').html(list);


	
}

var failAssignmentGet=function(){
	alert('get fail..');
}


$(document).ready(function(){
	
	
	requestJsonData('/assignment/'+id,'GET',{},successAssignmentGet,failAssignmentGet);
	
});