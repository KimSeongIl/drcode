package kr.ac.skhu.drcode.assignment;

import java.util.Date;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import kr.ac.skhu.drcode.assignmentUser.AssignmentUserDto;
import kr.ac.skhu.drcode.subject.SubjectDto;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=AssignmentDto.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AssignmentDto {

	
	
	
	private int id;
	private String assignmentName;
	private String content;
	private Date limitTime;
	private Date retentionTime;
	private String answer;
	private Date createdAt;
	private SubjectDto subject;
	
	private Set<AssignmentUserDto> assignmentUsers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Date limitTime) {
		this.limitTime = limitTime;
	}

	public Date getRetentionTime() {
		return retentionTime;
	}

	public void setRetentionTime(Date retentionTime) {
		this.retentionTime = retentionTime;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public SubjectDto getSubject() {
		return subject;
	}

	public void setSubject(SubjectDto subject) {
		this.subject = subject;
	}

	public Set<AssignmentUserDto> getAssignmentUsers() {
		return assignmentUsers;
	}

	public void setAssignmentUsers(Set<AssignmentUserDto> assignmentUsers) {
		this.assignmentUsers = assignmentUsers;
	}
	
	
}
