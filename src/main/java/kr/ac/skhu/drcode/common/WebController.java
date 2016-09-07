package kr.ac.skhu.drcode.common;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.skhu.drcode.department.DepartmentService;
import kr.ac.skhu.drcode.exception.UserNotFoundException;
import kr.ac.skhu.drcode.user.UserDto;
import kr.ac.skhu.drcode.user.UserService;

@Controller
public class WebController {

	@Autowired
	private UserService userService;
	@Autowired
	private WebService webService;
	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public void index(HttpServletResponse response) throws IOException{

		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();


		String mainAuth=webService.getMainAuthority(authentication.getAuthorities());

		String mainPage=webService.getMainPageByMainAuth(mainAuth);



		response.sendRedirect(mainPage+".html");
	}

	@RequestMapping(value="/" , method=RequestMethod.POST)
	public String indexpost(){


		return "index";
	}

	@RequestMapping(value="/main.html")
	public String mainPage(Model model,HttpServletResponse response) throws IOException, UserNotFoundException{

		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		
		UserDto user=null;
		user = userService.getUserData(authentication.getName());		

		
		model.addAttribute("departmentList",departmentService.getDepartments());
		
		model.addAttribute("user",user);
		
		return "main";
	}

	@RequestMapping(value="/admin_main.html")
	public String adminMain(){

		return "admin_main";
	}



	@RequestMapping(value="/professor_main.html")
	public String professorMain(Model model) throws UserNotFoundException{

		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		UserDto user=userService.getUserData(authentication.getName());

		model.addAttribute("user",user);
		return "professor_main";
	}


	@RequestMapping(value="/professor_assignment_list.html")
	public String assignmentMain(@RequestParam int subjectId,Model model){

		model.addAttribute("subjectId",subjectId);
		return "professor_assignment_list";
	}

	@RequestMapping(value="/professor_assignment.html")
	public String assigmentContent(@RequestParam int id,Model model){

		model.addAttribute("id",id);
		return "professor_assignment";
	}


	@RequestMapping(value="/professor_assignment_add.html")
	public String assignmentAdd(@RequestParam("subjectId") int subjectId,Model model){
		
		System.out.println("dddd");
		model.addAttribute("subjectId",subjectId);

		return "professor_assignment_add";
	}


	@RequestMapping(value="/professor_assignment_userList.html")
	public String assignmentContent(@RequestParam int id,Model model) {
		model.addAttribute("id",id);
		return "professor_assignment_userList";
	}

	@RequestMapping(value="/myPage.html")
	public String myPage(Model model){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

		String auth= webService.getMainAuthority(authentication.getAuthorities());
		model.addAttribute("auth", auth);
		
		
		return "myPage";
	}
	
	@RequestMapping(value="/student_code.html")
	public String studentCode(@RequestParam int id,Model model){
		model.addAttribute("id",id);
		return "student_code";
	}
	
	@RequestMapping(value="/student_assignment_list.html")
	public String assignmentList(){

		return "student_assignment_list";
	}
	
	@RequestMapping(value="/student_assignment.html")
	public String student_assignment(@RequestParam int id, Model model) {
		model.addAttribute("id", id);
		return "student_assignment";
	}
	

}