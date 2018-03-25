package com.jebms.fnd.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FndRespVO extends FndResp{
	
    /**
     * 职责名称(多语言)
     */
    private String respName;

    /**
     * 职责描述(多语言)
     */
    private String description;

    /**
     * 菜单头代码
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 主菜单VO对象。需要注意，这里包括是菜单的次级子菜单对象，包括所有的级联子菜单！
     */
    private List<FndMenuVO> menuVOs = new ArrayList<>();

	public String getRespName() {
		return respName;
	}

	public void setRespName(String respName) {
		this.respName = respName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	//是否有效
    public boolean isEnabled() {
    	Date now=new Date();
    	return now.after(this.getStartDate())&&(this.getEndDate() == null || now.before(this.getEndDate()));
    }

	public List<FndMenuVO> getMenuVOs() {
		return menuVOs;
	}

	public void setMenuVOs(List<FndMenuVO> menuVOs) {
		this.menuVOs = menuVOs;
	}

}