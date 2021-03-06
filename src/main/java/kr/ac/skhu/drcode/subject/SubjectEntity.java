package kr.ac.skhu.drcode.subject;


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








import kr.ac.skhu.drcode.assignment.AssignmentEntity;
import kr.ac.skhu.drcode.user.UserEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "subject", catalog = "drcode")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=SubjectEntity.class)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubjectEntity implements Serializable{

	


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

	
}
