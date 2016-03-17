package kr.ac.skhu.drcode.test;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

	@Autowired
	private TestRepository testRepository;
	
	@Autowired
	private DozerBeanMapper dozer;
	
	public void saveTest(TestDto testDto){
		
		
		TestEntity testEntity=dozer.map(testDto, TestEntity.class);
		
		testRepository.save(testEntity);
	}
	
	public TestDto findTest(TestDto testDto){
		
		int id=testDto.getId();
		
		TestEntity testEntity=testRepository.findOne(id);
		
		return dozer.map(testEntity, TestDto.class);
	}
}
