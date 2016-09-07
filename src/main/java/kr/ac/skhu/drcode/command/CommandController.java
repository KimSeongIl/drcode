package kr.ac.skhu.drcode.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommandController {

	@Autowired
	private SimpMessagingTemplate template;
	@Autowired
	private CommandService commandservice;
	
	@RequestMapping(value="/compile",method=RequestMethod.POST)
	public Map<String,Object> compile(CommandDto commandDto) throws IOException{
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userNumber=authentication.getName();
		
		
		return CommandExecute.compile(commandDto.getInput(),commandDto.getLanguage(),userNumber);
	}

	@RequestMapping(value="/execute",method=RequestMethod.POST)
	public CommandDto execute(CommandDto commandDto,HttpServletRequest request) throws IOException{

		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userNumber=authentication.getName();
		if(userNumber.equals("anonymousUser"))
			userNumber=commandDto.getUserName();
		System.out.println(userNumber);

		HttpSession session=request.getSession();
		ThreadedCommand thread=new ThreadedCommand(template,commandDto,userNumber,session);

		thread.start();

		session.setAttribute("process", thread);

		/*
		if(commandDto.getError()!=null && !"".equals(commandDto.getError().trim())){
			commandDto.setResult("error");
		}
		 */



		return commandDto;
	}

	@RequestMapping(value="/input",method=RequestMethod.POST)
	public CommandDto input(CommandDto commandDto,HttpServletRequest request) throws IOException{

		HttpSession session=request.getSession();
		ThreadedCommand thread=(ThreadedCommand)session.getAttribute("process");

		if(thread ==null || !thread.isAlive()){
			
		}else{
			thread.input(commandDto.getInput());
		}

		return commandDto;
	}

	@RequestMapping(value="/stop", method=RequestMethod.POST)
	public void stop(HttpServletRequest request,HttpServletResponse response){

		HttpSession session=request.getSession();
		ThreadedCommand thread=(ThreadedCommand)session.getAttribute("process");
		System.out.println("-----------------------");
		System.out.println("thread : "+ thread);
		if(thread ==null || !thread.isAlive()){
			
		}else{
			System.out.println("------------------------------ffff");
			thread.exit();

			session.setAttribute("process", null);
			System.out.println("exit..........");
		}
		response.setStatus(200);
	}


	@RequestMapping(value="/query" ,method=RequestMethod.POST)
	public Object queryExecute(@RequestParam("query") String query) throws IOException, ClassNotFoundException, SQLException{
		
		
		Object result = commandservice.queryExecute(query);
		
		return result;

	}
	
	@RequestMapping(value="/dblogin", method=RequestMethod.POST)
	public void  dbLogin(DatabaseDto database, HttpSession session) throws ClassNotFoundException, SQLException {
		commandservice.dbLogin(database);
		session.setAttribute("userName", database.getUserName());
	}
	
	@RequestMapping(value="/disconnect", method=RequestMethod.POST)
	public void dbDisconnect(HttpSession session) throws SQLException {
		commandservice.dbDisconnect();
		session.removeAttribute("userName");
	}
	
	@RequestMapping(value="/sessionConfirm", method=RequestMethod.POST)
	public boolean sessionConfirm(HttpSession session) {
		
		if(session.getAttribute("userName") == null) {
			return false;
		}
		
		return true;
	}
}



