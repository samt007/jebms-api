package com.jebms.fnd.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_functions")
public class FndFunction extends BaseEntity{
    /**
     * 菜单行id
     */
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 功能应用id
     */
    private Long applId;

    /**
     * 功能代码
     */
    private String functionCode;

    /**
     * 功能链接url地址
     */
    private String functionHref;

    /**
     * 功能图标id
     */
    private Long functionIconId;

    /**
     * 权限标识
     */
    private String permission;

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

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getFunctionHref() {
        return functionHref;
    }

    public void setFunctionHref(String functionHref) {
        this.functionHref = functionHref;
    }

    public Long getFunctionIconId() {
        return functionIconId;
    }

    public void setFunctionIconId(Long functionIconId) {
        this.functionIconId = functionIconId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

}