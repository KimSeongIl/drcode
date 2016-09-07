package kr.ac.skhu.drcode.usersetting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSettingRepository extends JpaRepository<UserSettingEntity,Integer>{

	UserSettingEntity findByUserNumber(String userNumber);
}
