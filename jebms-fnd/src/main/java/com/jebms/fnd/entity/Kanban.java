package com.jebms.fnd.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.jebms.comm.core.BaseEntity;

@Table(name="bw_kanban")
public class Kanban extends BaseEntity{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;
	
	private Long customerId;
	
	private Integer amount;
	
	private String currency;
	
	private String payType;
	
	private String description;

	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date startDate;

	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date endDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}