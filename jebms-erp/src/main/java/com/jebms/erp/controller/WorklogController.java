package com.jebms.erp.controller;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.core.BaseController;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.erp.service.WorklogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * The test only controller.
 *
 * @author samt07@qq.com
 */
@Validated
@RestController
@RequestMapping("/worklog/")
@Api(value = "工作日志同步API", description = "工作日志")
@SuppressWarnings({"rawtypes","unchecked"})
public class WorklogController extends BaseController{
	
    @Autowired
    private WorklogService worklogService;

	@PostMapping(value = "/headerEdi")
    @ApiOperation(value = "工作日志头EDI")
    public ResultEntity headerEdi(@RequestBody JSONObject requestJson) throws Exception {
        return worklogService.headerEdi(JSONObject.toJavaObject(requestJson, Map.class));
    }
    
    @PostMapping(value = "/lineEdi")
    @ApiOperation(value = "工作日志行EDI")
    public ResultEntity lineEdi(@RequestBody JSONObject requestJson) throws Exception {
        return worklogService.lineEdi(JSONObject.toJavaObject(requestJson, Map.class));
    }
    
    @PostMapping(value = "/lineContentProp")
    @ApiOperation(value = "工作日志行内容EDI")
    public ResultEntity lineContentProp(@RequestBody JSONObject requestJson) throws Exception {
        return worklogService.lineContentProp(JSONObject.toJavaObject(requestJson, Map.class));
    }
}
