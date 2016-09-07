package kr.ac.skhu.drcode.department;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Integer>{

	

	List<DepartmentEntity> findById(int id);
	
	
	
}
