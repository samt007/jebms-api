package com.jebms.fnd.entity;


public class FndMenuHeaderVO extends FndMenuHeader{


    /**
     * 菜单图标
     */
    private String menuIconCode;

    /**
     * 菜单图标描述
     */
    private String menuIconDesc;
    
    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 描述
     */
    private String description;

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

	public String getMenuIconCode() {
		return menuIconCode;
	}

	public void setMenuIconCode(String menuIconCode) {
		this.menuIconCode = menuIconCode;
	}

	public String getMenuIconDesc() {
		return menuIconDesc;
	}

	public void setMenuIconDesc(String menuIconDesc) {
		this.menuIconDesc = menuIconDesc;
	}

}