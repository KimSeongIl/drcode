package kr.ac.skhu.drcode.assignment;

import java.util.Date;







import kr.ac.skhu.drcode.subject.SubjectDto;
import lombok.Data;

@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=AssignmentDto.class)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AssignmentDto {

	
	
	
	private int id;
	private String assignmentName;
	private String content;
	private Date limitTime;
	private Date retentionTime;
	private String answer;
	private Date createdAt;
	private SubjectDto subject;
	
	//private Set<AssignmentUserDto> assignmentUsers;

	
	
	
}
