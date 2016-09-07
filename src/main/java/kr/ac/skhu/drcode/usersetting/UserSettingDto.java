package kr.ac.skhu.drcode.usersetting;

import lombok.Data;

@Data
public class UserSettingDto {

	private int id;
	
	private String userNumber;
	
	private String language;
	
	private String theme;
	
	private String font;
}
