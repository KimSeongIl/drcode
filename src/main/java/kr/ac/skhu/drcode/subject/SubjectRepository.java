package kr.ac.skhu.drcode.subject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<SubjectEntity,Integer>{

	
}
