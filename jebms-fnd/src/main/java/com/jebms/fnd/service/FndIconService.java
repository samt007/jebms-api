package com.jebms.fnd.service;

import java.util.List;

import com.jebms.comm.core.BaseService;
import com.jebms.fnd.dao.FndIconDao;
import com.jebms.fnd.entity.FndIcon;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.utils.SqlUtil;

/**
 * 图标表的Service封装。是一个比较经典的单表DML模型Service
 *
 * @author samt007@qq.com
 */

@Service
@SuppressWarnings("rawtypes")
public class FndIconService extends BaseService<FndIconDao,FndIcon> {
    
	public ResultEntity selectForPage(SearchInfo searchInfo) throws Exception {
    	StringBuffer sqlConditionBuf=new StringBuffer();
    	sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"description","description"));
    	//sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"flv.lookup_type","lookupType"));
    	//sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"bk.start_date","startDateF","startDateT"));
    	searchInfo.setSqlCondition(sqlConditionBuf.toString());
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<FndIcon> pageList = this.mapper.selectForPage(searchInfo);
        PageInfo<FndIcon> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

}
