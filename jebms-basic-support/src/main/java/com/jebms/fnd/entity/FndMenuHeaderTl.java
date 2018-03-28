package com.jebms.fnd.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_menu_headers_tl")
public class FndMenuHeaderTl extends BaseEntity{

	@Id
    private Long menuId;

	@Id
    private String language;
    
    private String sourceLang;

    private String menuName;

    /**
     * 描述
     */
    private String description;

    public FndMenuHeaderTl() {
    }
    
    public FndMenuHeaderTl(Long menuId,String language) {
        this.menuId = menuId;
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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}