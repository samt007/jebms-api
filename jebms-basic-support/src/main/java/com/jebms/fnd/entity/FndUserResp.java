package com.jebms.fnd.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_user_resp")
public class FndUserResp extends BaseEntity{
    /**
     * 主键
     */
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 职责ID
     */
    private Long respId;

    /**
     * 职责应用ID
     */
    private Long respApplId;

    /**
     * 有效时间
     */
    private Date startDate;

    /**
     * 失效时间
     */
    private Date endDate;

    /**
     * 描述
     */
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRespId() {
        return respId;
    }

    public void setRespId(Long respId) {
        this.respId = respId;
    }

    public Long getRespApplId() {
        return respApplId;
    }

    public void setRespApplId(Long respApplId) {
        this.respApplId = respApplId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}