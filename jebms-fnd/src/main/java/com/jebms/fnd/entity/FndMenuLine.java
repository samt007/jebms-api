package com.jebms.fnd.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_menu_lines")
public class FndMenuLine extends BaseEntity{
    /**
     * 菜单头id
     */
	@Id
    private Long menuId;

    /**
     * 菜单序号
     */
	@Id
    private Integer menuSequence;
	
    /**
     * 子菜单id
     */
    private Long subMenuId;

    /**
     * 功能id
     */
    private Long functionId;

    /**
     * 启用标志
     */
    private String enabledFlag;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Integer getMenuSequence() {
        return menuSequence;
    }

    public void setMenuSequence(Integer menuSequence) {
        this.menuSequence = menuSequence;
    }

    public Long getSubMenuId() {
        return subMenuId;
    }

    public void setSubMenuId(Long subMenuId) {
        this.subMenuId = subMenuId;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

}