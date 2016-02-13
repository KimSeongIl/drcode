package kr.ac.skhu.drcode.assignmentUser;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import kr.ac.skhu.drcode.assignment.AssignmentDto;
import kr.ac.skhu.drcode.user.UserDto;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=AssignmentUserDto.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AssignmentUserDto {

	private int id;
	private AssignmentDto assignment;
	private UserDto user;
	private double score;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public AssignmentDto getAssignment() {
		return assignment;
	}
	public void setAssignment(AssignmentDto assignment) {
		this.assignment = assignment;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	
	
}
