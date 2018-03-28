package com.jebms.fnd.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_lookup_values")
public class FndLookupValue extends BaseEntity{

    /**
     * 功能应用id
     */
	@Id
    private Long applId;
	
    /**
     * 代码类型
     */
	@Id
    private String lookupType;

    /**
     * 代码值
     */
	@Id
    private String lookupCode;

    /**
     * 代码语言
     */
	@Id
    private String language;
    
    private String sourceLang;
	
    /**
     * 代码值meaning
     */
    private String meaning;

    /**
     * 代码描述
     */
    private String description;

    private String tag;

    private String enabledFlag;

    /**
     * 启用时间
     */
    private Date startDate;

    /**
     * 失效时间
     */
    private Date endDate;

    public String getLookupType() {
        return lookupType;
    }

    public void setLookupType(String lookupType) {
        this.lookupType = lookupType;
    }

    public String getLookupCode() {
        return lookupCode;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
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

	public Long getApplId() {
		return applId;
	}

	public void setApplId(Long applId) {
		this.applId = applId;
	}

	public String getSourceLang() {
		return sourceLang;
	}

	public void setSourceLang(String sourceLang) {
		this.sourceLang = sourceLang;
	}

}