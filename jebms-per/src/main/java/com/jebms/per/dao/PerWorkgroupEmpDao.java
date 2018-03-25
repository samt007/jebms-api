package com.jebms.per.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.per.entity.PerWorkgroupEmp;
import com.jebms.per.entity.PerWorkgroupEmpVO;

public interface PerWorkgroupEmpDao  extends Mapper<PerWorkgroupEmp> {

    /**
     * 查询VO数据
     * @param searchInfo 传入查询参数
     * @return
     */
    List<PerWorkgroupEmpVO> selectForPage(SearchInfo searchInfo);
    
    PerWorkgroupEmpVO selectVOByPK(@Param(value="id") Long id,@Param(value="lang") String lang);
}