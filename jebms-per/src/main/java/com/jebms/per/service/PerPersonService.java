package com.jebms.per.service;

import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.security.model.AuthUser;
import com.jebms.per.entity.PerPersonVO;

/**
 * 图标表的Service封装。是一个比较经典的单视图VO对象的DML模型Service
 *
 * @author samt007@qq.com
 */

@SuppressWarnings("rawtypes")
public interface PerPersonService {

	public ResultEntity selectForPage(SearchInfo searchInfo) throws Exception ;

    public PerPersonVO selectVOByPK(Long id) ;
    
    public ResultEntity insert(PerPersonVO record,AuthUser user) ;
    
    public ResultEntity update(PerPersonVO record,AuthUser user) ;
    
    public ResultEntity delete(PerPersonVO record,AuthUser user) ;

}
