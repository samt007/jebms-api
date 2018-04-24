package com.jebms.fnd.controller;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.core.BaseController;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndLookupTypeVO;
import com.jebms.fnd.entity.FndLookupValue;
import  com.jebms.fnd.service.FndLookupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The fnd lookup controller.
 *
 * @author samt07@qq.com
 */
@Validated
@RestController
@RequestMapping("/lookup")
@Api(value = "数据字典管理", description = "数据字典管理")
@SuppressWarnings("rawtypes")
public class FndLookupController extends BaseController{
    /**
     * 系统数据字典
     */
    @Autowired
    private FndLookupService lookupService;

    /**
     * Gets lookup type page.
     *
     * @return the lookup type page
     * @throws Exception 
     */
    @PreAuthorize("hasAuthority('fnd:lookup:view')")
    @PostMapping(value = "/getPageLookupType")
    @ApiOperation(value = "数据字典头列表")
    public ResultEntity getPageLookupType(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
    	searchInfo.putConditionMap("lookupType", requestJson.getString("lookupType")).andSqlCondition("flt.lookup_type","lookupType");
        return lookupService.selectForPageLookupType(searchInfo);
    }

    /**
     * Gets lookup type.
     *
     * @param PK
     * @return the lookup type
     */
    @PreAuthorize("hasAuthority('fnd:lookup:view')")
    @PostMapping(value = "/selectLookupType/{applId}/{lookupType}")
    @ApiOperation(value = "获取数据字典头")
    public ResultEntity selectLookupType(@PathVariable("applId") Long applId,@PathVariable("lookupType") String lookupType) {
        return ResultInfo.success(lookupService.selectLookupTypeVOByPK(applId,lookupType,authUser.getLanguage()));
    }

    /**
     * Save lookup type.
     *
     * @param lookup type
     * @return the lookup type
     */
    @PreAuthorize("hasAuthority('fnd:lookup:edit')")
    @PostMapping(value = "/insertLookupType")
    @ApiOperation(value = "新增数据字典头")
    public ResultEntity insertLookupType(@Valid @RequestBody FndLookupTypeVO record) {
        return lookupService.insertLookupType(record,this.authUser);
    }

    /**
     * Update lookup type.
     *
     * @param PK
     * @return the lookup type
     */
	@PreAuthorize("hasAuthority('fnd:lookup:edit')")
    @PostMapping(value = "/updateLookupType")
    @ApiOperation(value = "更新数据字典头")
    public ResultEntity updateLookupType(@Valid @RequestBody FndLookupTypeVO record) {
        return lookupService.updateLookupType(record,this.authUser);
    }

    /**
     * Delete lookup type.
     *
     * @param PK
     * @return
     */
	@PreAuthorize("hasAuthority('fnd:lookup:edit')")
	@PostMapping(value = "/deleteLookupType/{applId}/{lookupType}")
    @ApiOperation(value = "删除数据字典头")
    public ResultEntity deleteLookupType(@PathVariable("applId") Long applId,@PathVariable("lookupType") String lookupType) {
		FndLookupTypeVO record=new FndLookupTypeVO();
		record.setApplId(applId);
		record.setLookupType(lookupType);
        return lookupService.deleteLookupType(record,this.authUser);
    }

    /**
     * Gets lookup value page.
     *
     * @return the lookup value page
     * @throws Exception 
     */
//    @PreAuthorize("hasAuthority('fnd:lookup:view')")
    @PostMapping(value = "/getPageLookupValue")
    @ApiOperation(value = "数据字典行列表")
    public ResultEntity getPageLookupValue(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
    	searchInfo.putConditionMap("applId", requestJson.getString("applId")).andSqlCondition("flv.appl_id","applId");
    	searchInfo.putConditionMap("lookupType", requestJson.getString("lookupType")).andSqlCondition("flv.lookup_type","lookupType");
        return lookupService.selectForPageLookupValue(searchInfo);
    }

    /**
     * Gets lookup value.
     *
     * @param PK
     * @return ResultEntity
     */
    @PreAuthorize("hasAuthority('fnd:lookup:view')")
    @PostMapping(value = "/selectLookupValue/{applId}/{lookupType}/{lookupCode}")
    @ApiOperation(value = "获取数据字典行")
    public ResultEntity selectLookupValue(
    		@PathVariable("applId") Long applId
    		,@PathVariable("lookupType") String lookupType
    		,@PathVariable("lookupCode") String lookupCode) {
    	return ResultInfo.success(lookupService.selectLookupValueByPK(applId,lookupType,lookupCode,authUser.getLanguage()));
    }

    /**
     * Save lookup value.
     *
     * @param 
     * @return ResultEntity
     */
    @PreAuthorize("hasAuthority('fnd:lookup:edit')")
    @PostMapping(value = "/insertLookupValue")
    @ApiOperation(value = "新增数据字典行")
    public ResultEntity insertLookupValue(@Valid @RequestBody FndLookupValue record) {
        return lookupService.insertLookupValue(record,this.authUser);
    }

    /**
     * Update lookup value.
     *
     * @param Object
     * @return the response entity
     */
	@PreAuthorize("hasAuthority('fnd:lookup:edit')")
    @PostMapping(value = "/updateLookupValue")
    @ApiOperation(value = "更新数据字典行")
    public ResultEntity updateLookupValue(@Valid @RequestBody FndLookupValue record) {
        return lookupService.updateLookupValue(record,this.authUser);
    }

    /**
     * Delete lookup value.
     *
     * @param PK
     * @return ResultEntity
     */
	@PreAuthorize("hasAuthority('fnd:lookup:edit')")
	@PostMapping(value = "/deleteLookupValue/{applId}/{lookupType}/{lookupCode}")
    @ApiOperation(value = "删除数据字典行")
    public ResultEntity deleteLookupValue(
    		@PathVariable("applId") Long applId
    		,@PathVariable("lookupType") String lookupType
    		,@PathVariable("lookupCode") String lookupCode) {
    	FndLookupValue record=new FndLookupValue();
    	record.setApplId(applId);
    	record.setLookupType(lookupType);
    	record.setLookupCode(lookupCode);
        return lookupService.deleteLookupValue(record,this.authUser);
    }
}
