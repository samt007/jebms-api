package com.jebms.fnd.entity;

import java.util.Date;


public class FndUserRespVO extends FndUserResp{

    /**
     * 职责代码
     */
    private String respCode;
    
    /**
     * 职责名称(多语言)
     */
    private String respName;

    /**
     * 职责描述(多语言)
     */
    private String respDesc;

    /**
     * 菜单头代码
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 主菜单VO对象。包括所有的功能以及子菜单！
     */
    private FndMenuVO menuVO;

	public String getRespName() {
		return respName;
	}

	public void setRespName(String respName) {
		this.respName = respName;
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

	public FndMenuVO getMenuVO() {
		return menuVO;
	}

	public void setMenuVO(FndMenuVO menuVO) {
		this.menuVO = menuVO;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

}