package kr.ac.skhu.drcode.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
   
   @Autowired
   private UserService userService;

   @RequestMapping(value="/tt/{id}")
   public UserDto getUser(@PathVariable int id){
      
      UserDto user=userService.getUser(id);

      System.out.println(user.getUserNumber());
      
      return user;
   }
   
   @RequestMapping(value="/user/{userNumber}")
   public UserDto getUserByUserNumber(@PathVariable String userNumber){
            
      System.out.println("tt2컨트롤러");
      UserDto user =userService.getUserByUserNumber(userNumber);
      System.out.println(user);
      return user;
   }
   
   @RequestMapping(value="/user/insert/" ,method=RequestMethod.POST)
   public UserDto insertUser(@RequestBody UserDto user){
      
      System.out.println(user);
      System.out.println("number:"+user.getUserNumber());
      System.out.println(user.getAuth());
      
      userService.insertUser(user.getUserNumber(),user.getPassword()
            ,user.getEmail(),user.getAuth());
      
      return user;
      
   }
  
}