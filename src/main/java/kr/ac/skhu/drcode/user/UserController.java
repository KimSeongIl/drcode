package kr.ac.skhu.drcode.user;



import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.skhu.drcode.exception.DuplicatedUserException;
import kr.ac.skhu.drcode.exception.UpdateUserNotNullError;
import kr.ac.skhu.drcode.exception.UserNotFoundException;
import kr.ac.skhu.drcode.usersetting.UserSettingService;
import kr.ac.skhu.drcode.util.JsonUtil;
import kr.ac.skhu.drcode.util.SendMailUtil;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;





@RestController
public class UserController {
   
	@Autowired
	private UserService userService;  
	@Autowired
	private UserSettingService userSettingService;
	@Autowired
	private SendMailUtil sendMailUtil;
	@Autowired
	@Qualifier("authenticationManager")
	AuthenticationManager authenticationManager;

	@Autowired
	SecurityContextRepository scRepository;
	
	
   
   @RequestMapping(value="/tt/{id}")
   public UserDto getUser(@PathVariable int id){
      
      UserDto user=userService.getUser(id);

      System.out.println(user.getUserNumber());
      
      return user;
   }
   
   @RequestMapping(value="/user/{userNumber}", method=RequestMethod.GET)
   public UserDto getUserByUserNumber(@PathVariable String userNumber){

	  UserDto user =userService.getUserByUserNumber(userNumber);
      System.out.println(user);
      
   
      return user;
   }
   
   
   
   @RequestMapping(value="/user", method=RequestMethod.GET)
   public UserDto getUserData() throws UserNotFoundException{
	   
	   
	   Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
	   
	   UserDto user=userService.getUserInfoByUserNumber(authentication.getName());
	   

	   return user;
   }
 
   @RequestMapping(value="/user" ,method=RequestMethod.POST)
   public UserDto insertUser(UserDto user) throws DuplicatedUserException, IOException{
     
      userService.insertUser(user);
      userSettingService.insertUserSettingUserNumber(user); 
      
      return user;
   }
   
   
   @RequestMapping(value="/user", method=RequestMethod.PUT)
   public UserDto updateUser(@RequestBody UserDto user) throws UpdateUserNotNullError{
	   
	   Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
	   if(!authentication.getName().equals(user.getUserNumber())){
	   				throw new UpdateUserNotNullError("사용자 정보가 일치하지 않습니다.");
	   }
	 
	   userService.updateUser(user);
	   
	   return user;
   }
   
      
   
   
   @RequestMapping(value="/user/password", method=RequestMethod.PUT)
   public UserDto getUserPassword(@RequestBody UserDto user) throws UserNotFoundException, MessagingException{
	   
	   
	   
	   int userId=userService.getUserByUserNumberAndEmail(user);
	   
	   String tmpPassword=userService.getTmpPassword().toString();
	   sendMailUtil.sendMail(user.getEmail(),tmpPassword); 
	   
	   
	   userService.updateUserPassword(tmpPassword,userId);
	   return user;
   }
   
   
   
   //회원목록
   @RequestMapping(value="/users", method=RequestMethod.GET)
   public Map<String,Object> getUsers(UserDto user, @RequestParam("page") int thispage)  {
	   
	   
	   return userService.getUsers(user, thispage);
   }
   
   
   
   //회원삭제
   @RequestMapping(value="/users", method=RequestMethod.DELETE)
   public UserDto deleteUsers(@RequestBody UserDto user)  {
	
	  userService.deleteUsers(user);
	  
	  return user;
   }
   
   @RequestMapping(value="/users/department", method=RequestMethod.GET)
   public Map<String, Object> getProfessorsByDepartmentId(UserDto user, @RequestParam("page") int page) {
	   user.setAuth("professor");
	   
	   return userService.getProfessorsByDepartmentId(user, page);
	   
   }
   
   @RequestMapping(value="/users/professor", method=RequestMethod.GET)
   public Map<String, Object> getProfessors(@RequestParam("page") int thispage){
	   
	   UserDto user=new UserDto();
	   user.setAuth("professor");
	   
	   return userService.getUsers(user, thispage);
	   
   }
   
   
   @RequestMapping(value="/loginApp", method=RequestMethod.POST)
	public UserDto login(@RequestParam("username") String username,
			@RequestParam("password") String password,HttpServletRequest request,HttpServletResponse response) throws UserNotFoundException {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		


		try {
			Authentication auth = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			scRepository.saveContext(SecurityContextHolder.getContext(), request, response);
			request.getSession().setAttribute("aaa", username);
			System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
			
			UserDto user = userService.getUserData(username);	
			return user;
		} catch (BadCredentialsException e) {
			return null;
		}
	}

   
   

}