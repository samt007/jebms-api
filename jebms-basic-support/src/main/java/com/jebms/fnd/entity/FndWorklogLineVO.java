package com.jebms.fnd.entity;

public class FndWorklogLineVO extends FndWorklogLine{

    private String lineTypeDesc;

    private String lineSubTypeDesc;

    private String languageDesc;

	public String getLineTypeDesc() {
		return lineTypeDesc;
	}

	public void setLineTypeDesc(String lineTypeDesc) {
		this.lineTypeDesc = lineTypeDesc;
	}

	public String getLineSubTypeDesc() {
		return lineSubTypeDesc;
	}

	public void setLineSubTypeDesc(String lineSubTypeDesc) {
		this.lineSubTypeDesc = lineSubTypeDesc;
	}

	public String getLanguageDesc() {
		return languageDesc;
	}

	public void setLanguageDesc(String languageDesc) {
		this.languageDesc = languageDesc;
	}

}