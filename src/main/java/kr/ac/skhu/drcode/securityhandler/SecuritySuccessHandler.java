package kr.ac.skhu.drcode.securityhandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.skhu.drcode.security.SecurityUserDetailsCustom;
import kr.ac.skhu.drcode.util.JsonUtil;

@Component
public class SecuritySuccessHandler  implements AuthenticationSuccessHandler{
   
   @Override
   public void onAuthenticationSuccess(HttpServletRequest requset, HttpServletResponse response
         ,Authentication authentication) throws IOException, ServletException{
      
      response.setStatus(HttpServletResponse.SC_OK);
      
      List<GrantedAuthority> grantList = (List<GrantedAuthority>)authentication.getAuthorities();
     
      System.out.println(grantList);
      System.out.println(((SecurityUserDetailsCustom)authentication.getPrincipal()).getId());
      System.out.println((List<GrantedAuthority>)authentication.getAuthorities());
      
      ObjectMapper mapper = new ObjectMapper();
      Map<String,Object> json = new HashMap<String,Object>();
      json.put("userId",((SecurityUserDetailsCustom)authentication.getPrincipal()).getId());
      json.put("username", authentication.getName());
      json.put("userRoles", grantList.toArray());
      
      String jsonstr =mapper.writeValueAsString(JsonUtil.putSuccessJsonContainer(json));
      
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().write(jsonstr);
      
      response.sendRedirect("/user/"+authentication.getName());
      //mysuperscript.html
      
      /*
      int authNum=checkAuthority(grantList);         
      switch(authNum){
      case 1: 
         response.sendRedirect("/prof");
         break;
      case 2:
         response.sendRedirect("/prof");
         break;
      case 3:
         response.sendRedirect("/admin");
      }      */
   }
   
   
   public int checkAuthority(List<? extends GrantedAuthority> grantList){
      
      String auth = grantList.get(0).getAuthority();
      
      if(auth.equals("ROLE_STUDENT")) 
         return 1;
      else if(auth.equals("ROLE_PROFESSOR")) 
         return 2;
      else if(auth.equals("ROLE_ADMIN")) 
         return 3;
      else
         return 0;      

   }
}