package kr.ac.skhu.drcode.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.ac.skhu.drcode.command.CommandExecute;
import kr.ac.skhu.drcode.department.DepartmentEntity;
import kr.ac.skhu.drcode.exception.DuplicatedUserException;
import kr.ac.skhu.drcode.exception.UpdateUserNotNullError;
import kr.ac.skhu.drcode.exception.UserNotFoundException;
import kr.ac.skhu.drcode.subject.SubjectDto;
import kr.ac.skhu.drcode.subject.SubjectEntity;
import kr.ac.skhu.drcode.usersetting.UserSettingEntity;
import kr.ac.skhu.drcode.usersetting.UserSettingRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserService {

    private final UserRepository userRepository;

    
    @Autowired
    public UserService(UserRepository userRepository) {
       this.userRepository = userRepository;
      
    }
    
   
    
   @Autowired 
   private PasswordEncoder passwordEncoder;
   
   @Autowired
   private UserSettingRepository userSettingRepository;
   
  
   
   
   public UserDto getUser(int id){
      
      UserEntity entity=userRepository.findOne(id);
      UserDto user=new UserDto();
      user.setId(entity.getId());
      user.setUserNumber(entity.getUserNumber());
      user.setPassword(entity.getPassword());
      user.setAuth(entity.getAuth());
      user.setEmail(entity.getEmail());
      
      return user;
   }
   
   public UserDto getUserByUserNumber(String userNumber){
      
      UserEntity entity=userRepository.findByUserNumber(userNumber);
      UserDto user=new UserDto();
      user.setId(entity.getId());
      user.setUserNumber(entity.getUserNumber());
      user.setPassword(entity.getPassword());
      user.setAuth(entity.getAuth());
      user.setEmail(entity.getEmail());
      
      return user;
   }
   

   public int getUserByUserNumberAndEmail(UserDto user) throws UserNotFoundException{
	   
	   int userId;
	   UserEntity entity = new UserEntity();
	   entity=userRepository.findByUserNumberAndEmail(user.getUserNumber(), user.getEmail());
	   
	   if(entity==null)
		   throw new UserNotFoundException("학번과 이메일이 일치하는 사용자가 존재하지 않습니다.");
	   else{
		   userId = entity.getId();
		   return userId;
	   }   
   }
   
   public UserDto getUserInfoByUserNumber(String userNumber){
	   
	   UserEntity entity = userRepository.findUserInfoByUserNumber(userNumber);
	   UserDto user=new UserDto();
	   
	   user.setUserNumber(entity.getUserNumber());
	   user.setUserName(entity.getUserName());
	   user.setEmail(entity.getEmail());
	   
	   return user;
   }
   
   
   public void updateUserPassword(String tmpPassword,int userId){

	   userRepository.setFixedPasswordFor(passwordEncoder.encode(tmpPassword), userId);
	   
   }
   

   public void insertUser(UserDto user) throws DuplicatedUserException, IOException{


      UserEntity entity= new UserEntity(); 
      DepartmentEntity departmentEntity = new DepartmentEntity();
      departmentEntity.setId(user.getDepartmentId());
      
      if(userRepository.findByUserNumber(user.getUserNumber())!=null){
    	  throw new DuplicatedUserException("중복된 사용자"); 
      }else{
    	  
    	  CommandExecute.shellCmd("mkdir ~/drcode/user/"+user.getUserNumber());
    	  entity.setUserNumber(user.getUserNumber());
          entity.setUserName(user.getUserName());
          entity.setPassword(passwordEncoder.encode(user.getPassword()));
          entity.setAuth(user.getAuth());
          entity.setEmail(user.getEmail());
          entity.setDepartment(departmentEntity);
          
          userRepository.save(entity); 
          

        
     
      }
      
   }
   
   public void updateUser(UserDto user)throws UpdateUserNotNullError{
	  
	   
	   String userName=user.getUserName(); 
	   String password=user.getPassword();
	   String email=user.getEmail();
	   
	   if(("").equals(userName)){ // mypage의 이름,이메일 부분 require 속성 줌.
		   throw new UpdateUserNotNullError("사용자이름 공백 불가능");   
	   }else if(("").equals(email)){
		   throw new UpdateUserNotNullError("이메일 공백 불가능");
	   }
	   
	   UserEntity entity=userRepository.findByUserNumber(user.getUserNumber());
	   
	   entity.setUserName(userName);
	   entity.setEmail(email);
	   
	   
	   if(password==null){
		   userRepository.save(entity);
	   }else{
		   entity.setPassword(passwordEncoder.encode(password));
		   userRepository.save(entity);
	   }	   
	   
   }
   
   
   
   public UserDto getUserData(String userNumber) throws UserNotFoundException{
	   UserDto user=new UserDto();
	   UserEntity userEntity=userRepository.findByUserNumber(userNumber);
	   if(userEntity==null){
		   throw new UserNotFoundException("권한이 없습니다.");
	   }else{
		   user.setUserNumber(userNumber); 
		   user.setUserName(userEntity.getUserName());
		   
		   UserSettingEntity userSettingEntity=userSettingRepository.findByUserNumber(userNumber);
		   if(userSettingEntity!=null){
			   user.setLanguage(userSettingEntity.getLanguage());
			   user.setTheme(userSettingEntity.getTheme());
			   user.setFont(userSettingEntity.getFont());
		   }
	   }
	   
	   return user;
	   
   }
   
   public StringBuilder getTmpPassword(){
	   
	   StringBuilder tmpPassword=new StringBuilder("");

	      for(int i=0;i<10;i++){
	         int num=(int)(Math.random()*3);
	         char c = 0;
	         if(num==0){
	            c=(char)((int)(Math.random()*10)+48);
	         }else if(num==1){
	            c=(char)((int)(Math.random()*26)+65);
	         }else if(num==2){
	            c=(char)((int)(Math.random()*26)+97);
	         }


	         tmpPassword.append(c);

	      }
	   
	   return tmpPassword;

   }
   
public Map<String,Object> getProfessorsByDepartmentId(UserDto user, int thispage) {
	   
	   int count = (int) (((userRepository.countByAuth(user.getAuth())-1)/10)+1) ;

	   List<UserDto> dtoList=null;
	  
	   List<UserEntity> entityList = new ArrayList<UserEntity>();
	   entityList = userRepository.findByAuthAndDepartmentId(user.getAuth(), user.getDepartmentId(),new PageRequest(thispage-1, 10));
	   
	   //List<UserEntity> entityList = new ArrayList<UserEntity>();
	   //entityList=userRepository.findByAuth(user.getAuth());
	   sortByUserNumber(entityList);
	   
	   dtoList = new ArrayList<UserDto>();
	   
	   for(UserEntity entity : entityList) {
		   
	   UserDto user1=new UserDto();
	   user1.setId(entity.getId());
	   user1.setUserName(entity.getUserName());
	   user1.setUserNumber(entity.getUserNumber());
	   user1.setPassword(entity.getPassword());
	   user1.setAuth(entity.getAuth());
	   user1.setEmail(entity.getEmail());
	   dtoList.add(user1);
	  
	   }
	  
	   Map<String,Object> map=new HashMap<>();
	   map.put("userList", dtoList);
	   map.put("rowCount", count);
	   
	   return map;

   }
   
   public Map<String,Object> getUsers(UserDto user, int thispage) {
	   
	   int count = (int) (((userRepository.countByAuth(user.getAuth())-1)/10)+1) ;

	   List<UserDto> dtoList=null;
	  
	   List<UserEntity> entityList = new ArrayList<UserEntity>();
	   entityList = userRepository.findByAuth(user.getAuth(),new PageRequest(thispage-1, 10));
	   
	   //List<UserEntity> entityList = new ArrayList<UserEntity>();
	   //entityList=userRepository.findByAuth(user.getAuth());
	   sortByUserNumber(entityList);
	   
	   dtoList = new ArrayList<UserDto>();
	   
	   for(UserEntity entity : entityList) {
		   
	   UserDto user1=new UserDto();
	   user1.setId(entity.getId());
	   user1.setUserName(entity.getUserName());
	   user1.setUserNumber(entity.getUserNumber());
	   user1.setPassword(entity.getPassword());
	   user1.setAuth(entity.getAuth());
	   user1.setEmail(entity.getEmail());
	   dtoList.add(user1);
	  
	   }
	  
	   Map<String,Object> map=new HashMap<>();
	   map.put("userList", dtoList);
	   map.put("rowCount", count);
	   
	   return map;

   }
   
   private void sortByUserNumber(List<UserEntity> entity){
	   
	   
	   Collections.sort(entity,new Comparator<UserEntity>(){
		   
		   public int compare(UserEntity u1,UserEntity u2){
			   //System.out.println(u1.getUserNumber());
			   return u1.getUserNumber().compareToIgnoreCase(u2.getUserNumber());
		   }
		  
	   });
	   
	 
   }
   
   
   public void deleteUsers(UserDto user) {
	   int userId = user.getId();
	   
	  userRepository.delete(userId);;
	  

	  
   }
   
   public UserDto getUserIdByUserNumber(String userNumber) throws UserNotFoundException{
	   
	   UserDto user=new UserDto();
	   
	   UserEntity entity=userRepository.findIdByUserNumber(userNumber);
	   if(entity==null)
		   throw new UserNotFoundException("해당 userNumber의 user가 존재하지않습니다.");
	   else{
		   user.setId(entity.getId());   
	   }
	   
	   return user;
   }
   
   
   
}