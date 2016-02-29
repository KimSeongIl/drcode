package kr.ac.skhu.drcode.subject;









import kr.ac.skhu.drcode.user.UserDto;
import lombok.Data;

@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=SubjectDto.class)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubjectDto {

	private int id;
	private String subjectCode;
	private String subjectName;
	private UserDto professorId;
	//private Set<AssignmentDto> assignment;
	
	
	
}
