package com.jebms.fnd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndWorklogHeader;
import com.jebms.fnd.entity.FndWorklogHeaderVO;

public interface FndWorklogHeaderDao  extends Mapper<FndWorklogHeader> {

    /**
     * 查询VO数据
     * @param searchInfo 传入查询参数
     * @return
     */
    List<FndWorklogHeaderVO> selectForPage(SearchInfo searchInfo);
    
    FndWorklogHeaderVO selectVOByPK(@Param(value="id") Long id,@Param(value="lang") String lang);
}