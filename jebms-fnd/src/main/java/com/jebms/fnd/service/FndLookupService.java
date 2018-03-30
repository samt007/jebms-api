package com.jebms.fnd.service;

import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.security.model.AuthUser;
import com.jebms.fnd.entity.FndLookupTypeVO;
import com.jebms.fnd.entity.FndLookupValue;

/**
 * Lookup字典表的Service封装。也是一个的Master-Detail模型Service
 *
 * @author samt007@qq.com
 */

@SuppressWarnings("rawtypes")
public interface FndLookupService {
    
    //增删改Lookup头
	public ResultEntity selectForPageLookupType(SearchInfo searchInfo) throws Exception ;

    public FndLookupTypeVO selectLookupTypeVOByPK(Long applId,String lookupType,String lang) ;
    
    public ResultEntity insertLookupType(FndLookupTypeVO record,AuthUser user) ;
    
    public ResultEntity updateLookupType(FndLookupTypeVO record,AuthUser user) ;
    
    public ResultEntity deleteLookupType(FndLookupTypeVO record,AuthUser user) ;
    
    //增删改Lookup行
	public ResultEntity selectForPageLookupValue(SearchInfo searchInfo) throws Exception ;

    public FndLookupValue selectLookupValueByPK(Long applId,String lookupType,String lookupCode,String lang) ;
    
    // 这个处理比较特殊，因为语言表和主表合在一起了！
    public ResultEntity insertLookupValue(FndLookupValue record,AuthUser user) ;
    
    public ResultEntity updateLookupValue(FndLookupValue record,AuthUser user) ;
    
    public ResultEntity deleteLookupValue(FndLookupValue record,AuthUser user) ;
}
