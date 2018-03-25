package com.jebms.fnd.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndLookupValue;

import tk.mybatis.mapper.common.Mapper;

public interface FndLookupValueDao  extends Mapper<FndLookupValue> {
    /**
     * 查询VO数据
     * @param searchInfo 传入查询参数
     * @return
     */
    List<FndLookupValue> selectForPage(SearchInfo searchInfo);
    
    FndLookupValue selectVOByPK(@Param(value="applId") Long applId
    		,@Param(value="lookupType") String lookupType
    		,@Param(value="lookupCode") String lookupCode
    		,@Param(value="lang") String lang);
}