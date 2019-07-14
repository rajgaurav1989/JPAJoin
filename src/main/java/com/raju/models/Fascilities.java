package com.raju.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fascilities")
public class Fascilities {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "fascilityName", unique = false, nullable = false)
	private String fascilityName;
	
	@OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "employeeId", nullable = false) private Employee employee;
	
	@Column(name = "employeeId", insertable = false,updatable = false)
	private Long employeeId;
	
	public Fascilities() {
		
	}
	
	public Fascilities(String fascilityName, Long employeeId) {
		this.fascilityName = fascilityName ;
		this.employeeId = employeeId ;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFascilityName() {
		return fascilityName;
	}

	public void setFascilityName(String fascilityName) {
		this.fascilityName = fascilityName;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
		Employee employee = new Employee() ;
		employee.setId(employeeId);
		this.setEmployee(employee);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	
	
	
	
}
