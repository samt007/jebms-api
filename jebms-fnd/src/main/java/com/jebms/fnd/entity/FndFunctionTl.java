package com.jebms.fnd.entity;


import javax.persistence.Id;
import javax.persistence.Table;

import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_functions_tl")
public class FndFunctionTl extends BaseEntity{
    /**
     * 功能id
     */
	@Id
    private Long functionId;

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
     * 功能名称
     */
    private String functionName;

    /**
     * 功能描述
     */
    private String description;
    
    public FndFunctionTl() {
    }
    
    public FndFunctionTl(Long functionId,String language) {
        this.functionId = functionId;
        this.language = language;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
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

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}