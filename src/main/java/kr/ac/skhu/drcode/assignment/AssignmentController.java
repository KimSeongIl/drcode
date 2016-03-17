package kr.ac.skhu.drcode.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssignmentController {

	@Autowired
	private AssignmentService assignmentService;
	
	@RequestMapping(value ="/aa/{id}")
	public AssignmentDto getAssignment(@PathVariable int id){
		
		return assignmentService.getAssignment(id);
		
	}
}
