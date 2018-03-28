package com.jebms.fnd.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_lookup_types")
public class FndLookupType extends BaseEntity{

    /**
     * 功能应用id
     */
	@Id
    private Long applId;
	
    /**
     * 代码类型
     */
	@Id
    private String lookupType;

	public Long getApplId() {
		return applId;
	}

	public void setApplId(Long applId) {
		this.applId = applId;
	}

	public String getLookupType() {
		return lookupType;
	}

	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}
	
}