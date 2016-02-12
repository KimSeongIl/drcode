package kr.ac.skhu.drcode.assignment;

import java.util.Date;

import kr.ac.skhu.drcode.subject.SubjectEntity;
import lombok.Data;

@Data
public class AssignmentDto {

	public AssignmentDto(){
		
	}
	
	
	private int id;
	private String assignmentName;
	private String content;
	private Date limitTime;
	private Date retentionTime;
	private String answer;
	private Date createdAt;
	private int subjectId;
}
