package kr.ac.skhu.drcode.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class CommandExecute {

	private final static String DRCODE_PATH="/home/centos/drcode/user/";
	
	public static Map<String,Object> compile(String input,String language,String userNumber)throws IOException{
		input=input.replaceAll("'", "'\\\\''");
		input=input.replaceAll("\\\\n", "\\\\\\\\n");
		input=input.replaceAll("\\\\r","\\\\\\\\r");
		input=input.replaceAll("\\\\t", "\\\\\\\\t");
		
		
		String command="";
		if("c".equals(language)){
			command=("(echo -e '"+input+"' > "+DRCODE_PATH+userNumber+"/Main.c) && (gcc -w -o "+DRCODE_PATH+userNumber+"/Main -include "+DRCODE_PATH+"header.c "+DRCODE_PATH+userNumber+"/Main.c ) ");

			return getCError(shellCmd(command).getError());
		}else if("java".equals(language)){

			command=("(echo -e '"+input+"' > "+DRCODE_PATH+userNumber+"/Main.java) && (javac "+DRCODE_PATH+userNumber+"/Main.java) ");
			return getJavaError(shellCmd(command).getError());
		}
		
		
	
		return null;
		

		
	}

	public static CommandDto shellCmd(String command) throws IOException{
		Runtime runtime = Runtime.getRuntime();
		String[] arr={"/bin/sh","-c",command};
		Process process = runtime.exec(arr);
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		StringBuffer strbuf = new StringBuffer ( );
		
		while((line = br.readLine()) != null) {
			strbuf.append(line +"<br>");;
		}
		is.close();
		isr.close();
		br.close();
		CommandDto commandDto=new CommandDto();
		
		commandDto.setOutput(strbuf.toString());
		


		InputStream error= process.getErrorStream();

		InputStreamReader eir=new InputStreamReader(error);
		BufferedReader ebr=new BufferedReader(eir);

		strbuf = new StringBuffer ( );  
		while((line=ebr.readLine())!=null){
			strbuf.append(line+"<br>");
		}
		error.close();
		eir.close();
		ebr.close();
		
		commandDto.setError(strbuf.toString());
		

		return commandDto;
	}
	
	public static Map<String,Object> getCError(String err){


		String reg="Main.c:";
		Map<String,Object> param=new HashMap<>();
		String text=null;
		StringBuffer findSyn=new StringBuffer();
		int num=reg.length();
		int findNum=0;
		
		
		int find=err.indexOf(reg);
		
		
		if(find!=-1){
			


			while(find!=-1){
				char tmp=err.charAt(find+num);
				findNum++;
				num=reg.length();
				if(findNum==2){
					while(isNum(tmp)){

						findSyn.append(tmp);

						num++;
						if(find+num <err.length()){
							tmp=err.charAt(find+num);
						}else{
							break;
						}

					}

					
					break;
					

				}else if(findNum>2){
					break;
				}

				find=err.indexOf(reg,find+1);

			}



		}

		if(findSyn.toString().length()>=1){
			text=err.substring(find+num,err.indexOf("<br>",find+num));
			param.put("errorLine", findSyn.toString());
			param.put("text", text);
			
			return param;
		}else{
			return null;
		}
	}
	
	public static Map<String,Object> getJavaError(String err){
		String reg="Main.java:";
		Map<String,Object> param=new HashMap<>();
		String text=null;
		int num = 0;
		
		int find=err.indexOf(reg);
		
		StringBuffer findSyn=new StringBuffer();

		if(find!=-1){
			num=reg.length();



			char tmp=err.charAt(find+num);	
			
			while(isNum(tmp)){
				findSyn.append(tmp);
				num++;
				if(find+num <err.length()){
					tmp=err.charAt(find+num);
				}else{
					break;
				}

			}
			
			





			
			

		}
		
		if(findSyn.toString().length()>=1){
			text=err.substring(find+num+1,err.indexOf("<br>",find+num));
			param.put("errorLine", findSyn.toString());
			param.put("text", text);
			
			return param;
			 
		}else{
			return null;
		}

	}
	private static boolean isNum(Character ch){
		String str=ch.toString();


		try{
			Integer.parseInt(str);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
}
