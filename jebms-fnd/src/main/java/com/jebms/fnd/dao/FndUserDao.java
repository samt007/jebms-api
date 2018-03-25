package com.jebms.fnd.dao;


/**
 * 用户Dao
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年8月30日
 */


import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndUser;
import com.jebms.fnd.entity.FndUserVO;

public interface FndUserDao  extends Mapper<FndUser> {

    FndUserVO selectByUserName(@Param(value = "username") String username);

    List<FndUserVO> selectForPage(SearchInfo searchInfo);

    FndUserVO selectVOByPK(Long id);
}