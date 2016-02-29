package kr.ac.skhu.drcode.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.ac.skhu.drcode.user.UserDto;

public class SecurityUserDetailsCustom implements UserDetails,CredentialsContainer {
	
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	
	public SecurityUserDetailsCustom(UserDto drUser){
		
		this.username=((Integer)drUser.getUserNumber()).toString();
		this.password=drUser.getPassword();
		this.authorities=getAuthorities(drUser);		
		this.accountNonExpired=true;
		this.accountNonLocked=true;
		this.credentialsNonExpired=true;
		this.enabled=true;
		
	}

	/*
	 * User상태에 따른 생성자
	 * 1.계정활성(true:enabled)
	 * 2.계정만료(true:none expired)
	 * 3.비밀번호만료(true:credential none expired)
	 * 4.계정잠금(true:account none locked)
	 */
	public SecurityUserDetailsCustom(UserDto drUser,int status){
		
		this.username=((Integer)drUser.getUserNumber()).toString();
		this.password=drUser.getPassword();
		this.authorities=getAuthorities(drUser);
		this.accountNonExpired=true;
		this.accountNonLocked=true;
		this.credentialsNonExpired=true;
		this.enabled=true;
		
		switch(status){
		case 1:
			this.enabled=false;
			break;
		case 2:
			this.accountNonExpired=false;
			break;
		case 3:
			this.credentialsNonExpired=false;
			break;
		case 4:
			this.accountNonLocked=false;
			break;
		default:
			
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	/*
	 * UserDto객체의 auth컬럼에 따른 ROLE_권한 부여 메소드
	 * default: ROLE_USER 권한
	 * add: ROLE_+@
	 */
	private Collection<? extends GrantedAuthority> getAuthorities(UserDto drUser) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		String authority = drUser.getAuth().toUpperCase();
		String role = "ROLE_" + authority;

		authorities.add(new SimpleGrantedAuthority(role));

		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void eraseCredentials() {
	}
}