package com.jebms.fnd.entity;


import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.jebms.comm.core.BaseEntity;

@Table(name="country")
public class Country extends BaseEntity {
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private String countryName;
    
    private String countryCode;
    
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date startDate;

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date endDate;

    private Integer typeId;
    
    private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
