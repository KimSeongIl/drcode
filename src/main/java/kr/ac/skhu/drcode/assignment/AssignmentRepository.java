package kr.ac.skhu.drcode.assignment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends CrudRepository<AssignmentEntity,Integer>{

	
}
