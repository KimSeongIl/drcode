package kr.ac.skhu.drcode.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping("/")
	public String index(){
		
		return "index";
	}
}
