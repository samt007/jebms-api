package com.jebms.fnd.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="fnd_logins")
public class FndLogin {
    /**
     * 主键
     */
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登入时间
     */
    private Date startDate;

    /**
     * 登出时间
     */
    private Date endDate;

    /**
     * 登录语言
     */
    private String language;

    /**
     * 登录IP地址
     */
    private String ipAddress;

    /**
     * 描述
     */
    private String description;

    public FndLogin() {
    }
    
    public FndLogin(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}