package com.jebms.per.entity;

public class PerPersonVO extends PerPerson{

    private String jobCode;

    private String jobName;

    private String departmentCode;

    private String departmentName;

    private String managerNumber;

    private String managerFname;

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getManagerNumber() {
		return managerNumber;
	}

	public void setManagerNumber(String managerNumber) {
		this.managerNumber = managerNumber;
	}

	public String getManagerFname() {
		return managerFname;
	}

	public void setManagerFname(String managerFname) {
		this.managerFname = managerFname;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


}