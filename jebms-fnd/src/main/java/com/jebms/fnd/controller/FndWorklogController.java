package com.jebms.fnd.controller;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.core.BaseController;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.utils.TypeConverter;
import com.jebms.fnd.entity.FndWorklogHeaderVO;
import com.jebms.fnd.entity.FndWorklogLineVO;
import  com.jebms.fnd.service.FndWorklogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The fnd worklog controller.
 *
 * @author samt07@qq.com
 */
@Validated
@RestController
@RequestMapping("/fnd/worklog")
@Api(value = "工作日志管理", description = "工作日志管理")
@SuppressWarnings("rawtypes")
public class FndWorklogController extends BaseController{
    /**
     * 系统数据字典
     */
    @Autowired
    private FndWorklogService worklogService;

    //@PreAuthorize("hasAuthority('fnd:worklog:view')")
    @PostMapping(value = "/getPageHeader")
    @ApiOperation(value = "工作日志列表")
    public ResultEntity getPageHeader(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
    	searchInfo.getConditionMap().put("workItem", requestJson.getString("workItem"));
    	searchInfo.getConditionMap().put("workOwnerPid", requestJson.getString("workOwnerPid"));
    	searchInfo.getConditionMap().put("actualStartDateF", requestJson.getString("actualStartDateF"));
    	searchInfo.getConditionMap().put("actualStartDateT", requestJson.getString("actualStartDateT"));
        return worklogService.selectForPageHeader(searchInfo);
    }

    //@PreAuthorize("hasAuthority('fnd:worklog:view')")
    @PostMapping(value = "/selectHeader/{id}")
    @ApiOperation(value = "获取工作日志头")
    public ResultEntity selectHeader(@PathVariable("id") Long id) {
        return ResultInfo.success(worklogService.selectHeaderVOByPK(id,authUser.getLanguage()));
    }

    @PreAuthorize("hasAuthority('fnd:worklog:edit')")
    @PostMapping(value = "/insertHeader")
    @ApiOperation(value = "新增工作日志头")
    public ResultEntity insertHeader(@Valid @RequestBody FndWorklogHeaderVO record) {
        return worklogService.insertHeader(record,this.authUser,this.request);
    }

	@PreAuthorize("hasAuthority('fnd:worklog:edit')")
    @PostMapping(value = "/updateHeader")
    @ApiOperation(value = "更新工作日志头")
    public ResultEntity updateHeader(@Valid @RequestBody FndWorklogHeaderVO record) {
        return worklogService.updateHeader(record,this.authUser,this.request);
    }

	@PreAuthorize("hasAuthority('fnd:worklog:edit')")
	@PostMapping(value = "/deleteHeader/{id}")
    @ApiOperation(value = "删除工作日志头")
    public ResultEntity deleteHeader(@PathVariable("id") Long id,@RequestBody JSONObject requestJson) {
		FndWorklogHeaderVO record=new FndWorklogHeaderVO();
		record.setId(id);
		record.setErpHeaderId(TypeConverter.str2Long(requestJson.getString("erpHeaderId")));// 设置这个才可以正常edi删除erp的数据！
        return worklogService.deleteHeader(record,this.authUser,this.request);
    }

	// 工作日志行明细维护
    //@PreAuthorize("hasAuthority('fnd:worklog:view')")
    @PostMapping(value = "/getPageLine")
    @ApiOperation(value = "工作日志行列表")
    public ResultEntity getPageLine(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
    	searchInfo.getConditionMap().put("headerId", requestJson.getString("headerId"));
    	searchInfo.getConditionMap().put("lineContent", requestJson.getString("lineContent"));
        return worklogService.selectForPageLine(searchInfo);
    }

    //@PreAuthorize("hasAuthority('fnd:worklog:view')")
    @PostMapping(value = "/selectLine/{id}")
    @ApiOperation(value = "获取工作日志行")
    public ResultEntity selectLine(@PathVariable("id") Long id) {
        return ResultInfo.success(worklogService.selectLineVOByPK(id,authUser.getLanguage()));
    }

    @PreAuthorize("hasAuthority('fnd:worklog:edit')")
    @PostMapping(value = "/insertLine")
    @ApiOperation(value = "新增工作日志行")
    public ResultEntity insertLine(@Valid @RequestBody FndWorklogLineVO record) {
        return worklogService.insertLine(record,this.authUser,this.request);
    }

	@PreAuthorize("hasAuthority('fnd:worklog:edit')")
    @PostMapping(value = "/updateLine")
    @ApiOperation(value = "更新工作日志行")
    public ResultEntity updateLine(@Valid @RequestBody FndWorklogLineVO record) {
        return worklogService.updateLine(record,this.authUser,this.request);
    }

	@PreAuthorize("hasAuthority('fnd:worklog:edit')")
	@PostMapping(value = "/deleteLine/{id}")
    @ApiOperation(value = "删除工作日志行")
    public ResultEntity deleteLine(@PathVariable("id") Long id,@RequestBody JSONObject requestJson) {
		FndWorklogLineVO record=new FndWorklogLineVO();
		record.setId(id);
		record.setHeaderId(TypeConverter.str2Long(requestJson.getString("headerId")));
		record.setErpLineId(TypeConverter.str2Long(requestJson.getString("erpLineId")));
        return worklogService.deleteLine(record,this.authUser,this.request);
    }

	@PostMapping(value = "/getMaxLineNum/{headerId}")
    @ApiOperation(value = "获取最大的行id")
    public ResultEntity getMaxLineNum(@PathVariable("headerId") Long headerId) throws Exception {
        return worklogService.getMaxLineNum(headerId);
    }

	@PostMapping(value = "/getPersonDepartment/{personId}")
    @ApiOperation(value = "获取用户部门")
    public ResultEntity getPersonDepartment(@PathVariable("personId") Long personId) throws Exception {
        return worklogService.getPersonDepartment(personId);
    }

	@PostMapping(value = "/getPersonWorkGroup/{departmentId}/{personId}")
    @ApiOperation(value = "获取用户部门组")
    public ResultEntity getPersonWorkGroup(@PathVariable("departmentId") Long departmentId
    		,@PathVariable("personId") Long personId) throws Exception {
        return worklogService.getPersonWorkGroup(departmentId,personId);
    }

	@PostMapping(value = "/getLineContentProp")
    @ApiOperation(value = "从erp获取日志行的一些关键属性")
    public ResultEntity getLineContentProp(@RequestBody JSONObject requestJson) throws Exception {
		String lineContent=requestJson.getString("lineContent");
		//System.out.println("lineContent:"+lineContent);
        return worklogService.getLineContentProp(lineContent,this.request);
    }
}
