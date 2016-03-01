package kr.ac.skhu.drcode.security;

import kr.ac.skhu.drcode.security.SecurityUserDetailServiceCustom;
import kr.ac.skhu.drcode.securityhandler.SecurityFailureHandler;
import kr.ac.skhu.drcode.securityhandler.SecuritySuccessHandler;
import kr.ac.skhu.drcode.user.UserRepository;
import kr.ac.skhu.drcode.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter {
	
	@Autowired SecuritySuccessHandler securitySuccessHandler;
	@Autowired SecurityFailureHandler securityFailureHandler;
	
	@Autowired UserRepository userRepository;
	@Autowired UserService userService;
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	
		auth
			.userDetailsService(new SecurityUserDetailServiceCustom(userRepository,userService));
			//.passwordEncoder(new BCryptPasswordEncoder());


	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/resources/static/**").permitAll()
				.antMatchers("/adminpagetest.html").hasRole("USER")
			.and()
			.formLogin()
				.loginPage("/")
				.loginProcessingUrl("/loginProcessingUrltest")
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(securitySuccessHandler)
				.failureHandler(securityFailureHandler)
				.permitAll()
			.and()
				.csrf()
					.disable()
			.httpBasic();
	}
}