package kr.ac.skhu.drcode.subject;


import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import kr.ac.skhu.drcode.assignment.AssignmentDto;
import kr.ac.skhu.drcode.user.UserDto;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=SubjectDto.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubjectDto {

	private int id;
	private String subjectCode;
	private String subjectName;
	private UserDto professorId;
	private Set<AssignmentDto> assignment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public UserDto getProfessorId() {
		return professorId;
	}
	public void setProfessorId(UserDto professorId) {
		this.professorId = professorId;
	}
	public Set<AssignmentDto> getAssignment() {
		return assignment;
	}
	public void setAssignment(Set<AssignmentDto> assignment) {
		this.assignment = assignment;
	}
	
	
}
