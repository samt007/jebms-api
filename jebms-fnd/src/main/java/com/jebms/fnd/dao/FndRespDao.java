package com.jebms.fnd.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndResp;
import com.jebms.fnd.entity.FndRespVO;

import tk.mybatis.mapper.common.Mapper;

public interface FndRespDao  extends Mapper<FndResp> {
    
    /**
     * 查询VO数据
     * @param searchInfo 传入查询参数
     * @return
     */
    List<FndRespVO> selectForPage(SearchInfo searchInfo);
	
	FndRespVO selectVOByPK(@Param(value="id") Long id,@Param(value="lang") String lang);
	
    /**
     * 查询用户角色列表
     *
     * @param userId the user id
     * @return the list
     */
    List<FndRespVO> selectListByUserId(@Param(value="userId") Long userId,@Param(value="lang") String lang);
}