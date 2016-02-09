package kr.ac.skhu.drcode.test;

import java.util.HashMap;
import java.util.Map;

import kr.ac.skhu.drcode.exception.TestException;
import kr.ac.skhu.drcode.util.JsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Autowired
	private TestService testService;

	@RequestMapping(value="/test")
	public Map<String,Object> test() throws TestException{
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("aa", "bb");
		
		return JsonUtil.putSuccessJsonContainer(map);
		
	}
	
	@RequestMapping(value="/test2")
	public Map<String,Object> test2() throws TestException{
		
	
		
		throw new TestException("errorTest");
		
	}
	
	@RequestMapping(value="/test3" , method=RequestMethod.POST)
	public void test3(TestDto testDto){
		
		testService.saveTest(testDto);
		
		
	}
	
	@RequestMapping(value="/test3/{id}" , method=RequestMethod.GET)
	public TestDto test3(@PathVariable int id){
		
		TestDto testDto=new TestDto();
		testDto.setId(id);
		
		
		return testService.findTest(testDto);
		
	}
}
