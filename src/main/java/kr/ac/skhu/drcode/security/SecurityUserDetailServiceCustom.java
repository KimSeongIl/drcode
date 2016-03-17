package kr.ac.skhu.drcode.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.skhu.drcode.user.UserDto;
import kr.ac.skhu.drcode.user.UserRepository;
import kr.ac.skhu.drcode.user.UserService;

@Component
@Service
public class SecurityUserDetailServiceCustom implements UserDetailsService {

	private final UserRepository userRepository;
	private final UserService userService;

	@Autowired
	public SecurityUserDetailServiceCustom(UserRepository userRepository,UserService userService){
		this.userRepository = userRepository;
		this.userService = userService;
	}

	
	
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		
		UserDto drUser = userService.getUserByUserNumber(username);
		System.out.println(drUser);
		System.out.println("권한:"+drUser.getAuth());
		
		
		//status 미사용시
		//SecurityUserDetailsCustom user = new SecurityUserDetailsCustom(drUser);
		SecurityUserDetailsCustom user = new SecurityUserDetailsCustom(drUser,0);
		
		return user;
	}
}