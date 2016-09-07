var compileSuccess=function(data){
	
	if(data.errorLine != undefined && data.errorLine !=null){
		editor.getSession().setAnnotations([{
		    row: data.errorLine-1,
		    text: data.text,
		    type: "error" // also warning and information
		}]);
	}
}

$(document).ready(function(){
	
	var rCompiler=false;
	$('#codeContainer').keyup(function(){
		
		
		if(!rCompiler){
			rCompiler=true;
			var checkValue=editor.getValue();
			setTimeout(function(){  
				
		        if(rCompiler && checkValue!=editor.getValue()){
		        	
		        	rCompiler=false;
		        	editor.getSession().clearAnnotations();
		        	requestJsonData("/compile","POST",{input:editor.getValue(),language:languageInit},compileSuccess);
		        }else if(rCompiler){
		        	rCompiler=false;
		        }  
		    },5000);  
		}
	})
	
	$('#codeContainer').css('height',$('body').outerHeight()-($('#header').outerHeight()+$('#inputArea').outerHeight()));
	

	var editor = ace.edit("editor");                   // the editor object
	var doc = editor.getSession().getDocument();

	editor.on("change", function() {
	    
	    editor.resize();
	});

	$('#console').resize(function(){
		
		editor.resize();
	})
	//editor.setValue("fff");
	
	
	
})

