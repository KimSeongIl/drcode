package kr.ac.skhu.drcode.security;

import kr.ac.skhu.drcode.security.SecurityUserDetailServiceCustom;

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
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(new SecurityUserDetailServiceCustom());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/").hasRole("USER")
			.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/")
				.usernameParameter("username")
				.passwordParameter("pw")
			.and()
				.csrf()
					.disable()
			.httpBasic();
	}
}