package com.jebms.fnd.controller;


import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.core.BaseController;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndRespVO;
import  com.jebms.fnd.service.SystemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The type fnd resp controller. 注意：未测试！
 *
 * @author samt07@qq.com
 */
@Validated
@RestController
@RequestMapping("/resp")
@Api(value = "职责管理", description = "职责管理")
@SuppressWarnings("rawtypes")
public class FndRespController extends BaseController{
    /**
     * 系统菜单服务
     */
    @Autowired
    private SystemService systemService;


    /**
     * Gets menu nav.
     *
     * @return the menu nav
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/user")
    @ApiOperation(value = "用户的有效职责列表")
    public ResultEntity getRespListByUser() {
        return ResultInfo.success(systemService.getRespListByUserId(authUser.getId(),authUser.getLanguage()));
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/get/{id}")
    @ApiOperation(value = "获取单个完整的职责信息，包括菜单树")
    public ResultEntity getRespById(@PathVariable("id") Long id) {
    	FndRespVO record=systemService.selectRespVOByPK(id,authUser.getLanguage());
    	record.setMenuVOs(systemService.makeTree(systemService.getMenuListByMenuId(record.getMenuId(),authUser.getLanguage()),false));
        return ResultInfo.success(record);
    }
    
    /**
     * Gets resp page.
     *{ "conditionMap": {"id":"2"},"orderBy":"fml.menu_sequence"}
     * @return the resp page
     * @throws Exception 
     */
    //@PreAuthorize("hasAuthority('fnd:resp:view')")
    @PostMapping(value = "/getPage")
    @ApiOperation(value = "职责列表")
    public ResultEntity getPage(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
        return systemService.selectForPageResp(searchInfo);
    }

    /**
     * Gets resp.
     *
     * @param id the resp id
     * @return ResultEntity
     */
    //@PreAuthorize("hasAuthority('fnd:resp:view')")
    @PostMapping(value = "/select/{id}")
    @ApiOperation(value = "获取职责")
    public ResultEntity select(@PathVariable("id") Long id) {
    	return ResultInfo.success(systemService.selectRespVOByPK(id,authUser.getLanguage()));
    }

    /**
     * Save resp
     *
     * @param respVO
     * @return ResultEntity
     */
    //@PreAuthorize("hasAuthority('fnd:resp:edit')")
    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增职责")
    public ResultEntity insert(@Valid @RequestBody FndRespVO record) {
        return systemService.insertResp(record,this.authUser);
    }

    /**
     * Update resp
     *
     * @param respVO
     * @return the response entity
     */
	//@PreAuthorize("hasAuthority('fnd:resp:edit')")
    @PostMapping(value = "/update")
    @ApiOperation(value = "更新职责")
    public ResultEntity update(@Valid @RequestBody FndRespVO record) {
        return systemService.updateResp(record,this.authUser);
    }

    /**
     * Delete resp
     *
     * @param id
     * @return ResultEntity
     */
	@PreAuthorize("hasAuthority('fnd:resp:edit')")
	@PostMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除职责")
    public ResultEntity delete(@PathVariable("id") Long id) {
    	FndRespVO record=new FndRespVO();
    	record.setId(id);
        return systemService.deleteResp(record,this.authUser);
    }
}
