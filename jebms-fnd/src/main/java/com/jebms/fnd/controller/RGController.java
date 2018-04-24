package com.jebms.fnd.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
import com.jebms.fnd.service.RGService;

/**
 * Author: Sam.T
 * Email: samt007@qq.com
 * Date: 2017/6/23
 * Describe: LOV/RG功能处理
 */
@Controller
@RequestMapping(value = "/")
@SuppressWarnings("rawtypes")
@Api(value = "Record Group",description="记录组接口，通常是被Lov和List所用！")
public class RGController  extends BaseController  {
    @Autowired
    private RGService rgService;
    
    /**
     * 查询所有看板信息 并使用PageHelp分页
     * @throws Exception 
     */
	@ResponseBody
    @PostMapping(value = "/lov/getPageCustomer")
	@ApiOperation(value="获取客户名称接口", notes="获取客户名称的LOV的数据接口")
    public ResultEntity getPageCustomer(@RequestBody JSONObject requestJson) throws Exception {
		SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
        searchInfo.putConditionMap("customerName", requestJson.getString("customerName")).andSqlCondition("customer_name","customerName");
        searchInfo.putConditionMap("customerCode", requestJson.getString("customerCode")).andSqlCondition("customer_code","customerCode");
        return rgService.getPageCustomer(searchInfo);
    }
    
    @ResponseBody
    @PostMapping(value = "/list/getCurrencyCode")
    public ResultEntity getCurrencyCode() throws Exception {
        return ResultInfo.success(rgService.getLookups("CURRENCY",authUser.getLanguage()));
    }
    
    @ResponseBody
    @PostMapping(value = "/list/getPayTypeCode")
    public ResultEntity getPayTypeCode() throws Exception {
        return ResultInfo.success(rgService.getLookups("PAY_TYPE",authUser.getLanguage()));
    }
    
    @ResponseBody
    @PostMapping(value = "/list/getLookups/{lookupType}")
    public ResultEntity getLookups(@PathVariable("lookupType") String lookupType) throws Exception {
        return ResultInfo.success(rgService.getLookups(lookupType,authUser.getLanguage()));
    }
    
    /**
     * 查询用户职责的lov 并使用PageHelp分页
     * @throws Exception 
     */
	@ResponseBody
    @PostMapping(value = "/lov/getPageUserResp")
	@ApiOperation(value="获取用户职责列表接口", notes="获取用户职责列表接口")
    public ResultEntity getPageUserResp(@RequestBody JSONObject requestJson) throws Exception {
		SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
        searchInfo.putConditionMap("userId", requestJson.getString("userId")).andSqlCondition("fur.user_id","userId");
        searchInfo.putConditionMap("respName", requestJson.getString("respName")).andSqlCondition("frt.resp_name","respName");
        return rgService.getPageUserResp(searchInfo);
    }
	
	@ResponseBody
    @PostMapping(value = "/lov/getPageIcon")
	@ApiOperation(value="获取图标接口", notes="获取图标的LOV的数据接口")
    public ResultEntity getPageIcon(@RequestBody JSONObject requestJson) throws Exception {
		SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
        searchInfo.putConditionMap("iconCode", requestJson.getString("iconCode")).andSqlCondition("icon_code","iconCode");
        return rgService.selectForPageIcon(searchInfo);
    }
	
	@ResponseBody
    @PostMapping(value = "/lov/getPageFunction")
	@ApiOperation(value="获取函数接口", notes="获取函数的LOV的数据接口")
    public ResultEntity getPageFunction(@RequestBody JSONObject requestJson) throws Exception {
		SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
        searchInfo.putConditionMap("functionCode", requestJson.getString("functionCode")).andSqlCondition("ff.function_code","functionCode");
        searchInfo.putConditionMap("functionName", requestJson.getString("functionName")).andSqlCondition("fft.function_name","functionName");
        return rgService.selectForPageFunction(searchInfo);
    }
	
