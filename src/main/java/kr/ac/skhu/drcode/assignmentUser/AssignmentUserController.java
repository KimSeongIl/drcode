package kr.ac.skhu.drcode.assignmentUser;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.skhu.drcode.exception.UserNotFoundException;
import kr.ac.skhu.drcode.user.UserDto;
import kr.ac.skhu.drcode.user.UserService;



@RestController
public class AssignmentUserController {

	@Autowired
	private AssignmentUserService assignmentUserService;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/assignmentUserList/{id}", method=RequestMethod.GET)
	public Map<String,Object> getUserListByAssignment(@PathVariable int id, @RequestParam("page") int page) {
		
		return assignmentUserService.getUserListByAssignment(id, page);
		
	}
	
	@RequestMapping(value="/assignmentSubmit", method=RequestMethod.POST)
	public void assignmentUserInsert(AssignmentUserDto assignmentUserDto) {
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		UserDto userDto = userService.getUserByUserNumber(authentication.getName());	
		
		
		
		assignmentUserService.assignmentUserInsert(assignmentUserDto, userDto);
		
	}
	
	@RequestMapping(value="/studentSubjectList", method=RequestMethod.GET)
	public Map<String, Object> getSubjectListByStudent() throws UserNotFoundException{
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		int user_id =userService.getUserIdByUserNumber(authentication.getName()).getId();
		
		
		return assignmentUserService.getSubjectListByStudent(user_id);
	
	}
	
	@RequestMapping(value="/studentAssignmentList", method=RequestMethod.GET)
	public Map<String, Object> getAssignmentListBySubjectId(@RequestParam("subject_id")int subject_id, @RequestParam("page")int page) throws UserNotFoundException {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		int user_id =userService.getUserIdByUserNumber(authentication.getName()).getId();
		
		System.out.println(user_id);
		System.out.println(subject_id);
		System.out.println(page);
		
		return assignmentUserService.getAssignmentListBySubjectId(user_id, subject_id, page);
	}
	
	@RequestMapping(value="/studentGetCode", method=RequestMethod.GET)
	public Map<String, Object> getAnserByAssignmentId(@RequestParam("assignmentId") int assignmentId) throws UserNotFoundException {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		int user_id =userService.getUserIdByUserNumber(authentication.getName()).getId();
		
		return assignmentUserService.getAnserByAssignmentId(assignmentId, user_id);
	}
	
}
