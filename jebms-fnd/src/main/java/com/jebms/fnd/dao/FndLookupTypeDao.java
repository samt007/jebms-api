package com.jebms.fnd.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndLookupType;
import com.jebms.fnd.entity.FndLookupTypeVO;

import tk.mybatis.mapper.common.Mapper;

public interface FndLookupTypeDao  extends Mapper<FndLookupType> {

    /**
     * 查询VO数据
     * @param searchInfo 传入查询参数
     * @return
     */
    List<FndLookupTypeVO> selectForPage(SearchInfo searchInfo);
    
    FndLookupTypeVO selectVOByPK(@Param(value="applId") Long applId
    		,@Param(value="lookupType") String lookupType
    		,@Param(value="lang") String lang);
}