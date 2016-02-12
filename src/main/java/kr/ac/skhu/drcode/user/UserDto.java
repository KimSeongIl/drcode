package kr.ac.skhu.drcode.user;

import lombok.Data;

@Data
public class UserDto {
	private int id;
	private int userNumber;
	private String password;
	private String email;
	private String auth;	
}
