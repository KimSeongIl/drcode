package kr.ac.skhu.drcode.user;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
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
