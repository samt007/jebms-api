package com.jebms.fnd.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_worklog_headers")
public class FndWorklogHeader extends BaseEntity{
    /**
     * 菜单行id
     */
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private Long departmentId;

    private Long workGroupId;

    private String workType;

    private String defLineType;

    private String workPriorty;

    private String workItem;

    private String workReqDocument;

    private Long workRequestPid;

    private Long workOwnerPid;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date scheduleStartDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date actualStartDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date scheduleFinishDate;

    private Integer workSpendHours;

    private String workLog;

    private String status;

    private Date statusWtDate;

    private Long statusWtId;

    private Date cancelDate;

    private Long cancelUserId;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date actualFinishDate;

    private Long finishUserId;

    private Long erpHeaderId;

    private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getDefLineType() {
		return defLineType;
	}

	public void setDefLineType(String defLineType) {
		this.defLineType = defLineType;
	}

	public String getWorkPriorty() {
		return workPriorty;
	}

	public void setWorkPriorty(String workPriorty) {
		this.workPriorty = workPriorty;
	}

	public String getWorkItem() {
		return workItem;
	}

	public void setWorkItem(String workItem) {
		this.workItem = workItem;
	}

	public String getWorkReqDocument() {
		return workReqDocument;
	}

	public void setWorkReqDocument(String workReqDocument) {
		this.workReqDocument = workReqDocument;
	}

	public Long getWorkRequestPid() {
		return workRequestPid;
	}

	public void setWorkRequestPid(Long workRequestPid) {
		this.workRequestPid = workRequestPid;
	}

	public Long getWorkOwnerPid() {
		return workOwnerPid;
	}

	public void setWorkOwnerPid(Long workOwnerPid) {
		this.workOwnerPid = workOwnerPid;
	}

	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDateDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getScheduleFinishDate() {
		return scheduleFinishDate;
	}

	public void setScheduleFinishDate(Date scheduleFinishDate) {
		this.scheduleFinishDate = scheduleFinishDate;
	}

	public Integer getWorkSpendHours() {
		return workSpendHours;
	}

	public void setWorkSpendHours(Integer workSpendHours) {
		this.workSpendHours = workSpendHours;
	}

	public String getWorkLog() {
		return workLog;
	}

	public void setWorkLog(String workLog) {
		this.workLog = workLog;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusWtDate() {
		return statusWtDate;
	}

	public void setStatusWtDate(Date statusWtDate) {
		this.statusWtDate = statusWtDate;
	}

	public Long getStatusWtId() {
		return statusWtId;
	}

	public void setStatusWtId(Long statusWtId) {
		this.statusWtId = statusWtId;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public Long getCancelUserId() {
		return cancelUserId;
	}

	public void setCancelUserId(Long cancelUserId) {
		this.cancelUserId = cancelUserId;
	}

	public Date getActualFinishDate() {
		return actualFinishDate;
	}

	public void setActualFinishDate(java.sql.Date actualFinishDate) {
		this.actualFinishDate = actualFinishDate;
	}

	public Long getFinishUserId() {
		return finishUserId;
	}

	public void setFinishUserId(Long finishUserId) {
		this.finishUserId = finishUserId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getWorkGroupId() {
		return workGroupId;
	}

	public void setWorkGroupId(Long workGroupId) {
		this.workGroupId = workGroupId;
	}

	public Long getErpHeaderId() {
		return erpHeaderId;
	}

	public void setErpHeaderId(Long erpHeaderId) {
		this.erpHeaderId = erpHeaderId;
	}
}