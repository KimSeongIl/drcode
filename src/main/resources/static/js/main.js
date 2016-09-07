var themeInit="sqlserver";
var fontInit="16px";
var languageInit="c";

var dbconnectInit=false;

var socketNum=0;
var connectNum=0;
var stompClient;
var socketFail=function(error){
	if(connectNum<=10){
		socketConnect(socketNum);
		connectNum++;
	}else{
		alert(error);
		connectNum=0;
	}
	
}

var proPage=1;
var subPage=1;
var assPage=1;
var departPage=1;

var professorNumber="";
var professorName="";
var subjectId="";
var assignmentName="";

var assignmentId="";
var assignmentContent="";

function bgLayerOpen(){
	if(!$('.bgLayer').length){
		$('<div class="bgLayer"></div>').appendTo($('body'));
	}
	var object=$('.bgLayer');
	var w=$(document).width()+12;
	var h=$(document).height();
	
	object.css({'width':w,'height':h});
	//object.fadeIn(500);
}

function bgLayerClear(){
	var object=$('.bgLayer');
	if(object.length){
		//object.fadeOut(500,function(){
			object.remove();
		//})
	}
}

function popupOpen(){
	
	var $layerPopupObj=$('#submitModal');
	var left=($(window).scrollLeft()+($(window).width()-$layerPopupObj.width())/2);
	var top=($(window).scrollTop()+($(window).height()-$layerPopupObj.height())/2)-50;
	$layerPopupObj.css({'left':left,'top':top,'position':'absolute'});
	
	if(document.all.submitModal.style.display=='none'){
		
		document.all.submitModal.style.display='block';
		bgLayerOpen();
		
		return false;
	}else{
		
		document.all.submitModal.style.display='none';
		bgLayerClear();
		
		return false;
	}
}


var successDepartmentGet=function(departmentList) {
var subjectTable="<table class='table table-hover'>";
	
	for(i=0;i<departmentList.length;i++){
		
		subjectTable+="<tr>";
		subjectTable+="<td style='cursor:pointer;' onclick='professorGet(\""+departmentList[i].id+"\",\""+departmentList[i].departmentName+"\")'>"+departmentList[i].departmentName+"</td>";
		subjectTable+="</tr>";
	}
	subjectTable+="</table>";
	
	modalChange("학과목록",subjectTable);
	
}

var successProfessorGet=function(professorList){
	
	var professorTable="<table class='table table-hover'>";
	
	for(i=0;i<professorList.userList.length;i++){
		professorTable+="<tr>";
		
		professorTable+="<td style='cursor:pointer;' onclick='subjectGet(\""+professorList.userList[i].userNumber+"\",\""+professorList.userList[i].userName+"\")'>"+professorList.userList[i].userName+"</td>";
		professorTable+="</tr>";
	}
	professorTable+="</table>";
	
	var pageCount = professorList.rowCount;
	professorTable += pagination(pageCount, proPage, "professorPage"); 
	modalChange("<a href='#' onclick='departmentBack()'>&lt;</a>&nbsp;&nbsp;"+departmentName, professorTable);
}

var departmentBack=function(){
	requestJsonData('/getDepartments', 'GET' , {}, successDepartmentGet);
	
}

var professorBack=function(){
	requestJsonData('/users/department', 'GET' ,  {page : proPage, departmentId : departmentId}, successProfessorGet);
}
var subjectBack=function(){
	requestJsonData('/subjects','GET',{'userNumber':professorNumber,page:subPage},subjectGetSuccess);
}
var assignmentBack=function(){
	requestJsonData('/assignments','GET',{subjectId:subjectId,page:assPage},assignmentGetSuccess);
}

var assignmentSubmitSuccess = function() {
	alert('제출 성공');
	popupOpen();
}

var assignmentSubmitFail = function() {
	alert('제출 실패');
}


