package com.jebms.per.service.impl;

import java.util.List;

import javax.sql.DataSource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.security.model.AuthUser;
import com.jebms.comm.springjdbc.DevJdbcDaoSupport;
import com.jebms.per.dao.PerWorkgroupDao;
import com.jebms.per.dao.PerWorkgroupEmpDao;
import com.jebms.per.entity.PerWorkgroupVO;
import com.jebms.per.entity.PerWorkgroupEmpVO;
import com.jebms.per.service.PerWorkgroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 组表和组人员表的Service封装。
 *
 * @author samt007@qq.com
 */

@Service
@SuppressWarnings("rawtypes")
public class PerWorkgroupServiceImpl  extends DevJdbcDaoSupport implements PerWorkgroupService {
    
    @Autowired
    private PerWorkgroupDao groupDao;
    
    @Autowired
    private PerWorkgroupEmpDao groupEmpDao;
    
	@Autowired
	PerWorkgroupServiceImpl(DataSource dataSource) {
	    setDataSource(dataSource);
	}
    
    //增删改头
	public ResultEntity selectForPageWorkgroup(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<PerWorkgroupVO> pageList = groupDao.selectForPage(searchInfo);
        PageInfo<PerWorkgroupVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

    public PerWorkgroupVO selectWorkgroupVOByPK(Long id,String lang) {
    	PerWorkgroupVO record=groupDao.selectVOByPK(id,lang);
    	record.setValueUUID();
    	return record;
    }
    
    @Transactional(readOnly = false)
    public ResultEntity insertWorkgroup(PerWorkgroupVO record,AuthUser user) {
    	record.setWhoInsert(user);
    	if(groupDao.insert(record)==1){
    		record=this.selectWorkgroupVOByPK(record.getId(),user.getLanguage());
    		return ResultInfo.success(record);
    	}else{
    		return ResultInfo.error("新增数据失败！");
    	}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity updateWorkgroup(PerWorkgroupVO record,AuthUser user) {
    	if(!record.equalValueUUID(groupDao)){
    		return ResultInfo.error("数据已经被修改！请重新查询再更新！");
    	}
    	record.setWhoUpdate(user);
    	if(groupDao.updateByPrimaryKey(record)==1){
    		record=this.selectWorkgroupVOByPK(record.getId(),user.getLanguage());
    		return ResultInfo.success(record);
		}else{
			return ResultInfo.error("未更新数据！请检查条件！");
		}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity deleteWorkgroup(PerWorkgroupVO record,AuthUser user) {
    	if(groupDao.deleteByPrimaryKey(record)==1){
    		return ResultInfo.success();
    	}else{
    		return ResultInfo.error("未删除数据！请检查条件！");
    	}
    }
    
    //增删改行
	public ResultEntity selectForPageWorkgroupEmp(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<PerWorkgroupEmpVO> pageList = groupEmpDao.selectForPage(searchInfo);
        PageInfo<PerWorkgroupEmpVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

    public PerWorkgroupEmpVO selectWorkgroupEmpVOByPK(Long id,String lang) {
    	PerWorkgroupEmpVO record=groupEmpDao.selectVOByPK(id,lang);
    	record.setValueUUID();
    	return record;
    }
    
    @Transactional(readOnly = false)
    public ResultEntity insertWorkgroupEmp(PerWorkgroupEmpVO record,AuthUser user) {
    	record.setWhoInsert(user);
    	if(groupEmpDao.insert(record)==1){
    		record=this.selectWorkgroupEmpVOByPK(record.getId(),user.getLanguage());
    		return ResultInfo.success(record);
    	}else{
    		return ResultInfo.error("新增数据失败！");
    	}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity updateWorkgroupEmp(PerWorkgroupEmpVO record,AuthUser user) {
    	if(!record.equalValueUUID(groupEmpDao)){
    		return ResultInfo.error("数据已经被修改！请重新查询再更新！");
    	}
    	record.setWhoUpdate(user);
    	if(groupEmpDao.updateByPrimaryKey(record)==1){
    		record=this.selectWorkgroupEmpVOByPK(record.getId(),user.getLanguage());
    		return ResultInfo.success(record);
		}else{
			return ResultInfo.error("未更新数据！请检查条件！");
		}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity deleteWorkgroupEmp(PerWorkgroupEmpVO record,AuthUser user) {
    	if(groupEmpDao.deleteByPrimaryKey(record)==1){
    		return ResultInfo.success();
    	}else{
    		return ResultInfo.error("未删除数据！请检查条件！");
    	}
    }
}
