package kr.ac.skhu.drcode.assignmentUser;

import lombok.Data;

@Data
public class AssignmentUserDto {

	private int id;
	private int assignmentId;
	private int userId;
	private double score;
}
