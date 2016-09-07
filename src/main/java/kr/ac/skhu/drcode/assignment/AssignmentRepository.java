package kr.ac.skhu.drcode.assignment;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.ac.skhu.drcode.subject.SubjectEntity;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Integer> {

	List<AssignmentEntity> findBySubjectId(int subjectId);
	
	List<AssignmentEntity> findBySubjectId(int subjectId, Pageable pageable);

	int countBySubjectId(int subjectId);

	AssignmentEntity findById(int id);
	
	void deleteBySubjectId(int subjectId);
	
	

}
