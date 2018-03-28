package com.jebms.fnd.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_menu_headers")
public class FndMenuHeader extends BaseEntity{
    /**
     * 菜单头id
     */
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 菜单应用id
     */
    private Long applId;

    /**
     * 菜单头代码
     */
    private String menuCode;

    /**
     * 图标id
     */
    private Long menuIconId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplId() {
        return applId;
    }

    public void setApplId(Long applId) {
        this.applId = applId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public Long getMenuIconId() {
        return menuIconId;
    }

    public void setMenuIconId(Long menuIconId) {
        this.menuIconId = menuIconId;
    }

}