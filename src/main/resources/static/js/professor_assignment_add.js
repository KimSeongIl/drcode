var momentFormat="YYYY/MM/DD HH:mm";


/*insertAssignment ajax function*/
var successInsertAssignment=function(){
	alert('success insert assignment');
	location.href="/professor_assignment_list.html?subjectId="+subjectId;
}

var failInsertAssignment=function(){
	alert('fail insert assignment');
}

var checkAssignmentDataValidity=function(){

	if($('#assignmentName').val().trim() == ''){
		$('#assignmentName').val(moment().format(momentFormat)+"에 제출된 과제");
	}
	if($('#content').val().trim() == ''){
		$('#content').val("내용없음");
	}
	if(!$('#checkAutoScoring').prop('checked')){
		$('#answer').val("");
	}
	if($('#limitTime').val().trim() == ''){
		$('#limitTime').val(moment().format(momentFormat));
	}
	if($('#extensionTime').val().trim() == ''){
		$('#extensionTime').val( moment($('#limitTime').val().trim()).add(1,'day').format(momentFormat) );
	}
}

$(document).ready(function () {
	
	/*데이트피커*/
	$('#limitTimePicker').datetimepicker({
		format: momentFormat
    });
    
    $('#extensionTimePicker').datetimepicker({
        useCurrent: false,
        format: momentFormat
    });
    
    $("#limitTimePicker").on("dp.change", function (e) {
        $('#limitTimePicker').data("DateTimePicker").minDate(new Date());
        $('#extensionTimePicker').data("DateTimePicker").minDate(e.date);
    });
    
    $("#extensionTimePicker").on("dp.change", function (e) {
    	$('#limitTimePicker').data("DateTimePicker").maxDate(e.date);
        
    });
    
    
    /*스마트에디터*/
    $("textarea.smarteditor2").each(function () {  
        var textareaId = $(this).attr("id"); 
        se2_init(textareaId); 
    });
    
   
    /*과제추가*/
    $("#insertAssignment").click(function() { 
    	
    	se2_syncData();
		checkAssignmentDataValidity();
		
		requestJsonData('/assignment','POST',{
			
			answer : $('#answer').val().trim(),
			assignmentName:$('#assignmentName').val().trim(),
			content:$('#content').val().trim(),
			createdAtStr : moment().format(momentFormat),
			limitTimeStr : $('#limitTime').val().trim(),
			extensionTimeStr : $('#extensionTime').val().trim(),
			subjectId : subjectId
			
		},successInsertAssignment,failInsertAssignment);
		
		
		return false;
    });
});


