package kr.ac.skhu.drcode.security;

import kr.ac.skhu.drcode.config.CORSFilter;
import kr.ac.skhu.drcode.security.SecurityUserDetailServiceCustom;
import kr.ac.skhu.drcode.securityhandler.SecurityFailureHandler;
import kr.ac.skhu.drcode.securityhandler.SecurityLogoutSuccessHandler;
import kr.ac.skhu.drcode.securityhandler.SecuritySuccessHandler;
import kr.ac.skhu.drcode.user.UserRepository;
import kr.ac.skhu.drcode.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter {

	@Autowired SecuritySuccessHandler securitySuccessHandler;
	@Autowired SecurityFailureHandler securityFailureHandler;
	@Autowired UserRepository userRepository;
	@Autowired UserService userService;
	@Autowired SecurityLogoutSuccessHandler securityLogoutSuccessHandler;
	@Autowired CORSFilter corsFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth
		.userDetailsService(new SecurityUserDetailServiceCustom(userRepository,userService))
		.passwordEncoder(new BCryptPasswordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/resources/static/**").permitAll()
				.antMatchers("/main.html").hasRole("STUDENT")
				.antMatchers("/admin_main.html").hasRole("ADMIN")
				.antMatchers("/**").permitAll()//.hasRole("USER")
			.and()
			.formLogin()
				.loginPage("/permission.html")
				.loginProcessingUrl("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(securitySuccessHandler)
				.failureHandler(securityFailureHandler)
				.permitAll()
			.and()
				.logout()
					.logoutSuccessHandler(securityLogoutSuccessHandler)
					.logoutUrl("/logout")		
			.and()
				.addFilterBefore(corsFilter,LogoutFilter.class)
					.csrf()
					.disable()
			.httpBasic()
			.and()
				.headers()
					.frameOptions()
						.disable();
	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	};

	@Bean
	public SecurityContextRepository securityContextRepository() {
		return new HttpSessionSecurityContextRepository();
	}


}