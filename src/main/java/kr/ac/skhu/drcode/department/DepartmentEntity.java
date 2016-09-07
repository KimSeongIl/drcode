package kr.ac.skhu.drcode.department;

import java.io.Serializable;
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


import kr.ac.skhu.drcode.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "department", catalog = "drcode")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id" ,scope=SubjectEntity.class)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DepartmentEntity implements Serializable{

	


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",  unique = true, nullable = false)
	private int id;
	
	@Column(name = "department_name")
	private String departmentName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department" )
	private Set<UserEntity> users = new HashSet<UserEntity>(0);



}





