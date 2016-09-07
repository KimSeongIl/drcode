package kr.ac.skhu.drcode.assignmentUser;



import java.util.Date;

import kr.ac.skhu.drcode.assignment.AssignmentDto;
import kr.ac.skhu.drcode.user.UserDto;
import lombok.Data;

@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=AssignmentUserDto.class)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AssignmentUserDto {

	private int id;
	private Integer score;
	private String code;
	private Date submitTime;
	
	//private AssignmentDto assignment;
	private int assignmentId;
	private String assignmentName;
	private Date limitTime;
	private Date extensionTime;
	private String limitTimeStr;
	private String extensionTimeStr;
	private String createdAtStr;
	
	//private UserDto user;
	private String userName;
	private String userNumber;
	
	
	
	
	
	
	
	
}
