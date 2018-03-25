package com.jebms.per.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import com.jebms.comm.core.BaseEntity;

@Table(name="per_workgroup_emps")
public class PerWorkgroupEmp extends BaseEntity{
    /**
     * id
     */
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private String workGroupId;

    private Long personId;

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

	public String getWorkGroupId() {
		return workGroupId;
	}

	public void setWorkGroupId(String workGroupId) {
		this.workGroupId = workGroupId;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

}