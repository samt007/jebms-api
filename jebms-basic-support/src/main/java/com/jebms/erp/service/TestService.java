package com.jebms.erp.service;


import java.util.Map;

import com.jebms.comm.entity.ResultEntity;

/**
 * 测试
 *
 * @author samt007@qq.com
 * @version 1.0
 */

@SuppressWarnings("rawtypes")
public interface TestService {
    
	public ResultEntity testSql() throws Exception;
    
	public ResultEntity worklogHeaderEdi(Map<String,Object> params) throws Exception;
	
	public ResultEntity worklogLineEdi(Map<String,Object> params) throws Exception;
	
}