var assignmentContentGet=function(assId,assName,assContent){
	var contentTable="<table class='table table-hover'>";
	contentTable+="<tr>";
	contentTable+="<td>"+unescape(assContent)+"</td>";
	contentTable+="</tr>";
	contentTable+="</table>";
	contentTable+="<div id='assignmentSubmit'><input type='button' class='btn btn-primary' value='제출'></div>";
	modalChange("<a href='#' onclick='assignmentBack()'>&lt;</a>&nbsp;&nbsp;"+assName,contentTable);
	
	$('#assignmentSubmit').click(function(){
		if(confirm('현재 작성된 코드로 제출됩니다. 제출하시겠습니까?')){		
			requestJsonData('/assignmentSubmit', 'POST',{ assignmentId : assId, code: editor.getValue()}, assignmentSubmitSuccess, assignmentSubmitFail);	
		}
	})
}


var assignmentGetSuccess=function(data){
	var assignmentTable="<table class='table table-hover'>";
	
	for(i=0;i<data.assignmentDtoList.length;i++){
		
		assignmentTable+="<tr>";
		assignmentTable+="<td style='cursor:pointer;' onclick='assignmentContentGet(\""+data.assignmentDtoList[i].id+"\",\""+data.assignmentDtoList[i].assignmentName+"\",\""+escape(data.assignmentDtoList[i].content)+"\")'>"+data.assignmentDtoList[i].assignmentName+"</td>";
		assignmentTable+="</tr>";
	}
	assignmentTable+="</table>";
	
	var pageCount = data.rowCount;
	assignmentTable += pagination(pageCount, proPage, "assignmentPage"); 
	modalChange("<a href='#' onclick='subjectBack()'>&lt;</a>&nbsp;&nbsp;"+assignmentName+" 과제목록",assignmentTable);
}

var professorGet=function(departId, departName) {
	departmentId=departId;
	departmentName=departName;
	requestJsonData('/users/department', 'GET', {departmentId: departId, page:departPage}, successProfessorGet)
}

var assignmentGet=function(subId,assName){
	
	subjectId=subId;
	assignmentName=assName;
	requestJsonData('/assignments','GET',{subjectId:subjectId,page:assPage},assignmentGetSuccess);
}
var subjectGetSuccess=function(data){
	var subjectTable="<table class='table table-hover'>";
	
	for(i=0;i<data.subDtoList.length;i++){
		
		subjectTable+="<tr>";
		subjectTable+="<td style='cursor:pointer;' onclick='assignmentGet(\""+data.subDtoList[i].id+"\",\""+data.subDtoList[i].subjectName+"\")'>"+data.subDtoList[i].subjectName+"</td>";
		subjectTable+="</tr>";
	}
	subjectTable+="</table>";
	
	var pageCount = data.rowCount;
	subjectTable += pagination(pageCount, proPage, "subjectPage"); 
	modalChange("<a href='#' onclick='professorBack()'>&lt;</a>&nbsp;&nbsp;"+professorName+" 강의목록",subjectTable);
}
var subjectGet=function(profNumber,profName){
	
	professorNumber=profNumber;
	professorName=profName;
	requestJsonData('/subjects','GET',{'userNumber':profNumber,page:subPage},subjectGetSuccess);
}
var modalChange=function(modalHeader,modalBody){
	$('#submitModal .modalHeader h4').html(modalHeader);
	$('#submitModal .modalBody').html(modalBody);
	
    $('.professorPage').click(function(){ // 페이지 클릭시
		
		thisPage=$(this).attr('page'); // 누른 페이지 번호
		
		
		requestJsonData('/users/professor', 'GET' ,  {page : thisPage}, successProfessorGet);
	})
	
	$('.subjectPage').click(function(){ // 페이지 클릭시
		
		thisPage=$(this).attr('page'); // 누른 페이지 번호
		
		
		requestJsonData('/subjects', 'GET' ,  {page : thisPage}, successProfessorGet);
	})
}
//유효성검사 필요하다.
var databaseLogin = function() {
	 
	if ($('#url').val().trim() == '') {
		alert('URL은 반드시 입력해야합니다.');
		return;
	}
	else if($('#dbname').val().trim() == '') {
		alert('DbName은 반드시 입력해야합니다.')
		return;
	}
	else if($('#username').val().trim() == '') {
		alert('UserName은 반드시 입력해야합니다.')
		return;
	}
	else if($('#password').val().trim() == '') {
		alert('Password은 반드시 입력해야합니다.')
		return;
	}
	
	requestJsonData('/dblogin', 'POST', {
		url : $('#url').val().trim(),
		dbName : $('#dbname').val().trim(),
		userName : $('#username').val().trim(),
		password : $('#password').val().trim()

	}, dbloginSuccess);
	
	return true;
}


