package kr.ac.skhu.drcode.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.messaging.simp.SimpMessagingTemplate;

public class ThreadedCommand extends Thread{

	private SimpMessagingTemplate template;
	private CommandDto commandDto;
	private String userNumber;
	private String command;
	private HttpSession session;
	private BufferedWriter writer;
	private StringBuffer outString=new StringBuffer();
	private boolean stopFlag=false;

	private final static String DRCODE_PATH="/home/centos/drcode/user/";
	
	public ThreadedCommand(SimpMessagingTemplate template,CommandDto commandDto,String userNumber,HttpSession session){
		this.template=template;
		this.commandDto=commandDto;
		this.userNumber=userNumber;
		this.session=session;
	}
	public static boolean isAlive(Process p) {
		try {
			p.exitValue();
			return false;
		}
		catch (IllegalThreadStateException e) {
			return true;
		}
	}
	@Override
	public void run() {


		Map<String,Object> msg=new HashMap<>();
		String input=commandDto.getInput();
		input=input.replaceAll("'", "'\\\\''");
		input=input.replaceAll("\\\\n", "\\\\\\\\n");
		input=input.replaceAll("\\\\r","\\\\\\\\r");
		input=input.replaceAll("\\\\t", "\\\\\\\\t");
		String language=commandDto.getLanguage();
		commandDto=new CommandDto();


		if("c".equals(language)){

			command=("(echo -e '"+input+"' > "+DRCODE_PATH+userNumber+"/Main.c) && (gcc -w -o "+DRCODE_PATH+userNumber+"/Main -include "+DRCODE_PATH+"header.c "+DRCODE_PATH+userNumber+"/Main.c ) &&("+DRCODE_PATH+userNumber+"/Main)");

		}else if("java".equals(language)){

			command=("(echo -e '"+input+"' > "+DRCODE_PATH+userNumber+"/Main.java) && (javac "+DRCODE_PATH+userNumber+"/Main.java) && cd "+DRCODE_PATH+userNumber+" ;java Main");
		}
		String[] arr={"/bin/sh","-c",command};
		ProcessBuilder builder = new ProcessBuilder(arr);
		//builder.redirectErrorStream(false); // so we can ignore the error stream
		try{
			Process process = builder.start();
			InputStream out = process.getInputStream();
			/*OutputStream in = process.getOutputStream();*/
			writer = new BufferedWriter(
					new OutputStreamWriter(process.getOutputStream()));

			byte[] buffer = new byte[4000];

			long start = System.currentTimeMillis();
			long end = 0;
			while (isAlive(process) && !stopFlag && (( end - start )/1000.0)<20) {

				int no = out.available();
				if (no > 0) {
					int n = out.read(buffer, 0, Math.min(no, buffer.length));
					outString.append(new String(buffer, 0, n));
				}

				System.out.println(outString.toString()+" :leng:"+outString.toString().length());
				if(outString.toString().length()>0){
					msg.put("msg", outString.toString().replaceAll("\\n", "<br>"));
					template.convertAndSendToUser(userNumber,"/topic/coding",msg);
					outString=new StringBuffer();
				}

				try {
					Thread.sleep(1000);

				}
				catch (InterruptedException e) {

				}
				end = System.currentTimeMillis();
			}

			int no = out.available();
			if (no > 0) {
				int n = out.read(buffer, 0, Math.min(no, buffer.length));
				outString.append(new String(buffer, 0, n));
			}
			System.out.println(outString.toString()+" :middle:"+outString.toString().length());
			if(outString.toString().length()>0){
				msg.put("msg", outString.toString().replaceAll("\\n", "<br>"));
				template.convertAndSendToUser(userNumber,"/topic/coding",msg);
				outString=new StringBuffer();
			}
			System.out.println("exit....");



			InputStream error= process.getErrorStream();

			InputStreamReader eir=new InputStreamReader(error);
			BufferedReader ebr=new BufferedReader(eir);

			StringBuffer strbuf = new StringBuffer (); 

			String line;
			while((line=ebr.readLine())!=null){
				strbuf.append(line+"<br>");
			}
			error.close();
			eir.close();
			ebr.close();

			System.out.println(strbuf.toString()+" :error:"+strbuf.toString().length());
			if(strbuf.toString().length()>0){

				String err=strbuf.toString().replaceAll("\\n", "<br>");
				err=err.replaceAll(DRCODE_PATH+userNumber+"/", "");
				if("c".equals(language)){

					Map<String,Object> errorParam=CommandExecute.getCError(err);

					if(errorParam !=null){
						msg.put("errorLine", errorParam.get("errorLine"));
						msg.put("text", errorParam.get("text"));
					}

				}else if("java".equals(language)){

					Map<String,Object> errorParam=CommandExecute.getJavaError(err);
					
					if(errorParam !=null){
						msg.put("errorLine", errorParam.get("errorLine"));
						msg.put("text", errorParam.get("text"));
					}

				}
				msg.put("msg", err);
				template.convertAndSendToUser(userNumber,"/topic/coding",msg);
				strbuf=new StringBuffer();
			}

			session.setAttribute("process", null);
			out.close();
			process.destroy();

		}catch(Exception e){
			e.printStackTrace();
			msg.put("msg", e.getMessage());
			template.convertAndSendToUser(userNumber,"/topic/coding",msg);
		}


	}

	


	public BufferedWriter getWriter(){
		return writer;
	}
	public String getOutput(){

		return outString.toString();
	}
	public void input(String str) throws IOException{

		writer.write(str);

		writer.newLine();

		writer.flush();

	}

	


	public void exit(){
		stopFlag=true;
	}
}