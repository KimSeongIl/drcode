package kr.ac.skhu.drcode.subject;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kr.ac.skhu.drcode.assignment.AssignmentEntity;
import kr.ac.skhu.drcode.user.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Entity
@Table(name = "subject", catalog = "drcode")
public class SubjectEntity {

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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
	private List<AssignmentEntity> assignments = new ArrayList<AssignmentEntity>(0);
	
	public void addAssignments(AssignmentEntity assignment){
		this.assignments.add(assignment);
	}
	
}
