package com.jebms.fnd.service;


import java.util.List;
import java.util.Map;

import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.SearchInfo;

/**
 * 记录组的业务方法
 * 提供所有的LOV和List所需要的查询！
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年6月25日
 */

@SuppressWarnings("rawtypes")
public interface RGService {

	public ResultEntity getPageCustomer(SearchInfo searchInfo) throws Exception ;
    
    public List<Map<String,Object>> getLookups(String lookupType,String lang) throws Exception;
    
	public ResultEntity getPageUserResp(SearchInfo searchInfo) throws Exception ;
    
	public ResultEntity selectForPageIcon(SearchInfo searchInfo) throws Exception ;
    
	public ResultEntity selectForPageFunction(SearchInfo searchInfo) throws Exception ;
    
	public ResultEntity selectForPageMenu(SearchInfo searchInfo) throws Exception ;
    
	public ResultEntity selectForPageAppl(SearchInfo searchInfo) throws Exception ;

	public ResultEntity selectForPageResp(SearchInfo searchInfo) throws Exception ;

	public ResultEntity selectForPagePerson(SearchInfo searchInfo) throws Exception ;
    
    public ResultEntity getPeopleById(Long id) throws Exception ;
    
    public ResultEntity getPeopleByNum(String empNumber) throws Exception ;
    
    public ResultEntity getPeopleByFname(String fullName) throws Exception ;

	public ResultEntity selectForPageDepartment(SearchInfo searchInfo) throws Exception ;
}
