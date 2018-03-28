package com.jebms.per.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import com.jebms.comm.core.BaseEntity;

@Table(name="per_workgroups")
public class PerWorkgroup extends BaseEntity{
    /**
     * id
     */
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private String workGroupCode;

    private String workGroupName;

    private Long departmentId;

    private Long managerId;

    /**
     * 启用时间
     */
    private Date startDate;

    /**
     * 失效时间
     */
    private Date endDate;
    
    private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkGroupCode() {
		return workGroupCode;
	}

	public void setWorkGroupCode(String workGroupCode) {
		this.workGroupCode = workGroupCode;
	}

	public String getWorkGroupName() {
		return workGroupName;
	}

	public void setWorkGroupName(String workGroupName) {
		this.workGroupName = workGroupName;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}