package com.jebms.per.controller;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.core.BaseController;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.per.entity.PerPersonVO;
import com.jebms.per.service.PerPersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The per person controller.
 *
 * @author samt07@qq.com
 */
@Validated
@RestController
@RequestMapping("/person")
@Api(value = "人员管理", description = "人员管理")
@SuppressWarnings("rawtypes")
public class PerPersonController extends BaseController{

    @Autowired
    private PerPersonService personService;

    //@PreAuthorize("hasAuthority('per:person:view')")
    @PostMapping(value = "/getPage")
    @ApiOperation(value = "人员列表")
    public ResultEntity getPage(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
    	searchInfo.putConditionMap("empNumberQ", requestJson.getString("empNumberQ")).andSqlCondition("pp.emp_number","empNumberQ");
    	searchInfo.putConditionMap("fullNameQ", requestJson.getString("fullNameQ")).andSqlCondition("pp.full_name","fullNameQ");
        return personService.selectForPage(searchInfo);
    }

    //@PreAuthorize("hasAuthority('per:person:view')")
    @PostMapping(value = "/select/{id}")
    @ApiOperation(value = "获取人员")
    public ResultEntity select(@PathVariable("id") Long id) {
        return ResultInfo.success(personService.selectVOByPK(id));
    }

    @PreAuthorize("hasAuthority('per:person:edit')")
    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增人员")
    public ResultEntity insert(@Valid @RequestBody PerPersonVO record) {
        return personService.insert(record,this.authUser);
    }

	@PreAuthorize("hasAuthority('per:person:edit')")
    @PostMapping(value = "/update")
    @ApiOperation(value = "更新人员")
    public ResultEntity update(@Valid @RequestBody PerPersonVO record) {
        return personService.update(record,this.authUser);
    }

	@PreAuthorize("hasAuthority('per:person:edit')")
	@PostMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除人员")
    public ResultEntity delete(@PathVariable("id") Long id) {
		PerPersonVO record=new PerPersonVO();
		record.setId(id);
        return personService.delete(record,this.authUser);
    }
}
