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
	private Date extensionTime;
	private String answer;
	private Date createdAt;
	private SubjectDto subject;
	private int subjectId;
	private String subjectName;
	
	private String limitTimeStr;
	private String extensionTimeStr;
	private String createdAtStr;
	
	//private Set<AssignmentUserDto> assignmentUsers;

	
	
	
}
