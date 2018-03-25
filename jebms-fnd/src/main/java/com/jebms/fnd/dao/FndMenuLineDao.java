package com.jebms.fnd.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndMenuLine;
import com.jebms.fnd.entity.FndMenuLineVO;
import com.jebms.fnd.entity.FndMenuVO;

import tk.mybatis.mapper.common.Mapper;

public interface FndMenuLineDao  extends Mapper<FndMenuLine> {

    /**
     * 查询VO数据
     * @param searchInfo 传入查询参数
     * @return
     */
    List<FndMenuLineVO> selectForPage(SearchInfo searchInfo);
    
	FndMenuLineVO selectVOByPK(@Param(value="menuId") long menuId,@Param(value="menuSequence") Integer menuSequence,@Param(value="lang") String lang);
	
	List<FndMenuVO> selectListByMenuId(@Param(value="menuId") long menuId,@Param(value="lang") String lang);
}