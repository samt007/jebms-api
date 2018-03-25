package com.jebms.fnd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.core.BaseController;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.security.model.AuthUser;
import com.jebms.fnd.entity.Kanban;
import com.jebms.fnd.entity.KanbanVO;
import com.jebms.fnd.service.KanbanService;

/**
 * Author: Sam.T
 * Email: samt007@qq.com
 * Date: 2017/6/23
 * Describe: 看板功能处理
 */
@Controller
@RequestMapping(value = "/kanban")
@Api(value = "看板管理", description = "看板管理API")
@SuppressWarnings({"rawtypes","unchecked"})
public class KanbanController  extends BaseController  {
    @Autowired
    private KanbanService kanbanService;
    
    /**
     * 查询所有看板信息 并使用PageHelp分页
     * @throws Exception 
     */
	@ResponseBody
	@PostMapping(value = "/getPageShow")
    @ApiOperation(value = "查询所有看板信息")
    public ResultEntity getPageShow(@RequestBody SearchInfo searchInfo) throws Exception {
    	searchInfo.setCount(true);
    	this.authUser=new AuthUser(0L);
    	this.authUser.setLanguage("ZHS");
    	searchInfo.setAuthUser(this.authUser);
        searchInfo.getConditionMap().put("currency", "CNY");
        searchInfo.getConditionMap().put("amountFlag", "Y");
    	ResultEntity ret=kanbanService.selectForPage(searchInfo);
    	ret.setParam1(kanbanService.getKanbanCustomerCnt().toString());
    	ret.setParam2(kanbanService.selectForTop());
        return ret;
    }
    
    /**
     * 查询分页的视图
     * @throws Exception 
     */
    @ResponseBody
    @PostMapping(value = "/getPage")
    @ApiOperation(value = "查询所有看板分页")
    public ResultEntity getPage(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
        searchInfo.getConditionMap().put("customerName", requestJson.getString("customerNameQ"));
        searchInfo.getConditionMap().put("startDateF", requestJson.getString("startDateF"));
        searchInfo.getConditionMap().put("startDateT", requestJson.getString("startDateT"));
        return kanbanService.selectForPage(searchInfo);
    }

	@ResponseBody
	@PostMapping(value = "/select/{id}")
    @ApiOperation(value = "获取看板信息")
    public ResultEntity get(@PathVariable("id") Long id) throws Exception
    {
        return ResultInfo.success(kanbanService.selectVOByPK(id,this.authUser.getLanguage()));
    }

	@ResponseBody
    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增看板信息")
	public ResultEntity insert(@Valid @RequestBody Kanban record){
		record.setWhoInsert(this.authUser);
		ResultEntity<KanbanVO> ret=kanbanService.insert(record);
		ret.setObj(kanbanService.selectVOByPK(record.getId(),this.authUser.getLanguage()));
		return ret;
	}

	@ResponseBody
	@PostMapping(value = "/update")
    @ApiOperation(value = "更新看板信息")
	public ResultEntity update(@Valid @RequestBody KanbanVO record) throws Exception{
        record.setWhoUpdate(this.authUser);
        ResultEntity<KanbanVO> ret=kanbanService.update(record);
        ret.setObj(kanbanService.selectVOByPK(record.getId(),this.authUser.getLanguage()));
		return ret;
	}

	@ResponseBody
	@PostMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除看板信息")
	public ResultEntity delete(@PathVariable("id") Long id){
        Kanban record=new Kanban();
        record.setId(id);
		return kanbanService.delete(record);
	}
}
