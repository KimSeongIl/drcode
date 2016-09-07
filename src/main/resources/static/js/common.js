var requestJsonDataPUT=function(requestUrl,requestParam,successFunction,errorFunction){
	
	$.ajax({
		url : requestUrl,
		type : 'PUT',
		async: true,
		data : JSON.stringify(requestParam),
		dataType : "json",
		timeout: 10000,	
		contentType:"application/json",
		
		beforeSend:function(){
	        $('.wrap-loading').removeClass('display-none');
	    },
	    complete:function(){
	        $('.wrap-loading').addClass('display-none');
	    },
		success : function(data){
			
			if(successFunction!=undefined){
				successFunction(data);
			}
			
		},
		error : function(request,status,error){
			
			var err=JSON.parse(request.responseText);
			
			
			
			alert(err.resData[0].errorMsg);
			if(errorFunction!=undefined){
				errorFunction();
			}
				
	        $('.wrap-loading').addClass('display-none');
		},
		fail : function() {
			alert("인터넷 연결 상태를 확인해주세요.");
	        $('.wrap-loading').addClass('display-none');
		}
	});
}


var requestJsonDataDELETE=function(requestUrl,requestParam,successFunction,errorFunction){
	
	$.ajax({
		url : requestUrl,
		type : 'DELETE',
		async: true,
		data : JSON.stringify(requestParam),
		dataType : "json",
		timeout: 10000,	
		contentType:"application/json",
		
		beforeSend:function(){
	        $('.wrap-loading').removeClass('display-none');
	    },
	    complete:function(){
	        $('.wrap-loading').addClass('display-none');
	    },
		success : function(data){
			if(successFunction!=undefined){
				successFunction(data);
			}
			
		},
		error : function(request,status,error){
			
			var err=JSON.parse(request.responseText);
			
			
			
			alert(err.resData[0].errorMsg);
			if(errorFunction!=undefined){
				errorFunction();
			}
				
	        $('.wrap-loading').addClass('display-none');
		},
		fail : function() {
			alert("인터넷 연결 상태를 확인해주세요.");
	        $('.wrap-loading').addClass('display-none');
		}
	});
}

var requestJsonData=function(requestUrl,method,requestParam,successFunction,errorFunction){
	console.log(JSON.stringify(requestParam));
	
	$.ajax({
		url : requestUrl,
		type : method,
		async: true,
		data : requestParam,
		dataType : "json",
		timeout: 10000,
		
		
		beforeSend:function(){
	        $('.wrap-loading').removeClass('display-none');
	    },
	    complete:function(){
	        $('.wrap-loading').addClass('display-none');
	    },
		success : function(data){
			if(successFunction!=undefined){
				successFunction(data);
			}
			
		},
		error : function(request,status,error){
			console.log(request);
			if(request.responseText != '' ){
				var err=JSON.parse(request.responseText);		
				alert(err.resData[0].errorMsg);
			}else{
				alert(status+":"+error);
				console.log(request);
			}
			
			if(errorFunction!=undefined){
				errorFunction();
			}
			$('.wrap-loading').addClass('display-none');
			
		},
		fail : function() {
			alert("인터넷 연결 상태를 확인해주세요.");
	        $('.wrap-loading').addClass('display-none');
		}
	});
}
var requestJsonDataFile=function(requestUrl,requestParam,successFunction){
	
	$.ajax({
		url : requestUrl,
		type : "POST",
		async: true,
		data : requestParam,
		dataType:"json",
		processData: false, 
        contentType:false,
		enctype: 'multipart/form-data',
		timeout: 10000,
		beforeSend:function(){
	        $('.wrap-loading').removeClass('display-none');
	        
	    },
	    complete:function(){
	        $('.wrap-loading').addClass('display-none');
	       
	    },
		success : function(data){
			
			successFunction(data);			
			
		},
		error : function(request,status,error){
			alert("오류가 발생했습니다.\n재시도 또는 다시 접속해주세요.\n\n[오류정보]\n\ncode:"+request.status+"\n\n"+"message:"+request.responseText+"\n\n"+"error:"+error);
	        $('.wrap-loading').addClass('display-none');
		},
		fail : function() {
			alert("인터넷 연결 상태를 확인해주세요.");
	        $('.wrap-loading').addClass('display-none');
		}
	});
}

var requestJsonDataNoLoading=function (requestUrl, method,requestParam, successFunction) {
	$.ajax({
		url : requestUrl,
		type : method,
		async: true,
		data : requestParam,
		dataType : "json",
		timeout: 10000,
		success : function(data){
			
			successFunction(data);
			
		},
		error : function(request,status,error){
			alert("오류가 발생했습니다.\n재시도 또는 다시 접속해주세요.\n\n[오류정보]\ncode:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		},
		fail : function() {
			alert("인터넷 연결 상태를 확인해주세요.");
		}
	});	
}


var pagination=function(pageCount,thisPage,eventName){ // pageCount: 페이지 갯수, thisPage: 현재페이지, eventName : 페이지 목록에 붙일 클래스 이름
	var str="";
	str+="<div class='paginationContainer'>";
	str+="<ul class='pagination'>";
	
	
	
	
	if(pageCount>=1){
		
		var currentPage=Math.floor((thisPage-1)/5);
		
		if(currentPage!=0){
			str+="<li class="+eventName+" page="+((currentPage-1)*5+1)+"><a aria-label='Previous' ><span aria-hidden='true'>&laquo;</span></a></li>";
		}
		
		
		var disabled=thisPage==1? 'disabled' : eventName;
		
		str+="<li class="+disabled+" page="+(Number(thisPage)-1)+"><a aria-label='Previous' ><span aria-hidden='true'>이전</span></a></li>";
		for(var i=(currentPage*5+1);i<=(currentPage*5+5);i++){
			
			if(i<=pageCount)
				str+="<li class='"+(thisPage==i? 'active' : eventName)+" ' page="+i+"><a>"+i+"</a></li>";
		}
		disabled=thisPage==pageCount? 'disabled' : eventName;
		str+="<li class="+disabled+" page="+(Number(thisPage)+1)+"> <a aria-label='Next'><span aria-hidden='true'>다음</span></a></li>";
		if(Math.floor(pageCount/5)!=currentPage){
			str+="<li class="+eventName+" page="+((currentPage+1)*5+1)+"><a aria-label='Previous' ><span aria-hidden='true'>&raquo;</span></a></li>";
		}
	}
		
	str+="</ul>";
	str+="</div>";
	return str;
}








