package kr.ac.skhu.drcode.assignmentUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentUserRepository extends JpaRepository<AssignmentUserEntity,Integer>{

	
}
