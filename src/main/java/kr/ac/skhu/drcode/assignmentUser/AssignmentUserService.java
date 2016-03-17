package kr.ac.skhu.drcode.assignmentUser;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentUserService {

	@Autowired
	private AssignmentUserRepository assignmentUserRepository;
	
	@Autowired
	private DozerBeanMapper dozer;
	
	public AssignmentUserDto getAssignmentUser(int id){
		
		return dozer.map(assignmentUserRepository.findOne(id), AssignmentUserDto.class);
	}
}
