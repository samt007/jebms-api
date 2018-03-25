package com.jebms.fnd.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_menu_lines_tl")
public class FndMenuLineTl extends BaseEntity{

	@Id
    private Long menuId;

	@Id
    private Integer menuSequence;

	@Id
    private String language;
    
    private String sourceLang;

    private String prompt;

    /**
     * 描述
     */
    private String description;

    public FndMenuLineTl() {
    }
    
    public FndMenuLineTl(Long menuId,Integer menuSequence,String language) {
        this.menuId = menuId;
        this.menuSequence = menuSequence;
        this.language = language;
    }
    
    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
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

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
}