package kr.ac.skhu.drcode.department;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	public List<DepartmentDto> getDepartments() {
		
		List<DepartmentEntity> departmentEntityList = departmentRepository.findAll();
		List<DepartmentDto> departmentDtoList = new ArrayList<>();
			
		for(DepartmentEntity e : departmentEntityList) {
			DepartmentDto departmentDto = new DepartmentDto();
			departmentDto.setId(e.getId());
			departmentDto.setDepartmentName(e.getDepartmentName());
			
			departmentDtoList.add(departmentDto);
		}
		
		return departmentDtoList;
	}

}
