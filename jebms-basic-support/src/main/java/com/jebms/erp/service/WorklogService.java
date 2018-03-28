package com.jebms.erp.service;


import java.util.Map;

import com.jebms.comm.entity.ResultEntity;

/**
 * 工作日志数据EDI同步
 *
 * @author samt007@qq.com
 * @version 1.0
 */

@SuppressWarnings("rawtypes")
public interface WorklogService {
    
	public ResultEntity headerEdi(Map<String,Object> params) throws Exception;
	
	public ResultEntity lineEdi(Map<String,Object> params) throws Exception;
	
	public ResultEntity lineContentProp(Map<String,Object> params) throws Exception;
	
}
