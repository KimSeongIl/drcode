package kr.ac.skhu.drcode.assignment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.ac.skhu.drcode.assignmentUser.AssignmentUserService;
import kr.ac.skhu.drcode.common.WebService;
import kr.ac.skhu.drcode.subject.SubjectDto;
import kr.ac.skhu.drcode.subject.SubjectEntity;
import kr.ac.skhu.drcode.subject.SubjectRepository;
import kr.ac.skhu.drcode.user.UserEntity;
import kr.ac.skhu.drcode.user.UserRepository;

@Service
public class AssignmentService {

	@Autowired
	private WebService webService;
	@Autowired
	private AssignmentUserService assignmentUserService;
	@Autowired
	private AssignmentRepository assignmentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	

	public Map<String, Object> getAssignmentsBySubjectId(int subjectId, int page) {

		int rowCount = (int) (((assignmentRepository.countBySubjectId(subjectId) - 1) / 10) + 1);

		List<AssignmentEntity> assignmentEntityList = assignmentRepository.findBySubjectId(subjectId,
				new PageRequest(page - 1, 10));

		sortById(assignmentEntityList);

		List<AssignmentDto> assignmentDtoList = new ArrayList<AssignmentDto>();
		for (AssignmentEntity e : assignmentEntityList) {

			AssignmentDto assignmentDto = new AssignmentDto();
			assignmentDto.setId(e.getId());
			assignmentDto.setAnswer(e.getAnswer());
			assignmentDto.setAssignmentName(e.getAssignmentName());
			assignmentDto.setContent(e.getContent());
			assignmentDto.setCreatedAt(e.getCreatedAt());
			assignmentDto.setLimitTime(e.getLimitTime());
			assignmentDto.setExtensionTime(e.getExtensionTime());
			assignmentDto.setCreatedAtStr(e.getCreatedAt().toString());
			assignmentDto.setLimitTimeStr(e.getLimitTime().toString());
			assignmentDto.setExtensionTimeStr(e.getExtensionTime().toString());
			assignmentDtoList.add(assignmentDto);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("assignmentDtoList", assignmentDtoList);
		map.put("rowCount", rowCount);

		return map;
	}

	public AssignmentDto getAssignmentById(int id) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String auth = webService.getMainAuthority(authentication.getAuthorities()); // 교수
																					// 계정일
																					// 경우에만
																					// answer를
																					// 웹에
																					// 전달하기위해
																					// 권한검사

		AssignmentEntity assignmentEntity = assignmentRepository.findById(id);

		AssignmentDto assignmentDto = new AssignmentDto();
		assignmentDto.setId(assignmentEntity.getId());

		if ("ROLE_PROFESSOR".equals(auth)) {
			assignmentDto.setAnswer(assignmentEntity.getAnswer());
		}

		assignmentDto.setAssignmentName(assignmentEntity.getAssignmentName());
		assignmentDto.setContent(assignmentEntity.getContent());
		assignmentDto.setCreatedAtStr(format.format(assignmentEntity.getCreatedAt()));
		assignmentDto.setLimitTimeStr(format.format(assignmentEntity.getLimitTime()));
		assignmentDto.setExtensionTimeStr(format.format(assignmentEntity.getExtensionTime()));
		assignmentDto.setSubjectName(assignmentEntity.getSubject().getSubjectName());

		return assignmentDto;
	}

	private void sortById(List<AssignmentEntity> e) {

		Collections.sort(e, new Comparator<AssignmentEntity>() {
			public int compare(AssignmentEntity e1, AssignmentEntity e2) {
				return String.valueOf(e1.getId()).compareTo(String.valueOf(e2.getId()));
			}
		});

	}

	public Map<String, Object> getSubjectsIdByUserId(int userId) {

		Map<String, Object> map = new HashMap<>();

		UserEntity e = userRepository.findOne(userId);
		Set<SubjectEntity> set = e.getSubjects();

		for (SubjectEntity s : set) {
			SubjectDto dto = new SubjectDto();
			dto.setId(s.getId());

			map.put(String.valueOf(dto.getId()), dto.getId());
		}

		return map;
	}

	public List<Integer> getAssignmentIdBySubjectId(int subjectId) {

		List<Integer> subjectIdList = new ArrayList<Integer>();

		List<AssignmentEntity> assignmentEntityList = assignmentRepository.findBySubjectId(subjectId);
		for (AssignmentEntity e : assignmentEntityList)
			subjectIdList.add(e.getId());

		return subjectIdList;
	}

	public void insertAssignment(AssignmentDto assignmentDto) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		AssignmentEntity entity = new AssignmentEntity();
		entity.setAssignmentName(assignmentDto.getAssignmentName());
		entity.setContent(assignmentDto.getContent());
		entity.setAnswer(assignmentDto.getAnswer());
		entity.setLimitTime(format.parse(assignmentDto.getLimitTimeStr()));
		entity.setExtensionTime(format.parse(assignmentDto.getExtensionTimeStr()));
		entity.setCreatedAt(format.parse(assignmentDto.getCreatedAtStr()));
		entity.setSubject(subjectRepository.findOne(assignmentDto.getSubjectId()));

		assignmentRepository.save(entity);

	}

	public void deleteAssignmentBySubjectId(int subjectId) {

		List<AssignmentEntity> assignmentEntityList = assignmentRepository.findBySubjectId(subjectId);

		for (AssignmentEntity e : assignmentEntityList)
			assignmentRepository.delete(e);

	}

	public List<Integer> getJsonDeleteSubjectIdList(Map<String, Object> jsonSubjectDto) {

		List<Map> jsonList = (List<Map>) ((Map<String, Object>) jsonSubjectDto.get("checkDeleteArr"))
				.get("AssignmentDto");
		List<Integer> idList = new ArrayList<>();

		for (Map m : jsonList) {
			idList.add(Integer.parseInt((String) m.get("id")));
		}

		return idList;

	}

	public void deleteAssignmentsById(List<Integer> idList) {

		for (int assignmentId : idList) {

			assignmentUserService.deleteAssignmentUsersByAssignmentId(assignmentId);
			assignmentRepository.delete(assignmentId);
		}

	}

}