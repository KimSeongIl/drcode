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
		
		int userNumber = Integer.parseInt(username);
		System.out.println("유저넘버:"+userNumber);
		
		UserDto drUser = userService.getUser(userNumber);
		System.out.println(drUser);
		
		
		//status 미사용시
		//SecurityUserDetailsCustom user = new SecurityUserDetailsCustom(drUser);
		SecurityUserDetailsCustom user = new SecurityUserDetailsCustom(drUser,0);
		return user;
	}
}