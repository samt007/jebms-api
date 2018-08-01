package com.jebms.comm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.security.model.AuthUser;
import com.jebms.comm.utils.SqlUtil;

/**
 * 封装搜索 Entity
 * <br>conditionMap：搜索的Map条件对象
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2018年3月8日 更新：将条件添加再完美点！！
 */

@ApiModel(value="封装分页搜索 Entity",description="搜索对象")
public class SearchInfo extends ConditionInfo {

    /**
     * 当前页
     */
	@ApiModelProperty(value = "当前页",required=true,dataType = "int", example = "1")
    private int pageNum;
    /**
     * 每页行数
     */
	@ApiModelProperty(value = "每页行数",required=true,dataType = "int", example = "10")
    private int pageSize;
    /**
     * 是否查询总行数
     */
	@ApiModelProperty(value = "查询总行数标识",required=true, example = "false")
    private boolean count;

    public SearchInfo(){
    	this(null);
    }
    
    /**
     * 直接根据请求的json组合成一个查询对象。方便使用。
     * @param json
     * @param user
     */
    public SearchInfo(AuthUser user){
    	super(user);
    	this.pageNum=1;
    	this.pageSize=10;
    	this.count=false;
    }
    
    /**
     * 直接根据请求的json组合成一个查询对象。方便使用。
     * @param json
     * @param user
     */
    public SearchInfo(JSONObject json,AuthUser user){
    	this.pageNum=json.getIntValue("pageNum");
    	this.pageSize=json.getIntValue("pageSize");
    	this.count=json.getBooleanValue("count");
    	this.setConditionMap(new HashMap<String,Object>());
    	this.setOrderBy(json.getString("orderBy"));
    	this.setAuthUser(user);
    	if(this.getOrderBy()==null||this.getOrderBy().length()==0){
    		this.setOrderBy("1");
    	}else{
    		this.setOrderBy(SqlUtil.getUnderScoreOrderBy(this.getOrderBy()));
    	}
    	this.initSqlCondition();
    }
    
	/**
	 * 初始化搜索对象，为了避免sql注入！
	 */
	public void init(AuthUser authUser,Map<String, Object> conditionMap) {
		super.init(authUser, conditionMap);
	}
	/**
	 * 2018.4.17：初始化，注意这里也初始化SqlCondition字符串（避免sql注入）！
	 */
	public void init(AuthUser authUser) {
		super.init(authUser);
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isCount() {
		return count;
	}

	public void setCount(boolean count) {
		this.count = count;
	}

}
