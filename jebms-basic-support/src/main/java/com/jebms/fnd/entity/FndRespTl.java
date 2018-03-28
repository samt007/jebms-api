package com.jebms.fnd.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_resp_tl")
public class FndRespTl extends BaseEntity{
    /**
     * 对应职责ID
     */
	@Id
    private Long respId;

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
     * 职责名称
     */
    private String respName;

    /**
     * 职责描述
     */
    private String description;

    public FndRespTl() {
    }
    
    public FndRespTl(Long respId,String language) {
        this.respId = respId;
        this.language = language;
    }
    
    public Long getRespId() {
        return respId;
    }

    public void setRespId(Long respId) {
        this.respId = respId;
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

    public String getRespName() {
        return respName;
    }

    public void setRespName(String respName) {
        this.respName = respName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}