package com.jebms.per.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.per.entity.PerPerson;
import com.jebms.per.entity.PerPersonVO;

public interface PerPersonDao  extends Mapper<PerPerson> {

    /**
     * 查询VO数据
     * @param searchInfo 传入查询参数
     * @return
     */
    List<PerPersonVO> selectForPage(SearchInfo searchInfo);
    
    PerPersonVO selectVOByPK(@Param(value="id") Long id);
}