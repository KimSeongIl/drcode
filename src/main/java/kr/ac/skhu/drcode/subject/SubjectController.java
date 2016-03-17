package kr.ac.skhu.drcode.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping(value ="/ss/{id}")
	public SubjectDto getSubject(@PathVariable int id){
		
		return subjectService.getSubject(id);
	}
}
