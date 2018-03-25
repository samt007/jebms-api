package com.jebms.comm.entity;

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
 * @date 2017年5月27日
 */

public class SearchInfo {

    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 每页行数
     */
    private int pageSize;
    /**
     * 是否查询总行数
     */
    private boolean count;
    /**
     * 2017.7.4添加排序
     */
    private String orderBy;

    /**
     * 搜索条件Map类型
     */
    private Map<String,Object> conditionMap;

    /**
     * 搜索条件String类型。
     * 注意：这个是经过自动处理的，提供给Map.xml使用的！
     */
    private String sqlCondition;

    /**
     * 用户信息。这个信息由Token获取！
     */
    private AuthUser authUser;
    
    public SearchInfo(){
    	this.pageNum=1;
    	this.pageSize=10;
    	this.count=false;
    	this.conditionMap=new HashMap<String,Object>();
    	this.orderBy="1";
    }
    
    //直接根据请求的json组合成一个查询对象。方便使用。
    public SearchInfo(JSONObject json,AuthUser user){
    	this.pageNum=json.getIntValue("pageNum");
    	this.pageSize=json.getIntValue("pageSize");
    	this.count=json.getBooleanValue("count");
    	this.conditionMap=new HashMap<String,Object>();
    	this.orderBy=json.getString("orderBy");
    	this.authUser=user;
    	if(this.orderBy==null||this.orderBy.length()==0){
    		this.orderBy="1";
    	}else{
    		this.orderBy=SqlUtil.getUnderScoreOrderBy(this.orderBy);
    	}
    }

    public Map<String,Object> getConditionMap() {
        return conditionMap;
    }
    
	public void setConditionMap(Map<String, Object> conditionMap) {
		this.conditionMap = conditionMap;
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

	public String getSqlCondition() {
		return sqlCondition;
	}

	public void setSqlCondition(String sqlCondition) {
		this.sqlCondition = sqlCondition;
	}

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
	
	/**
	 * 转换为下横线的排序order by。
	 * 按需使用：当你的mapper的sql是正常的栏位信息，则在service层调用这个转换order by条件！
	 */
	public void toUnderScoreOrderBy(){
		this.orderBy=SqlUtil.getUnderScoreOrderBy(this.orderBy);
	}

}
