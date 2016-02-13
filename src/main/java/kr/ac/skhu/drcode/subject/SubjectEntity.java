package kr.ac.skhu.drcode.subject;


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


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import kr.ac.skhu.drcode.assignment.AssignmentEntity;
import kr.ac.skhu.drcode.user.UserEntity;




@Entity
@Table(name = "subject", catalog = "drcode")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=SubjectEntity.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubjectEntity{

	


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",  unique = true, nullable = false)
	private int id;
	
	
	
	@Column(name = "subject_code")
	private String subjectCode;
	
	@Column(name = "subject_name")
	private String subjectName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "professor_id")
	private UserEntity professor;
	

	
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subject" )
	private Set<AssignmentEntity> assignments = new HashSet<AssignmentEntity>(0);
	
	public void addAssignments(AssignmentEntity assignment){
		this.assignments.add(assignment);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public UserEntity getProfessor() {
		return professor;
	}

	public void setProfessor(UserEntity professor) {
		this.professor = professor;
	}

	public Set<AssignmentEntity> getAssignments() {
		return assignments;
	}

	public void setAssignments(Set<AssignmentEntity> assignments) {
		this.assignments = assignments;
	}

	
	
	
}
