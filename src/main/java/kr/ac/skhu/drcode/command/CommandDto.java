package kr.ac.skhu.drcode.command;

import lombok.Data;

@Data
public class CommandDto {

	private String result;
	
	private String command;
	
	private String language;
	
	private String input;
	
	private String output;
	
	private String error;
	
	private String userName;
}
