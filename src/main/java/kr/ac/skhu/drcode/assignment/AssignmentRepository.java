package kr.ac.skhu.drcode.assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity,Integer>{

	
}
