package com.jebms.fnd.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_worklog_lines")
public class FndWorklogLine extends BaseEntity{
    /**
     * 菜单行id
     */
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private Long headerId;

    private Integer lineNum;

    private String lineType;

    private String lineSubType;

    private String lineContent;

    private Date lineStartDate;

    private Date lineFinishDate;

    private String applicationShortName;

    private String language;

    private String description;
    
    private Long erpLineId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHeaderId() {
		return headerId;
	}

	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public String getLineSubType() {
		return lineSubType;
	}

	public void setLineSubType(String lineSubType) {
		this.lineSubType = lineSubType;
	}

	public String getLineContent() {
		return lineContent;
	}

	public void setLineContent(String lineContent) {
		this.lineContent = lineContent;
	}

	public Date getLineStartDate() {
		return lineStartDate;
	}

	public void setLineStartDate(Date lineStartDate) {
		this.lineStartDate = lineStartDate;
	}

	public Date getLineFinishDate() {
		return lineFinishDate;
	}

	public void setLineFinishDate(Date lineFinishDate) {
		this.lineFinishDate = lineFinishDate;
	}

	public String getApplicationShortName() {
		return applicationShortName;
	}

	public void setApplicationShortName(String applicationShortName) {
		this.applicationShortName = applicationShortName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getErpLineId() {
		return erpLineId;
	}

	public void setErpLineId(Long erpLineId) {
		this.erpLineId = erpLineId;
	}
}