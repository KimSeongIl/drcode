package kr.ac.skhu.drcode.file;


import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;



import org.springframework.stereotype.Service;



@Service
public class FileService {
	
	public void fileDownload(FileDto file, HttpServletResponse response) throws IOException{
		String content = file.getContent();
		String language = file.getLanguage();
		
		byte[] fileByte = content.getBytes();
		
		response.setContentType("application/octet-stream");
	    response.setContentLength(fileByte.length);
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("Drcode."+language,"UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.getOutputStream().write(fileByte);
	    
	    response.getOutputStream().flush();
	    response.getOutputStream().close();	
	}
}
