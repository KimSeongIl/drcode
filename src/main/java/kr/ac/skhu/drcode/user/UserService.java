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
		return dozer.map(userRepository.findOne(id),UserDto.class);
	}
}
