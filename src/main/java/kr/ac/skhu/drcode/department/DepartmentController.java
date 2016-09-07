package kr.ac.skhu.drcode.department;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.skhu.drcode.exception.UserNotFoundException;



@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	

	
	
	@RequestMapping(value="/getDepartments", method=RequestMethod.GET)
	public List<DepartmentDto> getDepartments() throws UserNotFoundException {
		
		return departmentService.getDepartments();
	}
	
	
	
	
}
