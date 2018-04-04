package com.jebms.fnd.controller;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.core.BaseController;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndFunctionVO;
import  com.jebms.fnd.service.SystemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The fnd function controller.
 *
 * @author samt07@qq.com
 */
@Validated
@RestController
@RequestMapping("/function")
@Api(value = "功能管理", description = "功能管理")
@SuppressWarnings("rawtypes")
public class FndFunctionController extends BaseController{
    /**
     * 系统菜单服务
     */
    @Autowired
    private SystemService systemService;

    /**
     * Gets function page.
     *{ "conditionMap": {"id":"2"},"orderBy":"fml.menu_sequence"}
     * @return the menu line page
     * @throws Exception 
     */
    //@PreAuthorize("hasAuthority('fnd:function:view')")
    @PostMapping(value = "/getPage")
    @ApiOperation(value = "功能列表")
    public ResultEntity getPage(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
        return systemService.selectForPageFunction(searchInfo);
    }

    /**
     * Gets function.
     *
     * @param menuId the menu id,menuSequence the menu line seq
     * @return ResultEntity
     */
    //@PreAuthorize("hasAuthority('fnd:function:view')")
    @PostMapping(value = "/select/{id}")
    @ApiOperation(value = "获取功能")
    public ResultEntity select(@PathVariable("id") Long id) {
    	FndFunctionVO record=systemService.selectFunctionVOByPK(id,authUser.getLanguage());
    	return ResultInfo.success(record);
    }

    /**
     * Save function
     *
     * @param functionVO
     * @return ResultEntity
     */
    //@PreAuthorize("hasAuthority('fnd:function:edit')")
    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增功能")
    public ResultEntity insert(@Valid @RequestBody FndFunctionVO record) {
        return systemService.insertFunction(record,this.authUser);
    }

    /**
     * Update function
     *
     * @param functionVO
     * @return the response entity
     */
	//@PreAuthorize("hasAuthority('fnd:function:edit')")
    @PostMapping(value = "/update")
    @ApiOperation(value = "更新功能")
    public ResultEntity update(@Valid @RequestBody FndFunctionVO record) {
        return systemService.updateFunction(record,this.authUser);
    }

    /**
     * Delete function
     *
     * @param id
     * @return ResultEntity
     */
	@PreAuthorize("hasAuthority('fnd:function:edit')")
	@PostMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除功能")
    public ResultEntity delete(@PathVariable("id") Long id) {
    	FndFunctionVO record=new FndFunctionVO();
    	record.setId(id);
        return systemService.deleteFunction(record,this.authUser);
    }
}
