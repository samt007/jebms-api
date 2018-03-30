package com.jebms.fnd.service;

import java.util.List;

//import com.jebms.comm.core.BaseInterfaceService;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndIcon;

/**
 * 图标表的Service封装。是一个比较经典的单表DML模型Service
 *
 * @author samt007@qq.com
 */

@SuppressWarnings("rawtypes")
public interface FndIconService {
	
	public FndIcon selectByPrimaryKey(FndIcon record);
	
	public List<FndIcon> selectAll() ;

	//Param1记录新增行的记录数
    public ResultEntity insertAll(List<FndIcon> recordList) ;
	
    public ResultEntity insert(FndIcon record) ;
    
	public ResultEntity update(FndIcon record) ;

    public ResultEntity delete(FndIcon record) ;
    
    public ResultEntity selectForPage(SearchInfo searchInfo) throws Exception;

}
