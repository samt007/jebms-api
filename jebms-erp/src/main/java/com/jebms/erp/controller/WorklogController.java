package com.jebms.erp.controller;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.core.BaseController;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.utils.TypeConverter;
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
@RequestMapping("/erp/worklog/")
@Api(value = "工作日志同步API", description = "工作日志")
@SuppressWarnings("rawtypes")
public class WorklogController extends BaseController{
    /**
     * 系统菜单服务
     */
    @Autowired
    private WorklogService worklogService;

    @PostMapping(value = "/headerEdi")
    @ApiOperation(value = "工作日志头EDI")
    public ResultEntity headerEdi(@RequestBody JSONObject requestJson) throws Exception {
    	Map<String,Object> params=new HashMap<String,Object>();
    	params.put("P_ID", requestJson.getString("id"));
    	params.put("P_DEPARTMENT_CODE", requestJson.getString("departmentCode"));
    	params.put("P_WORK_GROUP_CODE", requestJson.getString("workGroupCode"));
    	params.put("P_WORK_TYPE", requestJson.getString("workType"));
    	params.put("P_DEF_LINE_TYPE", requestJson.getString("defLineType"));
    	params.put("P_WORK_PRIORTY", requestJson.getString("workPriorty"));
    	params.put("P_WORK_ITEM", requestJson.getString("workItem"));
    	params.put("P_WORK_REQ_DOCUMENT", requestJson.getString("workReqDocument"));
    	params.put("P_WORK_REQUEST_NUMBER", requestJson.getString("workRequestNumber"));
    	params.put("P_WORK_OWNER_NUMBER", requestJson.getString("workOwnerNumber"));
    	params.put("P_SCHEDULE_START_DATE", TypeConverter.str2Timestamp(requestJson.getString("scheduleStartDate")));
    	params.put("P_ACTUAL_START_DATE", TypeConverter.str2Timestamp(requestJson.getString("actualStartDate")));
    	params.put("P_SCHEDULE_FINISH_DATE", TypeConverter.str2Timestamp(requestJson.getString("scheduleFinishDate")));
    	params.put("P_WORK_SPEND_HOURS", requestJson.getString("workSpendHours"));
    	params.put("P_WORK_LOG", requestJson.getString("workLog"));
    	params.put("P_STATUS", requestJson.getString("status"));
    	params.put("P_STATUS_WT_DATE", TypeConverter.str2Timestamp(requestJson.getString("statusWtDate")));
    	params.put("P_STATUS_WT_NUMBER", requestJson.getString("statusWtNumber"));
    	params.put("P_CANCEL_DATE", TypeConverter.str2Timestamp(requestJson.getString("cancelDate")));
    	params.put("P_CANCEL_USER_NUMBER", requestJson.getString("cancelUserNumber"));
    	params.put("P_ACTUAL_FINISH_DATE", TypeConverter.str2Timestamp(requestJson.getString("actualFinishDate")));
    	params.put("P_FINISH_USER_NUMBER", requestJson.getString("finishUserNumber"));
    	params.put("P_DESCRIPTION", requestJson.getString("description"));
    	//params.put("P_HEADER_ID", requestJson.getString("headerId"));
    	params.put("P_EDI_TYPE", requestJson.getString("ediType"));
    	params.put("P_EDI_EMP_NUMBER", requestJson.getString("ediEmpNumber"));
    	params.put(ResultEntity.PARAM1, requestJson.getString("erpHeaderId"));
        return worklogService.headerEdi(params);
    }
    
    @PostMapping(value = "/lineEdi")
    @ApiOperation(value = "工作日志行EDI")
    public ResultEntity lineEdi(@RequestBody JSONObject requestJson) throws Exception {
    	Map<String,Object> params=new HashMap<String,Object>();
    	params.put("P_ID", requestJson.getString("id"));
    	params.put("P_HEADER_ID", requestJson.getString("headerId"));
    	params.put("P_LINE_NUM", requestJson.getString("lineNum"));
    	params.put("P_LINE_TYPE", requestJson.getString("lineType"));
    	params.put("P_LINE_SUB_TYPE", requestJson.getString("lineSubType"));
    	params.put("P_LINE_CONTENT", requestJson.getString("lineContent"));
    	params.put("P_LINE_START_DATE", TypeConverter.str2Timestamp(requestJson.getString("lineStartDate")));
    	params.put("P_LINE_FINISH_DATE", TypeConverter.str2Timestamp(requestJson.getString("lineFinishDate")));
    	params.put("P_APPLICATION_SHORT_NAME", requestJson.getString("applicationShortName"));
    	params.put("P_LANGUAGE", requestJson.getString("language"));
    	params.put("P_DESCRIPTION", requestJson.getString("description"));
    	params.put("P_EDI_TYPE", requestJson.getString("ediType"));
    	params.put("P_EDI_EMP_NUMBER", requestJson.getString("ediEmpNumber"));
    	params.put("P_ERP_HEADER_ID", requestJson.getString("erpHeaderId"));
    	params.put(ResultEntity.PARAM1, requestJson.getString("erpLineId"));
        return worklogService.lineEdi(params);
    }
    
    @PostMapping(value = "/lineContentProp")
    @ApiOperation(value = "工作日志行内容EDI")
    public ResultEntity lineContentProp(@RequestBody JSONObject requestJson) throws Exception {
    	Map<String,Object> params=new HashMap<String,Object>();
    	params.put("P_LINE_CONTENT", requestJson.getString("lineContent"));
        return worklogService.lineContentProp(params);
    }
}
