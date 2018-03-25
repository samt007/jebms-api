package com.jebms.fnd.dao;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndIcon;

public interface FndIconDao  extends Mapper<FndIcon> {
    /**
     * 查询VO数据
     * @param searchInfo 传入查询参数
     * @return
     */
    List<FndIcon> selectForPage(SearchInfo searchInfo);
}