var userNumber=userData.userNumber;
var page=1;
var checkDeleteArr = {'SubjectDto':[]};


/*get subject list*/
var successGetSubejcts = function(data){
	
	console.log(data);
	
	/*print subject list*/
	var list='';
	
	list+='<table class="table list_table">';
	list+=	'<tr>';
	list+=		'<th>번호</th>';
	list+=		'<th>과목코드</th>';
	list+=		'<th>과목명</th>';
	list+=		'<th>선택</th>'
	list+=	'</tr>';
	$.each(data.subDtoList ,function(index,value){
		
		list+='<tr>';
		list+=	'<th>'+(index+1)+'</th>';
		list+=	'<th>'+value.subjectCode+'</th>';
		list+=	'<th><a href="/professor_assignment_list.html?subjectId='+value.id+'">'+value.subjectName+'</a></th>';
		list+= 	'<th><label class="checkbox-inline"><input name="checkDelete" type="checkbox" value="'+value.id+'"></label></th>'
		list+='</tr>';	
		
	});
	list+='</table>';
	
	

	var pageCount=data.rowCount;
	list += pagination(pageCount, page, 'subjectPage');
	
	$('#subjectList').html(list);

	$('.subjectPage').click(function(){
		page=$(this).attr('page');	
		getSubjects(userNumber,page);
	});
	
}

var failGetSubjects = function(){
	alert('과목조회 실패');
}

/*insert subject*/
var successInsertSubject = function(){
	console.log(111);
	alert('insert subject success');
	location.href="/";
}

var failInsertSubject = function(){
	alert('insert subject fail');
}

/*delete subject*/
var successDeleteSubject = function(){
	alert('success delete subject');
	checkDeleteArr.length=0;
	location.href="/";
}

var failDeleteSubject = function(){
	alert('fail delete subject');
}


/*ajax function*/
var getSubjects = function(userNumber,page){
	console.log('get subjects');
	requestJsonData('/subjects' ,'GET',{ userNumber:userNumber ,page:page }, successGetSubejcts, failGetSubjects);
}

var deleteSubjects = function(checkDeleteArr){
	requestJsonDataDELETE('/subjects',{ checkDeleteArr : checkDeleteArr },successDeleteSubject,failDeleteSubject);
}


/*다중삭제목록 제이슨삽입*/
var getCheckedValue = function(){
	
	$('input[name=checkDelete]:checked').each(function(){
		checkDeleteArr.SubjectDto.push({id:$(this).val()});
		
	});	
	
}

$(document).ready(function(){ 

	console.log(userNumber);
	getSubjects(userNumber,page);
	
	
	
	
	$('#insertSubject').click(function(){
				
				requestJsonData('/subject','POST',
				{
					"subjectCode": $('#subjectCode').val().trim(),
					"subjectName": $('#subjectName').val().trim(),
					"userNumber": userNumber
				},successInsertSubject,failInsertSubject);
				
	});
	

	
	$('.deleteSubject').click(function(){
		getCheckedValue();
		deleteSubjects(checkDeleteArr);
	});
	
});