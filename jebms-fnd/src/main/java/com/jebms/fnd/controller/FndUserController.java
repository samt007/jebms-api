package com.jebms.fnd.controller;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.jebms.comm.core.BaseController;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.utils.TypeConverter;
import com.jebms.fnd.entity.FndUserRespVO;
import com.jebms.fnd.entity.FndUserVO;
import  com.jebms.fnd.service.SystemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The fnd user controller.
 *
 * @author samt07@qq.com
 */
@Validated
@RestController
@RequestMapping("/user")
@Api(value = "用户管理", description = "用户管理")
@SuppressWarnings("rawtypes")
public class FndUserController extends BaseController{

    @Autowired
    private SystemService systemService;
    /**
     * 密码加密
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    //@PreAuthorize("hasAuthority('fnd:user:view')")
    @PostMapping(value = "/getPageUser")
    @ApiOperation(value = "用户列表")
    public ResultEntity getPageUser(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
    	searchInfo.putConditionMap("username", requestJson.getString("username")).andSqlCondition("fu.username","username");
        return systemService.selectForPageUser(searchInfo);
    }
    
    //@PreAuthorize("hasAuthority('fnd:user:view')")
    @PostMapping(value = "/selectUser/{id}")
    @ApiOperation(value = "获取用户")
    public ResultEntity selectUser(@PathVariable("id") Long id) {
    	FndUserVO record=systemService.selectUserVOByPK(id,this.authUser.getLanguage());
    	record.setValueUUID();
    	return ResultInfo.success(record);
    }

    //@PreAuthorize("hasAuthority('fnd:user:edit')")
    @PostMapping(value = "/insertUser")
    @ApiOperation(value = "新增用户")
    public ResultEntity insertUser(@Valid @RequestBody FndUserVO record) {
    	String password = record.getPassword();
        record.setPassword(passwordEncoder.encode(password));
        record.setPasswordDate(new Date());
        record.setRegisteredDate(new Date());
        record.setUsername(record.getUsername().toUpperCase());
        return systemService.insertUser(record,this.authUser);
    }
    
	//@PreAuthorize("hasAuthority('fnd:user:edit')")
    @PostMapping(value = "/updateUser")
    @ApiOperation(value = "更新用户")
    public ResultEntity updateUser(@Valid @RequestBody FndUserVO record) {
    	// 这里特殊处理，永远不直接更新用户的密码！
    	//FndUserVO user=systemService.selectUserVOByPK(record.getId(),this.authUser.getLanguage());
    	//record.setPassword(user.getPassword());
    	//record.setPasswordDate(user.getPasswordDate());
        return systemService.updateUser(record,this.authUser);
    }

	@PreAuthorize("hasAuthority('fnd:user:edit')")
	@PostMapping(value = "/deleteUser/{id}")
    @ApiOperation(value = "删除用户")
    public ResultEntity deleteUser(@PathVariable("id") Long id) {
		FndUserVO record=new FndUserVO();
    	record.setId(id);
        return systemService.deleteUser(record,this.authUser);
    }
	

    //@PreAuthorize("hasAuthority('fnd:user:view')")
    @PostMapping(value = "/getPageUserResp")
    @ApiOperation(value = "用户职责列表")
    public ResultEntity getPageUserResp(@RequestBody JSONObject requestJson) throws Exception {
    	SearchInfo searchInfo = new SearchInfo(requestJson,this.authUser);
    	searchInfo.putConditionMap("userId", requestJson.getString("userId")).andSqlCondition("fur.user_id","userId");
        return systemService.selectForPageUserResp(searchInfo);
    }
    
    //@PreAuthorize("hasAuthority('fnd:user:view')")
    @PostMapping(value = "/selectUserResp/{id}")
    @ApiOperation(value = "获取用户职责")
    public ResultEntity selectUserResp(@PathVariable("id") Long id) {
    	FndUserRespVO record=systemService.selectUserRespVOByPK(id,this.authUser.getLanguage());
    	record.setValueUUID();
    	return ResultInfo.success(record);
    }

    //@PreAuthorize("hasAuthority('fnd:user:edit')")
    @PostMapping(value = "/insertUserResp")
    @ApiOperation(value = "新增用户职责")
    public ResultEntity insertUserResp(@Valid @RequestBody FndUserRespVO record) {
        return systemService.insertUserResp(record,this.authUser);
    }
    
	//@PreAuthorize("hasAuthority('fnd:user:edit')")
    @PostMapping(value = "/updateUserResp")
    @ApiOperation(value = "更新用户职责")
    public ResultEntity updateUserResp(@Valid @RequestBody FndUserRespVO record) {
        return systemService.updateUserResp(record,this.authUser);
    }

	@PreAuthorize("hasAuthority('fnd:user:edit')")
	@PostMapping(value = "/deleteUserResp/{id}")
    @ApiOperation(value = "删除用户职责")
    public ResultEntity deleteUserResp(@PathVariable("id") Long id) {
		FndUserRespVO record=new FndUserRespVO();
    	record.setId(id);
        return systemService.deleteUserResp(record,this.authUser);
    }

    /**
     * Reset password response entity.
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/password/{id}")
    @ApiOperation(value = "重置密码")
    public ResultEntity resetPassword(@PathVariable("id") Long id,@RequestBody JSONObject requestJson) {
        String oldPassword = requestJson.getString("oldPassword");
        String newPassword = requestJson.getString("newPassword");
        // 重置密码
        if (TypeConverter.isNotEmpty(oldPassword) && TypeConverter.isNotEmpty(newPassword)) {
        	FndUserVO record=systemService.selectUserVOByPK(id, this.authUser.getLanguage());
            if (!passwordEncoder.matches(oldPassword, record.getPassword())) {
            	return ResultInfo.error("旧密码错误");
            }
            record.setPassword(passwordEncoder.encode(newPassword));
            record.setPasswordDate(new Date());
            return systemService.updateUser(record,this.authUser);
        }else{
        	return ResultInfo.error("旧密码和新密码都不允许为空");
        }
    }
}
