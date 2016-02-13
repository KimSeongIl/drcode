package kr.ac.skhu.drcode.user;


import java.util.HashSet;





import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;




















import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import kr.ac.skhu.drcode.assignmentUser.AssignmentUserEntity;
import kr.ac.skhu.drcode.subject.SubjectEntity;




@Entity
@Table(name = "user", catalog = "drcode")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=UserEntity.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity{

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
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "professor" )
	private Set<SubjectEntity> subjects = new HashSet<SubjectEntity>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user" )
	private Set<AssignmentUserEntity> assignmentUsers = new HashSet<AssignmentUserEntity>(0);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public Set<SubjectEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<SubjectEntity> subjects) {
		this.subjects = subjects;
	}

	public Set<AssignmentUserEntity> getAssignmentUsers() {
		return assignmentUsers;
	}

	public void setAssignmentUsers(Set<AssignmentUserEntity> assignmentUsers) {
		this.assignmentUsers = assignmentUsers;
	}

	







	

}
