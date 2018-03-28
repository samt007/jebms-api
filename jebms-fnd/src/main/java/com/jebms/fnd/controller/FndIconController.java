package com.jebms.fnd.controller;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.core.BaseController;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndIcon;
import  com.jebms.fnd.service.FndIconService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The fnd icon controller.
 *
 * @author samt07@qq.com
 */
@Validated
@RestController
@RequestMapping("/fnd/icon")
@Api(value = "图标管理", description = "图标管理")
@SuppressWarnings("rawtypes")
public class FndIconController extends BaseController{
    /**
     * 系统图标服务
     */
    @Autowired
    private FndIconService iconService;

    //@PreAuthorize("hasAuthority('fnd:icon:view')")
    @PostMapping(value = "/getPage")
    @ApiOperation(value = "图标列表")
    public ResultEntity getPage(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
    	searchInfo.getConditionMap().put("description", requestJson.getString("description"));
        return iconService.selectForPage(searchInfo);
    }

    //@PreAuthorize("hasAuthority('fnd:icon:view')")
    @PostMapping(value = "/select/{id}")
    @ApiOperation(value = "获取图标")
    public ResultEntity select(@PathVariable("id") Long id) {
    	FndIcon record=(FndIcon) iconService.selectByPrimaryKey(new FndIcon(id));
    	return ResultInfo.success(record);
    }

    //@PreAuthorize("hasAuthority('fnd:icon:edit')")
    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增图标")
    public ResultEntity insert(@Valid @RequestBody FndIcon record) {
    	record.setWhoInsert(this.authUser);
        return iconService.insert(record);
    }

	//@PreAuthorize("hasAuthority('fnd:icon:edit')")
    @PostMapping(value = "/update")
    @ApiOperation(value = "更新图标")
    public ResultEntity update(@Valid @RequestBody FndIcon record) {
    	record.setWhoUpdate(this.authUser);
        return iconService.update(record);
    }

	@PreAuthorize("hasAuthority('fnd:icon:edit')")
	@PostMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除图标")
    public ResultEntity delete(@PathVariable("id") Long id) {
		FndIcon record=new FndIcon(id);
        return iconService.delete(record);
    }
}
