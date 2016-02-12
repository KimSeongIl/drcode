package kr.ac.skhu.drcode.subject;

import java.util.List;
import java.util.Set;

import kr.ac.skhu.drcode.assignment.AssignmentDto;
import kr.ac.skhu.drcode.user.UserDto;
import lombok.Data;

@Data
public class SubjectDto {

	private int id;
	private String subjectCode;
	private String subjectName;
	private UserDto professor;
	private List<AssignmentDto> assignment;
}
