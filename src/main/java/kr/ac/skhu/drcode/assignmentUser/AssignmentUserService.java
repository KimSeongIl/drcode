package kr.ac.skhu.drcode.assignmentUser;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.ac.skhu.drcode.assignment.AssignmentDto;
import kr.ac.skhu.drcode.assignment.AssignmentEntity;
import kr.ac.skhu.drcode.assignment.AssignmentRepository;
import kr.ac.skhu.drcode.subject.SubjectDto;
import kr.ac.skhu.drcode.subject.SubjectEntity;
import kr.ac.skhu.drcode.user.UserDto;
import kr.ac.skhu.drcode.user.UserEntity;


@Service
public class AssignmentUserService {

	@Autowired
	private AssignmentUserRepository assignmentUserRepository;
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	
	
	public Map<String, Object> getUserListByAssignment(int id, int page) {

		int pageCount = (int) (((assignmentUserRepository.countByAssignment_id(id) - 1) / 10) + 1);
		
		List<AssignmentUserEntity> assignmentUserEntityList = assignmentUserRepository.findByAssignment_id(id,
				new PageRequest(page - 1, 10));
		
		sortById(assignmentUserEntityList);
		
		List<AssignmentUserDto> assignmentUserDtoList = new ArrayList<AssignmentUserDto>();		
		
		AssignmentDto assignmentDto = new AssignmentDto();
		assignmentDto.setId(id);
		assignmentDto.setAssignmentName(assignmentRepository.findById(id).getAssignmentName());
		
		for (AssignmentUserEntity e : assignmentUserEntityList) {

			AssignmentUserDto assignmentUserDto = new AssignmentUserDto();
			
			
			assignmentUserDto.setId(e.getId());
			assignmentUserDto.setUserName((e.getUser().getUserName()));
			assignmentUserDto.setUserNumber((e.getUser().getUserNumber()));
			assignmentUserDto.setScore(e.getScore());
	
			
			assignmentUserDtoList.add(assignmentUserDto);
		}
		
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("assignmentUserDtoList", assignmentUserDtoList);
		map.put("assignmentDto", assignmentDto);
		map.put("pageCount", pageCount);

		return map;
	}
	
	public Map<String, Object> getAnserByAssignmentId(int assignmentId, int userId) {
		
		AssignmentUserEntity assignmentUserEntity = assignmentUserRepository.findByAssignment_idAndUser_id(assignmentId, userId);

		Map<String, Object> map = new HashMap<>();
		map.put("code", assignmentUserEntity.getCode());
		map.put("assignmentName",assignmentUserEntity.getAssignment().getAssignmentName());
		
		return map; 
		
	}
	
	public void assignmentUserInsert(AssignmentUserDto assignmentUserDto, UserDto userDto) {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userDto.getId());
		
		AssignmentEntity assignmentEntity = new AssignmentEntity();
		assignmentEntity.setId(assignmentUserDto.getAssignmentId());
		
		AssignmentUserEntity findEntity = assignmentUserRepository.findByAssignment_idAndUser_id(assignmentUserDto.getAssignmentId(), userDto.getId());
	    		
		if(findEntity == null) {
			AssignmentUserEntity assignmentUserEntity = new AssignmentUserEntity();		
			assignmentUserEntity.setUser(userEntity);
			assignmentUserEntity.setAssignment(assignmentEntity);
			assignmentUserEntity.setCode(assignmentUserDto.getCode());
			assignmentUserEntity.setSubmitTime(new Date());
			assignmentUserRepository.save(assignmentUserEntity);
		}
		else {
			findEntity.setSubmitTime(new Date());
			findEntity.setCode(assignmentUserDto.getCode());
			assignmentUserRepository.save(findEntity);
		}
		
	}
	

	private void sortById(List<AssignmentUserEntity> e) {

		Collections.sort(e, new Comparator<AssignmentUserEntity>() {
			public int compare(AssignmentUserEntity e1, AssignmentUserEntity e2) {
				return String.valueOf(e1.getId()).compareTo(String.valueOf(e2.getId()));
			}
		});

	}
	
	//학생페이지 강좌내역 리스트
	public Map<String, Object> getSubjectListByStudent(int id) {

		List<AssignmentUserEntity> assignmentUserEntityList = assignmentUserRepository.findByUser_id(id);
		List<AssignmentUserDto> assignmentUserDtoList = new ArrayList<AssignmentUserDto>();	
		
		HashSet<SubjectDto> subjectList = new HashSet<SubjectDto>(); // 과목리스트가 중복되서 탭으로 출력되면 안되기 때문에 값 중복이 안되는 HashSet사용
		
		for (AssignmentUserEntity e : assignmentUserEntityList) {
			SubjectDto subjectDto = new SubjectDto();
			
			AssignmentUserDto assignmentUserDto = new AssignmentUserDto();
			
			subjectDto.setId(e.getAssignment().getSubject().getId());
			subjectDto.setSubjectName(((e.getAssignment().getSubject().getSubjectName())));
			
			subjectList.add(subjectDto);
				
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("subjectList", subjectList);

		return map;
	}
	
	
	public Map<String, Object> getAssignmentListBySubjectId(int user_id, int subject_id, int page) {
		int entityCount = assignmentUserRepository.countByUser_id(user_id);
	    
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		//System.out.println(pageCount);
		
		List<AssignmentUserEntity> assignmentUserEntityList = assignmentUserRepository.findByUser_id(user_id);
		List<AssignmentUserDto> assignmentUserDtoList = new ArrayList<AssignmentUserDto>();
		
		for(AssignmentUserEntity e : assignmentUserEntityList) {
			
			if(e.getAssignment().getSubject().getId()==subject_id) {	
			AssignmentUserDto assignmentUserDto = new AssignmentUserDto();
			assignmentUserDto.setAssignmentId(e.getAssignment().getId());
			assignmentUserDto.setAssignmentName(e.getAssignment().getAssignmentName());
			assignmentUserDto.setScore(e.getScore());
			assignmentUserDto.setLimitTimeStr(format.format(e.getAssignment().getLimitTime()));
			assignmentUserDto.setExtensionTimeStr(format.format(e.getAssignment().getExtensionTime()));
			assignmentUserDtoList.add(assignmentUserDto);
			}
			else
				entityCount--;			
		}
		
		int pageCount = (int) (((entityCount - 1) / 10) + 1);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("pageCount", pageCount);
		map.put("assignmentList", assignmentUserDtoList);
		
		
		return map ;
	}
	
	
	public void deleteAssignmentUsersByAssignmentId(int assignmentId){
		
		List<AssignmentUserEntity> assignmentUserEntityList = assignmentUserRepository.findByAssignmentId(assignmentId);
		
		for(AssignmentUserEntity e : assignmentUserEntityList)
			assignmentUserRepository.delete(e);
		
	}
	
}
