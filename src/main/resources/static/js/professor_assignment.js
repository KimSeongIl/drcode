var successAssignmentGet=function(data){
	
	var limitTime = new Date(data.limitTime);
	var extensionTime = new Date(data.extensionTime);
	
	var list='';
	list+='<table class="table professor_assignment_table">';
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
	list+='<tr>';
	list+=	'<th>제출시간</th>';
	list+=	'<th>'+data.createdAtStr+'</th>';
	list+='</tr>';
	list+=	'<th>제출기한</th>';
	list+=	'<th>'+data.limitTimeStr+'</th>';
	list+='</tr>';
	list+='<tr>';
	list+=	'<th>연장기한</th>';
	list+=	'<th>'+data.extensionTimeStr+'</th>';
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



