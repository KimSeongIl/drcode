package kr.ac.skhu.drcode.user;




import lombok.Data;

@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=UserDto.class)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserDto {
	public UserDto(){
		
	}
	
	private int id;
	private String userNumber;
	private String password;
	private String email;
	private String auth;
	//private Set<AssignmentUserDto> assignmentUsers;
	//private Set<SubjectDto> subjects;
	
	
	
}
