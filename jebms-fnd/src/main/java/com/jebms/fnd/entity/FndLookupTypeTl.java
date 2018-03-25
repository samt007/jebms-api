package com.jebms.fnd.entity;


import javax.persistence.Id;
import javax.persistence.Table;

import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_lookup_types_tl")
public class FndLookupTypeTl extends BaseEntity{

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
     * 语言
     */
	@Id
    private String language;
	
    /**
     * 数据来源语言
     */
    private String sourceLang;

    /**
     * 代码含义
     */
    private String meaning;

    /**
     * 功能描述
     */
    private String description;
    
    public FndLookupTypeTl() {
    }
    
    public FndLookupTypeTl(Long applId,String lookupType,String language) {
        this.applId = applId;
        this.lookupType = lookupType;
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    public String getSourceLang() {
        return sourceLang;
    }

    public void setSourceLang(String sourceLang) {
        this.sourceLang = sourceLang;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public Long getApplId() {
		return applId;
	}

	public void setApplId(Long applId) {
		this.applId = applId;
	}

	public String getLookupType() {
		return lookupType;
	}

	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}
}