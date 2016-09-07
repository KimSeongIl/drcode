package kr.ac.skhu.drcode.common;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class WebService {
	
	/*
	 * 메인권한반환
	 */
	public String getMainAuthority(Collection<? extends GrantedAuthority> authList){
		
		
		
		String mainAuth=((List)authList).get(0).toString();
		
		
		return mainAuth;
		
		
	}
	/*
	 *메인권한별 메인페이지 반환 
	 */
	public String getMainPageByMainAuth(String mainAuth){
		String mainPage=null;
		
		switch(mainAuth){
			case "ROLE_ANONYMOUS":
				mainPage="index";
				break;
			case "ROLE_STUDENT":
				mainPage="main";
				break;
			case "ROLE_PROFESSOR":
				mainPage="professor_main";
				break;
			case "ROLE_ADMIN":
				mainPage="admin_main";
				break;
		}
		return mainPage;
		
	}

}
