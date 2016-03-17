package kr.ac.skhu.drcode.assignmentUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssignmentUserController {

	@Autowired
	private AssignmentUserService assignmentUserService;
	
	@RequestMapping(value ="/au/{id}")
	public AssignmentUserDto getAssignmentUser(@PathVariable int id){
		
		return assignmentUserService.getAssignmentUser(id);
	}
}
