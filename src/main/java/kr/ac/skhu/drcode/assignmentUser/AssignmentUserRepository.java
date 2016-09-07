package kr.ac.skhu.drcode.assignmentUser;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.skhu.drcode.assignment.AssignmentEntity;
import kr.ac.skhu.drcode.user.UserEntity;


@Repository
public interface AssignmentUserRepository extends JpaRepository<AssignmentUserEntity,Integer>{
	
	int countByAssignment_id(int id);
	 
	int countByUser_id(int id);
	 
	
	 
	List<AssignmentUserEntity> findByAssignment_id(int id, Pageable pageable);
	 
	AssignmentUserEntity findByAssignment_idAndUser_id(int assignmentId, int userId);
	 
	List<AssignmentUserEntity> findByUser_id(int id);
	
	List<AssignmentUserEntity> findByAssignmentId(int assignmentId);

	@Transactional
	void deleteByAssignmentId(int assignmentId);
	 
	 
	// List<AssignmentUserEntity> findByUser_idAndSubject_id(int user_id, int subject_id);
	 //("select new AssignmentUserEntity(au.score), AssignmentEntity(a.assignment_name)"
	 	//	+ "from AssignmentUserEntity au inner join Assignment a on au.assignment_id = a.id"
	 //		+ "where au.user_id=?1 and a.subject_id=?2")
	 //  List<AssignmentUserEntity> findAssignmentByUser_idAndSubject_id(int user_id, int subject_id);
	 

	   
}
