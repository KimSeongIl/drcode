package kr.ac.skhu.drcode.subject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.ac.skhu.drcode.assignment.AssignmentRepository;
import kr.ac.skhu.drcode.assignment.AssignmentService;
import kr.ac.skhu.drcode.assignmentUser.AssignmentUserService;
import kr.ac.skhu.drcode.user.UserDto;
import kr.ac.skhu.drcode.user.UserEntity;
import kr.ac.skhu.drcode.user.UserRepository;
import kr.ac.skhu.drcode.user.UserService;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private AssignmentService assignmentService;
	@Autowired
	private AssignmentUserService assignmentUserService;
	

	public SubjectDto getSubject(int id) {

		SubjectEntity se = subjectRepository.findOne(id);
		SubjectDto sd = new SubjectDto();
		sd.setId(se.getId());
		// sd.setProfessorId(null);
		sd.setSubjectCode(se.getSubjectCode());
		sd.setSubjectName(se.getSubjectName());

		return sd;
	}

	public Map<String,Object> getSubjectsByProfessorId(int professorId, int page) {

		int rowCount = (int) ((((subjectRepository.countByProfessorId(professorId)-1) / 10) + 1));

		List<SubjectEntity> subEntityList = subjectRepository.findByProfessorId(professorId,
				new PageRequest(page - 1, 10));

		sortBySubjectNumber(subEntityList);

		List<SubjectDto> subDtoList = new ArrayList<SubjectDto>();

		for (SubjectEntity e : subEntityList) {

			SubjectDto subjectDto = new SubjectDto();

			subjectDto.setId(e.getId());
			subjectDto.setSubjectCode(e.getSubjectCode());
			subjectDto.setSubjectName(e.getSubjectName());
			subjectDto.setProfessorId(professorId);

			subDtoList.add(subjectDto);

		}
		
		
		Map<String,Object> map=new HashMap<>();
		map.put("subDtoList", subDtoList);
		map.put("rowCount", rowCount);

		   

		return map;

	}

	private void sortBySubjectNumber(List<SubjectEntity> subEntityList) {

		Collections.sort(subEntityList, new Comparator<SubjectEntity>() {

			public int compare(SubjectEntity s1, SubjectEntity s2) {
				return s1.getSubjectCode().compareToIgnoreCase(s2.getSubjectCode());
			}

		});

	}
	
	public void insertSubject(SubjectDto subjectDto,String userNumber){
				
		
		UserEntity userEntity=userRepository.findOne(userRepository.findIdByUserNumber(userNumber).getId());
		
		SubjectEntity entity = new SubjectEntity();
		entity.setSubjectCode(subjectDto.getSubjectCode());
		entity.setSubjectName(subjectDto.getSubjectName());
		entity.setProfessor(userEntity);
		
		subjectRepository.save(entity);
		
	}
	
	public void deleteSubjectsBySubjectId(List<Integer> idList){
		
		for(int subjectId: idList){
			List<Integer> assignmentIdList=assignmentService.getAssignmentIdBySubjectId(subjectId);
			
			for(int assignmentId : assignmentIdList)
				assignmentUserService.deleteAssignmentUsersByAssignmentId(assignmentId);

			assignmentService.deleteAssignmentBySubjectId(subjectId);
			subjectRepository.delete(subjectId);
		}
	}
	
	public List<Integer> getJsonDeleteSubjectIdList(Map<String,Object> jsonSubjectDto){
		
		List<Map> jsonList=(List<Map>)((Map<String, Object>) jsonSubjectDto.get("checkDeleteArr")).get("SubjectDto");
		List<Integer> idList=new ArrayList<>(); 
		
		for(Map m : jsonList){	
			idList.add(Integer.parseInt((String) m.get("id")));
		}
		
		return idList;
		
		
	}
	
	public boolean checkDeleteSubjectAuthority(int loginId,List<Integer> idList){
		
		for(int id: idList){
			
			if(loginId != subjectRepository.findOne(id).getProfessor().getId())
				return false;
		}
		
		return true;
	}

}
