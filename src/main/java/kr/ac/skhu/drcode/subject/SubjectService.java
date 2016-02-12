package kr.ac.skhu.drcode.subject;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import kr.ac.skhu.drcode.assignment.AssignmentDto;
import kr.ac.skhu.drcode.assignment.AssignmentEntity;
import kr.ac.skhu.drcode.user.UserDto;
import kr.ac.skhu.drcode.util.DozerHelper;

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
		SubjectDto subject=new SubjectDto();
		SubjectEntity subjectEntity=subjectRepository.findOne(id);
		
		subject.setId(subjectEntity.getId());
		subject.setSubjectCode(subjectEntity.getSubjectCode());
		subject.setSubjectName(subjectEntity.getSubjectName());
		subject.setProfessor(dozer.map(subjectEntity.getProfessor(), UserDto.class));
		
		subject.setAssignment(DozerHelper.map(dozer, subjectEntity.getAssignments(), AssignmentDto.class));
		
		
		
		
		
		
		return subject;
	}
}
