package com.jebms.per.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.security.model.AuthUser;
import com.jebms.per.dao.PerPersonDao;
import com.jebms.per.entity.PerPersonVO;
import com.jebms.per.service.PerPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 图标表的Service封装。是一个比较经典的单表DML模型Service
 *
 * @author samt007@qq.com
 */

@Service
@SuppressWarnings("rawtypes")
public class PerPersonServiceImpl implements PerPersonService {
	
	@Autowired
	private PerPersonDao personDao;
	
    //增删改查
	public ResultEntity selectForPage(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<PerPersonVO> pageList = personDao.selectForPage(searchInfo);
        PageInfo<PerPersonVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

    public PerPersonVO selectVOByPK(Long id) {
    	PerPersonVO record=personDao.selectVOByPK(id);
    	record.setValueUUID();
    	return record;
    }
    
    @Transactional(readOnly = false)
    public ResultEntity insert(PerPersonVO record,AuthUser user) {
    	record.setWhoInsert(user);
    	if(personDao.insert(record)==1){
    		record=this.selectVOByPK(record.getId());
    		return ResultInfo.success(record);
    	}else{
    		return ResultInfo.error("新增数据失败！");
    	}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity update(PerPersonVO record,AuthUser user) {
    	if(!record.equalValueUUID(personDao)){
    		return ResultInfo.error("数据已经被修改！请重新查询再更新！");
    	}
    	record.setWhoUpdate(user);
    	if(personDao.updateByPrimaryKey(record)==1){
    		record=this.selectVOByPK(record.getId());
    		return ResultInfo.success(record);
		}else{
			return ResultInfo.error("未更新数据！请检查条件！");
		}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity delete(PerPersonVO record,AuthUser user) {
    	if(personDao.deleteByPrimaryKey(record)==1){
    		return ResultInfo.success();
    	}else{
    		return ResultInfo.error("未删除数据！请检查条件！");
    	}
    }

}
