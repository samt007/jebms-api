package com.jebms.fnd.entity;

import java.util.ArrayList;
import java.util.List;

public class FndUserVO extends FndUser{

    /**
     * 用户登录的语言
     */
    private String language;
    
    private String empNumber;
    
    private String fullName;
    
    /**
     * 职责列表
     */
    private List<FndRespVO> respVOs = new ArrayList<>();
    
    /**
     * 菜单列表
     */
    private List<FndMenuVO> menuVOs = new ArrayList<>();

	public List<FndRespVO> getRespVOs() {
		return respVOs;
	}

	public void setRespVOs(List<FndRespVO> respVOs) {
		this.respVOs = respVOs;
	}

	public List<FndMenuVO> getMenuVOs() {
		return menuVOs;
	}

	public void setMenuVOs(List<FndMenuVO> menuVOs) {
		this.menuVOs = menuVOs;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}