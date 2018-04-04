package com.jebms.fnd.controller;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.core.BaseController;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndMenuHeaderVO;
import com.jebms.fnd.entity.FndMenuLineVO;
import  com.jebms.fnd.service.SystemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The type fnd menu controller. Include the menu header and line!
 *
 * @author samt07@qq.com
 */
@Validated
@RestController
@RequestMapping("/menu")
@Api(value = "菜单管理", description = "菜单管理")
@SuppressWarnings("rawtypes")
public class FndMenuController extends BaseController{
    /**
     * 系统菜单服务
     */
    @Autowired
    private SystemService systemService;

    /**
     * Gets menu tree.
     *
     * @return the menu tree
     */
    /*
    @PreAuthorize("hasAuthority('fnd:menu:view')")
    @PostMapping(value = "/tree")
    @ApiOperation(value = "菜单树")
    public List<FndMenuVO> getMenuTree() {
        return systemService.getMenuTree(this.authUser.getId(),authUser.getLanguage());
    }
*/
    
    /**
     * Gets menu header page.
     *
     * @return the menu page
     * @throws Exception 
     */
//    @PreAuthorize("hasAuthority('sys:menu:view')")
    @PostMapping(value = "/getPageHeader")
    @ApiOperation(value = "菜单头列表")
    public ResultEntity getPageHeader(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
        return systemService.selectForPageMenuHeader(searchInfo);
    }

    /**
     * Gets menu header.
     *
     * @param menuId the menu id
     * @return the menu header
     */
    @PreAuthorize("hasAuthority('fnd:menu:view')")
    @PostMapping(value = "/selectHeader/{menuId}")
    @ApiOperation(value = "获取菜单头")
    public ResultEntity selectHeader(@PathVariable("menuId") Long menuId) {
        return ResultInfo.success(systemService.selectMenuHeaderVOByPK(menuId,authUser.getLanguage()));
    }

    /**
     * Save menu sys menu.
     *
     * @param menu the menu
     * @return the sys menu
     */
    @PreAuthorize("hasAuthority('fnd:menu:edit')")
    @PostMapping(value = "/insertHeader")
    @ApiOperation(value = "新增菜单头")
    public ResultEntity insertHeader(@Valid @RequestBody FndMenuHeaderVO record) {
        return systemService.insertMenuHeader(record,this.authUser);
    }

    /**
     * Update menu response entity.
     *
     * @param menuId the menu id
     * @return the response entity
     */
	@PreAuthorize("hasAuthority('fnd:menu:edit')")
    @PostMapping(value = "/updateHeader")
    @ApiOperation(value = "更新菜单头")
    public ResultEntity updateHeader(@Valid @RequestBody FndMenuHeaderVO menu) {
        return systemService.updateMenuHeader(menu,this.authUser);
    }

    /**
     * Delete menu response entity.
     *
     * @param menuId the menu id
     * @return the response entity
     */
	@PreAuthorize("hasAuthority('fnd:menu:edit')")
	@PostMapping(value = "/deleteHeader/{menuId}")
    @ApiOperation(value = "删除菜单头")
    public ResultEntity deleteHeader(@PathVariable("menuId") Long menuId) {
    	FndMenuHeaderVO menu=new FndMenuHeaderVO();
    	menu.setId(menuId);
        return systemService.deleteMenuHeader(menu,this.authUser);
    }

	
    /**
     * Gets menu Lines page.
     *{ "conditionMap": {"menu_id":"2"},"orderBy":"fml.menu_sequence"}
     * @return the menu line page
     * @throws Exception 
     */
//    @PreAuthorize("hasAuthority('fnd:menu:view')")
    @PostMapping(value = "/getPageLine")
    @ApiOperation(value = "菜单行列表")
    public ResultEntity getPageLine(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
    	searchInfo.getConditionMap().put("menuId", requestJson.getString("menuId"));
        return systemService.selectForPageMenuLine(searchInfo);
    }

    /**
     * Gets menu line.
     *
     * @param menuId the menu id,menuSequence the menu line seq
     * @return ResultEntity
     */
    @PreAuthorize("hasAuthority('fnd:menu:view')")
    @PostMapping(value = "/selectLine/{menuId}/{menuSequence}")
    @ApiOperation(value = "获取菜单行")
    public ResultEntity selectLine(@PathVariable("menuId") Long menuId,@PathVariable("menuSequence") Integer menuSequence) {
    	return ResultInfo.success(systemService.selectMenuLineVOByPK(menuId,menuSequence,authUser.getLanguage()));
    }

    /**
     * Save menu sys menu line.
     *
     * @param menuLine the menuLine
     * @return ResultEntity
     */
    @PreAuthorize("hasAuthority('fnd:menu:edit')")
    @PostMapping(value = "/insertLine")
    @ApiOperation(value = "新增菜单行")
    public ResultEntity insertLine(@Valid @RequestBody FndMenuLineVO menuLine) {
        return systemService.insertMenuLine(menuLine,this.authUser);
    }

    /**
     * Update menu line.
     *
     * @param menuId the menu id
     * @return the response entity
     */
	@PreAuthorize("hasAuthority('fnd:menu:edit')")
    @PostMapping(value = "/updateLine")
    @ApiOperation(value = "更新菜单行")
    public ResultEntity updateLine(@Valid @RequestBody FndMenuLineVO menuLine) {
        return systemService.updateMenuLine(menuLine,this.authUser);
    }

    /**
     * Delete menu line.
     *
     * @param menuId the menu line
     * @return ResultEntity
     */
	@PreAuthorize("hasAuthority('fnd:menu:edit')")
	@PostMapping(value = "/deleteLine/{menuId}/{menuSequence}")
    @ApiOperation(value = "删除菜单行")
    public ResultEntity deleteLine(@PathVariable("menuId") Long menuId,@PathVariable("menuSequence") Integer menuSequence) {
    	FndMenuLineVO menuLine=new FndMenuLineVO();
    	menuLine.setMenuId(menuId);
    	menuLine.setMenuSequence(menuSequence);
        return systemService.deleteMenuLine(menuLine,this.authUser);
    }
}
