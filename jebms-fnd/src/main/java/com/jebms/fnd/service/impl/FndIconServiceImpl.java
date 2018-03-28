package com.jebms.fnd.service.impl;

import com.jebms.comm.core.BaseService;
import com.jebms.fnd.dao.FndIconDao;
import com.jebms.fnd.entity.FndIcon;
import com.jebms.fnd.service.FndIconService;

import org.springframework.stereotype.Service;


/**
 * 图标表的Service封装。是一个比较经典的单表DML模型Service
 *
 * @author samt007@qq.com
 */

@Service
public class FndIconServiceImpl extends BaseService<FndIconDao,FndIcon> implements FndIconService {

	/*public ResultEntity selectForPage(SearchInfo searchInfo) throws Exception {
    	StringBuffer sqlConditionBuf=new StringBuffer();
    	sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"description","description"));
    	searchInfo.setSqlCondition(sqlConditionBuf.toString());
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<FndIcon> pageList = this.mapper.selectForPage(searchInfo);
        PageInfo<FndIcon> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }*/

}
