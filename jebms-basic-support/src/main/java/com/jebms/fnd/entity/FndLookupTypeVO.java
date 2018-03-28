package com.jebms.fnd.entity;

public class FndLookupTypeVO extends FndLookupType{

    /**
     * 应用代码
     */
    private String applCode;

    /**
     * 应用描述
     */
    private String applDesc;

    /**
     * 代码含义
     */
    private String meaning;

    /**
     * 功能描述
     */
    private String description;

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