package com.jebms.fnd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndFunction;
import com.jebms.fnd.entity.FndFunctionVO;

public interface FndFunctionDao  extends Mapper<FndFunction> {

    /**
     * 查询VO数据
     * @param searchInfo 传入查询参数
     * @return
     */
    List<FndFunctionVO> selectForPage(SearchInfo searchInfo);
    
	FndFunctionVO selectVOByPK(@Param(value="id") Long id,@Param(value="lang") String lang);
}