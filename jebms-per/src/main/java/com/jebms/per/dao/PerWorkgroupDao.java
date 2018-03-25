package com.jebms.per.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.per.entity.PerWorkgroup;
import com.jebms.per.entity.PerWorkgroupVO;

public interface PerWorkgroupDao  extends Mapper<PerWorkgroup> {

    /**
     * 查询VO数据
     * @param searchInfo 传入查询参数
     * @return
     */
    List<PerWorkgroupVO> selectForPage(SearchInfo searchInfo);
    
    PerWorkgroupVO selectVOByPK(@Param(value="id") Long id,@Param(value="lang") String lang);
}