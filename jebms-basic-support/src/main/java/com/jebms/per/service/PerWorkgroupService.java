package com.jebms.per.service;

import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.security.model.AuthUser;
import com.jebms.per.entity.PerWorkgroupVO;
import com.jebms.per.entity.PerWorkgroupEmpVO;

/**
 * 组表和组人员表的Service封装。
 *
 * @author samt007@qq.com
 */

@SuppressWarnings("rawtypes")
public interface PerWorkgroupService {
    
    //增删改头
	public ResultEntity selectForPageWorkgroup(SearchInfo searchInfo) throws Exception ;

    public PerWorkgroupVO selectWorkgroupVOByPK(Long id,String lang) ;
    
    public ResultEntity insertWorkgroup(PerWorkgroupVO record,AuthUser user) ;
    
    public ResultEntity updateWorkgroup(PerWorkgroupVO record,AuthUser user) ;
    
    public ResultEntity deleteWorkgroup(PerWorkgroupVO record,AuthUser user) ;
    
    //增删改行
	public ResultEntity selectForPageWorkgroupEmp(SearchInfo searchInfo) throws Exception ;

    public PerWorkgroupEmpVO selectWorkgroupEmpVOByPK(Long id,String lang) ;
    
    public ResultEntity insertWorkgroupEmp(PerWorkgroupEmpVO record,AuthUser user) ;
    
    public ResultEntity updateWorkgroupEmp(PerWorkgroupEmpVO record,AuthUser user) ;
    
    public ResultEntity deleteWorkgroupEmp(PerWorkgroupEmpVO record,AuthUser user) ;
}
