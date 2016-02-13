package kr.ac.skhu.drcode.assignmentUser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentUserRepository extends CrudRepository<AssignmentUserEntity,Integer>{

	
}
