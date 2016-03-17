package kr.ac.skhu.drcode.subject;




import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private DozerBeanMapper dozer;
	
	public SubjectDto getSubject(int id){
		
		
		SubjectEntity se = subjectRepository.findOne(id);
		SubjectDto sd = new SubjectDto();
		sd.setId(se.getId());
		sd.setProfessorId(null);
		sd.setSubjectCode(se.getSubjectCode());
		sd.setSubjectName(se.getSubjectName());
		
		
		
		
		return sd;
	}
}
