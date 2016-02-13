package kr.ac.skhu.drcode.assignment;


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import kr.ac.skhu.drcode.assignmentUser.AssignmentUserEntity;
import kr.ac.skhu.drcode.subject.SubjectEntity;



@Entity
@Table(name = "assignment", catalog = "drcode")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=AssignmentEntity.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AssignmentEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",  unique = true, nullable = false)
	private int id;
	
	
	@Column(name = "assignment_name")
	private String assignmentName;
	
	@Column(name = "content")
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "limit_time")
	private Date limitTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "retention_time")
	private Date retentionTime;
	
	@Column(name = "answer")
	private String answer;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id", nullable = false)
	private SubjectEntity subject;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "assignment")
	Set<AssignmentUserEntity> assignmentUsers =new HashSet<AssignmentUserEntity>(0);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Date limitTime) {
		this.limitTime = limitTime;
	}

	public Date getRetentionTime() {
		return retentionTime;
	}

	public void setRetentionTime(Date retentionTime) {
		this.retentionTime = retentionTime;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}

	public Set<AssignmentUserEntity> getAssignmentUsers() {
		return assignmentUsers;
	}

	public void setAssignmentUsers(Set<AssignmentUserEntity> assignmentUsers) {
		this.assignmentUsers = assignmentUsers;
	}


	
	
	
}
