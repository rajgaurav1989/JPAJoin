package com.raju.dto;

public class FascilityDto {
	private Long id ;
	private String fascilityName;
	private Long employeeId;
	
	public FascilityDto(Long id,String fascilityName,Long employeeId) {
		this.employeeId = employeeId;
		this.id = id;
		this.fascilityName = fascilityName ;
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
	}
	
}
