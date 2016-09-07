package kr.ac.skhu.drcode.subject;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity,Integer>{

	

	List<SubjectEntity> findByProfessorId(int professorId,Pageable pageable);
	
	int countByProfessorId(int professorId);
	
}
