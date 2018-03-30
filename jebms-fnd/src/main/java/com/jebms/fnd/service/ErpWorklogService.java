package com.jebms.fnd.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.entity.ResultEntity;

/**
 * Worklog工作日志表的Service封装。是一个非常经典的Master-Detail模型Service
 *
 * @author samt007@qq.com
 */

@SuppressWarnings("rawtypes")
@FeignClient(name= "jebms-erp")
public interface ErpWorklogService {
    
    @PostMapping(value = "/erp/worklog/headerEdi")
    public ResultEntity  headerEdi(@RequestBody JSONObject requestJson);
    
    @PostMapping(value = "/erp/worklog/lineEdi")
    public ResultEntity  lineEdi(@RequestBody JSONObject requestJson);
    
    @PostMapping(value = "/erp/worklog/lineContentProp")
    public ResultEntity lineContentProp(@RequestBody JSONObject requestJson);
    
}
