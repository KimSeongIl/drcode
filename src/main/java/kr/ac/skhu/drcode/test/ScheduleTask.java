package kr.ac.skhu.drcode.test;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleTask {

    @Autowired
    private SimpMessagingTemplate template;

  
    // this will send a message to an endpoint on which a client can subscribe
   
    public void trigger() {
        // sends the message to /topic/message
    	System.out.println(11111);
    	try{
    	System.out.println("hehehe"+ template);
        this.template.convertAndSend("/topic/message", "Date: " + new Date());
        
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

}