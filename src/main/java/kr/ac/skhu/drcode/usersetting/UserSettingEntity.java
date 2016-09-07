package kr.ac.skhu.drcode.usersetting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "user_setting", catalog = "drcode")
public class UserSettingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",  unique = true, nullable = false)
	private int id;
	
	@Column(name = "user_number")
	private String userNumber;
	
	@Column(name = "language")
	private String language;
	
	@Column(name = "theme")
	private String theme;
	
	@Column(name = "font")
	private String font;
}
