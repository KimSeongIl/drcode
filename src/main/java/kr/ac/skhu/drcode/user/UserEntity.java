package kr.ac.skhu.drcode.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;






import kr.ac.skhu.drcode.assignmentUser.AssignmentUserEntity;
import kr.ac.skhu.drcode.subject.SubjectEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Entity
@Table(name = "user", catalog = "drcode")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",  unique = true, nullable = false)
	private int id;

	@Column(name = "user_number")
	private int userNumber;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "auth")
	private String auth;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "professor" , targetEntity = SubjectEntity.class)
	private List<SubjectEntity> subjects = new ArrayList<SubjectEntity>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user" , targetEntity = AssignmentUserEntity.class)
	private List<AssignmentUserEntity> assignmentUsers = new ArrayList<AssignmentUserEntity>(0);

}
