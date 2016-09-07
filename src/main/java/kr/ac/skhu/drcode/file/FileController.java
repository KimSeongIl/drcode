package kr.ac.skhu.drcode.file;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.skhu.drcode.user.UserService;



@RestController
public class FileController {
	
	@Autowired
	private FileService fileService;


	@RequestMapping(value="/filedownload" ,method=RequestMethod.POST)
	public void fileDownload(FileDto file, HttpServletResponse response) throws IOException{

		
		fileService.fileDownload(file, response);


		
	}

}



