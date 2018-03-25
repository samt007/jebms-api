package com.jebms.fnd.entity;

public class FndMenuLineVO extends FndMenuLine{


    /**
     * 菜单行提示
     */
    private String prompt;

    /**
     * 菜单行描述
     */
	private String description;
    
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
     * 功能图标
     */
    private String functionIconCode;

    /**
     * 功能权限标识
     */
    private String permission;

    /**
     * 功能名称
     */
    private String functionName;

    /**
     * 子菜单图标id
     */
    private String subMenuCode;

    /**
     * 子菜单图标id
     */
    private String subMenuName;

    /**
     * 子菜单图标id
     */
    private Long subMenuIconId;
    
    /**
     * 子菜单图标
     */
    private String subMenuIconCode;
    
    //private FndMenuVO subMenuVO;//子菜单VO对象

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

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
/*
	public FndMenuVO getSubMenuVO() {
		return subMenuVO;
	}

	public void setSubMenuVO(FndMenuVO subMenuVO) {
		this.subMenuVO = subMenuVO;
	}
*/
	public String getFunctionIconCode() {
		return functionIconCode;
	}

	public void setFunctionIconCode(String functionIconCode) {
		this.functionIconCode = functionIconCode;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubMenuIconCode() {
		return subMenuIconCode;
	}

	public void setSubMenuIconCode(String subMenuIconCode) {
		this.subMenuIconCode = subMenuIconCode;
	}

	public Long getSubMenuIconId() {
		return subMenuIconId;
	}

	public void setSubMenuIconId(Long subMenuIconId) {
		this.subMenuIconId = subMenuIconId;
	}

	public String getSubMenuCode() {
		return subMenuCode;
	}

	public void setSubMenuCode(String subMenuCode) {
		this.subMenuCode = subMenuCode;
	}

	public String getSubMenuName() {
		return subMenuName;
	}

	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}
}