	@ResponseBody
    @PostMapping(value = "/lov/getPageMenu")
	@ApiOperation(value="获取菜单接口", notes="获取菜单的LOV的数据接口")
    public ResultEntity getPageMenu(@RequestBody JSONObject requestJson) throws Exception {
		SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
        searchInfo.putConditionMap("menuCode", requestJson.getString("menuCode")).andSqlCondition("fmh.menu_code","menuCode");
        searchInfo.putConditionMap("menuName", requestJson.getString("menuName")).andSqlCondition("fmht.menu_name","menuName");
        return rgService.selectForPageMenu(searchInfo);
    }
	
	@ResponseBody
    @PostMapping(value = "/lov/getPageAppl")
	@ApiOperation(value="获取应用接口", notes="获取应用的LOV的数据接口")
    public ResultEntity getPageAppl(@RequestBody JSONObject requestJson) throws Exception {
		SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
        searchInfo.putConditionMap("applCode", requestJson.getString("applCode")).andSqlCondition("appl_code","applCode");
        searchInfo.putConditionMap("description", requestJson.getString("description")).andSqlCondition("description","description");
        return rgService.selectForPageAppl(searchInfo);
    }
	
	@ResponseBody
    @PostMapping(value = "/lov/getPageResp")
	@ApiOperation(value="获取职责接口", notes="获取职责的LOV的数据接口")
    public ResultEntity getPageResp(@RequestBody JSONObject requestJson) throws Exception {
		SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
        searchInfo.putConditionMap("respCode", requestJson.getString("respCode")).andSqlCondition("fr.resp_code","respCode");
        searchInfo.putConditionMap("respName", requestJson.getString("respName")).andSqlCondition("frt.resp_name","respName");
        return rgService.selectForPageResp(searchInfo);
    }
	
	@ResponseBody
    @PostMapping(value = "/lov/getPagePerson")
	@ApiOperation(value="获取人员接口", notes="获取人员的LOV的数据接口")
    public ResultEntity getPagePerson(@RequestBody JSONObject requestJson) throws Exception {
		SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
        searchInfo.putConditionMap("empNumber", requestJson.getString("empNumber")).andSqlCondition("emp_number","empNumber");
        searchInfo.putConditionMap("fullName", requestJson.getString("fullName")).andSqlCondition("full_name","fullName");
        return rgService.selectForPagePerson(searchInfo);
    }
	
	@ResponseBody
    @PostMapping(value = "/lov/getPeopleById/{id}")
	@ApiOperation(value="获取单个人员信息", notes="获取单个人员信息接口")
    public ResultEntity getPeopleById(@PathVariable("id") Long id) throws Exception {
        return rgService.getPeopleById(id);
    }
	
	@ResponseBody
    @PostMapping(value = "/lov/getPeopleByNum/{empNumber}")
	@ApiOperation(value="获取单个人员信息", notes="获取单个人员信息接口")
    public ResultEntity getPeopleByNum(@PathVariable("empNumber") String empNumber) throws Exception {
        return rgService.getPeopleByNum(empNumber);
    }
	
	@ResponseBody
    @PostMapping(value = "/lov/getPeopleByFname/{fullName}")
	@ApiOperation(value="获取单个人员信息", notes="获取单个人员信息接口")
    public ResultEntity getPeopleByFname(@PathVariable("fullName") String fullName) throws Exception {
        return rgService.getPeopleByFname(fullName);
    }
	
	@ResponseBody
    @PostMapping(value = "/lov/getPageDepartment")
	@ApiOperation(value="获取部门接口", notes="获取部门的LOV的数据接口")
    public ResultEntity getPageDepartment(@RequestBody JSONObject requestJson) throws Exception {
		SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
        searchInfo.putConditionMap("departmentName", requestJson.getString("departmentName")).andSqlCondition("department_name","departmentName");
        searchInfo.putConditionMap("departmentCode", requestJson.getString("departmentCode")).andSqlCondition("department_code","departmentCode");
        return rgService.selectForPageDepartment(searchInfo);
    }
}
