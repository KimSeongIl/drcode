package kr.ac.skhu.drcode.subject;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.skhu.drcode.exception.AuthorityException;
import kr.ac.skhu.drcode.exception.UserNotFoundException;
import kr.ac.skhu.drcode.user.UserService;


@RestController
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	@Autowired
	private UserService userService;
	
	
	
	
	@RequestMapping(value ="/ss/{id}")
	public SubjectDto getSubject(@PathVariable int id){
		
		return subjectService.getSubject(id);
	}
	
	
	@RequestMapping(value="/subjects", method=RequestMethod.GET)
	public Map<String,Object> getSubjects(@RequestParam("userNumber") String userNumber,@RequestParam("page") int page) throws UserNotFoundException, AuthorityException{
		 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();				

		//if(userService.getUserIdByUserNumber(userNumber).getId()!=userService.getUserIdByUserNumber(authentication.getName()).getId())
		//	throw new AuthorityException("권한이 없습니다."); 


		
		return subjectService.getSubjectsByProfessorId(userService.getUserIdByUserNumber(userNumber).getId(),page);
	}
	
	@RequestMapping(value="/subjects", method=RequestMethod.DELETE)
	public Map<String,Object> deletSubjects(@RequestBody Map<String,Object> jsonSubjectDto) throws AuthorityException, UserNotFoundException {
		
		List<Integer> idList=subjectService.getJsonDeleteSubjectIdList(jsonSubjectDto);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int loginId=userService.getUserIdByUserNumber(authentication.getName()).getId();

		if(!subjectService.checkDeleteSubjectAuthority(loginId,idList))
			throw new AuthorityException("삭제하려는 해당과목의 권한이 없습니다.");
		
				
		subjectService.deleteSubjectsBySubjectId(idList);
		return jsonSubjectDto;
	}
	
	
	
	@RequestMapping(value="/subject", method=RequestMethod.POST)
	public SubjectDto addSubject(SubjectDto subjectDto,@RequestParam("userNumber") String userNumber) throws UserNotFoundException, AuthorityException{

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(userService.getUserIdByUserNumber(userNumber).getId()!=userService.getUserIdByUserNumber(authentication.getName()).getId())
			throw new AuthorityException("권한이 없습니다.");
		
		subjectService.insertSubject(subjectDto,userNumber);
		
		return subjectDto;
	}
}
