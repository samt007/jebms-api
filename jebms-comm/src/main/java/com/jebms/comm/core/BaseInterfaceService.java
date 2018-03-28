package com.jebms.comm.core;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.security.model.AuthUser;

/**
 * 对单表做增删改查的封装好的的业务方法
 * TODO:暂时无法做到让interface的Service直接继承它，避免重复代码！BaseInterfaceService
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年6月22日
 */
@SuppressWarnings("rawtypes")
public abstract interface BaseInterfaceService<D extends Mapper<T>,T extends BaseEntity> {
    
	public D getMapper() ;
	
    public AuthUser getAuthUser() ;
    
	public T selectByPrimaryKey(T record);
	
	public List<T> selectAll() ;
	
    public ResultEntity insert(T record) ;

	//Param1记录新增行的记录数
    public ResultEntity insertAll(List<T> recordList) ;
    
	public ResultEntity update(T record) ;

    public ResultEntity delete(T record) ;
    
    public ResultEntity selectForPage(SearchInfo searchInfo) throws Exception;

}
