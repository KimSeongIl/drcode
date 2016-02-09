package kr.ac.skhu.drcode.test;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "test", catalog = "drcode")
public class TestEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",  unique = true, nullable = false)
	private int id;

	@Column(name = "message", length = 11)
	private String message;
	


	

}
