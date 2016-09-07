package kr.ac.skhu.drcode.usersetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSettingController {

	@Autowired
	private UserSettingService userSettingService;
	
	@RequestMapping(value="/userSetting/{value}",method=RequestMethod.GET)
	public void updateUserSetting(@PathVariable String value,UserSettingDto userSetting){
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userName=authentication.getName();
		if(userName.equals("anonymousUser")){
			userName=userSetting.getUserNumber();
		}
		userSettingService.updateUserSetting(value, userSetting, userName);
	}
	
}
