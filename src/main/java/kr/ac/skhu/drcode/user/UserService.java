package kr.ac.skhu.drcode.user;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
   private DozerBeanMapper dozer;
   
   
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
   
   public void insertUser(String userNumber, String pw, String email, String auth){
      
      UserEntity entity= new UserEntity();
      
      
      entity.setUserNumber(userNumber);
      entity.setPassword(pw);
      entity.setAuth(auth);
      entity.setEmail(email);
      
      userRepository.save(entity);
      
   }
}