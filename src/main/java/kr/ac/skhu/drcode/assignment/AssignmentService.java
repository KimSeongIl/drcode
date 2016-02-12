package kr.ac.skhu.drcode.assignment;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {

	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Autowired
	private DozerBeanMapper dozer;
	
	public AssignmentDto getAssignment(int id){
		
		return dozer.map(assignmentRepository.findOne(id), AssignmentDto.class);
	}
}
