package com.jebms.erp.entity;


public class FndUserVO extends FndUser{

    /**
     * 用户登录的语言
     */
    private String language;
    
    private String empNumber;
    
    private String fullName;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}