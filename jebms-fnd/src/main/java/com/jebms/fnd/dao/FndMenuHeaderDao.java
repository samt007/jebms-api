package com.jebms.fnd.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndMenuHeader;
import com.jebms.fnd.entity.FndMenuHeaderVO;

import tk.mybatis.mapper.common.Mapper;

public interface FndMenuHeaderDao  extends Mapper<FndMenuHeader> {

    /**
     * 查询VO数据
     * @param searchInfo 传入查询参数
     * @return
     */
    List<FndMenuHeaderVO> selectForPage(SearchInfo searchInfo);
	FndMenuHeaderVO selectVOByPK(@Param(value="id") Long id,@Param(value="lang") String lang);
}