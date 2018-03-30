package com.jebms.fnd.service;


import javax.servlet.http.HttpServletRequest;

import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.security.model.AuthUser;
import com.jebms.fnd.entity.FndWorklogHeaderVO;
import com.jebms.fnd.entity.FndWorklogLineVO;

/**
 * Worklog工作日志表的Service封装。是一个非常经典的Master-Detail模型Service
 *
 * @author samt007@qq.com
 */

@SuppressWarnings("rawtypes")
public interface FndWorklogService {
    
    //增删改头
	public ResultEntity selectForPageHeader(SearchInfo searchInfo) throws Exception ;

    public FndWorklogHeaderVO selectHeaderVOByPK(Long id,String lang) ;
    
    public ResultEntity insertHeader(FndWorklogHeaderVO record,AuthUser user, HttpServletRequest request) ;
    
    public void ediHeaders(String ediType, FndWorklogHeaderVO record, AuthUser user, HttpServletRequest request) throws Exception;

    public ResultEntity updateHeader(FndWorklogHeaderVO record,AuthUser user,HttpServletRequest request) ;
    
    public ResultEntity deleteHeader(FndWorklogHeaderVO record,AuthUser user,HttpServletRequest request) ;
    
    //增删改行
	public ResultEntity selectForPageLine(SearchInfo searchInfo) throws Exception ;

    public FndWorklogLineVO selectLineVOByPK(Long id,String lang) ;
    
    public void ediLines(String ediType, FndWorklogLineVO record, AuthUser user, HttpServletRequest request) throws Exception;
    
    public ResultEntity getLineContentProp(String lineContent, HttpServletRequest request) throws Exception;
    
    public ResultEntity insertLine(FndWorklogLineVO record,AuthUser user,HttpServletRequest request) ;
    
    public ResultEntity updateLine(FndWorklogLineVO record,AuthUser user,HttpServletRequest request);
    
    public ResultEntity deleteLine(FndWorklogLineVO record,AuthUser user,HttpServletRequest request);
    
    // 自动从数据库找对应的日志的最大行号
    public ResultEntity getMaxLineNum(Long headerId) throws Exception ;
    
    public ResultEntity getPersonDepartment(Long personId) throws Exception;
    
    public ResultEntity getPersonWorkGroup(Long departmentId,Long personId) throws Exception ;
    
}
