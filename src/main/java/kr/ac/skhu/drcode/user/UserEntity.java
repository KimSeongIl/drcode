package kr.ac.skhu.drcode.user;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kr.ac.skhu.drcode.assignmentUser.AssignmentUserEntity;
import kr.ac.skhu.drcode.department.DepartmentEntity;
import kr.ac.skhu.drcode.subject.SubjectEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@Entity
@Table(name = "user", catalog = "drcode")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=UserEntity.class)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity implements Serializable{

	public UserEntity(){
		
	}
	
	
	public UserEntity(String userNumber, String userName, String email){
		
		this.userNumber=userNumber;
		this.userName=userName;
		this.email=email;
		
	}
	
	public UserEntity(int id){
		this.id=id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",  unique = true, nullable = false)
	private int id;
	
	

	@Column(name = "user_number")
	private String userNumber;
	
	@Column(name = "user_name")
	private String userName;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private DepartmentEntity department;
	
	public void addSubjects(SubjectEntity subjects){
		this.subjects.add(subjects);
	}
	
	public void addAssignmentUsers(AssignmentUserEntity assignmentUsers){
		this.assignmentUsers.add(assignmentUsers);
	}
	
	
	
		

}
