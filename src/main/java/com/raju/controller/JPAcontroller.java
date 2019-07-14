package com.raju.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raju.dto.FascilityDto;
import com.raju.exceptions.ResourceNotFoundException;
import com.raju.models.Employee;
import com.raju.models.Fascilities;
import com.raju.repository.EmployeeRepository;
import com.raju.repository.FascilitiesRepository;

@RestController
@RequestMapping("/api/v1")
public class JPAcontroller {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired private FascilitiesRepository fascilitiesRepository;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/join/{id}")
	public List<FascilityDto> getJoin(@PathVariable(value = "id") Long id){
		List<Fascilities> intmresult = fascilitiesRepository.getJoin(id);
		List<FascilityDto> result = new ArrayList<>();
		
		for (Fascilities fascility : intmresult) {
			result.add(new FascilityDto(fascility.getId(),fascility.getFascilityName(),fascility.getEmployeeId()));
		}
		
		return result;
	}
	
	@GetMapping("/joinOther/{id}")
	public List<FascilityDto> getJoinOther(@PathVariable(value = "id") Long id){
		List<Object []> intmresult = fascilitiesRepository.getJoinOther(id);
		List<FascilityDto> result = new ArrayList<>();
		
		for (int index = 0 ; index < intmresult.size() ; index++) {
			Object[] item = (Object []) intmresult.get(index);
			FascilityDto fascilityDto = new FascilityDto((Long) item[0],(String) item[1],(Long) item[2]);
			result.add(fascilityDto);
		}
		return result;
	}
	
	@PostMapping("/fascilities")
	public Fascilities createFascilities(@Valid @RequestBody Fascilities fascility) {
		return fascilitiesRepository.save(fascility);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
