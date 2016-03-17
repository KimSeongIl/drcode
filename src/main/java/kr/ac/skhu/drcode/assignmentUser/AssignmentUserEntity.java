package kr.ac.skhu.drcode.assignmentUser;





import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kr.ac.skhu.drcode.assignment.AssignmentEntity;
import kr.ac.skhu.drcode.user.UserEntity;





import lombok.Data;





@Data
@Entity
@Table(name = "assignment_user", catalog = "drcode")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=AssignmentUserEntity.class)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AssignmentUserEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",  unique = true, nullable = false)
	private int id;
		
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignment_id", nullable = false)
	private AssignmentEntity assignment;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;
	
	@Column(name = "score")
	private double score;

	


	
	
	
	
}