var dbloginSuccess = function() {
	alert("DB로그인 성공");
	dialog.dialog("close");
	dbconnectInit = true;
	$('#connect').hide();
	$('#disconnect').show();
}

var disconnectSuccess = function() {
	alert('DB연결 종료');
	dbconnectInit = false;
	$('#connect').show();
	$('#disconnect').hide();

}

var sessionSuccess = function(data) {

	if (data == false) {
		dbconnectInit = false;
		dialog.dialog("open");
		$('#connect').show();
		$('#disconnect').hide();
	} else {
		dbconnectInit = true;
		$('#connect').hide();
		$('#disconnect').show();
	}
}



	 

var socketConnect=function(userNumber){
	var socket = new SockJS('/hello');
    stompClient = Stomp.over(socket);
    
    stompClient.connect({}, function(frame) {
        //setConnected(true);
        console.log('Connected: ' + frame);
        //stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name }));
        stompClient.subscribe('/user/'+userNumber+'/topic/coding', function(greeting){
        	
        	var data=JSON.parse(greeting.body);
        	if(data.errorLine != undefined){
        		editor.getSession().setAnnotations([{
        		    row: data.errorLine-1,
        		    text: data.text,
        		    type: "error" // also warning and information
        		}]);
        	}
        	$('#console').html(function(index,html){
				
				return html+data.msg;
			})
        	
        	var console=document.getElementById('console');
        	console.scrollTop=console.scrollHeight;
        	
        });
    },socketFail);
}



var runSuccess=function(data){
	
	var result="";
	if(data.result=='error'){
		result="<span class='error'>"+data.error+"</span>";
	}else{
		//result='컴파일 성공';
	}
	
	
	//$('#console').html(result+"<br>");
	
	
	
	
	
}

function post_to_url(content, language) {
	var form = document.getElementById('downloadform');

	var hiddenField1 = document.getElementById('content');
	var hiddenField2 = document.getElementById('language');
	
	hiddenField1.setAttribute("value", content);
	hiddenField2.setAttribute("value", language);
	

	form.submit();
	
	hiddenField1 = null;
	hiddenField2 = null;
}

var sussecesSql = function(data) {
	var table = $('#console');
	table.html("");
	var content = "<table class='table table-bordered'>";
	content += "<tr>";

	var k = null;

	for ( var key in data) {
		content += "<th>" + key + "</th>";
		if (k == null) {
			k = key;
		}
	}
	if(k=='count'){
		table.html(data.count+' 개의 행이 변경되었습니다.');
	}
	else if (k != null) {
		content += "</tr>";

		var length = data[k].length;

		for (var i = 0; i < length; i++) {
			content += "<tr>";
			$.each(data, function(key, value) {
				content += ("<td>" + value[i] + "</td>");

			});

			content += "</tr>";
		}
		content += "</table>";

		//alert(Object.keys(data[key]).length);
		table.html(content);
	}

};



