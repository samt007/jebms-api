package com.jebms.fnd.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_resp")
public class FndResp extends BaseEntity{
    /**
     * 主键
     */
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 职责应用ID
     */
    private Long applId;

    /**
     * 职责代码
     */
    private String respCode;

    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 启用时间
     */
    private Date startDate;

    /**
     * 失效时间
     */
    private Date endDate;

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

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}