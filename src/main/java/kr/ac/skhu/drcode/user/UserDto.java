package kr.ac.skhu.drcode.user;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import kr.ac.skhu.drcode.assignmentUser.AssignmentUserDto;
import kr.ac.skhu.drcode.subject.SubjectDto;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=UserDto.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserDto {
	public UserDto(){
		
	}
	
	private int id;
	private int userNumber;
	private String password;
	private String email;
	private String auth;
	private Set<AssignmentUserDto> assignmentUsers;
	private Set<SubjectDto> subjects;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public Set<AssignmentUserDto> getAssignmentUsers() {
		return assignmentUsers;
	}
	public void setAssignmentUsers(Set<AssignmentUserDto> assignmentUsers) {
		this.assignmentUsers = assignmentUsers;
	}
	public Set<SubjectDto> getSubjects() {
		return subjects;
	}
	public void setSubjects(Set<SubjectDto> subjects) {
		this.subjects = subjects;
	}
	
	
}
