package kr.ac.skhu.drcode.usersetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.skhu.drcode.user.UserDto;

@Service
public class UserSettingService {

	@Autowired
	private UserSettingRepository userSettingRepository;
	
	public void insertUserSettingUserNumber(UserDto user){
		
		UserSettingEntity userSettingEntity=new UserSettingEntity();
		userSettingEntity.setUserNumber(user.getUserNumber());
		userSettingRepository.save(userSettingEntity);
	}
	

	public void updateUserSetting(String value,UserSettingDto userSetting,String userNumber){

		UserSettingEntity userSettingEntity=userSettingRepository.findByUserNumber(userNumber);
		if(userSettingEntity!=null){
			switch(value){
			case "language":
				userSettingEntity.setLanguage(userSetting.getLanguage());
				break;
			case "theme":
				userSettingEntity.setTheme(userSetting.getTheme());
				break;
			case "font":
				userSettingEntity.setFont(userSetting.getFont());
				break;

			}
			
			userSettingRepository.save(userSettingEntity);
		}


	}
}
