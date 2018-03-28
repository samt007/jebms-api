package com.jebms.fnd.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jebms.comm.core.BaseEntity;

@Table(name="fnd_icons")
public class FndIcon extends BaseEntity{
    /**
     * id
     */
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 图标代码
     */
    private String iconCode;

    /**
     * 图标源
     */
    private String iconSource;

    /**
     * 图标源
     */
    private String description;
    
    public FndIcon(){
    }
    
    public FndIcon(Long id){
    	this.id=id;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIconCode() {
		return iconCode;
	}

	public void setIconCode(String iconCode) {
		this.iconCode = iconCode;
	}

	public String getIconSource() {
		return iconSource;
	}

	public void setIconSource(String iconSource) {
		this.iconSource = iconSource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}