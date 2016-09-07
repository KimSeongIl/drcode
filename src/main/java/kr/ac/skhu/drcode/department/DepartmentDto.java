package kr.ac.skhu.drcode.department;


import lombok.Data;

@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=SubjectDto.class)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DepartmentDto {

	private int id;
	private String DepartmentName;

	//private Set<AssignmentDto> assignment;
	
	
	
}
