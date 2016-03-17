package kr.ac.skhu.drcode.assignmentUser;



import kr.ac.skhu.drcode.assignment.AssignmentDto;
import kr.ac.skhu.drcode.user.UserDto;
import lombok.Data;

@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=AssignmentUserDto.class)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AssignmentUserDto {

	private int id;
	private AssignmentDto assignment;
	private UserDto user;
	private double score;
	
	
	
	
}
