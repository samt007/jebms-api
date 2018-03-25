package com.jebms.comm.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Date;

/**
 * Security User
 */
@SuppressWarnings("serial")
public class AuthUser extends AbstractAuthUser {

    private Long id;

    private String username;

    private String password;

    private String nicename;

    private String phone;

    private String email;
    
    private Date registeredDate;

    private Date passwordDate;

    private Date startDate;

    private Date endDate;

    private Long personId;
    
    private String empNumber;
    
    private String fullName;
    
    private String description;

    //最后一次有效的正常使用的时间:暂时不需要了
    //private Date lastActiveDate;

    /**
     * 登录ID
     */
    private Long loginId;
    
    /**
     * 登录语言
     */
    private String language;
    
    /**
     * 权限
     */
    private Collection<SimpleGrantedAuthority> authorities;

    public AuthUser(Long id) {
        this.id = id;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * 锁定(失效)
     */
    public void setEnabled(boolean enabled) {
        if(!enabled){
        	this.endDate=new Date();
        }else{
        	this.endDate=null;
        }
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
    	Date now=new Date();
    	return now.after(this.startDate)&&(this.endDate == null || now.before(this.endDate));
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    @Override
    public String getUsername() {
        return username;
    }
	
	public void setUsername(String username) {
		this.username = username;
	}
	
    @JsonIgnore
    @Override
	public String getPassword() {
		return password;
	}
    
	public void setPassword(String password) {
		this.password = password;
	}

	public String getNicename() {
		return nicename;
	}

	public void setNicename(String nicename) {
		this.nicename = nicename;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public Date getPasswordDate() {
		return passwordDate;
	}

	public void setPasswordDate(Date passwordDate) {
		this.passwordDate = passwordDate;
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

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

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

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}
}
