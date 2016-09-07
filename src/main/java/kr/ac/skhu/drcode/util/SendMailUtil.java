package kr.ac.skhu.drcode.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendMailUtil {

	
	@Value("${email.id}")
	private String id;
	@Value("${email.pw}")
	private String pw;
	
	public void sendMail(String toAddr, String tmpPassword) throws MessagingException{
		
		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true");     // gmail은 무조건 true 고정
	    p.put("mail.smtp.host", "smtp.gmail.com");      // smtp 서버 주소
	    p.put("mail.smtp.auth","true");                 // gmail은 무조건 true 고정
	    p.put("mail.smtp.port", "587");                 // gmail 포트
	           
	    Authenticator auth = new MyAuthentication(id,pw);
	    
	    
	    Session session = Session.getDefaultInstance(p, auth);
	    MimeMessage msg = new MimeMessage(session);
	    
	    session.setDebug(true);
	    
	    
	    	
	    	msg.setSentDate(new Date());
	    	
	    	//from
	    	InternetAddress from = new InternetAddress();
	    	from = new InternetAddress("skhudrcode<skhudrcode@gmail.com>");
	    	msg.setFrom(from);
	    	
	    	//to
	    	InternetAddress to = new InternetAddress(toAddr);
	    	msg.setRecipient(Message.RecipientType.TO, to);
	    	
	    	//title
	    	msg.setSubject("dr.code 임시 비밀번호 입니다.", "UTF-8");
	    	
	    	//content
	    	msg.setText("임시 비밀번호 :"+tmpPassword+"입니다.","UTF-8");
	    	
	    	//email-header
	    	msg.setHeader("content-Type", "text/html");
	    	
	    	//send
	    	javax.mail.Transport.send(msg);
	    
	    
	}
}

class MyAuthentication extends Authenticator{ //google계정: skhudrcode@gmail.com/drcode123
	
	PasswordAuthentication pa;
	
	
	public MyAuthentication(String id,String pw){
		
		pa=new PasswordAuthentication(id,pw);
	}
	
	
	
	public PasswordAuthentication getPasswordAuthentication(){
		return pa;
	}
	
	
	
	
}