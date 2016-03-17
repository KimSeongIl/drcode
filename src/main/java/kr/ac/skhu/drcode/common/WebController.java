package kr.ac.skhu.drcode.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.skhu.drcode.test.TestDto;

@Controller
public class WebController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(){
		
		return "index";
	}
	
	@RequestMapping(value="/" , method=RequestMethod.POST)
	public String indexpost(){
		
		
		return "index";
	}

}