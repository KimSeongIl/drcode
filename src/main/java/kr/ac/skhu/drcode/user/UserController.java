package kr.ac.skhu.drcode.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/tt/{id}")
	public UserDto getUser(@PathVariable int id){
		
		return userService.getUser(id);
	}
}
