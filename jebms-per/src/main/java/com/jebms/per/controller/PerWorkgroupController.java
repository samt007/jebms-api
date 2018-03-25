package com.jebms.per.controller;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.core.BaseController;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.per.entity.PerWorkgroupEmpVO;
import com.jebms.per.entity.PerWorkgroupVO;
import  com.jebms.per.service.PerWorkgroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The per workgroup controller.
 *
 * @author samt07@qq.com
 */
@Validated
@RestController
@RequestMapping("/per/workgroup")
@Api(value = "工作组管理", description = "工作组管理")
@SuppressWarnings("rawtypes")
public class PerWorkgroupController extends BaseController{
    /**
     * 系统数据字典
     */
    @Autowired
    private PerWorkgroupService groupService;

    //@PreAuthorize("hasAuthority('per:workgroup:view')")
    @PostMapping(value = "/getPageWorkgroup")
    @ApiOperation(value = "组列表")
    public ResultEntity getPageWorkgroup(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
    	//searchInfo.getConditionMap().put("workItem", requestJson.getString("workItem"));
    	//searchInfo.getConditionMap().put("workOwnerPid", requestJson.getString("workOwnerPid"));
    	//searchInfo.getConditionMap().put("actualStartDateF", requestJson.getString("actualStartDateF"));
    	//searchInfo.getConditionMap().put("actualStartDateT", requestJson.getString("actualStartDateT"));
        return groupService.selectForPageWorkgroup(searchInfo);
    }

    //@PreAuthorize("hasAuthority('per:workgroup:view')")
    @PostMapping(value = "/selectWorkgroup/{id}")
    @ApiOperation(value = "获取组")
    public ResultEntity selectWorkgroup(@PathVariable("id") Long id) {
        return ResultInfo.success(groupService.selectWorkgroupVOByPK(id,authUser.getLanguage()));
    }

    @PreAuthorize("hasAuthority('per:workgroup:edit')")
    @PostMapping(value = "/insertWorkgroup")
    @ApiOperation(value = "新增组")
    public ResultEntity insertWorkgroup(@Valid @RequestBody PerWorkgroupVO record) {
        return groupService.insertWorkgroup(record,this.authUser);
    }

	@PreAuthorize("hasAuthority('per:workgroup:edit')")
    @PostMapping(value = "/updateWorkgroup")
    @ApiOperation(value = "更新组")
    public ResultEntity updateWorkgroup(@Valid @RequestBody PerWorkgroupVO record) {
        return groupService.updateWorkgroup(record,this.authUser);
    }

	@PreAuthorize("hasAuthority('per:workgroup:edit')")
	@PostMapping(value = "/deleteWorkgroup/{id}")
    @ApiOperation(value = "删除组")
    public ResultEntity deleteWorkgroup(@PathVariable("id") Long id,@RequestBody JSONObject requestJson) {
		PerWorkgroupVO record=new PerWorkgroupVO();
		record.setId(id);
        return groupService.deleteWorkgroup(record,this.authUser);
    }

	// 组人员明细维护
    //@PreAuthorize("hasAuthority('per:workgroup:view')")
    @PostMapping(value = "/getPageWorkgroupEmp")
    @ApiOperation(value = "组人员列表")
    public ResultEntity getPageWorkgroupEmp(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
    	searchInfo.getConditionMap().put("workGroupId", requestJson.getString("workGroupId"));
    	//searchInfo.getConditionMap().put("WorkgroupEmpContent", requestJson.getString("WorkgroupEmpContent"));
        return groupService.selectForPageWorkgroupEmp(searchInfo);
    }

    //@PreAuthorize("hasAuthority('per:workgroup:view')")
    @PostMapping(value = "/selectWorkgroupEmp/{id}")
    @ApiOperation(value = "获取组人员")
    public ResultEntity selectWorkgroupEmp(@PathVariable("id") Long id) {
        return ResultInfo.success(groupService.selectWorkgroupEmpVOByPK(id,authUser.getLanguage()));
    }

    @PreAuthorize("hasAuthority('per:workgroup:edit')")
    @PostMapping(value = "/insertWorkgroupEmp")
    @ApiOperation(value = "新增工作日志行")
    public ResultEntity insertWorkgroupEmp(@Valid @RequestBody PerWorkgroupEmpVO record) {
        return groupService.insertWorkgroupEmp(record,this.authUser);
    }

	@PreAuthorize("hasAuthority('per:workgroup:edit')")
    @PostMapping(value = "/updateWorkgroupEmp")
    @ApiOperation(value = "更新工作日志行")
    public ResultEntity updateWorkgroupEmp(@Valid @RequestBody PerWorkgroupEmpVO record) {
        return groupService.updateWorkgroupEmp(record,this.authUser);
    }

	@PreAuthorize("hasAuthority('per:workgroup:edit')")
	@PostMapping(value = "/deleteWorkgroupEmp/{id}")
    @ApiOperation(value = "删除工作日志行")
    public ResultEntity deleteWorkgroupEmp(@PathVariable("id") Long id,@RequestBody JSONObject requestJson) {
		PerWorkgroupEmpVO record=new PerWorkgroupEmpVO();
		record.setId(id);
        return groupService.deleteWorkgroupEmp(record,this.authUser);
    }
}
