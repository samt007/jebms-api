package com.jebms.comm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

import com.jebms.comm.security.model.AuthUser;
import com.jebms.comm.utils.SqlUtil;

/**
 * 封装搜索条件 Entity，和Page分页对象分家！
 * <br>conditionMap：搜索的Map条件对象
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2018年4月25日
 */

@ApiModel(value="封装SQL搜索条件 Entity",description="SQL搜索条件对象")
public class ConditionInfo {

    /**
     * 搜索条件Map类型
     */
	@ApiModelProperty(value = "搜索条件",dataType = "Map", example = "",hidden = true)
	private Map<String,Object> conditionMap;

    /**
     * 搜索条件String类型。
     * 注意：这个是经过自动处理的，提供给Map.xml使用的！
     */
	@ApiModelProperty(value = "搜索条件String类型",dataType = "String", example = "",hidden = true)
	private String sqlCondition;

    /**
     * 2017.7.4添加排序
     */
	@ApiModelProperty(value = "数据排序",dataType = "String", example = "")
	private String orderBy;

    /**
     * 用户信息。这个信息由Token获取！
     */
    @ApiModelProperty(value = "认证用户对象",hidden = true)
    private AuthUser authUser;
    
    public ConditionInfo(){
    	this.conditionMap=new HashMap<String,Object>();
    	this.orderBy="1";
    	this.initSqlCondition();
    }
    
    /**
     * 直接根据请求的json组合成一个查询对象。方便使用。
     * @param json
     * @param user
     */
    public ConditionInfo(AuthUser user){
    	this();
    	this.authUser=user;
    }
	
	/**
	 * 初始化搜索对象，为了避免sql注入！
	 */
	public void init(AuthUser authUser,Map<String, Object> conditionMap) {
		this.initCondition(conditionMap);
		this.authUser=authUser;
	}
	/**
	 * 2018.4.17：初始化，注意这里也初始化SqlCondition字符串（避免sql注入）！
	 */
	public void init(AuthUser authUser) {
		this.initSqlCondition();
		this.authUser=authUser;
	}
	
	/**
	 * 初始化字符串的条件，为了避免sql注入！
	 */
	public void initCondition(Map<String, Object> conditionMap){
		this.initSqlCondition();
		this.conditionMap = conditionMap;
	}
	/**
	 * 初始化字符串的条件 基础 方法，为了避免sql注入！
	 */
	public void initSqlCondition(){
		this.sqlCondition = "";
	}
	
	/**
	 * 2018.4.17新增：添加conditionMap条件。为联调用。
	 */
	public ConditionInfo putConditionMap(String key,Object value) throws Exception{
		this.conditionMap.put(key, value);
		return this;
	}
	
	/**
	 * 系统封装好的根据栏位和值动态添加一个and条件
	 */
	public ConditionInfo andSqlCondition(String colName,String colParamName) throws Exception{
		this.sqlCondition += SqlUtil.getAndStmtMyBatis(this.getConditionMap(),colName,colParamName);
		return this;
	}
	/**
	 * 系统封装好的根据栏位和值动态添加一个and条件。最后一个条件是限制条件是否必输。
	 */
	public ConditionInfo andSqlCondition(String colName,String colParamName,Boolean forceFlag) throws Exception{
		this.sqlCondition += SqlUtil.getAndStmtMyBatis(this.getConditionMap(),colName,colParamName,forceFlag);
		return this;
	}

	/**
	 * 系统封装好的根据栏位和值动态添加一个and条件
	 */
	public ConditionInfo andSqlCondition(String colName,String colParamNameLow,String colParamNameHigh) throws Exception{
		this.sqlCondition += SqlUtil.getAndStmtMyBatis(this.getConditionMap(),colName,colParamNameLow,colParamNameHigh);
		return this;
	}
	/**
	 * 系统封装好的根据栏位和值动态添加一个and条件
	 */
	public ConditionInfo andSqlCondition(String colName,String colParamNameLow,String colParamNameHigh,Boolean forceFlag) throws Exception{
		this.sqlCondition += SqlUtil.getAndStmtMyBatis(this.getConditionMap(),colName,colParamNameLow,colParamNameHigh,forceFlag);
		return this;
	}
	
	/**
	 * 注意，这里是单纯添加sql条件，类似append功能
	 * <br/>样例： AND ORG_ID = #{conditionMap.salesOrgId}  AND ACCOUNT_NAME LIKE #{conditionMap.accountName} 
	 */
	public ConditionInfo appendSqlCondition(String condiftion){
		this.sqlCondition += condiftion;
		return this;
	}
	
	/**
	 * 转换为下横线的排序order by。
	 * <br/>按需使用：当你的mapper的sql是正常的栏位信息，则在service层调用这个转换order by条件！
	 */
	public ConditionInfo toUnderScoreOrderBy(){
		this.orderBy=SqlUtil.getUnderScoreOrderBy(this.orderBy);
		return this;
	}

	
	
    public Map<String,Object> getConditionMap() {
        return conditionMap;
    }
    
	public void setConditionMap(Map<String, Object> conditionMap) {
		this.conditionMap = conditionMap;
	}
    
	public String getSqlCondition() {
		return sqlCondition;
	}

	/* 2018.4.17：该方法不允许开发！否则会导致条件整体被替换的问题。
	public void setSqlCondition(String sqlCondition) {
		this.sqlCondition = sqlCondition;
	}*/

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public AuthUser getAuthUser() {
		return authUser;
	}

	public void setAuthUser(AuthUser authUser) {
		this.authUser = authUser;
	}
}
