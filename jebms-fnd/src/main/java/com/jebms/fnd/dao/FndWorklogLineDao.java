package com.jebms.fnd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndWorklogLine;
import com.jebms.fnd.entity.FndWorklogLineVO;

public interface FndWorklogLineDao  extends Mapper<FndWorklogLine> {

    /**
     * 查询VO数据
     * @param searchInfo 传入查询参数
     * @return
     */
    List<FndWorklogLineVO> selectForPage(SearchInfo searchInfo);
    
    FndWorklogLineVO selectVOByPK(@Param(value="id") Long id,@Param(value="lang") String lang);
}