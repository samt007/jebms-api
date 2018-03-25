package com.jebms.fnd.entity;


/**
 * VO Entity对象，扩展Country基表类！
 * <br/>不直接用Country基表主要是为了2个目的：
 * <br/>1 首先，单从理解来说，Country基表对象和其加多了很多栏位的VO对象还是很大差异的。
 * <br/>2 基表对象的查询结果，如果多了几个串出来的栏位，但是通用Mapper又没办法自动产生，以后对开发会导致很大的误解！
 * <br/>3 如果一个基表对象对应N个VO对象（很可能的），那这样子就更加需要拆分了。不同的VO对应不同的业务！
 * @author Sam.T
 */
public class CountryVO extends Country {
	
    private String createdByUser;

	public String getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

}
