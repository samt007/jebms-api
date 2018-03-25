package com.jebms.fnd.entity;

public class FndFunctionVO extends FndFunction{

    /**
     * 功能图标
     */
    private String functionIconCode;
    /**
     * 功能名称
     */
    private String functionName;

    /**
     * 功能描述
     */
    private String description;
    /**
     * 应用代码
     */
    private String applCode;

    /**
     * 应用描述
     */
    private String applDesc;
    
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

	public String getFunctionIconCode() {
		return functionIconCode;
	}

	public void setFunctionIconCode(String functionIconCode) {
		this.functionIconCode = functionIconCode;
	}

	public String getApplCode() {
		return applCode;
	}

	public void setApplCode(String applCode) {
		this.applCode = applCode;
	}

	public String getApplDesc() {
		return applDesc;
	}

	public void setApplDesc(String applDesc) {
		this.applDesc = applDesc;
	}

}