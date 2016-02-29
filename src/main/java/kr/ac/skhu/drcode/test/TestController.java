package kr.ac.skhu.drcode.test;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import kr.ac.skhu.drcode.exception.TestException;
import kr.ac.skhu.drcode.util.JsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.sockjs.transport.session.WebSocketServerSockJsSession;

@RestController
public class TestController {



	@Autowired
	private ScheduleTask task;
	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/hello")
	@SendToUser("/topic/greetings")
	public Greeting greeting(HelloMessage message,SimpMessageHeaderAccessor headerAccessor) throws Exception {


		Thread.sleep(3000); // simulated delay
		System.out.println(headerAccessor.getSessionId());

		
		
		
		
		return new Greeting(headerAccessor.getSessionId());
	}

	@MessageMapping("/hello2")
	public void hello2(HelloMessage message,SimpMessageHeaderAccessor headerAccessor) throws Exception {


		Thread.sleep(3000); // simulated delay
		System.out.println(headerAccessor.getSessionId());
		
		template.convertAndSendToUser(headerAccessor.getSessionId(),"/topic/greetings", "dkdkdk"+headerAccessor.getSessionId());
		
		
		
	}
	
	@RequestMapping("/test22")
	public TestDto test22() throws InterruptedException{


		Thread.sleep(3000);
		task.trigger();

		System.out.println(22);
		return null;
	}

}