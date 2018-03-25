package com.jebms.fnd.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndUserResp;
import com.jebms.fnd.entity.FndUserRespVO;

import tk.mybatis.mapper.common.Mapper;

public interface FndUserRespDao  extends Mapper<FndUserResp> {

	
    FndUserRespVO selectVOByPK(@Param(value="id") Long id,@Param(value="lang") String lang);
    
    /**
     * 查询VO数据
     * @param searchInfo 传入查询参数
     * @return
     */
    List<FndUserRespVO> selectForPage(SearchInfo searchInfo);
	
    /**
     * 查询用户角色列表
     *
     * @param userId the user id
     * @return the list
     */
    List<FndUserRespVO> selectListByUserId(@Param(value="userId") Long userId,@Param(value="lang") String lang);
}