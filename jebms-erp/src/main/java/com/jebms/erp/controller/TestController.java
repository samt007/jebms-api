package com.jebms.erp.controller;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.core.BaseController;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.erp.service.TestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * The test only controller.
 *
 * @author samt07@qq.com
 */
@Validated
@RestController
@RequestMapping("/test")
@Api(value = "测试", description = "测试")
@SuppressWarnings("rawtypes")
public class TestController extends BaseController{
    /**
     * 系统菜单服务
     */
    @Autowired
    private TestService testService;

    //@PreAuthorize("hasAuthority('fnd:function:view')")
    @PostMapping(value = "/testSql")
    @ApiOperation(value = "测试执行sql")
    public ResultEntity testSql(@RequestBody JSONObject requestJson) throws Exception {
        return testService.testSql();
    }
}
