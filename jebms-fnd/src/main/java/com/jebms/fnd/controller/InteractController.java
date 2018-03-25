package com.jebms.fnd.controller;


import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.core.BaseController;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.service.InteractService;

//import javax.servlet.http.HttpSession;


/**
 * 交互式报表处理
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年6月8日
 */

@Controller
@RequestMapping(value = "/fnd/irr")
@Api(value = "交互式报表API", description = "交互式报表管理")
@SuppressWarnings("rawtypes")
public class InteractController extends BaseController {

    @Autowired
    private InteractService irrService;

    //192.168.88.123:8080/jEBMS/irr/getDefaultIrr.do
	@PostMapping(value = "/getDefaultIrr")
	@ResponseBody
	public ResultEntity getDefaultIrr(@RequestBody JSONObject requestJson) throws Exception
	{   	
		//获取用户的默认打开的交互式报表定义
		Long userId=requestJson.getLong("userId");
		String interactCode=requestJson.getString("interactCode");
		//System.out.println("userId:"+userId+",interactCode:"+interactCode);
		return irrService.getDefaultIrr(userId,interactCode);
	}
	
	@PostMapping(value = "/getPageIrrHeaders")
	@ResponseBody
	public ResultEntity getPageIrrHeaders(@RequestBody JSONObject requestJson) throws Exception
	{   	
		//获取用户可用的所有IRR定义的头列表
		SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
        searchInfo.getConditionMap().put("userInteractName", requestJson.getString("userInteractName"));
        searchInfo.getConditionMap().put("interactCode", requestJson.getString("interactCode"));
		return irrService.selectForPageIrrHeaders(searchInfo);
	}
	
	@PostMapping(value = "/getIrr")
	@ResponseBody
	public ResultEntity getIrr(@RequestBody JSONObject requestJson) throws Exception
	{   	
		//获取用户的指定的打开的交互式报表定义
		Long id=requestJson.getLong("id");
		return irrService.getIrr(id);
	}
	
	@PostMapping(value = "/deleteIrr")
	@ResponseBody
	public ResultEntity deleteIrr(@RequestBody JSONObject requestJson) throws Exception
	{   	
		//根据ID删除交互式报表定义
		Long id=requestJson.getLong("id");
		return irrService.deleteIrr(id);
	}
	
	@PostMapping(value = "/saveIrr")
	@ResponseBody
	public ResultEntity saveIrr(@RequestBody JSONObject requestJson) throws Exception
	{   	
		Map<String, Object> headerParamMap=new HashMap<String, Object>();
		headerParamMap.put("userId",requestJson.getLong("userId"));
		headerParamMap.put("interactCode",requestJson.getString("interactCode"));
		headerParamMap.put("userInteractName",requestJson.getString("userInteractName"));
		headerParamMap.put("description",requestJson.getString("description"));
		headerParamMap.put("publicFlag",requestJson.getString("publicFlag"));
		headerParamMap.put("autoqueryFlag",requestJson.getString("autoqueryFlag"));
		headerParamMap.put("defaultFlag",requestJson.getString("defaultFlag"));
		headerParamMap.put("orderBy",requestJson.getString("orderBy"));
		headerParamMap.put("pageSize",requestJson.getLong("pageSize"));
		headerParamMap.put("language","ZHS");//this.getPara("language")
		headerParamMap.put("createdBy",requestJson.getLong("userId"));
		headerParamMap.put("lastUpdatedBy",requestJson.getLong("userId"));
		headerParamMap.put("lastUpdateLogin",-1L);
		//Thread.sleep(2000);
		return irrService.insertIrr(headerParamMap,requestJson.getString("seq"),requestJson.getLong("userId"));
	}
}
