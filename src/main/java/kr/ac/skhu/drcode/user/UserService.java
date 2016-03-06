package kr.ac.skhu.drcode.user;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserService {

	 private final UserRepository userRepository;
	 
	 @Autowired
	 public UserService(UserRepository userRepository) {
		 this.userRepository = userRepository;
	 }
	
	@Autowired
	private DozerBeanMapper dozer;
	
	
	public UserDto getUser(int id){
		
		UserEntity entity=userRepository.findOne(id);
		UserDto user=new UserDto();
		user.setId(entity.getId());
		user.setPassword(entity.getPassword());
		user.setAuth(entity.getAuth());
		user.setEmail(entity.getEmail());
	
		
		
		return user;
	}
}
