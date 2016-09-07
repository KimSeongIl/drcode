package kr.ac.skhu.drcode.assignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import kr.ac.skhu.drcode.exception.AuthorityException;
import kr.ac.skhu.drcode.exception.UserNotFoundException;
import kr.ac.skhu.drcode.user.UserService;
import lombok.extern.java.Log;
import springfox.documentation.spring.web.paths.Paths;



@Log
@RestController
public class AssignmentController {

	@Autowired
	private AssignmentService assignmentService;
	@Autowired
	private UserService userService;
	@Autowired
	private Environment env;

	@RequestMapping(value = "/assignments", method = RequestMethod.GET)
	public Map<String, Object> getAssignments(@RequestParam("subjectId") int subjectId, @RequestParam("page") int page)
			throws UserNotFoundException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userLoginId = userService.getUserIdByUserNumber((authentication.getName())).getId();

		Map<String, Object> map = new HashMap<>();
		map = assignmentService.getSubjectsIdByUserId(userLoginId);

		// if(!map.containsKey(String.valueOf(subjectId))){
		// throw new UserNotFoundException("해당과제 조회권한없음");
		// }

		return assignmentService.getAssignmentsBySubjectId(subjectId, page);
	}

	@RequestMapping(value = "/assignment/{id}", method = RequestMethod.GET)
	public AssignmentDto getAssignment(@PathVariable int id) {

		AssignmentDto assignment = assignmentService.getAssignmentById(id);

		return assignment;
	}

	@RequestMapping(value = "/assignment", method = RequestMethod.POST)
	public AssignmentDto insertAssignment(AssignmentDto assignmentDto) throws ParseException, UserNotFoundException {
/*
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userLoginId = userService.getUserIdByUserNumber((authentication.getName())).getId();

		Map<String, Object> map = new HashMap<>();
		map = assignmentService.getSubjectsIdByUserId(userLoginId);

		if (!map.containsKey(String.valueOf(subjectId))) {
			throw new AuthorityException("해당과제 추가권한없음");
		}*/

		
		assignmentService.insertAssignment(assignmentDto);
		return assignmentDto;

	}

	//samrteditor single image upload(
	@RequestMapping(value = "/imageUpload")
	public String imageUpload() {

		return null;
	}

	//smarteditor multi image upload
	@RequestMapping(value = "/multiImageUpload", method=RequestMethod.POST)
	public void multiImageUpload(HttpServletRequest request,HttpServletResponse response) throws IOException{		


		String filename = request.getHeader("file-name");
        String filename_ext = (filename.substring(filename.lastIndexOf(".")+1)).toLowerCase();
	    String directory = new File(".").getCanonicalPath();
	    
	    System.out.println(filename+"/"+filename_ext);
	    System.out.println(directory);
	    System.out.println(directory+"static"+File.separator+"photoUpload"+File.separator);


	    //파일경로찾기  
	    //ex)/Users/user/Documents/svn/drcode/drcode/src/main/resources/static/photoUpload
        String filePath = directory + File.separator +"src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "photoUpload" + File.separator;
        File file = new File(filePath);
        
        if(!file.exists()) {
           file.mkdirs();
        }
        
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String today= format.format(new java.util.Date());
        String serverFileName = today + UUID.randomUUID().toString() + "." +filename_ext;
        String serverFilePath = filePath + serverFileName;
        
        
        //서버 사진파일업로드
        InputStream inputStream = request.getInputStream();
        OutputStream outputStream=new FileOutputStream(serverFilePath);
        
        int numRead;
        byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
        
        while((numRead = inputStream.read(b,0,b.length)) != -1){
           outputStream.write(b,0,numRead);
        }
        if(inputStream != null) {
           inputStream.close();
        }
        outputStream.flush();
        outputStream.close();
        
        
        //스마트에디터 이미지태그 출력
        String sFileInfo = "&bNewLine=true";
        sFileInfo += "&sFileName="+ filename;;
        sFileInfo += "&sFileURL="+"/photoUpload/"+serverFileName;
        
        System.out.println(sFileInfo);
        
        PrintWriter print = response.getWriter();
        print.print(sFileInfo);
        print.flush();
        print.close();
				
	}
	
	@RequestMapping(value="/assignment", method=RequestMethod.DELETE)
	public Map<String,Object> deleteAssignment(@RequestBody Map<String,Object> jsonAssignmentDto){
		
		List<Integer> idList=assignmentService.getJsonDeleteSubjectIdList(jsonAssignmentDto);


		assignmentService.deleteAssignmentsById(idList);
		return jsonAssignmentDto;	
	}

}
