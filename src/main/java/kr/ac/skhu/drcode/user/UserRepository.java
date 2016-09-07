package kr.ac.skhu.drcode.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{

   UserEntity findByUserNumber(String userNumber);
   
   UserEntity findByUserNumberAndEmail(String userNumber, String email);
   
   List<UserEntity> findByAuthAndDepartmentId(String auth, int departmentId ,Pageable pageable);
   
   List<UserEntity> findByAuth (String auth,Pageable pageable);
   
   int countByAuth(String auth);
   
   
   @Modifying
   @Transactional
   @Query(value="update drcode.user u set u.password=?1 where u.id=?2",nativeQuery=true) 
   void setFixedPasswordFor(String password,int id);
   
   
   
   @Query("select new UserEntity(u.userNumber, u.userName, u.email) from UserEntity u where u.userNumber=?1")
   UserEntity findUserInfoByUserNumber(String userNumber);
   
   @Query("select new UserEntity(u.id) from UserEntity u where u.userNumber=?1")
   UserEntity findIdByUserNumber(String userNumber);
   
  // @Query(value="select UserEntity(u.id, u.userNumber, u.userName, u.email, u.auth) from UserEntity u where 행번호 > (?1 -1) * 5 AND 행번호 <= ?1 * 5 AND auth = ?3 ORDER BY 행번호")
  // List<UserEntity> findByAuthFromUser(int start, int end);

}