$(document).ready(function(){
	
	$(window).resize(function(){
		$('.bgLayer').css('width',$(window).width()-0);
		$('.bgLayer').css('height',$(window).height()-0);
	})
	
	successDepartmentGet(departmentList);
	
	
	
	

	
	
	
	$('#connect').click(function() {
		dialog.dialog("open");
	})

	$('#disconnect').click(function() {
		requestJsonData('/disconnect', 'POST', {

		}, disconnectSuccess);
	})

	

	
	
	
	//requestJsonData("/user","GET",{},userGetData,userGetDataError);

	if(userData.language==undefined){
		$('#codeContainer').load('main_c.html');
		$('#lang li:first-child').addClass('selected');
		$('#stop').show();
		$('#inputArea').show();
	}else{
		if(userData.language=="c_cpp"){
			$('#codeContainer').load('main_c.html');
			$('#lang li:first-child').addClass('selected');
			$('#stop').show();
			$('#inputArea').show();
			$('#connect').hide();
			$('#disconnect').hide();
			
			languageInit="c";
		}else if(userData.language=="java"){
			$('#codeContainer').load('main_java.html');
			$('#lang li:nth-child(2)').addClass('selected');
			$('#stop').show();
			$('#inputArea').show();
			$('#connect').hide();
			$('#disconnect').hide();
			
			languageInit="java"
		}else if(userData.language=="html"){
			$('#codeContainer').html('');
			$('#codeContainer').load('main_html.html');
			$('#lang li:nth-child(3)').addClass('selected');
			$('#stop').hide();
			$('#inputArea').hide();
			$('#connect').hide();
			$('#disconnect').hide();
			
			languageInit="html";
		}else if(userData.language=="mysql"){
			$('#codeContainer').html('');
			$('#codeContainer').load('main_mysql.html');
			$('#lang li:nth-child(4)').addClass('selected');
			$('#stop').hide();
			$('#inputArea').hide();
			
			
			languageInit="mysql";
		}
		
	}
	
	socketNum=userData.userNumber;
	socketConnect(socketNum);
	
	if(userData.theme==undefined){
		themeInit="sqlserver";
		$('#theme li:first-child').addClass('selected');
	}else{
		themeInit=userData.theme;
		
		$.each($('#theme li'),function(key,value){
			if(value.innerText == userData.theme){
				$('#theme li:nth-child('+(key+1)+')').addClass('selected');
			}else if(userData.theme == 'sqlserver'){
				$('#theme li:first-child').addClass('selected');
			}
		})
	}
	
	if(userData.font==undefined){
		fontInit="16px;"
	}else{
		fontInit=userData.font;
	}
	$.each($('#font li'),function(key,value){
		if(value.innerText == fontInit){
			$('#font li:nth-child('+(key+1)+')').addClass('selected');
		}
	})
	
	$('#download').click(function() {
		var content;
		//var editor = ace.edit("editor");
		content = editor.getValue();
		
		post_to_url(content, languageInit);
		
	})
	
	$('#run').click(function(){
		if(languageInit=="c" || languageInit=="java"){
			if(stompClient.connected){
				$('#console').html('');
				editor.getSession().clearAnnotations();
				requestJsonData("/execute","POST",
						{
							input: editor.getValue(),
							language: languageInit
						},runSuccess);
			}else{
				//socketConnect(socketNum);
				alert('다시 시도해주세요!');
			}
		}
		else if(languageInit=="mysql") {
			
			if(dbconnectInit==true){
			$('#console').html('');
			editor.getSession().clearAnnotations();
			requestJsonData('/query', 'POST', {
				query : editor.getValue()
			}, sussecesSql);
			}
			else {
				dialog.dialog("open");
			}

		}
			
		})
		
	$('#stop').click(function(){
		
		requestJsonData("/stop","POST",{});
	})


	

	//$('#codeContainer').load('main_c.html');

		
		$('#inputArea').keypress(function(event){
			if(event.keyCode=='13'){
				
				var inputVal=$('#inputArea textarea').val();
				inputVal=inputVal.replace(/ /g, '&nbsp');
				inputVal=inputVal.replace(/</g, '&lt;');
				inputVal=inputVal.replace(/>/g, '&gt;');
				$('#console').html(function(index,html){
					
					return html+inputVal+"<br>";
				})
				
				var console=document.getElementById('console');
				console.scrollTop=console.scrollHeight;
				
				$('#inputArea textarea').val('');
				
				requestJsonData("/input","POST",{input:inputVal});
				return false;
			}
			
		})
		
		$('#theme li').click(function(){
			
			var themeVal=$(this).text();
			if(themeVal=='default'){
				
				themeVal='sqlserver';
				
			}
			
			editor.setTheme('ace/theme/'+themeVal);
			$('#theme').find('.selected').removeClass('selected');
			$(this).addClass("selected");
			requestJsonData("/userSetting/theme","GET",{theme:themeVal});
			themeInit=themeVal;
			
		})
		$('#font li').click(function(){
			var editorDiv = document.getElementById("editor"); 
			editorDiv.style.fontSize=$(this).text();
			requestJsonData("/userSetting/font","GET",{font:$(this).text()});
			fontInit=$(this).text();
			$('#font').find('.selected').removeClass('selected');
			$(this).addClass("selected");
		})
		$('#lang li').click(function(){
			if(confirm('변경하면 데이터는 사라집니다.\n변경하시겠습니까?')){
				
				var lang;
				switch($(this).text()){
				
				
				case 'C':
					$('#codeContainer').html('');
					$('#codeContainer').load('main_c.html');
					$('#stop').show();
					$('#inputArea').show();
					$('#connect').hide();
					$('#disconnect').hide();
					lang="c_cpp";
					languageInit="c";
					break;
				case 'JAVA':
					$('#codeContainer').html('');
					$('#codeContainer').load('main_java.html');
					$('#stop').show();
					$('#inputArea').show();
					$('#connect').hide();
					$('#disconnect').hide();
					lang="java";
					languageInit="java";
					break;
				case 'HTML':
					$('#codeContainer').html('');
					$('#codeContainer').load('main_html.html');
					$('#stop').hide();
					$('#inputArea').hide();
					$('#connect').hide();
					$('#disconnect').hide();
					lang="html";
					languageInit="html";
					break;
				case 'MYSQL':
					$('#codeContainer').html('');
					$('#codeContainer').load('main_mysql.html');
					$('#stop').hide();
					$('#inputArea').hide();
					lang="mysql";
					languageInit="mysql";
					
				}
				$('#lang').find('.selected').removeClass('selected');
				$(this).addClass("selected");
				
				requestJsonData("/userSetting/language","GET",{language:lang});
			}
			
		})
		$('#lang,#theme,#font,#manage,#settingImg').click(function(){
			
			if($(this).attr('id')=='settingImg'){
				if($('#manage ul').css('display')=='block'){
					$('#manage').css('background-color','inherit');
				}else{
					$('#manage').css('background-color','#E7E7E7');
				}
				
				
			}else{
				if($(this).find('ul').css('display')=='block'){
					$(this).css('background-color','inherit');
				}else{
					$(this).css('background-color','#E7E7E7');
				}
				
			}
			
		})
		
		
		
		$(document).keydown(function(event){
			if(event.keyCode==116){
				
				if(!confirm('새로고침하면 데이터는 사라집니다.\n새로고침하시겠습니까?')){
					return false;
				}else{
					location.reload();
				}
			}
		})
		
		$('body').click(function(event){
			
			var targetId=event.target.getAttribute('id');
			if(targetId=='settingImg'){
				targetId='manage';
			}
			if(targetId !='lang' && targetId !='theme' && targetId !='font' && targetId !='manage'){
				$.each($('#header ul'),function(key,value){
					if($(this).css('display')=='block'){
						$(this).css('display','none');
						$(this).parent().css('background-color','inherit');
					}
				})
			}else if(targetId =='lang' || targetId =='theme' || targetId =='font' || targetId =='manage'){
				
				$.each($('#header div'),function(key,value){
					if($(this).attr('id')!=targetId){
						$(this).find('ul').css('display','none');
						$(this).css('background-color','inherit');
					}
						
					
				})
				$('#'+targetId+' ul').toggle();
				
				
			}
			
			
			
		})
		
	})